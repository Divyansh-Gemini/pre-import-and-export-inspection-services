package com.ciclabsindia.cic.draftDetails;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.ciclabsindia.cic.R;
import com.ciclabsindia.cic.database.DatabaseHandler;
import com.ciclabsindia.cic.model.Draft;

import java.util.Calendar;

public class D_01_DraftInfo extends Fragment {
    EditText editText1, editText2, editText3;
    Button btn_next;
    int dd, mm, yyyy;
    String certificate_no, report_no, date, shipper_name, shipper_address, consignee_name, consignee_address, notify_name,
            notify_address, port_of_loading, port_of_discharge, final_destination, description_of_goods, gross_weight,
            net_weight, total_no_of_bags, invoice_no_pk, invoice_date, packing, bl_no;
    DatabaseHandler handler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_d_01_draft_info, container, false);
        editText1 = myView.findViewById(R.id.editText1);
        editText2 = myView.findViewById(R.id.editText2);
        editText3 = myView.findViewById(R.id.editText3);
        btn_next = myView.findViewById(R.id.buttonNext);
        handler = new DatabaseHandler(getActivity());

        // Getting current date
        Calendar ca = Calendar.getInstance();
        dd = ca.get(Calendar.DATE);
        mm = ca.get(Calendar.MONTH);
        yyyy = ca.get(Calendar.YEAR);

        // Getting Invoice No. to DraftDetailsActivity
        Bundle b = getArguments();
        invoice_no_pk = b.getString("invoice_no");

        // Getting data from Database
        Draft draft = handler.getSingleDraft(invoice_no_pk);
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
            invoice_no_pk = draft.getInvoice_no_pk();
            invoice_date = draft.getInvoice_date();
            packing = draft.getPacking();
            bl_no = draft.getBl_no();
        }

        editText1.setText(certificate_no);
        editText2.setText(report_no);
        editText3.setText(date);

        editText3.setOnClickListener(v -> new DatePickerDialog(getActivity(), listener, yyyy, mm, dd).show());

        btn_next.setOnClickListener(v -> {
            certificate_no = editText1.getText().toString().toUpperCase();
            report_no = editText2.getText().toString().toUpperCase();
            date = editText3.getText().toString();

            //##################### SENDING DATA TO NEXT FRAGMENT #####################
            D_02_ShipperConsigneeNotify frg = new D_02_ShipperConsigneeNotify();
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

    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            editText3.setText(String.format("%d.%d.%d", dayOfMonth, month + 1, year));
            yyyy = year;
            mm = month;
            dd = dayOfMonth;
        }
    };
}