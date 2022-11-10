package com.ciclabsindia.cic.certificateDetails;

import static android.app.Activity.RESULT_OK;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.ciclabsindia.cic.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class C_04_GoodsDescription extends Fragment {
    EditText editText1, editText2, editText3, editText4, editText5, editText6;
    Button btn_upload, btn_next;
    ImageView imageView;
    int d, m, y;
    Bitmap bmp;
    Uri imageURI = null;
    byte[] marking_of_bag_byteArray;
    String certificate_no, report_no, date, shipper_name, shipper_address, shipper_tel, shipper_fax,
            shipper_gst, notify_name, notify_address, notify_tel, notify_fax, description_of_goods,
            contract_no, invoice_no_pk, place_of_inspection, date_of_inspection, port_of_discharge,
            total_no_of_bags, gross_weight, tare_weight, net_weight, cleanliness_statement,
            quality_statement, packing, weight, conclusion, category, check_parameter,
            specification_in_parts, specification, test_result, extra_well_milled,
            container_no, container_size, no_of_bags, condition, old_invoice_no;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_c_04_goods_description, container, false);
        editText1 = myView.findViewById(R.id.editText1);
        editText2 = myView.findViewById(R.id.editText2);
        editText3 = myView.findViewById(R.id.editText3);
        editText4 = myView.findViewById(R.id.editText4);
        editText5 = myView.findViewById(R.id.editText5);
        editText6 = myView.findViewById(R.id.editText6);
        btn_upload = myView.findViewById(R.id.buttonUpload);
        imageView = myView.findViewById(R.id.imageView);
        btn_next = myView.findViewById(R.id.buttonNext);

        Calendar ca = Calendar.getInstance();
        d = ca.get(Calendar.DATE);
        m = ca.get(Calendar.MONTH);
        y = ca.get(Calendar.YEAR);

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
        old_invoice_no = invoice_no_pk;

        // Converting byte[] to Bitmap
        if (marking_of_bag_byteArray!=null)
            bmp = BitmapFactory.decodeByteArray(marking_of_bag_byteArray, 0, marking_of_bag_byteArray.length);

//        editText1.setText(description_of_goods);
//        editText2.setText(contract_no);
//        editText3.setText(invoice_no_pk);
//        editText4.setText(place_of_inspection);
//        editText5.setText(date_of_inspection);
//        editText6.setText(port_of_discharge);
        imageView.setImageBitmap(bmp);

        editText5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), listener, y, m, d).show();
            }
        });

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dlg = new Dialog(getActivity());
                dlg.setContentView(R.layout.custom_dialog_camera_gallery);
                dlg.setCanceledOnTouchOutside(false);
                dlg.show();

                ImageButton btn_cam = dlg.findViewById(R.id.imageButton1);
                ImageButton btn_gal = dlg.findViewById(R.id.imageButton2);

                btn_cam.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Opening Camera using Intent
                        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        Uri imagePath = createImage();
                        i.putExtra(MediaStore.EXTRA_OUTPUT, imagePath);
                        startActivityForResult(i, 101);
                        dlg.dismiss();
                    }
                });

                btn_gal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Opening Gallery using Intent
                        Intent i = new Intent();
                        i.setType("image/*");
                        i.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(i, 102);
                        dlg.dismiss();
                    }
                });
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                description_of_goods = editText1.getText().toString().toUpperCase();
                contract_no = editText2.getText().toString().toUpperCase();
                invoice_no_pk = editText3.getText().toString().toUpperCase();
                place_of_inspection = editText4.getText().toString().toUpperCase();
                date_of_inspection = editText5.getText().toString();
                port_of_discharge = editText6.getText().toString().toUpperCase();

                if (invoice_no_pk.isEmpty())
                    invoice_no_pk = String.valueOf(System.currentTimeMillis());

                //##################### SENDING DATA TO NEXT FRAGMENT #####################
                C_05_Quantity frg = new C_05_Quantity();
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
                b.putString("container_no",container_no );
                b.putString("container_size", container_size);
                b.putString("no_of_bags", no_of_bags);
                b.putString("condition", condition);
                b.putString("old_invoice_no", old_invoice_no);
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
            editText5.setText(dayOfMonth + "-" + (month+1) + "-" + year);
            y = year;
            m = month;
            d = dayOfMonth;
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK) {
            switch (requestCode) {
                case 101:
                    imageView.setImageURI(imageURI);
                    break;
                case 102:
                    imageURI = data.getData();
                    imageView.setImageURI(imageURI);
                    break;
            }
            // Uri to Bitmap
            try{bmp = MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(), imageURI);}
            catch (IOException e)   {e.printStackTrace();}

            // Bitmap to byte[]
            if (bmp!=null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.JPEG, 10, byteArrayOutputStream);
                marking_of_bag_byteArray = byteArrayOutputStream.toByteArray();
            }
        }
    }

    private Uri createImage()
    {   // Storing the image captured by Camera at "Pictures/CIC_Labs_India/Marking_of_Bags/" so that we can get its URI

        ContentResolver resolver = getActivity().getApplicationContext().getContentResolver();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
            imageURI = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);
        else
            imageURI = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        ContentValues contentValues = new ContentValues();
        contentValues.put(MediaStore.Images.Media.DISPLAY_NAME, "IMG_" + System.currentTimeMillis() + ".jpg");
        contentValues.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/" + "CIC Labs India/" + "Marking of Bags/");

        imageURI = resolver.insert(imageURI, contentValues);
        return imageURI;
    }
}