package com.lopez.julz.crmcrewhub;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.media.MediaScannerConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;
import androidx.room.Update;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.lopez.julz.crmcrewhub.classes.AlertHelpers;
import com.lopez.julz.crmcrewhub.classes.Barangays;
import com.lopez.julz.crmcrewhub.classes.ObjectHelpers;
import com.lopez.julz.crmcrewhub.classes.Towns;
import com.lopez.julz.crmcrewhub.database.AppDatabase;
import com.lopez.julz.crmcrewhub.database.LineAndMetering;
import com.lopez.julz.crmcrewhub.database.MeterInstallation;
import com.lopez.julz.crmcrewhub.database.ServiceConnectionInspections;
import com.lopez.julz.crmcrewhub.database.ServiceConnections;
import com.mihir.drawingcanvas.drawingView;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

public class UpdateApplication extends AppCompatActivity {

    public Toolbar updateToolbar;

    public String scId, inspId, userId, crew;

    public AppDatabase db;

    public ServiceConnections serviceConnection;

    Towns town;
    Barangays barangay;
    public MeterInstallation meterInstallation;
    public LineAndMetering lineAndMetering;
    public ServiceConnectionInspections inspections;
    public String customerSignature = "";

    public ExtendedFloatingActionButton saveButton;
    public MaterialButton addSignatureBtn, viewDetails;

    public TextView accountName, accountAddress, accountNumber, applicationType;
    public ImageView signaturePreview;
    public EditText newMeterNo, newMeterBrand, newMeterAmperes, oldMeterNo, lnVoltage, lgVoltage, ngVoltage, initialReading, dateInstalled, multiplier, transformerCapacity, transformerId, feeder, poleId, remarks, dateEnergized;
    public RadioGroup assessment;

    public FloatingActionButton dateInstalledBtn, dateEnergizedBtn, metering_serviceDoneButton;

    public ListView filesList;

    public MaterialCardView meterInstallationAccountability, metering, line;

    public EditText metering_meterSealNo, metering_meterNumber, metering_Multiplier, metering_meterType, metering_brand, metering_serviceDone, metering_electrician, metering_remarks;
    public EditText line_length, line_conductorType, line_size, line_units;
    public RadioGroup metering_leadSeal, metering_status;

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
        dateInstalledBtn = findViewById(R.id.dateInstalledBtn);
        dateEnergizedBtn = findViewById(R.id.dateEnergizedBtn);
        filesList = findViewById(R.id.filesList);
        accountNumber = findViewById(R.id.accountNumber);
        meterInstallationAccountability = findViewById(R.id.meterInstallationAccountability);
        metering = findViewById(R.id.metering);
        line = findViewById(R.id.line);
        applicationType = findViewById(R.id.applicationType);

        metering_meterSealNo = findViewById(R.id.metering_meterSealNo);
        metering_meterNumber = findViewById(R.id.metering_meterNumber);
        metering_Multiplier = findViewById(R.id.metering_Multiplier);
        metering_meterType = findViewById(R.id.metering_meterType);
        metering_brand = findViewById(R.id.metering_brand);
        metering_serviceDone = findViewById(R.id.metering_serviceDone);
        metering_electrician = findViewById(R.id.metering_electrician);
        metering_remarks = findViewById(R.id.metering_remarks);
        line_length = findViewById(R.id.line_length);
        line_conductorType = findViewById(R.id.line_conductorType);
        line_size = findViewById(R.id.line_size);
        line_units = findViewById(R.id.line_units);
        metering_leadSeal = findViewById(R.id.metering_leadSeal);
        metering_status = findViewById(R.id.metering_status);
        metering_serviceDoneButton = findViewById(R.id.metering_serviceDoneButton);
        viewDetails = findViewById(R.id.viewDetails);

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
                                    Bitmap saveBmp = newBitmap;
                                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                    newBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                                    byte[] byteArray = byteArrayOutputStream.toByteArray();

                                    // save bitmap to gallery
                                    File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

                                    // Create the storage directory if it does not exist
                                    if (!storageDir.exists()) {
                                        storageDir.mkdirs();
                                    }
                                    String fileName = "SIGN_" + scId + ".png";
                                    File file = new File(storageDir, fileName);
                                    FileOutputStream out = new FileOutputStream(file);
                                    saveBmp.compress(Bitmap.CompressFormat.PNG, 100, out);

                                    out.flush();
                                    out.close();

                                    MediaScannerConnection.scanFile(UpdateApplication.this, new String[]{file.getPath()}, new String[]{"image/png"}, null);

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

        dateInstalledBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                /**
                                 * GET SELECTED DAY
                                 */
                                String time = ObjectHelpers.getDateFromDatePicker(year + "-" + (monthOfYear+1) + "-" + dayOfMonth);

                                /*
                                 * INITIALIZE TIME
                                 */
                                TimePickerDialog tpd = TimePickerDialog.newInstance(
                                        new TimePickerDialog.OnTimeSetListener() {
                                            @Override
                                            public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
                                                dateInstalled.setText(time + " " + hourOfDay + ":" + minute + ":" + second);
                                            }
                                        },
                                        now.get(Calendar.HOUR_OF_DAY),
                                        now.get(Calendar.MINUTE),
                                        true
                                );

                                tpd.setVersion(TimePickerDialog.Version.VERSION_2);
                                tpd.setOkText("SELECT");
                                tpd.setCancelText("CLOSE");
                                tpd.show(getSupportFragmentManager(), "Select Time");
                            }
                        },
                        now.get(Calendar.YEAR), // Initial year selection
                        now.get(Calendar.MONTH), // Initial month selection
                        now.get(Calendar.DAY_OF_MONTH) // Inital day selection
                );
                dpd.setVersion(DatePickerDialog.Version.VERSION_2);
                dpd.setOkText("SELECT");
                dpd.setCancelText("CLOSE");
                dpd.show(getSupportFragmentManager(), "Select Date");
            }
        });

        dateEnergizedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                /**
                                 * GET SELECTED DAY
                                 */
                                String time = ObjectHelpers.getDateFromDatePicker(year + "-" + (monthOfYear+1) + "-" + dayOfMonth);

                                /*
                                 * INITIALIZE TIME
                                 */
                                TimePickerDialog tpd = TimePickerDialog.newInstance(
                                        new TimePickerDialog.OnTimeSetListener() {
                                            @Override
                                            public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
                                                dateEnergized.setText(time + " " + hourOfDay + ":" + minute + ":" + second);
                                            }
                                        },
                                        now.get(Calendar.HOUR_OF_DAY),
                                        now.get(Calendar.MINUTE),
                                        true
                                );

                                tpd.setVersion(TimePickerDialog.Version.VERSION_2);
                                tpd.setOkText("SELECT");
                                tpd.setCancelText("CLOSE");
                                tpd.show(getSupportFragmentManager(), "Select Time");
                            }
                        },
                        now.get(Calendar.YEAR), // Initial year selection
                        now.get(Calendar.MONTH), // Initial month selection
                        now.get(Calendar.DAY_OF_MONTH) // Inital day selection
                );
                dpd.setVersion(DatePickerDialog.Version.VERSION_2);
                dpd.setOkText("SELECT");
                dpd.setCancelText("CLOSE");
                dpd.show(getSupportFragmentManager(), "Select Date");
            }
        });

        metering_serviceDoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                /**
                                 * GET SELECTED DAY
                                 */
                                String time = ObjectHelpers.getDateFromDatePicker(year + "-" + (monthOfYear+1) + "-" + dayOfMonth);

                                /*
                                 * INITIALIZE TIME
                                 */
                                TimePickerDialog tpd = TimePickerDialog.newInstance(
                                        new TimePickerDialog.OnTimeSetListener() {
                                            @Override
                                            public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
                                                metering_serviceDone.setText(time + " " + hourOfDay + ":" + minute + ":" + second);
                                            }
                                        },
                                        now.get(Calendar.HOUR_OF_DAY),
                                        now.get(Calendar.MINUTE),
                                        true
                                );

                                tpd.setVersion(TimePickerDialog.Version.VERSION_2);
                                tpd.setOkText("SELECT");
                                tpd.setCancelText("CLOSE");
                                tpd.show(getSupportFragmentManager(), "Select Time");
                            }
                        },
                        now.get(Calendar.YEAR), // Initial year selection
                        now.get(Calendar.MONTH), // Initial month selection
                        now.get(Calendar.DAY_OF_MONTH) // Inital day selection
                );
                dpd.setVersion(DatePickerDialog.Version.VERSION_2);
                dpd.setOkText("SELECT");
                dpd.setCancelText("CLOSE");
                dpd.show(getSupportFragmentManager(), "Select Date");
            }
        });

        filesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                String info = ( (TextView) arg1 ).getText().toString();
//                Toast.makeText(FormEdit.this, info, Toast.LENGTH_SHORT).show();

                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() + "/lineman/" + serviceConnection.getId() + "/" + info);

                String extension = "";
                int lastDotIndex = info.lastIndexOf('.');
                if (lastDotIndex > 0) {
                    extension = info.substring(lastDotIndex + 1);
                }

                if (extension != null && extension.equals("pdf")) {
                    Intent intent = new Intent(UpdateApplication.this, PdfViewerActivity.class);
                    intent.putExtra("PDF_PATH", info);
                    intent.putExtra("SC_ID", serviceConnection.getId());
                    startActivity(intent);
                } else if (extension != null && (extension.equals("jpeg") | extension.equals("jpg") | extension.equals("png") | extension.equals("webp"))) {
                    Intent intent = new Intent(UpdateApplication.this, ImageViewerActivity.class);
                    intent.putExtra("IMG_PATH", info);
                    intent.putExtra("SC_ID", serviceConnection.getId());
                    startActivity(intent);
                } else {
                    Toast.makeText(UpdateApplication.this, "No application can open this file", Toast.LENGTH_SHORT).show();
                }

            }
        });

        viewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = LayoutInflater.from(UpdateApplication.this);
                View detailView = inflater.inflate(R.layout.alert_application_details, null);

                TextView details_dateOfApplication = detailView.findViewById(R.id.details_dateOfApplication);
                TextView details_address = detailView.findViewById(R.id.details_address);
                TextView details_accountType = detailView.findViewById(R.id.details_accountType);
                TextView details_loadType = detailView.findViewById(R.id.details_loadType);
                TextView details_contactNumber = detailView.findViewById(R.id.details_contactNumber);

                if (serviceConnection != null) {
                    details_dateOfApplication.setText(serviceConnection.getDateOfApplication());
                    details_address.setText((serviceConnection.getSitio() != null ? serviceConnection.getSitio() + ", " : "") + (barangay != null ? barangay.getBarangay() + ", " : "") + (town != null ? town.getTown() : ""));
                    details_accountType.setText(serviceConnection.getAccountType());
                    details_loadType.setText(serviceConnection.getLoadType());
                    details_contactNumber.setText(serviceConnection.getContactNumber());
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateApplication.this);
                builder.setTitle("Details")
                        .setView(detailView)
                        .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
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

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                serviceConnection = db.serviceConnectionsDao().getOne(scId);

                if (serviceConnection != null) {
                    town = db.townsDao().getOne(serviceConnection.getTown());
                    barangay = db.barangaysDao().getOne(serviceConnection.getBarangay());
                    meterInstallation = db.meterInstallationDao().getOneByServiceConnectionId(scId);
                    inspections = db.serviceConnectionInspectionsDao().getOneByServiceConnectionId(scId);
                    lineAndMetering = db.lineAndMeteringDao().getOne(scId);
                } else {
                    town = null;
                    barangay = null;
                    meterInstallation = null;
                    inspections = null;
                    lineAndMetering = null;
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
                    applicationType.setText(serviceConnection.getAccountApplicationType());

                    /**
                     * FILTER IF APPLICATION TYPE = NEW APPLICATION & TEMPORARY, OR OTHER SERVICES
                     */
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
                    } else {
                        if (inspections != null) {
                            transformerCapacity.setText(inspections.getSizeOfTransformer());
                            transformerId.setText(inspections.getTransformerNo());
                            feeder.setText(serviceConnection.getFeeder());
                            poleId.setText(serviceConnection.getPoleNumber());
                        }
                    }

                    if (lineAndMetering != null) {
                        metering_meterSealNo.setText(lineAndMetering.getMeterSealNumber());
                        metering_meterNumber.setText(lineAndMetering.getMeterNumber());
                        metering_Multiplier.setText(lineAndMetering.getMultiplier());
                        metering_meterType.setText(lineAndMetering.getMeterType());
                        metering_brand.setText(lineAndMetering.getMeterBrand());
                        metering_serviceDone.setText(lineAndMetering.getServiceDate());
                        metering_electrician.setText(lineAndMetering.getPrivateElectrician());
                        metering_remarks.setText(lineAndMetering.getNotes());
                        line_length.setText(lineAndMetering.getLineLength());
                        line_conductorType.setText(lineAndMetering.getConductorType());
                        line_size.setText(lineAndMetering.getConductorSize());
                        line_units.setText(lineAndMetering.getConductorUnit());
                        if (lineAndMetering.getIsLeadSeal() != null) {
                            metering_leadSeal.check(getLeadSeal(lineAndMetering.getIsLeadSeal()));
                        }
                        if (lineAndMetering.getMeterStatus() != null) {
                            metering_status.check(getLeadMeteringStatus(lineAndMetering.getMeterStatus()));
                        }
                    }

//                    if (serviceConnection.getAccountApplicationType() != null) {
//                        if (serviceConnection.getAccountApplicationType().equals("NEW INSTALLATION") | serviceConnection.getAccountApplicationType().equals("TEMPORARY")) {
//                            // SHOW METER INSTALLATION ACCOUNTABILITY
//                            meterInstallationAccountability.setVisibility(View.VISIBLE);
//                            metering.setVisibility(View.GONE);
//                            line.setVisibility(View.GONE);
//
//
//                        } else {
//                            // SHOW METERING AND LINE SERVICES
//                            meterInstallationAccountability.setVisibility(View.GONE);
//                            metering.setVisibility(View.VISIBLE);
//                            line.setVisibility(View.VISIBLE);
//
//
//                        }
//                    }

                    dateInstalled.setText(ObjectHelpers.getDate());
                    dateEnergized.setText(ObjectHelpers.getDate());

                    if (serviceConnection.getStatus() != null) {
                        if (serviceConnection.getStatus().equals("") | serviceConnection.getStatus().equals("For Energization")) {

                        } else {
                            assessment.check(getAssessment(serviceConnection.getStatus()));
                        }
                    }
                    remarks.setText(serviceConnection.getNotes());

                    accountNumber.setText(serviceConnection.getBarangayCode() + "-" + serviceConnection.getTypeOfCustomer() + "-" + serviceConnection.getAccountNumber() + "-" + serviceConnection.getNumberOfAccounts());

                    // display signature from bitmap file
                    Bitmap sign = getBitmapFromFile("SIGN_" + scId + ".png");
                    if (sign != null) {
                        signaturePreview.setImageBitmap(sign);
                    }

                    getFiles();
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
                // SAVE METERING ACCOUNTABILITY
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
                    //                    meterInstallation.setCustomerSignature(customerSignature);
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
                    //                    meterInstallation.setCustomerSignature(customerSignature);
                    meterInstallation.setInstalledBy(userId);

                    db.meterInstallationDao().insertAll(meterInstallation);
                }

                serviceConnection.setTransformerID(transformerId.getText().toString());
                serviceConnection.setPoleNumber(poleId.getText().toString());

                // SAVE LINE AND METERING
                lineAndMetering = db.lineAndMeteringDao().getOne(scId);
                if (lineAndMetering != null) {
                    lineAndMetering.setTypeOfService(serviceConnection.getAccountApplicationType());
                    lineAndMetering.setMeterSealNumber(metering_meterSealNo.getText().toString());
                    lineAndMetering.setMeterNumber(metering_meterNumber.getText().toString());
                    lineAndMetering.setMultiplier(metering_Multiplier.getText().toString());
                    lineAndMetering.setMeterType(metering_meterType.getText().toString());
                    lineAndMetering.setMeterBrand(metering_brand.getText().toString());
                    lineAndMetering.setServiceDate(metering_serviceDone.getText().toString());
                    lineAndMetering.setPrivateElectrician(metering_electrician.getText().toString());
                    lineAndMetering.setNotes(metering_remarks.getText().toString());
                    lineAndMetering.setUserId(userId);
                    lineAndMetering.setLineLength(line_length.getText().toString());
                    lineAndMetering.setConductorSize(line_size.getText().toString());
                    lineAndMetering.setConductorType(line_conductorType.getText().toString());
                    lineAndMetering.setConductorUnit(line_units.getText().toString());
                    if (ObjectHelpers.getSelectedTextFromRadioGroup(metering_leadSeal, getWindow().getDecorView()) != null) {
                        lineAndMetering.setIsLeadSeal(ObjectHelpers.getSelectedTextFromRadioGroup(metering_leadSeal, getWindow().getDecorView()));
                    }
                    if (ObjectHelpers.getSelectedTextFromRadioGroup(metering_status, getWindow().getDecorView()) != null) {
                        lineAndMetering.setMeterStatus(ObjectHelpers.getSelectedTextFromRadioGroup(metering_status, getWindow().getDecorView()));
                    }
                    lineAndMetering.setUploadable("Yes");

                    db.lineAndMeteringDao().update(lineAndMetering);
                } else {
                    lineAndMetering = new LineAndMetering();
                    lineAndMetering.setId(ObjectHelpers.getTimeInMillis());
                    lineAndMetering.setServiceConnectionId(scId);
                    lineAndMetering.setAccountNumber(serviceConnection.getBarangayCode() + "-" + serviceConnection.getTypeOfCustomer() + "-" + serviceConnection.getAccountNumber() + "-" + serviceConnection.getNumberOfAccounts());
                    lineAndMetering.setTypeOfService(serviceConnection.getAccountApplicationType());
                    lineAndMetering.setMeterSealNumber(metering_meterSealNo.getText().toString());
                    lineAndMetering.setMeterNumber(metering_meterNumber.getText().toString());
                    lineAndMetering.setMultiplier(metering_Multiplier.getText().toString());
                    lineAndMetering.setMeterType(metering_meterType.getText().toString());
                    lineAndMetering.setMeterBrand(metering_brand.getText().toString());
                    lineAndMetering.setServiceDate(metering_serviceDone.getText().toString());
                    lineAndMetering.setPrivateElectrician(metering_electrician.getText().toString());
                    lineAndMetering.setNotes(metering_remarks.getText().toString());
                    lineAndMetering.setUserId(userId);
                    lineAndMetering.setLineLength(line_length.getText().toString());
                    lineAndMetering.setConductorSize(line_size.getText().toString());
                    lineAndMetering.setConductorType(line_conductorType.getText().toString());
                    lineAndMetering.setConductorUnit(line_units.getText().toString());
                    if (ObjectHelpers.getSelectedTextFromRadioGroup(metering_leadSeal, getWindow().getDecorView()) != null) {
                        lineAndMetering.setIsLeadSeal(ObjectHelpers.getSelectedTextFromRadioGroup(metering_leadSeal, getWindow().getDecorView()));
                    }
                    if (ObjectHelpers.getSelectedTextFromRadioGroup(metering_status, getWindow().getDecorView()) != null) {
                        lineAndMetering.setMeterStatus(ObjectHelpers.getSelectedTextFromRadioGroup(metering_status, getWindow().getDecorView()));
                    }
                    lineAndMetering.setUploadable("Yes");

                    db.lineAndMeteringDao().insert(lineAndMetering);
                }

//                if (serviceConnection.getAccountApplicationType() != null) {
//                    if (serviceConnection.getAccountApplicationType().equals("NEW INSTALLATION") | serviceConnection.getAccountApplicationType().equals("TEMPORARY")) {
//
//                    } else {
//
//                    }
//                }

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
            } else if (recommendation.equals("Not Energized")) {
                return R.id.opsNotEnergized;
            } else {
                return -1;
            }
        } else {
            return 0;
        }
    }

    public int getLeadSeal(String leadSeal) {
        if (leadSeal != null) {
            if (leadSeal.equals("YES")) {
                return R.id.leadSealYes;
            } else if (leadSeal.equals("NO")) {
                return R.id.leadSealNo;
            } else {
                return -1;
            }
        } else {
            return 0;
        }
    }

    public int getLeadMeteringStatus(String meteringStatus) {
        if (meteringStatus != null) {
            if (meteringStatus.equals("ACTIVE")) {
                return R.id.statusActive;
            } else if (meteringStatus.equals("NOT")) {
                return R.id.statusNot;
            } else {
                return -1;
            }
        } else {
            return 0;
        }
    }

    public void getFiles() {
        try {
            File directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() + "/lineman/" + serviceConnection.getId());
            File[] files = directory.listFiles();
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,
                    getFileNames(files));

            filesList.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
            AlertHelpers.showInfoDialog(this, "Error Getting Files", e.getMessage());
        }
    }

    private String[] getFileNames(File[] files) {
        if (files == null) {
            return new String[0]; // Return an empty array if files is null
        }

        String[] fileNames = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            fileNames[i] = files[i].getName(); // Extract the file name
            Log.e("TEST", files[i].getName() + "");
        }
        return fileNames;
    }

    public Bitmap getBitmapFromFile(String fileName) {
        // Get the external storage directory
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        // Create the file object
        File file = new File(storageDir, fileName);

        // Check if the file exists
        if (file.exists()) {
            // Decode the file into a Bitmap
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            return bitmap;
        } else {
            // Handle the case where the file does not exist
            return null;
        }
    }
}