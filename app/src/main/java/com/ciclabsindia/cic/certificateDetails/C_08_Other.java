package com.ciclabsindia.cic.certificateDetails;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ciclabsindia.cic.GenerateCertificateActivity;
import com.ciclabsindia.cic.R;
import com.ciclabsindia.cic.database.DatabaseHandler;
import com.ciclabsindia.cic.model.Certificate;
import com.ciclabsindia.cic.model.Container;
import com.ciclabsindia.cic.model.Document;
import com.ciclabsindia.cic.model.QualityCheck;

import java.util.Calendar;

public class C_08_Other extends Fragment {
    EditText editText1, editText2, editText3, editText4, editText5;
    Button btn_submit;
    byte[] marking_of_bag_byteArray;
    String certificate_no, report_no, date, shipper_name, shipper_address, shipper_tel, shipper_fax,
            shipper_gst, notify_name, notify_address, notify_tel, notify_fax, description_of_goods,
            contract_no, invoice_no_pk, place_of_inspection, date_of_inspection, port_of_discharge,
            total_no_of_bags, gross_weight, tare_weight, net_weight, cleanliness_statement,
            quality_statement, packing, weight, conclusion, category, check_parameter,
            specification_in_parts, specification, test_result, extra_well_milled,
            container_no, container_size, no_of_bags, condition, last_edited_date_time, old_invoice_no;
    DatabaseHandler handler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_c_08_other, container, false);
        editText1 = myView.findViewById(R.id.editText1);
        editText2 = myView.findViewById(R.id.editText2);
        editText3 = myView.findViewById(R.id.editText3);
        editText4 = myView.findViewById(R.id.editText4);
        editText5 = myView.findViewById(R.id.editText5);
        btn_submit = myView.findViewById(R.id.buttonNext);
        handler = new DatabaseHandler(getActivity());

        //##################### GETTING DATA FROM PREVIOUS FRAGMENT #####################
        Bundle b = getArguments();
        certificate_no = b.getString("certificate_no");
        report_no = b.getString("report_no");
        date = b.getString("date");
        shipper_name = b.getString("shipper_name");
        shipper_address = b.getString("shipper_address");
        shipper_tel = b.getString("shipper_tel");
        shipper_fax = b.getString("shipper_fax");
        shipper_gst = b.getString("shipper_gst");
        notify_name = b.getString("notify_name");
        notify_address = b.getString("notify_address");
        notify_tel = b.getString("notify_tel");
        notify_fax = b.getString("notify_fax");
        description_of_goods = b.getString("description_of_goods");
        contract_no = b.getString("contract_no");
        invoice_no_pk = b.getString("invoice_no_pk");
        place_of_inspection = b.getString("place_of_inspection");
        date_of_inspection = b.getString("date_of_inspection");
        port_of_discharge = b.getString("port_of_discharge");
        marking_of_bag_byteArray = b.getByteArray("marking_of_bag_byteArray");
        total_no_of_bags = b.getString("total_no_of_bags");
        gross_weight = b.getString("gross_weight");
        tare_weight = b.getString("tare_weight");
        net_weight = b.getString("net_weight");
        cleanliness_statement = b.getString("cleanliness_statement");
        quality_statement = b.getString("quality_statement");
        packing = b.getString("packing");
        weight = b.getString("weight");
        conclusion = b.getString("conclusion");
        category = b.getString("category");
        check_parameter = b.getString("check_parameter");
        specification_in_parts = b.getString("specification_in_parts");
        specification = b.getString("specification");
        test_result = b.getString("test_result");
        extra_well_milled = b.getString("extra_well_milled");
        container_no = b.getString("container_no");
        container_size = b.getString("container_size");
        no_of_bags = b.getString("no_of_bags");
        condition = b.getString("condition");
        old_invoice_no = b.getString("old_invoice_no");

        editText1.setText(cleanliness_statement);
        editText2.setText(quality_statement);
        editText3.setText(packing);
        editText4.setText(weight);
        editText5.setText(conclusion);

        btn_submit.setOnClickListener(v -> {
            cleanliness_statement = editText1.getText().toString().toUpperCase();
            quality_statement = editText2.getText().toString().toUpperCase();
            packing = editText3.getText().toString().toUpperCase();
            weight = editText4.getText().toString().toUpperCase();
            conclusion = editText5.getText().toString().toUpperCase();

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
            else        mm = String.valueOf(mth);
            if (dt<10)  dd = "0" + dt;
            else        dd = String.valueOf(dt);
            if (mts<10) minutes = "0" + mts;
            else        minutes = String.valueOf(mts);
            last_edited_date_time = yyyy + "-" + mm + "-" + dd + "  " + hour + ":" + minutes;


            //##################### UPDATING DATA IN CERTIFICATE TABLE #####################
            Certificate certificate = new Certificate(certificate_no, report_no, date, shipper_name,
                    shipper_address, shipper_tel, shipper_fax, shipper_gst, notify_name, notify_address,
                    notify_tel, notify_fax, description_of_goods, contract_no, invoice_no_pk, place_of_inspection,
                    date_of_inspection, port_of_discharge, marking_of_bag_byteArray, total_no_of_bags, gross_weight,
                    tare_weight, net_weight, cleanliness_statement, quality_statement, packing, weight, conclusion, last_edited_date_time);
            long certificateResult = handler.updateCertificate(certificate, old_invoice_no);
            if (certificateResult > 0);
                //Toast.makeText(getActivity(), "Certificate updated!!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getActivity(), "Certificate Query problem!!", Toast.LENGTH_SHORT).show();


            //##################### UPDATING DATA IN CONTAINER TABLE #####################
            Container container1 = new Container(container_no, container_size, no_of_bags, condition, invoice_no_pk);
            long containerResult = handler.updateContainer(container1, old_invoice_no);
            if (containerResult > 0);
                //Toast.makeText(getActivity(), "Container updated!!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getActivity(), "Container Query problem!!", Toast.LENGTH_SHORT).show();


            //##################### UPDATING DATA IN QUALITY CHECK TABLE #####################
            QualityCheck qualityCheck = new QualityCheck(category, check_parameter, specification_in_parts,
                    specification, test_result, extra_well_milled, invoice_no_pk);
            long qualityResult = handler.updateQualityCheck(qualityCheck, old_invoice_no);
            if (qualityResult > 0);
                //Toast.makeText(getActivity(), "QualityCheck updated!!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getActivity(), "Quality Query problem!!", Toast.LENGTH_SHORT).show();


            //##################### UPDATING DATA IN DOCUMENT TABLE #####################
            Document document = new Document("C_" + invoice_no_pk, "certificate", shipper_name, invoice_no_pk, last_edited_date_time);
            long documentResult = handler.updateDocument(document, old_invoice_no);
            if (documentResult > 0);
                //Toast.makeText(getActivity(), "Document updated!!", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getActivity(), "Document Query problem!!", Toast.LENGTH_SHORT).show();


            //##################### SENDING INVOICE NO. TO PDF GENERATOR #####################
            Intent i = new Intent(getActivity(), GenerateCertificateActivity.class);
            i.putExtra("invoice_no", invoice_no_pk);
            startActivity(i);
        });
        return myView;
    }
}