package com.ciclabsindia.cic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

public class EntryActivity extends AppCompatActivity {
    ImageButton btn_signin;
    TextView skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        btn_signin = findViewById(R.id.imageButtonSignIn);
        skip = findViewById(R.id.textViewSkip);

        btn_signin.setOnClickListener(v -> startActivity(new Intent(EntryActivity.this, SignInActivity.class)));

        skip.setOnClickListener(v -> startActivity(new Intent(EntryActivity.this, UploadActivity.class)));
    }
}