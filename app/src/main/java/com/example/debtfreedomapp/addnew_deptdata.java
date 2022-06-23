package com.example.debtfreedomapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.example.debtfreedomapp.databinding.ActivityAddnewDeptdataBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class addnew_deptdata extends AppCompatActivity {

    ActivityAddnewDeptdataBinding binding;
    ImageButton arrow_debtDetails;
    DatabaseReference mDatabase;
    LinearLayout hiddenView_debtDetails;
    final ArrayList<User> messagesModules = new ArrayList<>();
    setdataAdapter adapter;

    AutoCompleteTextView actv_categoryDropdown;
    AutoCompleteTextView reninderDropDown;

    String[] categories = {
            "Credit Card",
            "Auto Loan",
            "Student Loan",
            "Medicine Loan",
            "Mortgage",
            "Personal Loan",
            "TAXES",
            "Utilites anad Bills",
            "Overdraft",
            "Others"

    };

    String[] reminder = {
            "5 days life",
            "10 days life",
            "15 days life",
            "20 days life",
            "25 days life",
            "30 days life"

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddnewDeptdataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mDatabase = FirebaseDatabase.getInstance().getReference();

        actv_categoryDropdown = findViewById(R.id.category);
        reninderDropDown = findViewById(R.id.remdate);

        arrow_debtDetails = findViewById(R.id.arrow_debtDetails);
        hiddenView_debtDetails = findViewById(R.id.hiddenView_debtDetails);


        ArrayAdapter<String> categoryAdapter = new ArrayAdapter(addnew_deptdata.this, R.layout.list_item, categories);
        actv_categoryDropdown.setAdapter(categoryAdapter);

        ArrayAdapter<String> reminderAdapter = new ArrayAdapter(addnew_deptdata.this, R.layout.list_item, reminder);
        reninderDropDown.setAdapter(reminderAdapter);


        EditText name = findViewById(R.id.name);
        EditText sbalance = findViewById(R.id.sbalance);
        EditText mpayment = findViewById(R.id.mpayment);
        EditText apr = findViewById(R.id.apr);
        EditText category = findViewById(R.id.category);
        EditText paydate = findViewById(R.id.paydate);
        EditText remdate = findViewById(R.id.remdate);
        getdata();
        adapter = new setdataAdapter(messagesModules,this);
        binding.debtrecyclerview.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.debtrecyclerview.setLayoutManager(layoutManager);


        hiddenView_debtDetails.setVisibility(View.VISIBLE);
        binding.savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!binding.name.getText().toString().isEmpty() && !binding.sbalance.getText().toString().isEmpty() &&
                        !binding.mpayment.getText().toString().isEmpty() && !binding.apr.getText().toString().isEmpty())
                {
                    if (binding.checkBox.isChecked())
                    {
                        writeNewUser(FirebaseAuth.getInstance().getUid(),
                                name.getText().toString(),sbalance.getText().toString(),
                                mpayment.getText().toString(),apr.getText().toString(),
                                category.getText().toString(),paydate.getText().toString(),remdate.getText().toString());

                    }
                    else
                    {
                        binding.checkBox.setError("Agree check box");
                        binding.checkBox.requestFocus();

                    }




                }
                else if (binding.name.getText().toString().isEmpty() )
                {
                    binding.name.setError("Enter the Name");
                    binding.name.requestFocus();
                }
                else if (binding.sbalance.getText().toString().isEmpty() )
                {
                    binding.sbalance.setError("Required starting balance");
                    binding.sbalance.requestFocus();

                }
                else if (binding.mpayment.getText().toString().isEmpty() )
                {
                    binding.mpayment.setError("Enter the Minimum Payment");
                    binding.mpayment.requestFocus();

                }
                else if (binding.apr.getText().toString().isEmpty() )
                {
                    binding.apr.setError("Enter the APR");
                    binding.apr.requestFocus();

                }
                else if (binding.checkBox.getText().toString().isEmpty() )
                {
                    binding.checkBox.setError("Please Agree checkbox");
                    binding.checkBox.requestFocus();

                }
            }
        });


        arrow_debtDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (hiddenView_debtDetails.getVisibility() == View.VISIBLE) {


                    hiddenView_debtDetails.setVisibility(View.GONE);
                    arrow_debtDetails.setImageResource(R.drawable.right_arrow);

                }


                else {


                    hiddenView_debtDetails.setVisibility(View.VISIBLE);
                    arrow_debtDetails.setImageResource(R.drawable.down_arrow);

                }
            }
        });
    }

    public void getdata(){

        mDatabase.child("Users data").child(FirebaseAuth.getInstance().getUid()).child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    User user = dataSnapshot.getValue(User.class);
                    messagesModules.add(user);
                }
                adapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void writeNewUser(String userId, String name, String sbalance,String mpayment,String apr, String category,String paydate,String remdate) {
        User user = new User(name, sbalance,mpayment,apr,category,paydate,remdate);

        mDatabase.child("Users data").child(userId).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                displayAlertbox();
            }
        });
    }

    public void displayAlertbox()
    {
        new AlertDialog.Builder(this).setMessage("Successfully insert data in firebase ").setTitle("Thank You ")
                .setCancelable(true)
                .setNeutralButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton){
                                finish();
                            }
                        })
                .show();

    }
}

