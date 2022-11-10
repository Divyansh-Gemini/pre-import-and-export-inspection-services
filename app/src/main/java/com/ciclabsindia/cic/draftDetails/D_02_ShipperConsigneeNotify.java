package com.ciclabsindia.cic.draftDetails;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ciclabsindia.cic.R;
import com.ciclabsindia.cic.certificateDetails.C_03_Notify;
import com.ciclabsindia.cic.database.DatabaseHandler;
import com.ciclabsindia.cic.model.Draft;

public class D_02_ShipperConsigneeNotify extends Fragment {
    EditText editText1, editText2, editText3, editText4, editText5, editText6;
    Button btn_next;
    String certificate_no, report_no, date, shipper_name, shipper_address, consignee_name, consignee_address, notify_name,
            notify_address, port_of_loading, port_of_discharge, final_destination, description_of_goods, gross_weight,
            net_weight, total_no_of_bags, invoice_no_pk, invoice_date, packing, bl_no;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_d_02_shipper_consignee_notify, container, false);
        editText1 = myView.findViewById(R.id.editText1);
        editText2 = myView.findViewById(R.id.editText2);
        editText3 = myView.findViewById(R.id.editText3);
        editText4 = myView.findViewById(R.id.editText4);
        editText5 = myView.findViewById(R.id.editText5);
        editText6 = myView.findViewById(R.id.editText6);
        btn_next = myView.findViewById(R.id.buttonNext);

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

        editText1.setText(shipper_name);
        editText2.setText(shipper_address);
        editText3.setText(consignee_name);
        editText4.setText(consignee_address);
        editText5.setText(notify_name);
        editText6.setText(notify_address);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shipper_name = editText1.getText().toString().toUpperCase();
                shipper_address = editText2.getText().toString().toUpperCase();
                consignee_name = editText3.getText().toString().toUpperCase();
                consignee_address = editText4.getText().toString().toUpperCase();
                notify_name = editText5.getText().toString().toUpperCase();
                notify_address = editText6.getText().toString().toUpperCase();

                //##################### SENDING DATA TO NEXT FRAGMENT #####################
                D_03_Locations frg = new D_03_Locations();
                Bundle b = new Bundle();
                b.putString("certificate_no", certificate_no);
                b.putString("report_no", report_no);
                b.putString("date", date);
                b.putString("shipper_name", shipper_name);
                b.putString("shipper_address", shipper_address);
                b.putString("consignee_name", consignee_name);
                b.putString("consignee_address", consignee_address);
                b.putString("notify_name", notify_name);
                b.putString("notify_address", notify_address);
                b.putString("port_of_loading", port_of_loading);
                b.putString("port_of_discharge", port_of_discharge);
                b.putString("final_destination", final_destination);
                b.putString("description_of_goods", description_of_goods);
                b.putString("gross_weight", gross_weight);
                b.putString("net_weight", net_weight);
                b.putString("total_no_of_bags", total_no_of_bags);
                b.putString("invoice_no_pk", invoice_no_pk);
                b.putString("invoice_date", invoice_date);
                b.putString("packing", packing);
                b.putString("bl_no", bl_no);
                frg.setArguments(b);

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.draftDetails, frg);
                ft.addToBackStack("");
                ft.commit();
            }
        });
        return myView;
    }
}