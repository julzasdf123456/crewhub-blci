package com.lopez.julz.crmcrewhub;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.lopez.julz.crmcrewhub.api.RequestPlaceHolder;
import com.lopez.julz.crmcrewhub.api.RetrofitBuilder;
import com.lopez.julz.crmcrewhub.classes.AlertHelpers;
import com.lopez.julz.crmcrewhub.classes.ObjectHelpers;
import com.lopez.julz.crmcrewhub.classes.UploadServiceConnectionsAdapter;
import com.lopez.julz.crmcrewhub.classes.UploadTicketsAdapter;
import com.lopez.julz.crmcrewhub.database.AppDatabase;
import com.lopez.julz.crmcrewhub.database.LineAndMetering;
import com.lopez.julz.crmcrewhub.database.MeterInstallation;
import com.lopez.julz.crmcrewhub.database.ServiceConnections;
import com.lopez.julz.crmcrewhub.database.ServiceConnectionsDao;
import com.lopez.julz.crmcrewhub.database.Settings;
import com.lopez.julz.crmcrewhub.database.Tickets;
import com.lopez.julz.crmcrewhub.database.TimeFrames;
import com.lopez.julz.crmcrewhub.database.TimeFramesDao;
import com.mapbox.mapboxsdk.plugins.annotation.Line;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadActivity extends AppCompatActivity {

    public RecyclerView uploadRecyclerViewServiceConnections;
    public UploadServiceConnectionsAdapter uploadServiceConnectionsAdapter;
    public List<ServiceConnections> serviceConnectionsList;
    public List<TimeFrames> timeFramesList;

    public RecyclerView uploadRecyclerViewTickets;
    public UploadTicketsAdapter uploadTicketsAdapter;
    public List<Tickets> ticketsList;

    public List<MeterInstallation> meterInstallations;

    public TextView scCount;

    public AppDatabase db;

    public FloatingActionButton uploadDataBtn;

    public boolean isUploadingServiceConnections = false;
    public boolean isUploadingSCTimeFrames = false;

    public RetrofitBuilder retrofitBuilder;
    private RequestPlaceHolder requestPlaceHolder;

    public CircularProgressIndicator scProgressUpload, ticketsProgressUpload;

    public AlertDialog dialog;
    public Settings settings;

    public File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

    public List<LineAndMetering> lineAndMeteringList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        uploadRecyclerViewServiceConnections = findViewById(R.id.uploadRecyclerViewServiceConnections);
        serviceConnectionsList = new ArrayList<>();
        timeFramesList = new ArrayList<>();
        ticketsList = new ArrayList<>();
        meterInstallations = new ArrayList<>();
        uploadServiceConnectionsAdapter = new UploadServiceConnectionsAdapter(serviceConnectionsList, this);
        uploadRecyclerViewServiceConnections.setAdapter(uploadServiceConnectionsAdapter);
        uploadRecyclerViewServiceConnections.setLayoutManager(new LinearLayoutManager(this));
        scProgressUpload = findViewById(R.id.scProgressUpload);
        uploadRecyclerViewTickets = findViewById(R.id.uploadRecyclerViewTickets);
        ticketsProgressUpload = findViewById(R.id.ticketsProgressUpload);
        uploadTicketsAdapter = new UploadTicketsAdapter(ticketsList, this);
        uploadRecyclerViewTickets.setAdapter(uploadTicketsAdapter);
        uploadRecyclerViewTickets.setLayoutManager(new LinearLayoutManager(this));
        lineAndMeteringList = new ArrayList<>();

        scProgressUpload.setVisibility(View.GONE);
        ticketsProgressUpload.setVisibility(View.GONE);

        db = Room.databaseBuilder(this, AppDatabase.class, ObjectHelpers.databaseName()).fallbackToDestructiveMigration().build();

        scCount = findViewById(R.id.scCount);
        uploadDataBtn = findViewById(R.id.uploadDataBtn);

        new FetchSettings().execute();
    }

    public void showLoadDialog() {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Finishing, please wait...");

            CircularProgressIndicator circularProgressIndicator = new CircularProgressIndicator(this);
            circularProgressIndicator.setIndeterminate(true);

            builder.setView(circularProgressIndicator);

            dialog = builder.create();

            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void uploadServiceConnectionData() {
        try {
            if (serviceConnectionsList.size() > 0) {
                Call<ServiceConnections> serviceConnectionsCall = requestPlaceHolder.uploadServiceConnection(serviceConnectionsList.get(0));

                serviceConnectionsCall.enqueue(new Callback<ServiceConnections>() {
                    @Override
                    public void onResponse(Call<ServiceConnections> call, Response<ServiceConnections> response) {
                        if (!response.isSuccessful()) {
                            Log.e("ERR_UPLD_SC", response.raw().toString());
                        } else {
                            if (response.code() == 200) {
                                new UpdateServiceConnection().execute(serviceConnectionsList.get(0));
                                serviceConnectionsList.remove(0);
                                uploadServiceConnectionsAdapter.notifyDataSetChanged();
                                uploadServiceConnectionData();
                            } else {
                                Log.e("ERR_UPLD_SC", response.raw().toString());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ServiceConnections> call, Throwable t) {
                        Log.e("ERR_UPLD_SC", t.getMessage());
                    }
                });
            } else {
                // UPLOAD SC TIMEFRAMES
                uploadSCTimeFrame();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class FetchUploadableServiceConnections extends AsyncTask<ServiceConnections, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            serviceConnectionsList.clear();
        }

        @Override
        protected Void doInBackground(ServiceConnections... serviceConnection) {
            ServiceConnectionsDao serviceConnectionsDao = db.serviceConnectionsDao();

            serviceConnectionsList.addAll(serviceConnectionsDao.getUploadableServicConnections());
            meterInstallations.addAll(db.meterInstallationDao().getUploadable());
            lineAndMeteringList.addAll(db.lineAndMeteringDao().getUploadables());

            for(int i=0; i<serviceConnectionsList.size(); i++) {
                File imgFile = new File(storageDir, "SIGN_" + serviceConnectionsList.get(i).getId() + ".png");
                List<MultipartBody.Part> photoParts = new ArrayList<>();
                if (imgFile.exists()) {
                    RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), imgFile);
                    MultipartBody.Part part = MultipartBody.Part.createFormData("files[]", imgFile.getName(), requestBody);
                    photoParts.add(part);
                }

                uploadFile(photoParts, serviceConnectionsList.get(i).getId());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);

            scCount.setText("Service Connections (" + serviceConnectionsList.size() + ")");

            uploadServiceConnectionsAdapter.notifyDataSetChanged();

        }
    }

    public class UpdateServiceConnection extends AsyncTask<ServiceConnections, Void, Void> {

        @Override
        protected Void doInBackground(ServiceConnections... serviceConnections) {
            try {
                if (serviceConnections != null) {
                    ServiceConnections sc = serviceConnections[0];
                    sc.setUploadStatus("UPLOADED");
                    db.serviceConnectionsDao().updateAll(sc);
                }
            } catch (Exception e) {
                Log.e("ERR_UPDT_TICKT", e.getMessage());
            }
            return null;
        }
    }

    public void uploadSCTimeFrame() {
        try {
            if (timeFramesList.size() > 0) {
                Call<TimeFrames> timeFramesCall = requestPlaceHolder.uploadTimeFrames(timeFramesList.get(0));

                timeFramesCall.enqueue(new Callback<TimeFrames>() {
                    @Override
                    public void onResponse(Call<TimeFrames> call, Response<TimeFrames> response) {
                        if (!response.isSuccessful()) {
                            Log.e("ERR_UPLD_SC", response.raw().toString());
                        } else {
                            if (response.code() == 200) {
                                new UpdateSCTimeFrames().execute(timeFramesList.get(0));
                                timeFramesList.remove(0);
                                uploadSCTimeFrame();
                            } else {
                                Log.e("ERR_UPLD_SC", response.raw().toString());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<TimeFrames> call, Throwable t) {
                        Log.e("ERR_UPLD_SC", t.getMessage());
                    }
                });
            } else {
                // UPLOAD METER INSTALLATIONS
                uploadMeterInstallations();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void uploadMeterInstallations() {
        try {
            if (meterInstallations.size() > 0) {
                Call<MeterInstallation> meterInstallationCall = requestPlaceHolder.uploadMeterInstallations(meterInstallations.get(0));

                meterInstallationCall.enqueue(new Callback<MeterInstallation>() {
                    @Override
                    public void onResponse(Call<MeterInstallation> call, Response<MeterInstallation> response) {
                        if (!response.isSuccessful()) {
                            Log.e("ERR_UPLD_MTR_INST", response.raw().toString() + "\n" + response.errorBody().toString());
                        } else {
                            if (response.code() == 200) {
                                new UpdateMeterInstallations().execute(meterInstallations.get(0));
                                meterInstallations.remove(0);
                                uploadMeterInstallations();
                            } else {
                                Log.e("ERR_UPLD_MTR_INST", response.raw().toString() + "\n" + response.errorBody().toString());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<MeterInstallation> call, Throwable t) {
                        Log.e("ERR_UPLD_MTR_INST", t.getMessage());
                    }
                });
            } else {
                // UPLOAD LINE AND METERING
                uploadLineAndMetering();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void uploadLineAndMetering() {
        try {
            if (lineAndMeteringList.size() > 0) {
                Call<LineAndMetering> lineAndMeteringCall = requestPlaceHolder.uploadLineAndMetering(lineAndMeteringList.get(0));

                lineAndMeteringCall.enqueue(new Callback<LineAndMetering>() {
                    @Override
                    public void onResponse(Call<LineAndMetering> call, Response<LineAndMetering> response) {
                        if (!response.isSuccessful()) {
                            Log.e("ERR_UPLD_LN_MTRNG", response.raw().toString() + "\n" + response.errorBody().toString());
                        } else {
                            if (response.code() == 200) {
                                new UpdateLineAndMetering().execute(lineAndMeteringList.get(0));
                                lineAndMeteringList.remove(0);
                                uploadLineAndMetering();
                            } else {
                                Log.e("ERR_UPLD_LN_MTRNG", response.raw().toString() + "\n" + response.errorBody().toString());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<LineAndMetering> call, Throwable t) {
                        Log.e("ERR_UPLD_LN_MTRNG", t.getMessage());
                    }
                });
            } else {
                // UPLOAD TICKETS
                scProgressUpload.setVisibility(View.GONE);
                if (ticketsList != null) {
                    ticketsProgressUpload.setVisibility(View.VISIBLE);
                    uploadTickets();
                } else {
                    ticketsProgressUpload.setVisibility(View.GONE);
                    AlertHelpers.showExitableInfoDialog(UploadActivity.this, UploadActivity.this, "Upload success!", "All data uploaded successfully!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void uploadFile(List<MultipartBody.Part> files, String serviceConnectionId) {
        try {

            Call<ResponseBody> uploadCall = requestPlaceHolder.saveUploadedImages(serviceConnectionId, files);

            uploadCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        if (response.code() == 200) {
                            Log.e("UPLD_IMG_OK", "Image uploaded for : " + serviceConnectionId);
                        } else {
                            try {
                                Log.e("ERR_UPLOD_IMG", response.errorBody().string());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    } else {
                        Log.e("UPLD_IMG_ERR", response.message());
                        try {
                            Log.e("ERR_UPLOD_IMG", response.errorBody().string());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.e("UPLD_IMG_ERR", t.getMessage());
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public class FetchSCTimeFrames extends AsyncTask<TimeFrames, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            timeFramesList.clear();
        }

        @Override
        protected Void doInBackground(TimeFrames... timeFrames) {
            try {
                TimeFramesDao timeFramesDao = db.timeFramesDao();

                timeFramesList.addAll(timeFramesDao.getUnuploaded());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);

        }
    }

    public class UpdateSCTimeFrames extends AsyncTask<TimeFrames, Void, Void> {

        @Override
        protected Void doInBackground(TimeFrames... timeFrames) {
            try {
                if (timeFrames != null) {
                    TimeFrames tf = timeFrames[0];
                    tf.setIsUploaded("Yes");
                    db.timeFramesDao().updateAll(tf);
                }
            } catch (Exception e) {
                Log.e("ERR_UPDT_SC_TFRME", e.getMessage());
            }
            return null;
        }
    }

    public class FetchUploadableTickets extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                ticketsList.addAll(db.ticketsDao().getUploadableTickets());

                for (Tickets tc: ticketsList) {
                    Log.e("TEST", tc.getPercentError());
                }
            } catch (Exception e) {
                Log.e("ERR_GET_TICKETS", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            uploadTicketsAdapter.notifyDataSetChanged();
        }
    }

    public void uploadTickets() {
        try {
            if (ticketsList.size() > 0) {
                Call<Tickets> ticketsCall = requestPlaceHolder.uploadTickets(ticketsList.get(0));
                ticketsCall.enqueue(new Callback<Tickets>() {
                    @Override
                    public void onResponse(Call<Tickets> call, Response<Tickets> response) {
                        if (response.isSuccessful()) {
                            new UpdateTicket().execute(ticketsList.get(0));
                            ticketsList.remove(0);
                            uploadTicketsAdapter.notifyDataSetChanged();
                            uploadTickets();
                        } else {
                            Log.e("ERR_UPLD_TICKETS", response.raw() + "");
                        }
                    }

                    @Override
                    public void onFailure(Call<Tickets> call, Throwable t) {
                        Log.e("ERR_UPLD_TICKETS", t.getMessage());
                    }
                });
            } else {
                ticketsProgressUpload.setVisibility(View.GONE);
                AlertHelpers.showExitableInfoDialog(UploadActivity.this, UploadActivity.this, "Upload success!", "All data uploaded successfully!");
            }
        } catch (Exception e) {
            Log.e("ERR_UPLD_TICKETS", e.getMessage());
        }
    }

    public class UpdateTicket extends AsyncTask<Tickets, Void, Void> {

        @Override
        protected Void doInBackground(Tickets... tickets) {
            try {
                if (tickets != null) {
                    Tickets tk = tickets[0];
                    tk.setUploadStatus("UPLOADED");
                    db.ticketsDao().updateAll(tk);
//                    db.ticketsDao().deleteOne(tk.getId());
                }
            } catch (Exception e) {
                Log.e("ERR_UPDT_TICKT", e.getMessage());
            }
            return null;
        }
    }

    public class FetchSettings extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                settings = db.settingsDao().getSettings();
            } catch (Exception e) {
                Log.e("ERR_FETCH_SETTINGS", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            if (settings != null) {
                retrofitBuilder = new RetrofitBuilder(settings.getDefaultServer());
                requestPlaceHolder = retrofitBuilder.getRetrofit().create(RequestPlaceHolder.class);

                new FetchUploadableServiceConnections().execute();
                new FetchSCTimeFrames().execute();
                new FetchUploadableTickets().execute();

                uploadDataBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        scProgressUpload.setVisibility(View.VISIBLE);
                        uploadServiceConnectionData();
                    }
                });

            } else {
                startActivity(new Intent(UploadActivity.this, ConnectionSettingsActivity.class));
            }
        }
    }

    public class UpdateMeterInstallations extends AsyncTask<MeterInstallation, Void, Void> {

        @Override
        protected Void doInBackground(MeterInstallation... meterInstallations) {
            try {
                MeterInstallation meterInstallation = meterInstallations[0];
                if (meterInstallation != null) {
                    meterInstallation.setUploadStatus("Uploaded");
                    db.meterInstallationDao().updateAll(meterInstallation);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public class UpdateLineAndMetering extends AsyncTask<LineAndMetering, Void, Void> {

        @Override
        protected Void doInBackground(LineAndMetering... lineAndMeterings) {
            try {
                LineAndMetering lam = lineAndMeterings[0];
                if (lam != null) {
                    lam.setUploadable("Uploaded");
                    db.lineAndMeteringDao().update(lam);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}