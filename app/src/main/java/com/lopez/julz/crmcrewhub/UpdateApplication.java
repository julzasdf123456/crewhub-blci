package com.lopez.julz.crmcrewhub;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.lopez.julz.crmcrewhub.classes.Barangays;
import com.lopez.julz.crmcrewhub.classes.ObjectHelpers;
import com.lopez.julz.crmcrewhub.classes.Towns;
import com.lopez.julz.crmcrewhub.database.AppDatabase;
import com.lopez.julz.crmcrewhub.database.MeterInstallation;
import com.lopez.julz.crmcrewhub.database.ServiceConnections;
import com.mihir.drawingcanvas.drawingView;

import java.io.ByteArrayOutputStream;

public class UpdateApplication extends AppCompatActivity {

    public Toolbar updateToolbar;

    public String scId, inspId, userId, crew;

    public AppDatabase db;

    public ServiceConnections serviceConnection;
    public MeterInstallation meterInstallation;
    public String customerSignature = "";

    public ExtendedFloatingActionButton saveButton;
    public MaterialButton addSignatureBtn;

    public TextView accountName, accountAddress;
    public ImageView signaturePreview;
    public EditText newMeterNo, newMeterBrand, newMeterAmperes, oldMeterNo, lnVoltage, lgVoltage, ngVoltage, initialReading, dateInstalled, multiplier, transformerCapacity, transformerId, feeder, poleId, remarks, dateEnergized;
    public RadioGroup assessment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_application);

        scId = getIntent().getExtras().getString("SCID");
        inspId = getIntent().getExtras().getString("INSP_ID");
        userId = getIntent().getExtras().getString("USERID");
        crew = getIntent().getExtras().getString("CREW");

        db = Room.databaseBuilder(this, AppDatabase.class, ObjectHelpers.databaseName()).fallbackToDestructiveMigration().build();

        updateToolbar = findViewById(R.id.updateToolbar);
        setSupportActionBar(updateToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_round_arrow_back_24);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        accountName = findViewById(R.id.accountName);
        accountAddress = findViewById(R.id.accountAddress);
        newMeterNo = findViewById(R.id.newMeterNo);
        newMeterBrand = findViewById(R.id.newMeterBrand);
        newMeterAmperes = findViewById(R.id.newMeterAmperes);
        oldMeterNo = findViewById(R.id.oldMeterNo);
        lnVoltage = findViewById(R.id.lnVoltage);
        lgVoltage = findViewById(R.id.lgVoltage);
        ngVoltage = findViewById(R.id.ngVoltage);
        initialReading = findViewById(R.id.initialReading);
        dateInstalled = findViewById(R.id.dateInstalled);
        multiplier = findViewById(R.id.multiplier);
        transformerCapacity = findViewById(R.id.transformerCapacity);
        transformerId = findViewById(R.id.transformerId);
        feeder = findViewById(R.id.feeder);
        poleId = findViewById(R.id.poleId);
        saveButton = findViewById(R.id.saveButton);
        addSignatureBtn = findViewById(R.id.addSignatureBtn);
        signaturePreview = findViewById(R.id.signaturePreview);
        remarks = findViewById(R.id.remarks);
        assessment = findViewById(R.id.assessment);
        dateEnergized = findViewById(R.id.dateEnergized);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SaveMeterInstallation().execute();
            }
        });

        addSignatureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateApplication.this);

                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.customer_signature_layout, null);
                builder.setView(dialogView);

                drawingView drawing_view = dialogView.findViewById(R.id.drawing_view);

                builder.setTitle("Sign Here")
                        .setCancelable(false)
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("PROCEED", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // SAVE IMAGE
                                drawing_view.setDrawingCacheEnabled(true);
                                Bitmap b = Bitmap.createBitmap(drawing_view.getDrawingCache());
                                drawing_view.destroyDrawingCache();

                                Bitmap newBitmap = Bitmap.createBitmap(b.getWidth(),b.getHeight(), Bitmap.Config.ARGB_8888);
                                newBitmap.eraseColor(Color.WHITE);
                                Canvas canvas2 = new Canvas(newBitmap);
                                canvas2.drawBitmap(b,0F,0F,null);

                                try {
                                    // CONVERT TO BASE64
                                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                    newBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                                    byte[] byteArray = byteArrayOutputStream.toByteArray();

                                    customerSignature = Base64.encodeToString(byteArray, Base64.DEFAULT);

                                    signaturePreview.setImageBitmap(newBitmap);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.e("ERR_SV_IMG", e.getMessage());
                                }
                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new GetServiceConnectionDetails().execute();
    }

    public class GetServiceConnectionDetails extends AsyncTask<Void, Void, Void> {

        Towns town;
        Barangays barangay;

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                serviceConnection = db.serviceConnectionsDao().getOne(scId);

                if (serviceConnection != null) {
                    town = db.townsDao().getOne(serviceConnection.getTown());
                    barangay = db.barangaysDao().getOne(serviceConnection.getBarangay());
                    meterInstallation = db.meterInstallationDao().getOneByServiceConnectionId(scId);
                } else {
                    town = null;
                    barangay = null;
                    meterInstallation = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);

            try {
                if (serviceConnection != null) {
                    accountName.setText(serviceConnection.getServiceAccountName());
                    accountAddress.setText((serviceConnection.getSitio() != null ? serviceConnection.getSitio() + ", " : "") + (barangay != null ? barangay.getBarangay() + ", " : "") + (town != null ? town.getTown() : ""));

                    if (meterInstallation != null) {
                        newMeterNo.setText(meterInstallation.getNewMeterNumber());
                        newMeterBrand.setText(meterInstallation.getNewMeterBrand());
                        newMeterAmperes.setText(meterInstallation.getNewMeterAmperes());
                        oldMeterNo.setText(meterInstallation.getOldMeterNumber());
                        lnVoltage.setText(meterInstallation.getNewMeterLineToNeutral());
                        lgVoltage.setText(meterInstallation.getNewMeterLineToGround());
                        ngVoltage.setText(meterInstallation.getNewMeterNeutralToGround());
                        initialReading.setText(meterInstallation.getNewMeterInitialReading());
                        dateInstalled.setText(meterInstallation.getDateInstalled());
                        multiplier.setText(meterInstallation.getNewMeterMultiplier());
                        transformerCapacity.setText(meterInstallation.getTransfomerCapacity());
                        customerSignature = meterInstallation.getCustomerSignature();
                        if (meterInstallation.getCustomerSignature() != null && meterInstallation.getCustomerSignature().length() > 10) {
                            byte[] decodedString = Base64.decode(meterInstallation.getCustomerSignature(), Base64.DEFAULT);
                            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                            signaturePreview.setImageBitmap(decodedByte);
                        }
                    }

                    feeder.setText(serviceConnection.getFeeder());
                    poleId.setText(serviceConnection.getPoleNumber());
                    transformerId.setText(serviceConnection.getTransformerID());
                    dateInstalled.setText(ObjectHelpers.getDate());
                    dateEnergized.setText(ObjectHelpers.getDate());

                    if (serviceConnection.getStatus() != null) {
                        if (serviceConnection.getStatus().equals("") | serviceConnection.getStatus().equals("For Energization")) {

                        } else {
                            assessment.check(getAssessment(serviceConnection.getStatus()));
                        }
                    }
                    remarks.setText(serviceConnection.getNotes());
                } else {
                    Toast.makeText(UpdateApplication.this, "Application Not Found!", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public class SaveMeterInstallation extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                meterInstallation = db.meterInstallationDao().getOneByServiceConnectionId(scId);
                if (meterInstallation != null) {
                    meterInstallation.setNewMeterNumber(newMeterNo.getText().toString());
                    meterInstallation.setNewMeterBrand(newMeterBrand.getText().toString());
                    meterInstallation.setNewMeterAmperes(newMeterAmperes.getText().toString());
                    meterInstallation.setOldMeterNumber(oldMeterNo.getText().toString());
                    meterInstallation.setNewMeterLineToNeutral(lnVoltage.getText().toString());
                    meterInstallation.setNewMeterLineToGround(lgVoltage.getText().toString());
                    meterInstallation.setNewMeterNeutralToGround(ngVoltage.getText().toString());
                    meterInstallation.setNewMeterInitialReading(initialReading.getText().toString());
                    meterInstallation.setDateInstalled(dateInstalled.getText().toString());
                    meterInstallation.setNewMeterMultiplier(multiplier.getText().toString());
                    meterInstallation.setTransfomerCapacity(transformerCapacity.getText().toString());
                    meterInstallation.setTransformerID(transformerId.getText().toString());
                    meterInstallation.setPoleID(poleId.getText().toString());
                    meterInstallation.setCustomerSignature(customerSignature);
                    meterInstallation.setInstalledBy(userId);

                    db.meterInstallationDao().updateAll(meterInstallation);
                } else {
                    meterInstallation = new MeterInstallation();

                    meterInstallation.setId(ObjectHelpers.getTimeInMillis());
                    meterInstallation.setServiceConnectionId(scId);
                    meterInstallation.setType("METER INSTALLATION AND ACCOUNTABILITY");
                    meterInstallation.setNewMeterNumber(newMeterNo.getText().toString());
                    meterInstallation.setNewMeterBrand(newMeterBrand.getText().toString());
                    meterInstallation.setNewMeterAmperes(newMeterAmperes.getText().toString());
                    meterInstallation.setOldMeterNumber(oldMeterNo.getText().toString());
                    meterInstallation.setNewMeterLineToNeutral(lnVoltage.getText().toString());
                    meterInstallation.setNewMeterLineToGround(lgVoltage.getText().toString());
                    meterInstallation.setNewMeterNeutralToGround(ngVoltage.getText().toString());
                    meterInstallation.setNewMeterInitialReading(initialReading.getText().toString());
                    meterInstallation.setDateInstalled(dateInstalled.getText().toString());
                    meterInstallation.setNewMeterMultiplier(multiplier.getText().toString());
                    meterInstallation.setTransfomerCapacity(transformerCapacity.getText().toString());
                    meterInstallation.setTransformerID(transformerId.getText().toString());
                    meterInstallation.setPoleID(poleId.getText().toString());
                    meterInstallation.setCustomerSignature(customerSignature);
                    meterInstallation.setInstalledBy(userId);

                    db.meterInstallationDao().insertAll(meterInstallation);
                }

                serviceConnection.setTransformerID(transformerId.getText().toString());
                serviceConnection.setPoleNumber(poleId.getText().toString());
                if (ObjectHelpers.getSelectedTextFromRadioGroup(assessment, getWindow().getDecorView()) != null) {
                    serviceConnection.setStatus(ObjectHelpers.getSelectedTextFromRadioGroup(assessment, getWindow().getDecorView()));

                    if (serviceConnection.getStatus() != null && serviceConnection.getStatus().equals("Energized")) {
                        serviceConnection.setUploadStatus("UPLOADABLE");
                    }
                }
                serviceConnection.setNotes(remarks.getText().toString());
                serviceConnection.setDateTimeOfEnergization(dateEnergized.getText().toString());
                db.serviceConnectionsDao().updateAll(serviceConnection);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            Toast.makeText(UpdateApplication.this, "Meter Installation Saved!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    public int getAssessment(String recommendation) {
        if (recommendation != null) {
            if (recommendation.equals("Energized")) {
                return R.id.opsEnergized;
            } else {
                return R.id.opsNotEnergized;
            }
        } else {
            return 0;
        }
    }
}