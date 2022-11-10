package com.ciclabsindia.cic.certificateDetails;

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
import com.ciclabsindia.cic.model.Certificate;
import com.ciclabsindia.cic.model.Container;
import com.ciclabsindia.cic.model.Draft;
import com.ciclabsindia.cic.model.QualityCheck;

import java.util.Calendar;

public class C_01_CertificateInfo extends Fragment {
    EditText editText1, editText2, editText3;
    Button btn_next;
    int dd, mm, yyyy;
    byte[] marking_of_bag_byteArray;
    String certificate_no, report_no, date, shipper_name, shipper_address, shipper_tel, shipper_fax,
            shipper_gst, notify_name, notify_address, notify_tel, notify_fax, description_of_goods,
            contract_no, invoice_no_pk, place_of_inspection, date_of_inspection, port_of_discharge,
            total_no_of_bags, gross_weight, tare_weight, net_weight, cleanliness_statement,
            quality_statement, packing, weight, conclusion, category, check_parameter,
            specification_in_parts, specification, test_result, extra_well_milled,
            container_no, container_size, no_of_bags, condition;
    DatabaseHandler handler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_c_01_certificate_info, container, false);
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

        // Getting data from Certificate table
        Certificate certificate = handler.getSingleCertificate(invoice_no_pk);
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
            notify_address = certificate.getNotify_address();
            notify_tel = certificate.getNotify_tel();
            notify_fax = certificate.getNotify_fax();
            description_of_goods = certificate.getDescription_of_goods();
            contract_no = certificate.getContract_no();
            invoice_no_pk = certificate.getInvoice_no_pk();
            place_of_inspection = certificate.getPlace_of_inspection();
            date_of_inspection = certificate.getDate_of_inspection();
            port_of_discharge = certificate.getPort_of_discharge();
            marking_of_bag_byteArray = certificate.getMarking_of_bag();
            total_no_of_bags = certificate.getTotal_no_of_bags();
            gross_weight = certificate.getGross_weight();
            tare_weight = certificate.getTare_weight();
            net_weight = certificate.getNet_weight();
            cleanliness_statement = certificate.getCleanliness_statement();
            quality_statement = certificate.getQuality_statement();
            packing = certificate.getPacking();
            weight = certificate.getWeight();
            conclusion = certificate.getConclusion();
        }

        // Getting data from QualityCheck table
        QualityCheck qualityCheck = handler.getSingleQualityCheck(invoice_no_pk);
        if (qualityCheck != null) {
            category = qualityCheck.getCategory();
            check_parameter = qualityCheck.getCheck_parameter();
            specification_in_parts = qualityCheck.getSpecification_in_parts();
            specification = qualityCheck.getSpecification();
            test_result = qualityCheck.getTest_result();
            extra_well_milled = qualityCheck.getExtra_well_milled();
        }

        // Getting data from Container table
        Container cn = handler.getSingleContainer(invoice_no_pk);
        if (cn != null) {
            container_no = cn.getContainer_no();
            container_size = cn.getContainer_size();
            no_of_bags = cn.getNo_of_bags();
            condition = cn.getCondition();
        }

//        editText1.setText(certificate_no);
//        editText2.setText(report_no);
//        editText3.setText(date);

        editText3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), listener, yyyy, mm, dd).show();
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                certificate_no = editText1.getText().toString().toUpperCase();
                report_no = editText2.getText().toString().toUpperCase();
                date = editText3.getText().toString();

                //##################### SENDING DATA TO NEXT FRAGMENT #####################
                C_02_Shipper frg = new C_02_Shipper();
                Bundle b = new Bundle();
                b.putString("certificate_no", certificate_no);
                b.putString("report_no", report_no);
                b.putString("date", date);
                b.putString("shipper_name", shipper_name);
                b.putString("shipper_address", shipper_address);
                b.putString("shipper_tel", shipper_tel);
                b.putString("shipper_fax", shipper_fax);
                b.putString("shipper_gst", shipper_gst);
                b.putString("notify_name", notify_name);
                b.putString("notify_address", notify_address);
                b.putString("notify_tel", notify_tel);
                b.putString("notify_fax", notify_fax);
                b.putString("description_of_goods", description_of_goods);
                b.putString("contract_no", contract_no);
                b.putString("invoice_no_pk", invoice_no_pk);
                b.putString("place_of_inspection", place_of_inspection);
                b.putString("date_of_inspection", date_of_inspection);
                b.putString("port_of_discharge", port_of_discharge);
                b.putByteArray("marking_of_bag_byteArray", marking_of_bag_byteArray);
                b.putString("total_no_of_bags", total_no_of_bags);
                b.putString("gross_weight", gross_weight);
                b.putString("tare_weight", tare_weight);
                b.putString("net_weight", net_weight);
                b.putString("cleanliness_statement", cleanliness_statement);
                b.putString("quality_statement", quality_statement);
                b.putString("packing", packing);
                b.putString("weight", weight);
                b.putString("conclusion", conclusion);
                b.putString("category", category);
                b.putString("check_parameter", check_parameter);
                b.putString("specification_in_parts", specification_in_parts);
                b.putString("specification", specification);
                b.putString("test_result", test_result);
                b.putString("extra_well_milled", extra_well_milled);
                b.putString("container_no", container_no);
                b.putString("container_size", container_size);
                b.putString("no_of_bags", no_of_bags);
                b.putString("condition", condition);
                frg.setArguments(b);

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.certificateDetails, frg);
                ft.addToBackStack("");
                ft.commit();
            }
        });
        return myView;
    }

    DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            editText3.setText(dayOfMonth + "-" + (month+1) + "-" + year);
            yyyy = year;
            mm = month;
            dd = dayOfMonth;
        }
    };
}