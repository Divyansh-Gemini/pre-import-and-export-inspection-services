package com.ciclabsindia.cic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.ciclabsindia.cic.database.DatabaseHandler;
import com.ciclabsindia.cic.model.Certificate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class GenerateCertificateActivity extends AppCompatActivity {
    File file;
    DatabaseHandler handler;

    byte[] marking_of_bag_byteArray;
    Bitmap marking_of_bag;
    String certificate_no, report_no, date, shipper_name, shipper_address, shipper_tel, shipper_fax,
            shipper_gst, notify_name, notify_address, notify_tel, notify_fax, description_of_goods,
            contract_no, invoice_no, place_of_inspection, date_of_inspection, port_of_discharge,
            marking_of_bag_string, total_no_of_bags, gross_weight, tare_weight, net_weight, category,
            check_parameter, specification_in_parts, specification, test_result, extra_well_milled,
            container_no, container_size, no_of_bags, condition, cleanliness, quality, packing, weight,
            conclusion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);
        handler = new DatabaseHandler(GenerateCertificateActivity.this);

        //##################### GETTING INVOICE NO. FROM PREVIOUS ACTIVITY #####################
        Bundle b = getIntent().getExtras();
        invoice_no = b.getString("invoice_no");

        //##################### GETTING DATA FROM DATABASE USING INVOICE NO. #####################
        Certificate certificate = handler.getSingleCertificate(invoice_no);
        if (certificate != null) {
            certificate_no = certificate.getCertificate_no();
            report_no = certificate.getReport_no();
            date = certificate.getDate();
            shipper_name = certificate.getShipper_name();
            shipper_address = certificate.getShipper_address();
            shipper_tel = certificate.getShipper_tel();
            shipper_fax = certificate.getShipper_fax();
            shipper_gst = certificate.getShipper_gst();
            notify_name = certificate.getNotify_name();
            notify_address = certificate.getShipper_address();
            notify_tel = certificate.getNotify_tel();
            notify_fax = certificate.getShipper_fax();
            description_of_goods = certificate.getDescription_of_goods();
            contract_no = certificate.getContract_no();
            invoice_no = certificate.getInvoice_no_pk();
            place_of_inspection = certificate.getPlace_of_inspection();
            date_of_inspection = certificate.getDate_of_inspection();
            port_of_discharge = certificate.getPort_of_discharge();
            marking_of_bag_byteArray = certificate.getMarking_of_bag();
            if (marking_of_bag_byteArray!=null)
                marking_of_bag = BitmapFactory.decodeByteArray(marking_of_bag_byteArray, 0, marking_of_bag_byteArray.length);   // Converting byte[] to Bitmap
            total_no_of_bags = certificate.getTotal_no_of_bags();
            gross_weight = certificate.getGross_weight();
            tare_weight = certificate.getTare_weight();
            net_weight = certificate.getNet_weight();
            cleanliness = certificate.getCleanliness_statement();
            quality = certificate.getQuality_statement();
            packing = certificate.getPacking();
            weight = certificate.getWeight();
            conclusion = certificate.getConclusion();
        }
        else
            Toast.makeText(GenerateCertificateActivity.this, "Can't Generate Certificate", Toast.LENGTH_SHORT).show();

        createCertificate();

        // Opening Excel
        open_file(file);
    }

    //##################### GENERATING CERTIFICATE PDF #####################
    public void createCertificate(){
        PdfDocument myPdfDocument = new PdfDocument();
        Paint myPaint = new Paint();
        PdfDocument.PageInfo myPageinfo1 = new PdfDocument.PageInfo.Builder(410,600,1).create();
        PdfDocument.Page myPage1 = myPdfDocument.startPage(myPageinfo1);
        Canvas canvas = myPage1.getCanvas();

        final int margin_top = 40;
        final Typeface NORMAL = myPaint.getTypeface();
        final Typeface BOLD = Typeface.create(NORMAL, Typeface.BOLD);
        final Typeface ITALIC = Typeface.create(NORMAL, Typeface.ITALIC);
        final Typeface BOLD_ITALIC = Typeface.create(NORMAL, Typeface.BOLD_ITALIC);
        myPaint.setTextSize(9.0f);

        myPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("Certificate No.: " + certificate_no,7,margin_top,myPaint);
        canvas.drawText("Report No.: " + report_no,7,margin_top + 10,myPaint);

        myPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("DATE: " + date,395,margin_top,myPaint);

        myPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("INSPECTION CERTIFICATE",myPageinfo1.getPageWidth()/2,margin_top + 40,myPaint);

        myPaint.setTextAlign(Paint.Align.LEFT);
        myPaint.setTypeface(BOLD);
        canvas.drawText("SHIPPER                                      :        " + shipper_name,7,margin_top + 70,myPaint);
        myPaint.setTypeface(NORMAL);
        canvas.drawText("                                                         :         " + shipper_address,7,margin_top + 80,myPaint);
        canvas.drawText("                                                         :         " + shipper_tel,7,margin_top + 90,myPaint);
        canvas.drawText("                                                         :         " + shipper_fax,7,margin_top + 100,myPaint);
        canvas.drawText("                                                         :         " + shipper_gst,7,margin_top + 110,myPaint);

        myPaint.setTypeface(BOLD);
        canvas.drawText("NOTIFY PARTY                          :        " + notify_name,7,margin_top + 140,myPaint);
        myPaint.setTypeface(NORMAL);
        canvas.drawText("                                                         :         " + notify_address,7,margin_top + 150,myPaint);
        canvas.drawText("                                                         :         " + notify_tel,7,margin_top + 160,myPaint);
        canvas.drawText("                                                         :         " + notify_fax,7,margin_top + 170,myPaint);

        myPaint.setTypeface(BOLD);
        canvas.drawText("DESCRIPTION OF GOODS     :        " + description_of_goods,7,margin_top + 200,myPaint);
        myPaint.setTypeface(NORMAL);
        canvas.drawText("CONTRACT NO.                           :         " + contract_no,7,margin_top + 210,myPaint);
        canvas.drawText("INVOICE NO.                                 :         " + invoice_no,7,margin_top + 220,myPaint);
        canvas.drawText("PLACE OF INSPECTION           :         " + place_of_inspection,7,margin_top + 230,myPaint);
        canvas.drawText("DATE OF INSPECTION               :         " + date_of_inspection,7,margin_top + 240,myPaint);
        canvas.drawText("QUANTITY                                     :         " + total_no_of_bags + ", " + net_weight + " NET, " + gross_weight + " GROSS",7,margin_top + 250,myPaint);
        canvas.drawText("PORT OF DISCHARGE               :         " + port_of_discharge,7,margin_top + 260,myPaint);

        myPaint.setTypeface(BOLD);
        canvas.drawText("MARKING OF BAGS: - I N S P E C T I O N   R E S U L T S", 7, margin_top + 290, myPaint);

        float width = 90;
        float height = 100;
        float x = (myPageinfo1.getPageWidth()-width) / 2;
        float y = margin_top + 300;
        RectF rect = new RectF(x, y, x+width, y+height);
        if (marking_of_bag!=null)
            canvas.drawBitmap(marking_of_bag, null, rect, null);

        myPaint.setTypeface(NORMAL);
        myPaint.setTextAlign(Paint.Align.CENTER);
        //canvas.drawText("[  TABLE WILL APPEAR HERE  ]",myPageinfo1.getPageWidth()/2,margin_top + 430,myPaint);

        myPaint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("(Authorised Signatory)",395,margin_top + 490,myPaint);

        myPdfDocument.finishPage(myPage1);

        // Giving filename
        file = new File(this.getExternalFilesDir(null),"/C_" + invoice_no.replace("/", "%") + ".pdf");

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
        Intent i = new Intent(GenerateCertificateActivity.this, HomeActivity.class);
        startActivity(i);
    }
}