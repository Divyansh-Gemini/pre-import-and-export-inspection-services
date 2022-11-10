package com.ciclabsindia.cic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.ciclabsindia.cic.certificateDetails.C_01_CertificateInfo;
import com.ciclabsindia.cic.draftDetails.D_01_DraftInfo;

public class CertificateDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificate_details);

        // Getting Invoice No. from UploadActivity
        Bundle b = getIntent().getExtras();
        String invoice_no = b.getString("invoice_no");

        // Sending Invoice No. to C_01_CertificateInfo
        C_01_CertificateInfo frg = new C_01_CertificateInfo();
        Bundle b2 = new Bundle();
        b2.putString("invoice_no", invoice_no);
        frg.setArguments(b2);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.certificateDetails, frg);
        ft.commit();
    }
}