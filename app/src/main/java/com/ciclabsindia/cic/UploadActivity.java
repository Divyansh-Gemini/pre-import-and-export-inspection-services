package com.ciclabsindia.cic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.ciclabsindia.cic.database.DatabaseHandler;
import com.ciclabsindia.cic.model.Certificate;
import com.ciclabsindia.cic.model.Container;
import com.ciclabsindia.cic.model.Document;
import com.ciclabsindia.cic.model.Draft;
import com.ciclabsindia.cic.model.FileInfo;
import com.ciclabsindia.cic.model.QualityCheck;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class UploadActivity extends AppCompatActivity {
    ImageButton btn_upload;
    String doc_type;
    StringBuilder fileContent = new StringBuilder();
    StorageReference storageReference;
    DatabaseReference databaseReference;
    DatabaseHandler handler;
    List<PyObject> extractedFields = new ArrayList<>();
    byte[] marking_of_bag;
    String cr_certificate_no, cr_report_no, cr_date, cr_shipper_name, cr_shipper_address, cr_shipper_tel, cr_shipper_fax,
            cr_shipper_gst, cr_notify_name, cr_notify_address, cr_notify_tel, cr_notify_fax, cr_description_of_goods,
            cr_contract_no, cr_invoice_no_pk, cr_place_of_inspection, cr_date_of_inspection, cr_port_of_discharge,
            cr_total_no_of_bags, cr_gross_weight, cr_tare_weight, cr_net_weight, cr_cleanliness_statement,
            cr_quality_statement, cr_packing, cr_weight, cr_conclusion, cr_last_edited_date_time, q_category,
            q_check_parameter, q_specification_in_parts, q_specification, q_test_result, q_extra_well_milled,
            cn_container_no, cn_container_size, cn_no_of_bags, cn_condition;
    String dr_certificate_no, dr_report_no, dr_date, dr_shipper_name, dr_shipper_address, dr_consignee_name,
            dr_consignee_address, dr_notify_name, dr_notify_address, dr_port_of_loading, dr_port_of_discharge,
            dr_final_destination, dr_description_of_goods, dr_gross_weight, dr_net_weight, dr_total_no_of_bags,
            dr_invoice_no_pk, dr_invoice_date, dr_packing, dr_bl_no, dr_last_edited_date_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        btn_upload = findViewById(R.id.buttonUpload);
        handler = new DatabaseHandler(UploadActivity.this);
        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("documents");
        if (!Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }

        //##################### GETTING DOC_TYPE FROM PREVIOUS ACTIVITY #####################
        Bundle b = getIntent().getExtras();
        doc_type = b.getString("doc_type");

        //##################### UPLOAD BUTTON #####################
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(UploadActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
                    ActivityCompat.requestPermissions(UploadActivity.this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE},1);
                Intent i = new Intent();
                i.setType("application/pdf");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(i, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1 && resultCode==RESULT_OK && data!=null) {
            Uri uri = data.getData();
            // Getting filename from uri
            String filepath;
            Cursor cursor = getContentResolver().query(uri,null,null,null,null);
            if (cursor == null)
                filepath = uri.getPath();
            else {
                cursor.moveToFirst();
                int idx = cursor.getColumnIndex(MediaStore.Files.FileColumns.DISPLAY_NAME);
                filepath = cursor.getString(idx);
                cursor.close();
            }
            String doc_name = filepath.substring(0,filepath.lastIndexOf("."));

            //##################### SETTING THE CURRENT DATE-TIME AS "LAST_EDITED_DATE_TIME #####################
            Calendar ca = Calendar.getInstance();
            int yyyy = ca.get(Calendar.YEAR);
            int mth = ca.get(Calendar.MONTH)+1;
            int dt = ca.get(Calendar.DATE);
            int hour = ca.get(Calendar.HOUR);
            int mts = ca.get(Calendar.MINUTE);

            // Formatting month, date, and minutes
            String mm, dd, minutes;
            if (mth<10) mm = "0" + mth;
            else        mm = "" + mth;
            if (dt<10)  dd = "0" + dt;
            else        dd = "" + dt;
            if (mts<10) minutes = "0" + mts;
            else        minutes = "" + mts;

            if (isConnectedToInternet())
            {
                // Progress Dialog
                ProgressDialog pd = new ProgressDialog(this);
                pd.setTitle("Fetching data from PDF");
                pd.show();

                try {
                    //##################### UPLOADING PDF FILE TO FIREBASE #####################
                    final StorageReference reference = storageReference.child("PackingList/" + doc_name + ".pdf");
                    reference.putFile(uri)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            FileInfo fileInfo = new FileInfo(doc_name, uri.toString());
                                            databaseReference.child(databaseReference.push().getKey()).setValue(fileInfo);
                                            pd.dismiss();
                                            Toast.makeText(UploadActivity.this, "Plz wait for a moment", Toast.LENGTH_SHORT).show();
                                            pd.setTitle("Fetching data from PDF");
                                            pd.show();

                                            try{
                                                Python py = Python.getInstance();
                                                PyObject pyObj = py.getModule("camScript");

                                                PyObject obj = pyObj.callAttr("getData", uri.toString());
                                                extractedFields = obj.asList();
                                            }
                                            catch (Exception e){
                                                Toast.makeText(UploadActivity.this, "Select a valid document", Toast.LENGTH_SHORT).show();
                                            }
                                            pd.dismiss();

                                            if (extractedFields.size() == 0)
                                            {
                                                Toast.makeText(UploadActivity.this, "Select a valid document", Toast.LENGTH_SHORT).show();
                                                onBackPressed();
                                            }
                                            else
                                            {
                                                //##################### INSERTING DATA INTO SQLITE DATABASE AND #####################
                                                //##################### GOING TO NEXT ACTIVITY ACCORDING TO DOC_TYPE #####################

                                                //##################### FOR DRAFT #####################
                                                if (doc_type.equals("draft")) {
                                                    dr_shipper_name = extractedFields.get(0).toString();
                                                    dr_shipper_address = extractedFields.get(1).toString();
                                                    dr_consignee_name = extractedFields.get(2).toString();
                                                    dr_consignee_address = extractedFields.get(3).toString();
                                                    dr_notify_name = extractedFields.get(4).toString();
                                                    dr_notify_address = extractedFields.get(5).toString();
                                                    dr_port_of_loading = extractedFields.get(9).toString();
                                                    dr_port_of_discharge = extractedFields.get(10).toString();
                                                    dr_final_destination = extractedFields.get(11).toString();
                                                    dr_description_of_goods = extractedFields.get(12).toString();
                                                    dr_gross_weight = extractedFields.get(13).toString();
                                                    dr_net_weight = extractedFields.get(14).toString();
                                                    dr_total_no_of_bags = extractedFields.get(15).toString();
                                                    dr_invoice_no_pk = extractedFields.get(6).toString();
                                                    dr_invoice_date = extractedFields.get(7).toString();
                                                    dr_packing = extractedFields.get(15).toString();
                                                    dr_last_edited_date_time = yyyy + "-" + mm + "-" + dd + "  " + hour + ":" + minutes;

//                                         if (dr_invoice_no_pk.isEmpty())
                                                    dr_invoice_no_pk = String.valueOf(System.currentTimeMillis());


                                                    //##################### INSERTING DATA TO DRAFT TABLE #####################
                                                    Draft draft = new Draft(dr_certificate_no, dr_report_no, dr_date, dr_shipper_name, dr_shipper_address,
                                                            dr_consignee_name, dr_consignee_address, dr_notify_name, dr_notify_address, dr_port_of_loading,
                                                            dr_port_of_discharge, dr_final_destination, dr_description_of_goods, dr_gross_weight, dr_net_weight,
                                                            dr_total_no_of_bags, dr_invoice_no_pk, dr_invoice_date, dr_packing, dr_bl_no, dr_last_edited_date_time);
                                                    long draftResult = handler.insertDraft(draft);
                                                    if (draftResult > 0);
                                                        //Toast.makeText(UploadActivity.this, "Data saved!!", Toast.LENGTH_SHORT).show();
                                                    else
                                                        Toast.makeText(UploadActivity.this, "Query problem!!", Toast.LENGTH_SHORT).show();


                                                    //##################### INSERTING DATA TO DOCUMENT TABLE #####################
                                                    Document document = new Document("D_" + dr_invoice_no_pk, "draft", dr_shipper_name, dr_invoice_no_pk, dr_last_edited_date_time);
                                                    long documentResult = handler.insertDocument(document);
                                                    if (documentResult > 0);
                                                        //Toast.makeText(UploadActivity.this, "Data saved!!", Toast.LENGTH_SHORT).show();
                                                    else
                                                        Toast.makeText(UploadActivity.this, "Query problem!!", Toast.LENGTH_SHORT).show();

                                                    Intent i = new Intent(UploadActivity.this, DraftDetailsActivity.class);
                                                    i.putExtra("invoice_no", dr_invoice_no_pk);
                                                    startActivity(i);
                                                }

                                                //##################### FOR CERTIFICATE #####################
                                                else {
                                                    if (doc_type.equals("certificate")) {
                                                        extractedFields.size();
                                                        cr_shipper_name = extractedFields.get(0).toString();
                                                        cr_shipper_address = extractedFields.get(1).toString();
                                                        cr_notify_name = extractedFields.get(2).toString();
                                                        cr_notify_address = extractedFields.get(3).toString();
                                                        cr_description_of_goods = extractedFields.get(11).toString();
                                                        cr_invoice_no_pk = extractedFields.get(6).toString();
                                                        cr_port_of_discharge = extractedFields.get(9).toString();
                                                        cr_total_no_of_bags = extractedFields.get(14).toString();
                                                        cr_gross_weight = extractedFields.get(12).toString();
                                                        cr_net_weight = extractedFields.get(13).toString();
                                                        cr_last_edited_date_time = yyyy + "-" + mm + "-" + dd + "  " + hour + ":" + minutes;

                                                        //                                         if (cr_invoice_no_pk.isEmpty())
                                                        cr_invoice_no_pk = String.valueOf(System.currentTimeMillis());

                                                        //##################### INSERTING DATA IN CERTIFICATE TABLE #####################
                                                        Certificate certificate = new Certificate(cr_certificate_no, cr_report_no, cr_date, cr_shipper_name,
                                                                cr_shipper_address, cr_shipper_tel, cr_shipper_fax, cr_shipper_gst, cr_notify_name, cr_notify_address,
                                                                cr_notify_tel, cr_notify_fax, cr_description_of_goods, cr_contract_no, cr_invoice_no_pk, cr_place_of_inspection,
                                                                cr_date_of_inspection, cr_port_of_discharge, marking_of_bag, cr_total_no_of_bags, cr_gross_weight,
                                                                cr_tare_weight, cr_net_weight, cr_cleanliness_statement, cr_quality_statement, cr_packing, cr_weight,
                                                                cr_conclusion, cr_last_edited_date_time);
                                                        long certificateResult = handler.insertCertificate(certificate);
                                                        if (certificateResult > 0);
                                                            //Toast.makeText(UploadActivity.this, "Data saved!!", Toast.LENGTH_SHORT).show();
                                                        else
                                                            Toast.makeText(UploadActivity.this, "Query problem!!", Toast.LENGTH_SHORT).show();


                                                        //##################### INSERTING DATA IN CONTAINER TABLE #####################
                                                        Container container = new Container(cn_container_no, cn_container_size, cn_no_of_bags, cn_condition, cr_invoice_no_pk);
                                                        long containerResult = handler.insertContainer(container);
                                                        if (containerResult > 0);
                                                            //Toast.makeText(UploadActivity.this, "Data saved!!", Toast.LENGTH_SHORT).show();
                                                        else
                                                            Toast.makeText(UploadActivity.this, "Query problem!!", Toast.LENGTH_SHORT).show();


                                                        //##################### INSERTING DATA IN QUALITY CHECK TABLE #####################
                                                        QualityCheck qualityCheck = new QualityCheck(q_category, q_check_parameter, q_specification_in_parts,
                                                                q_specification, q_test_result, q_extra_well_milled, cr_invoice_no_pk);
                                                        long qualityResult = handler.insertQualityCheck(qualityCheck);
                                                        if (qualityResult > 0);
                                                            //Toast.makeText(UploadActivity.this, "Data saved!!", Toast.LENGTH_SHORT).show();
                                                        else
                                                            Toast.makeText(UploadActivity.this, "Query problem!!", Toast.LENGTH_SHORT).show();


                                                        //##################### INSERTING DATA IN DOCUMENT TABLE #####################
                                                        Document document = new Document("C_" + cr_invoice_no_pk, "certificate", cr_shipper_name, cr_invoice_no_pk, cr_last_edited_date_time);
                                                        long documentResult = handler.insertDocument(document);
                                                        if (documentResult > 0);
                                                            //Toast.makeText(UploadActivity.this, "Data saved!!", Toast.LENGTH_SHORT).show();
                                                        else
                                                            Toast.makeText(UploadActivity.this, "Query problem!!", Toast.LENGTH_SHORT).show();

                                                        Intent i = new Intent(UploadActivity.this, CertificateDetailsActivity.class);
                                                        i.putExtra("invoice_no", cr_invoice_no_pk);
                                                        startActivity(i);
                                                    }
                                                }
                                            }
                                        }
                                    });
                                }
                            })
                            .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                                    long percent = (100*snapshot.getBytesTransferred()) / snapshot.getTotalByteCount();
                                    pd.setMessage("Fetching data : " + (int)percent + "%");
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(UploadActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    pd.dismiss();
                                }
                            });
                }
                catch (Exception e)
                {
                    Toast.makeText(this, "Select a valid document", Toast.LENGTH_SHORT).show();
                }
            }
            else
                Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    boolean isConnectedToInternet()
    {
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED || connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED)
            return true;
        else
            return false;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        fileContent.setLength(0);
    }
}