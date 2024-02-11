package com.ciclabsindia.cic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.ciclabsindia.cic.draftDetails.D_01_DraftInfo;

public class DraftDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draft_details);

        // Getting Invoice No. from UploadActivity
        Bundle b = getIntent().getExtras();
        String invoice_no = b.getString("invoice_no");

        // Sending Invoice No. to D_01_DraftInfo
        D_01_DraftInfo frg = new D_01_DraftInfo();
        Bundle b2 = new Bundle();
        b2.putString("invoice_no", invoice_no);
        frg.setArguments(b2);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.draftDetails, frg);
        ft.commit();
    }
}