package com.ciclabsindia.cic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.ciclabsindia.cic.database.DatabaseHandler;
import com.ciclabsindia.cic.model.Draft;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class GenerateDraftActivity extends AppCompatActivity {
    File file;
    DatabaseHandler handler;

    String certificate_no, report_no, date, shipper_name, shipper_address, consignee_name, consignee_address,
            notify_name, notify_address, port_of_loading, port_of_discharge, final_destination, description_of_goods,
            gross_weight, net_weight, total_no_of_bags, invoice_no, invoice_date, packing, bl_no;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);

        handler = new DatabaseHandler(GenerateDraftActivity.this);

        //##################### GETTING INVOICE NO. FROM PREVIOUS ACTIVITY #####################
        Bundle b = getIntent().getExtras();
        invoice_no = b.getString("invoice_no");

        //##################### GETTING DATA FROM DATABASE USING INVOICE NO. #####################
        Draft draft = handler.getSingleDraft(invoice_no);
        if (draft != null) {
            certificate_no = draft.getCertificate_no();
            report_no = draft.getReport_no();
            date = draft.getDate();
            shipper_name = draft.getShipper_name();
            shipper_address = draft.getShipper_address();
            consignee_name = draft.getConsignee_name();
            consignee_address = draft.getConsignee_address();
            notify_name = draft.getNotify_name();
            notify_address = draft.getNotify_address();
            port_of_loading = draft.getPort_of_loading();
            port_of_discharge = draft.getPort_of_discharge();
            final_destination = draft.getFinal_destination();
            description_of_goods = draft.getDescription_of_goods();
            gross_weight = draft.getGross_weight();
            net_weight = draft.getNet_weight();
            total_no_of_bags = draft.getTotal_no_of_bags();
            invoice_no = draft.getInvoice_no_pk();
            invoice_date = draft.getInvoice_date();
            packing = draft.getPacking();
            bl_no = draft.getBl_no();
        }
        else
            Toast.makeText(GenerateDraftActivity.this, "Can't Generate Draft", Toast.LENGTH_SHORT).show();

        createDraft();

        // Opening Excel
        open_file(file);
    }

    //##################### GENERATING DRAFT PDF #####################
    public void createDraft(){
        PdfDocument myPdfDocument = new PdfDocument();
        Paint myPaint = new Paint();
        PdfDocument.PageInfo myPageinfo1 = new PdfDocument.PageInfo.Builder(410,600,1).create();
        PdfDocument.Page myPage1 = myPdfDocument.startPage(myPageinfo1);
        Canvas canvas = myPage1.getCanvas();

        final int margin_top = 40;
        final Typeface plain = myPaint.getTypeface();
        final Typeface bold = Typeface.create(plain, Typeface.BOLD);
        myPaint.setTextSize(9.0f);

        myPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("Certificate No.: " + certificate_no,7,margin_top,myPaint);
        canvas.drawText("Report No.: " + report_no,7,margin_top + 10,myPaint);

        myPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("DATE: " + date,395,margin_top,myPaint);

        myPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("INSPECTION CERTIFICATE",myPageinfo1.getPageWidth()/2,margin_top + 40,myPaint);

        myPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("SHIPPER                                    :         " + shipper_name,7,margin_top + 70,myPaint);
        canvas.drawText("                                                      :         " + shipper_address,7,margin_top + 80,myPaint);

        canvas.drawText("CONSIGNEE                               :         " + consignee_name,7,margin_top + 110,myPaint);
        canvas.drawText("                                                      :         " + consignee_address,7,margin_top + 120,myPaint);

        canvas.drawText("NOTIFY PARTY                         :         " + notify_name,7,margin_top + 150,myPaint);
        canvas.drawText("                                                      :         " + notify_address,7,margin_top + 160,myPaint);

        canvas.drawText("PORT OF LOADING                 :         " + port_of_loading,7,margin_top + 190,myPaint);
        canvas.drawText("PORT OF DISCHARGE            :         " + port_of_discharge,7,margin_top + 200,myPaint);
        canvas.drawText("FINAL DESTINATION               :         " + final_destination,7,margin_top + 210,myPaint);
        myPaint.setTypeface(bold);
        canvas.drawText("DESCRIPTION OF GOODS   :        " + description_of_goods,7,margin_top + 220,myPaint);

        myPaint.setTypeface(plain);
        canvas.drawText("TOTAL GROSS WEIGHT          :         " + gross_weight,7,margin_top + 250,myPaint);
        canvas.drawText("TOTAL NET WEIGHT                :         " + net_weight,7,margin_top + 260,myPaint);
        canvas.drawText("TOTAL NUMBER OF BAGS    :         " + total_no_of_bags,7,margin_top + 290,myPaint);
        canvas.drawText("INVOICE NO & DATE                 :         " + invoice_no + " , " + invoice_date,7,margin_top + 300,myPaint);
        canvas.drawText("PACKING                                     :         " + packing,7,margin_top + 310,myPaint);
        canvas.drawText("B/L NO                                         :         " + bl_no,7,margin_top + 320,myPaint);

        canvas.drawText("THE INDIAN LONG GRAIN RICE, 5% BROKEN MAX IN REPORT NO C20220613002 HAS BEEN TASTED",7,margin_top + 350,myPaint);
        canvas.drawText("AND CONFIRMS THE STANDARDS LAID DOWN UNDER THE FOOD SAFETY & STANDARD ACT 2011",7,margin_top + 360,myPaint);
        canvas.drawText("WITH RESPECT TO THE TESTED PARAMETERS.",7,margin_top + 370,myPaint);

        canvas.drawText("ANALYSIS RESULTS: ",7,margin_top + 385,myPaint);
        canvas.drawText("One sealed sample drawn during inspection was analyzed us. Such product were found free from",7,margin_top + 395,myPaint);
        canvas.drawText("dust live and dead infestation and no any other object like Glass,Metals etc.not seen in the lot.",7,margin_top + 405,myPaint);
        canvas.drawText("These product are fit for Human Consumption.",7,margin_top + 415,myPaint);

        myPaint.setTypeface(bold);
        canvas.drawText("'ALL NECESSARY MEASURES HAVE BEEN TAKEN TO ENSURE THAT THE CONSIGNMENT IS NOT'",7,margin_top + 430,myPaint);
        canvas.drawText("CONTAMINATED WITH CORONA VIRUS (COVID-19),WHETHER IT RELATES TO WORKERS OR",7,margin_top + 440,myPaint);
        canvas.drawText("PROCEDURES.",7,margin_top + 450,myPaint);

        myPaint.setTypeface(plain);
        canvas.drawText("DATE: " + date,10,margin_top + 500,myPaint);

        myPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("(Authorised Signatory)",395,margin_top + 500,myPaint);
        canvas.drawText("(Technical Manager)  ",395,margin_top + 510,myPaint);

        myPdfDocument.finishPage(myPage1);

        // Giving filename
        file = new File(this.getExternalFilesDir(null),"/D_" + invoice_no.replace("/", "%") + ".pdf");

        // Saving PDF
        try {myPdfDocument.writeTo(new FileOutputStream(file));}
        catch (IOException e)   {e.printStackTrace();}

        myPdfDocument.close();
    }

    public void open_file(File file) {
        // Get URI & MIME type of file
        Uri uri = FileProvider.getUriForFile(this, "com.ciclabsindia.cic", file);
        String mime = getContentResolver().getType(uri);

        // Open file with user selected app
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, mime);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(intent);
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        // Going back to Home Activity on Restarting this Activity
        startActivity(new Intent(GenerateDraftActivity.this, HomeActivity.class));
    }
}