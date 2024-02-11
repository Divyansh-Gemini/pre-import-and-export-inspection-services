package com.ciclabsindia.cic.certificateDetails;

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

public class C_02_Shipper extends Fragment {
    EditText editText1, editText2, editText3, editText4, editText5;
    Button btn_next;
    byte[] marking_of_bag_byteArray;
    String certificate_no, report_no, date, shipper_name, shipper_address, shipper_tel, shipper_fax,
            shipper_gst, notify_name, notify_address, notify_tel, notify_fax, description_of_goods,
            contract_no, invoice_no_pk, place_of_inspection, date_of_inspection, port_of_discharge,
            total_no_of_bags, gross_weight, tare_weight, net_weight, cleanliness_statement,
            quality_statement, packing, weight, conclusion, category, check_parameter,
            specification_in_parts, specification, test_result, extra_well_milled,
            container_no, container_size, no_of_bags, condition;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_c_02_shipper, container, false);
        editText1 = myView.findViewById(R.id.editText1);
        editText2 = myView.findViewById(R.id.editText2);
        editText3 = myView.findViewById(R.id.editText3);
        editText4 = myView.findViewById(R.id.editText4);
        editText5 = myView.findViewById(R.id.editText5);
        btn_next = myView.findViewById(R.id.buttonNext);

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

        editText1.setText(shipper_name);
        editText2.setText(shipper_address);
        editText3.setText(shipper_tel);
        editText4.setText(shipper_fax);
        editText5.setText(shipper_fax);

        btn_next.setOnClickListener(v -> {
            shipper_name = editText1.getText().toString().toUpperCase();
            shipper_address = editText2.getText().toString().toUpperCase();
            shipper_tel = editText3.getText().toString();
            shipper_fax = editText4.getText().toString();
            shipper_gst = editText5.getText().toString().toUpperCase();

            //##################### SENDING DATA TO NEXT FRAGMENT #####################
            C_03_Notify frg = new C_03_Notify();
            Bundle b1 = new Bundle();
            b1.putString("certificate_no", certificate_no);
            b1.putString("report_no", report_no);
            b1.putString("date", date);
            b1.putString("shipper_name", shipper_name);
            b1.putString("shipper_address", shipper_address);
            b1.putString("shipper_tel", shipper_tel);
            b1.putString("shipper_fax", shipper_fax);
            b1.putString("shipper_gst", shipper_gst);
            b1.putString("notify_name", notify_name);
            b1.putString("notify_address", notify_address);
            b1.putString("notify_tel", notify_tel);
            b1.putString("notify_fax", notify_fax);
            b1.putString("description_of_goods", description_of_goods);
            b1.putString("contract_no", contract_no);
            b1.putString("invoice_no_pk", invoice_no_pk);
            b1.putString("place_of_inspection", place_of_inspection);
            b1.putString("date_of_inspection", date_of_inspection);
            b1.putString("port_of_discharge", port_of_discharge);
            b1.putByteArray("marking_of_bag_byteArray", marking_of_bag_byteArray);
            b1.putString("total_no_of_bags", total_no_of_bags);
            b1.putString("gross_weight", gross_weight);
            b1.putString("tare_weight", tare_weight);
            b1.putString("net_weight", net_weight);
            b1.putString("cleanliness_statement", cleanliness_statement);
            b1.putString("quality_statement", quality_statement);
            b1.putString("packing", packing);
            b1.putString("weight", weight);
            b1.putString("conclusion", conclusion);
            b1.putString("category", category);
            b1.putString("check_parameter", check_parameter);
            b1.putString("specification_in_parts", specification_in_parts);
            b1.putString("specification", specification);
            b1.putString("test_result", test_result);
            b1.putString("extra_well_milled", extra_well_milled);
            b1.putString("container_no",container_no );
            b1.putString("container_size", container_size);
            b1.putString("no_of_bags", no_of_bags);
            b1.putString("condition", condition);
            frg.setArguments(b1);

            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.certificateDetails, frg);
            ft.addToBackStack("");
            ft.commit();
        });
        return myView;
    }
}