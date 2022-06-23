package com.example.debtfreedomapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.debtfreedomapp.databinding.ActivitySignupBinding;
import com.example.debtfreedomapp.models.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class signup_Activity extends AppCompatActivity {

    ActivitySignupBinding binding;
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    ProgressDialog Loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

         Loading = new ProgressDialog(signup_Activity.this);

        Loading.setTitle("Creating Account");
        Loading.setMessage("Please Wait While Creating Account");



        binding.signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                // Validation for fields of Signup form
                // Begin



                if (TextUtils.isEmpty(binding.etname.getText().toString())){
                    binding.etname.setError("Please Enter Name");
                    binding.etname.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(binding.etemail.getText().toString())){
                    binding.etemail.setError("Please Enter Email");
                    binding.etemail.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(binding.etpass.getText().toString())){
                    binding.etpass.setError("Please Enter Password");
                    binding.etpass.requestFocus();
                    return;
                }
                //End Validation for fields of Signup form


                Loading.show();              //progress bar will show when click on signup button

                signup();



            }




        });




        binding.LoginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(signup_Activity.this,Login_Activity.class);
                startActivity(intent);

            }
        });
    }
    private void signup(){

        mAuth.createUserWithEmailAndPassword
                        (binding.etemail.getText().toString(), binding.etpass.getText().toString()).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        Loading.dismiss();    // to stop Progress bar

                        if (task.isSuccessful()){

                            // Insert data using Constructor
                            // Begin
                            String id = task.getResult().getUser().getUid();



                            Users user = new Users(binding.etname.getText().toString(),
                                    binding.etemail.getText().toString(), binding.etpass.getText().toString(),id);
                            // End Insert data using Constructor




                            database.getReference().child("Users").child((id)).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {

                                    Toast.makeText(signup_Activity.this, "User Created Successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(signup_Activity.this, Login_Activity.class);
                                    startActivity(intent);
                                }
                            });



                        }

                        else {
                            Toast.makeText(signup_Activity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

}