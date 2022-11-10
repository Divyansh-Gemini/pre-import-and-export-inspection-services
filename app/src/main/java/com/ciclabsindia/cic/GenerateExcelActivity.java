package com.ciclabsindia.cic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class GenerateExcelActivity extends AppCompatActivity {
    String invoice_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excel);

        // Getting Invoice No. from previous activity
        Bundle b = getIntent().getExtras();
        invoice_no = b.getString("invoice_no");

        // Creating ExcelWorkbook
        Workbook wb = new HSSFWorkbook();

        // Creating sheet
        Sheet sheet = wb.createSheet("Draft");    // Providing name to sheet

        Cell cell;

        // Now rows & columns

        Row row1 = sheet.createRow(6);

        cell = row1.createCell(0);
        cell.setCellValue("Certificate No");

        cell = row1.createCell(1);
        cell.setCellValue(" CIC/PKO/20-21/39");

        cell = row1.createCell(3);
        cell.setCellValue("DATE : 25TH DECEMBER, 2020 ");

        sheet.setColumnWidth(0,(10*250));
        sheet.setColumnWidth(1,(10*250));
        sheet.setColumnWidth(1,(10*250));

        Row row2 = sheet.createRow(7);

        cell = row2.createCell(0);
        cell.setCellValue("Report No");

        cell = row2.createCell(1);
        cell.setCellValue("Report No");

        sheet.setColumnWidth(0,(10*200));
        sheet.setColumnWidth(1,(10*250));

        Row row3 = sheet.createRow(8);
        cell = row3.createCell(1);
        cell.setCellValue("QUALITY,");

        cell = row3.createCell(1);
        cell.setCellValue(" QUANTITY AND WEIGHTMENT CERTIFICATE ");

        sheet.setColumnWidth(0,(10*200));
        sheet.setColumnWidth(1,(10*300));

        Row row4 = sheet.createRow(9);

        cell = row4.createCell(0);
        cell.setCellValue("EXPORTER  ");

        cell = row4.createCell(1);
        cell.setCellValue(":");

        cell = row4.createCell(2);
        cell.setCellValue("Exporter");

        sheet.setColumnWidth(0,(10*200));
        sheet.setColumnWidth(1,(10*10));
        sheet.setColumnWidth(2,(10*300));

        Row row5 = sheet.createRow(13);

        cell = row5.createCell(0);
        cell.setCellValue("Consignee");

        cell = row5.createCell(1);
        cell.setCellValue(":");

        cell = row5.createCell(2);
        cell.setCellValue("Consignee");

        sheet.setColumnWidth(0,(10*200));
        sheet.setColumnWidth(1,(10*10));
        sheet.setColumnWidth(2,(10*300));

        Row row6 = sheet.createRow(16);

        cell = row6.createCell(0);
        cell.setCellValue("Buyer ");

        cell = row6.createCell(1);
        cell.setCellValue(":");

        cell = row6.createCell(2);
        cell.setCellValue("Buyer");

        sheet.setColumnWidth(0,(10*350));
        sheet.setColumnWidth(1,(10*10));
        sheet.setColumnWidth(2,(10*400));

        Row row7 = sheet.createRow(18);

        cell = row7.createCell(0);
        cell.setCellValue("PORT OF LOADING");

        cell = row7.createCell(1);
        cell.setCellValue(":");

        cell = row7.createCell(2);
        cell.setCellValue("Loading");

        sheet.setColumnWidth(0,(10*450));
        sheet.setColumnWidth(1,(10*10));
        sheet.setColumnWidth(2,(10*450));

        Row row8 = sheet.createRow(19);

        cell = row8.createCell(0);
        cell.setCellValue("PORT OF DISCHARGE");
        cell = row8.createCell(1);
        cell.setCellValue(":");
        cell = row8.createCell(2);
        cell.setCellValue("Discharge");
        sheet.setColumnWidth(0,(10*450));
        sheet.setColumnWidth(1,(10*10));
        sheet.setColumnWidth(2,(10*300));

        Row row9 = sheet.createRow(20);

        cell = row9.createCell(0);
        cell.setCellValue("FINAL DESTINATION");
        cell = row9.createCell(1);
        cell.setCellValue(":");
        cell = row9.createCell(2);
        cell.setCellValue("Destination");

        sheet.setColumnWidth(0,(10*450));
        sheet.setColumnWidth(1,(10*10));
        sheet.setColumnWidth(2,(10*200));

        Row row10 = sheet.createRow(21);

        cell = row10.createCell(0);
        cell.setCellValue("DESCRIPTION OF GOODS");
        cell = row10.createCell(1);
        cell.setCellValue(":");
        cell = row10.createCell(2);
        cell.setCellValue("Discription_of_goods");
        sheet.setColumnWidth(0,(10*450));
        sheet.setColumnWidth(1,(10*10));
        sheet.setColumnWidth(2,(10*200));

        Row row11 = sheet.createRow(22);

        cell = row11.createCell(0);
        cell.setCellValue("TOTAL GROSS WEIGHT");
        cell = row11.createCell(1);
        cell.setCellValue(":");

        cell = row11.createCell(2);
        cell.setCellValue("Gweight");

        sheet.setColumnWidth(0,(10*450));
        sheet.setColumnWidth(1,(10*10));
        sheet.setColumnWidth(2,(10*450));

        Row row12 = sheet.createRow(23);
        cell = row12.createCell(0);
        cell.setCellValue("TOTAL NET WEIGHT");
        cell = row12.createCell(1);
        cell.setCellValue(":");
        cell = row12.createCell(2);
        cell.setCellValue("Nweight");
        sheet.setColumnWidth(0,(10*4500));
        sheet.setColumnWidth(1,(10*10));
        sheet.setColumnWidth(2,(10*200));

        Row row13 = sheet.createRow(24);

        cell = row13.createCell(0);
        cell.setCellValue("TOTAL NUMBER OF BAGS/CTNS");

        cell = row13.createCell(1);
        cell.setCellValue(":");
        cell = row13.createCell(2);
        cell.setCellValue("Totalbags");

        sheet.setColumnWidth(0,(10*500));
        sheet.setColumnWidth(1,(10*10));
        sheet.setColumnWidth(2,(10*300));

        Row row14 = sheet.createRow(25);

        cell = row14.createCell(0);
        cell.setCellValue("INVOICE NO & DATE");

        cell = row14.createCell(1);
        cell.setCellValue(":");

        cell = row14.createCell(2);
        cell.setCellValue("Invoice");

        sheet.setColumnWidth(0,(10*300));
        sheet.setColumnWidth(1,(10*10));
        sheet.setColumnWidth(2,(10*300));

        Row row15 = sheet.createRow(25);

        cell = row15.createCell(0);
        cell.setCellValue("PACKING");
        cell = row15.createCell(1);
        cell.setCellValue(":");

        cell = row15.createCell(2);
        cell.setCellValue("Packing");
        sheet.setColumnWidth(0,(10*200));
        sheet.setColumnWidth(1,(10*10));
        sheet.setColumnWidth(2,(10*200));

        Row row16 = sheet.createRow(28);

        cell = row16.createCell(0);
        cell.setCellValue("PACKING DATE");

        cell = row16.createCell(1);
        cell.setCellValue(":");

        cell = row16.createCell(2);
        cell.setCellValue("Pdate");

        sheet.setColumnWidth(0,(10*200));
        sheet.setColumnWidth(1,(10*10));
        sheet.setColumnWidth(2,(10*200));

        Row row17 = sheet.createRow(29);

        cell = row17.createCell(0);
        cell.setCellValue("EXPIRY DATE");

        cell = row17.createCell(1);
        cell.setCellValue(":");

        cell = row17.createCell(2);
        cell.setCellValue("Expirydate");

        sheet.setColumnWidth(0,(10*200));
        sheet.setColumnWidth(1,(10*10));
        sheet.setColumnWidth(2,(10*200));

        Row row18 = sheet.createRow(30);

        cell = row18.createCell(0);
        cell.setCellValue("ORIGIN OF COUNTRY");

        cell = row18.createCell(1);
        cell.setCellValue(":");

        cell = row18.createCell(2);
        cell.setCellValue("Origin");

        sheet.setColumnWidth(0,(10*250));
        sheet.setColumnWidth(1,(10*10));
        sheet.setColumnWidth(2,(10*200));

        Row row19 = sheet.createRow(31);

        cell = row19.createCell(0);
        cell.setCellValue("IEC CODE");
        cell = row19.createCell(1);
        cell.setCellValue(":");
        cell = row19.createCell(2);
        cell.setCellValue("Ieccode");

        sheet.setColumnWidth(0,(10*200));
        sheet.setColumnWidth(1,(10*10));
        sheet.setColumnWidth(2,(10*200));

        Row row20 = sheet.createRow(34);

        cell = row20.createCell(0);
        cell.setCellValue("THE INDIAN 1121 CREAM SELLA BASMATI RICE-MARYAM BRAND IN REPORT NO. C202012180007 BEEN ");

        sheet.setColumnWidth(1,(10*400));

        Row row21 = sheet.createRow(35);
        cell = row21.createCell(0);
        cell.setCellValue("THE INDIAN 1121 CREAM SELLA BASMATI RICE-MARYAM BRAND IN REPORT NO. C202012180007 BEEN ");

        sheet.setColumnWidth(1,(10*400));
        Row row22 = sheet.createRow(36);
        cell = row22.createCell(0);
        cell.setCellValue("THE INDIAN 1121 CREAM SELLA BASMATI RICE-MARYAM BRAND IN REPORT NO. C202012180007 BEEN ");
        sheet.setColumnWidth(1,(10*40));

        Row row23 = sheet.createRow(38);

        cell = row23.createCell(0);
        cell.setCellValue("ANALYSIS RESULTS:");

        sheet.setColumnWidth(1,(10*250));

        Row row24 = sheet.createRow(39);
        cell = row24.createCell(0);
        cell.setCellValue("One sealed sample drawn during inspection was analyzed by us. Such product were found free from  ");
        sheet.setColumnWidth(1,(10*400));

        Row row25 = sheet.createRow(40);
        cell = row25.createCell(0);
        cell.setCellValue("dust, live and dead infestation and no any other object like Glass, Metals etc. not seen in the lot.  ");
        sheet.setColumnWidth(1,(10*400));

        Row row26 = sheet.createRow(41);
        cell = row26.createCell(0);
        cell.setCellValue("These products are fit for human consumption. ");
        sheet.setColumnWidth(1,(10*400));

        Row row27 = sheet.createRow(42);
        cell = row27.createCell(0);
        cell.setCellValue("ALL NECESSARY MEASURES HAVE BEEN TAKEN TO ENSURE THAT THE CONSIGNMENT IS NOT");

        sheet.setColumnWidth(1,(10*400));
        Row row28 = sheet.createRow(43);
        cell = row28.createCell(0);
        cell.setCellValue("CONTAMINATED WITH CORONA VIRUS (COVID-19), WHETHER IT RELATES TO WORKERS OR PROCEDURE");
        sheet.setColumnWidth(1,(10*400));

        Row row29 = sheet.createRow(46);
        cell = row29.createCell(0);
        cell.setCellValue("DATE: 25.12.2020");
        cell = row29.createCell(3);
        cell.setCellValue("(Authorised Signatory)");

        sheet.setColumnWidth(0,(10*300));
        sheet.setColumnWidth(3,(10*300));
        Row row30 = sheet.createRow(47);
        cell = row30.createCell(3);
        cell.setCellValue("(Technical Manager)");
        sheet.setColumnWidth(3,(10*300));


        // Saving Excel
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(dir, "D_" + invoice_no.replace("/", "%") + ".xls");

        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            wb.write(outputStream);
        } catch (java.io.IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(),"Error in Generating Excel", Toast.LENGTH_LONG).show();
            try {
                outputStream.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        // Opening Excel
        open_file(file);
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
        Intent i = new Intent(GenerateExcelActivity.this, HomeActivity.class);
        startActivity(i);
    }
}