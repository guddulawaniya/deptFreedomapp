package com.example.debtfreedomapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.debtfreedomapp.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login_Activity extends AppCompatActivity {
    ActivityLoginBinding binding;
    EditText etemail,etpass;
    Button btnlogin;
    TextView signUpLink;
    FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;

    // declartion of variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Binding Syntax

        etemail=findViewById(R.id.etemail);
        etpass=findViewById(R.id.mypass);
        btnlogin=findViewById(R.id.btnlogin);
        signUpLink=findViewById(R.id.signUpLink);



//        String username  = database.getReference().child("Users").child("");
        auth=FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(Login_Activity.this);


        progressDialog.setTitle("Logging In");
        progressDialog.setMessage("Please Wait While Log in");


        binding.btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText useremail = findViewById(R.id.email);
                final EditText password = findViewById(R.id.mypass);
                if (useremail==null|| useremail.length()==0||useremail.equals(""))
                {
                    useremail.setError("Enter Email ID");
                    useremail.requestFocus();
                    return;
                }
                else if (password==null||password.length()==0||password.equals(""))
                {
                    password.setError("Create password");
                    password.requestFocus();
                    return;
                }
                else {
                    progressDialog.show();
                    auth.signInWithEmailAndPassword(binding.email.getText().toString(), binding.mypass.
                            getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()) {

                                Intent intent = new Intent(Login_Activity.this, profile_activity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(Login_Activity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }

            }
        });



        binding.signUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login_Activity.this, signup_Activity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(Login_Activity.this,profile_activity.class);
            startActivity(intent);
        }
    }


}