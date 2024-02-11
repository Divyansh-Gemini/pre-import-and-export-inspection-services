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

public class D_04_GoodsDescription extends Fragment {
    EditText editText1, editText2, editText3, editText4;
    Button btn_next;
    String certificate_no, report_no, date, shipper_name, shipper_address, consignee_name, consignee_address, notify_name,
            notify_address, port_of_loading, port_of_discharge, final_destination, description_of_goods, gross_weight,
            net_weight, total_no_of_bags, invoice_no_pk, invoice_date, packing, bl_no;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_d_04_goods_description, container, false);
        editText1 = myView.findViewById(R.id.editText1);
        editText2 = myView.findViewById(R.id.editText2);
        editText3 = myView.findViewById(R.id.editText3);
        editText4 = myView.findViewById(R.id.editText4);
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

        editText1.setText(description_of_goods);
        editText2.setText(gross_weight);
        editText3.setText(net_weight);
        editText4.setText(total_no_of_bags);

        btn_next.setOnClickListener(v -> {
            description_of_goods = editText1.getText().toString().toUpperCase();
            gross_weight = editText2.getText().toString().toUpperCase();
            net_weight = editText3.getText().toString().toUpperCase();
            total_no_of_bags = editText4.getText().toString().toUpperCase();

            //##################### SENDING DATA TO NEXT FRAGMENT #####################
            D_05_Other frg = new D_05_Other();
            Bundle b1 = new Bundle();
            b1.putString("certificate_no", certificate_no);
            b1.putString("report_no", report_no);
            b1.putString("date", date);
            b1.putString("shipper_name", shipper_name);
            b1.putString("shipper_address", shipper_address);
            b1.putString("consignee_name", consignee_name);
            b1.putString("consignee_address", consignee_address);
            b1.putString("notify_name", notify_name);
            b1.putString("notify_address", notify_address);
            b1.putString("port_of_loading", port_of_loading);
            b1.putString("port_of_discharge", port_of_discharge);
            b1.putString("final_destination", final_destination);
            b1.putString("description_of_goods", description_of_goods);
            b1.putString("gross_weight", gross_weight);
            b1.putString("net_weight", net_weight);
            b1.putString("total_no_of_bags", total_no_of_bags);
            b1.putString("invoice_no_pk", invoice_no_pk);
            b1.putString("invoice_date", invoice_date);
            b1.putString("packing", packing);
            b1.putString("bl_no", bl_no);
            frg.setArguments(b1);

            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.draftDetails, frg);
            ft.addToBackStack("");
            ft.commit();
        });
        return myView;
    }
}