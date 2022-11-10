package com.ciclabsindia.cic.draftDetails;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ciclabsindia.cic.GenerateDraftActivity;
import com.ciclabsindia.cic.R;
import com.ciclabsindia.cic.database.DatabaseHandler;
import com.ciclabsindia.cic.model.Certificate;
import com.ciclabsindia.cic.model.Document;
import com.ciclabsindia.cic.model.Draft;

import java.util.Calendar;

public class D_05_Other extends Fragment {
    EditText editText1, editText2, editText3, editText4;
    Button btn_next;
    String certificate_no, report_no, date, shipper_name, shipper_address, consignee_name, consignee_address, notify_name,
            notify_address, port_of_loading, port_of_discharge, final_destination, description_of_goods, gross_weight,
            net_weight, total_no_of_bags, invoice_no_pk, invoice_date, packing, bl_no, last_edited_date_time;
    DatabaseHandler handler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_d_05_other, container, false);
        editText1 = myView.findViewById(R.id.editText1);
        editText2 = myView.findViewById(R.id.editText2);
        editText3 = myView.findViewById(R.id.editText3);
        editText4 = myView.findViewById(R.id.editText4);
        btn_next = myView.findViewById(R.id.buttonNext);
        handler = new DatabaseHandler(getActivity());

        //##################### GETTING DATA FROM PREVIOUS FRAGMENT #####################
        Bundle b = getArguments();
        certificate_no = b.getString("certificate_no");
        report_no = b.getString("report_no");
        date = b.getString("date");
        shipper_name = b.getString("shipper_name");
        shipper_address = b.getString("shipper_address");
        consignee_name = b.getString("consignee_name");
        consignee_address = b.getString("consignee_address");
        notify_name = b.getString("notify_name");
        notify_address = b.getString("notify_address");
        port_of_loading = b.getString("port_of_loading");
        port_of_discharge = b.getString("port_of_discharge");
        final_destination = b.getString("final_destination");
        description_of_goods = b.getString("description_of_goods");
        gross_weight = b.getString("gross_weight");
        net_weight = b.getString("net_weight");
        total_no_of_bags = b.getString("total_no_of_bags");
        invoice_no_pk = b.getString("invoice_no_pk");
        invoice_date = b.getString("invoice_date");
        packing = b.getString("packing");
        bl_no = b.getString("bl_no");
        String old_invoice_no = invoice_no_pk;

        editText1.setText(invoice_no_pk);
        editText2.setText(invoice_date);
        editText3.setText(packing);
        editText4.setText(bl_no);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                invoice_no_pk = editText1.getText().toString().toUpperCase();
                invoice_date = editText2.getText().toString();
                packing = editText3.getText().toString().toUpperCase();
                bl_no = editText4.getText().toString().toUpperCase();

                if (invoice_no_pk.isEmpty())
                    invoice_no_pk = String.valueOf(System.currentTimeMillis());

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
                last_edited_date_time = yyyy + "-" + mm + "-" + dd + "  " + hour + ":" + minutes;

                //##################### UPDATING DATA IN DRAFT TABLE #####################
                Draft draft = new Draft(certificate_no, report_no, date, shipper_name, shipper_address,
                        consignee_name, consignee_address, notify_name, notify_address, port_of_loading,
                        port_of_discharge, final_destination, description_of_goods, gross_weight, net_weight,
                        total_no_of_bags, invoice_no_pk, invoice_date, packing, bl_no, last_edited_date_time);

                long draftResult = handler.updateDraft(draft, old_invoice_no);
                if (draftResult > 0);
                    //Toast.makeText(getActivity(), "Draft updated!!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(), "Query problem!!", Toast.LENGTH_SHORT).show();

                //##################### UPDATING DATA IN DOCUMENT TABLE #####################
                Document document = new Document("D_" + invoice_no_pk, "draft", shipper_name, invoice_no_pk, last_edited_date_time);

                long documentResult = handler.updateDocument(document, old_invoice_no);
                if (documentResult > 0);
                    //Toast.makeText(getActivity(), "Document updated!!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(), "Query problem!!", Toast.LENGTH_SHORT).show();

                //##################### INSERTING DATA TO DRAFT TABLE #####################
//                Draft draft = new Draft(certificate_no, report_no, date, shipper_name, shipper_address,
//                        consignee_name, consignee_address, notify_name, notify_address, port_of_loading,
//                        port_of_discharge, final_destination, description_of_goods, gross_weight, net_weight,
//                        total_no_of_bags, invoice_no_pk, invoice_date, packing, bl_no, last_edited_date_time);
//                long draftResult = handler.insertDraft(draft);
//                if (draftResult > 0)
//                    Toast.makeText(getActivity(), "Data saved!!", Toast.LENGTH_SHORT).show();
//                else
//                    Toast.makeText(getActivity(), "Query problem!!", Toast.LENGTH_SHORT).show();


                //##################### INSERTING DATA TO DOCUMENT TABLE #####################
//                Document document = new Document("D_" + invoice_no_pk, "draft", shipper_name, invoice_no_pk, last_edited_date_time);
//                long documentResult = handler.insertDocument(document);
//                if (documentResult > 0)
//                    Toast.makeText(getActivity(), "Data saved!!", Toast.LENGTH_SHORT).show();
//                else
//                    Toast.makeText(getActivity(), "Query problem!!", Toast.LENGTH_SHORT).show();


                //##################### SENDING INVOICE NO. TO DRAFT GENERATOR #####################
                Intent i = new Intent(getActivity(), GenerateDraftActivity.class);
                i.putExtra("invoice_no", invoice_no_pk);
                startActivity(i);
            }
        });
        return myView;
    }
}