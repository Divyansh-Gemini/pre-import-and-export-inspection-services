package com.ciclabsindia.cic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ciclabsindia.cic.adapter.RecyclerViewAdapterHistory;
import com.ciclabsindia.cic.adapter.RecyclerViewAdapterRecent;
import com.ciclabsindia.cic.database.DatabaseHandler;
import com.ciclabsindia.cic.model.Document;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    SearchView searchView;
    RecyclerView recyclerViewRecent, recyclerViewHistory;

    TextView newDraftText, newCertificateText;
    FloatingActionButton newDraftFAB, newCertificateFAB;
    ExtendedFloatingActionButton newDocFAB;
    ImageView no_doc_img, shape;
    Animation animation;

    DatabaseHandler handler;

    Integer[] icon = {R.drawable.image_certificate,
                      R.drawable.image_draft};
    ArrayList<String> doc_type = new ArrayList<String>();
    ArrayList<String> invoice_no = new ArrayList<String>();
    ArrayList<String> shipper_name = new ArrayList<String>();
    ArrayList<String> date_time = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_stylish);
        searchView = findViewById(R.id.searchView);
        recyclerViewRecent = findViewById(R.id.recyclerViewRecent);
        recyclerViewHistory = findViewById(R.id.recyclerViewHistory);
        newDraftText = findViewById(R.id.new_draft_text);
        newDraftFAB = findViewById(R.id.new_draft_fab);
        newCertificateText = findViewById(R.id.new_certificate_text);
        newCertificateFAB = findViewById(R.id.new_certificate_fab);
        newDocFAB = findViewById(R.id.new_doc_fab);
        no_doc_img = findViewById(R.id.imageView);
        shape = findViewById(R.id.shape);
        handler = new DatabaseHandler(HomeActivity.this);

        //##################### CODE FOR RECYCLER VIEW RECENT #####################
        List<Document> list_recent = handler.getAllDocuments();
        for (Document document : list_recent) {
            doc_type.add(document.getDoc_type());
            invoice_no.add(document.getInvoice_no_fk());
            shipper_name.add(document.getShipper_name_fk());
            date_time.add(document.getLast_edited_date_time_fk());
        }
        RecyclerViewAdapterRecent adapter_recent = new RecyclerViewAdapterRecent(this, icon, doc_type, invoice_no, shipper_name, date_time);
        recyclerViewRecent.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewRecent.setAdapter(adapter_recent);

        // To make the RecyclerView horizontally scrollable, uncomment the below statement
        recyclerViewRecent.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));


        //##################### CODE FOR RECYCLER VIEW HISTORY #####################
//        List<Document> list_history = handler.getAllDocuments();
//        for (Document document : list_history) {
//            doc_type.add(document.getDoc_type());
//            invoice_no.add(document.getInvoice_no_fk());
//            shipper_name.add(document.getShipper_name_fk());
//            date_time.add(document.getLast_edited_date_time_fk());
//        }
        RecyclerViewAdapterHistory adapter_history = new RecyclerViewAdapterHistory(this, icon, doc_type, invoice_no, shipper_name, date_time);
        recyclerViewHistory.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewHistory.setAdapter(adapter_history);



        if (invoice_no.size() == 0)
            no_doc_img.setVisibility(View.VISIBLE);
        else
            no_doc_img.setVisibility(View.INVISIBLE);


        //##################### CODE FOR SEARCH VIEW #####################
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchDocument(newText.trim().toUpperCase());
                return true;
            }
        });

        //##################### CODE FOR FLOATING ACTION BUTTON #####################
        shape.setVisibility(View.INVISIBLE);
        newDraftFAB.setVisibility(View.INVISIBLE);
        newCertificateFAB.setVisibility(View.INVISIBLE);
        newDraftText.setVisibility(View.INVISIBLE);
        newCertificateText.setVisibility(View.INVISIBLE);

        newDocFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (newDocFAB.isExtended()) {
                    newDocFAB.shrink();
                    shape.setVisibility(View.VISIBLE);
                    newDraftFAB.setVisibility(View.VISIBLE);
                    newCertificateFAB.setVisibility(View.VISIBLE);
                    newDraftText.setVisibility(View.VISIBLE);
                    newCertificateText.setVisibility(View.VISIBLE);

                    animation = AnimationUtils.loadAnimation(HomeActivity.this, R.anim.zoom_in);
                    animation.setDuration(100);
                    newDraftText.setAnimation(animation);
                    newDraftFAB.setAnimation(animation);
                    newCertificateText.setAnimation(animation);
                    newCertificateFAB.setAnimation(animation);

                    v.animate().setDuration(200)
                                .setListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        super.onAnimationEnd(animation);
                                    }
                                })
                                .rotation(45f);
                }
                else {
                    newDocFAB.extend();
                    shape.setVisibility(View.INVISIBLE);
                    newDraftFAB.setVisibility(View.INVISIBLE);
                    newCertificateFAB.setVisibility(View.INVISIBLE);
                    newDraftText.setVisibility(View.INVISIBLE);
                    newCertificateText.setVisibility(View.INVISIBLE);

                    animation = AnimationUtils.loadAnimation(HomeActivity.this, R.anim.zoom_out);
                    animation.setDuration(0);
                    newDraftText.setAnimation(animation);
                    newDraftFAB.setAnimation(animation);
                    newCertificateText.setAnimation(animation);
                    newCertificateFAB.setAnimation(animation);

                    v.animate().setDuration(200)
                                .setListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        super.onAnimationEnd(animation);
                                    }
                                })
                                .rotation(0f);
                }
            }
        });

        newDraftFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, UploadActivity.class);
                i.putExtra("doc_type", "draft");
                startActivity(i);
            }
        });

        newCertificateFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, UploadActivity.class);
                i.putExtra("doc_type", "certificate");
                startActivity(i);
            }
        });
    }

    //##################### CODE FOR SEARCHING #####################
    private void searchDocument(String newText) {
        RecyclerViewAdapterRecent adapter1 = new RecyclerViewAdapterRecent(this, icon, doc_type, invoice_no, shipper_name, date_time);
        recyclerViewRecent.setLayoutManager(new LinearLayoutManager(this));
        adapter1.clearList();
        recyclerViewRecent.setAdapter(adapter1);

        List<Document> list = handler.getSearchedDocuments(newText);
        for (Document document : list) {
            doc_type.add(document.getDoc_type());
            invoice_no.add(document.getInvoice_no_fk());
            shipper_name.add(document.getShipper_name_fk());
            date_time.add(document.getLast_edited_date_time_fk());
        }

        RecyclerViewAdapterRecent adapter2 = new RecyclerViewAdapterRecent(this, icon, doc_type, invoice_no, shipper_name, date_time);
        recyclerViewRecent.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewRecent.setAdapter(adapter2);
    }

    @Override
    protected void onRestart() {
        // To make the RecyclerView horizontally scrollable, uncomment the below statement
        recyclerViewRecent.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        super.onRestart();
    }

    @Override
    protected void onPostResume() {
        // To make the RecyclerView horizontally scrollable, uncomment the below statement
        recyclerViewRecent.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        super.onPostResume();
    }

    @Override
    protected void onStart() {
        // To make the RecyclerView horizontally scrollable, uncomment the below statement
        recyclerViewRecent.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        super.onStart();
    }

    //##################### CLOSING APP ON BACK PRESSED #####################
    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}