package com.ciclabsindia.cic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;


//      --> Code for Login is correct,
//          Just uncomment FirebaseAuthentication's dependency from build.gradle (module-level)
//          And Uncomment the commented code of this file


public class SignInActivity extends AppCompatActivity {
    TextInputLayout til1, til2;
    EditText editText1, editText2;
    Button btn;
    TextView skip;
//    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        til1 = findViewById(R.id.textInputLayout1);
        til2 = findViewById(R.id.textInputLayout2);
        editText1 = findViewById(R.id.editTextEmail);
        editText2 = findViewById(R.id.editTextPassword);
        btn = findViewById(R.id.buttonSignIn);
        skip = findViewById(R.id.textViewSkip);
//        auth = FirebaseAuth.getInstance();

        btn.setOnClickListener(v -> {
            String email = editText1.getText().toString();
            String password = editText2.getText().toString();

            if (email.isEmpty()){
                til2.setError(null);
                til1.setError("Enter Email Address");}
            else if (password.isEmpty()) {
                til1.setError(null);
                til2.setError("Enter Password");}
            else
            {
                til1.setError(null);
                til2.setError(null);
//                    auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                        @Override
//                        public void onSuccess(AuthResult authResult) {
//                            Toast.makeText(SignInActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
//                            Intent i = new Intent(SignInActivity.this, UploadActivity.class);
//                            startActivity(i);
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(SignInActivity.this, "Login Failed!!", Toast.LENGTH_SHORT).show();
//                        }
//                    });
            }
        });

        skip.setOnClickListener(v -> startActivity(new Intent(SignInActivity.this, UploadActivity.class)));
    }
}