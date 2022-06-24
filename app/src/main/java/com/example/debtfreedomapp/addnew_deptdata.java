package com.example.debtfreedomapp;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.debtfreedomapp.databinding.ActivityAddnewDeptdataBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class addnew_deptdata extends AppCompatActivity {

    ActivityAddnewDeptdataBinding binding;
    ImageButton arrow_debtDetails;
    DatabaseReference mDatabase;
    LinearLayout hiddenView_debtDetails;
    ArrayList<String> list = new ArrayList<>();
    AutoCompleteTextView actv_categoryDropdown;
    AutoCompleteTextView reninderDropDown;
    String userchildid;


    // Syntax of declaration of variable


    final Calendar myCalendar= Calendar.getInstance();

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

// Binding of variable  Syntax //

        mDatabase = FirebaseDatabase.getInstance().getReference();
        actv_categoryDropdown = findViewById(R.id.category);
        reninderDropDown = findViewById(R.id.remdate);
        arrow_debtDetails = findViewById(R.id.arrow_debtDetails);
        hiddenView_debtDetails = findViewById(R.id.hiddenView_debtDetails);

        EditText name = findViewById(R.id.name);
        EditText sbalance = findViewById(R.id.sbalance);
        EditText mpayment = findViewById(R.id.mpayment);
        EditText apr = findViewById(R.id.apr);
        EditText category = findViewById(R.id.category);
        EditText paydate = findViewById(R.id.paydate);
        EditText remdate = findViewById(R.id.remdate);


        ArrayAdapter<String> categoryAdapter = new ArrayAdapter(addnew_deptdata.this, R.layout.list_item, categories);
        actv_categoryDropdown.setAdapter(categoryAdapter);

        ArrayAdapter<String> reminderAdapter = new ArrayAdapter(addnew_deptdata.this, R.layout.list_item, reminder);
        reninderDropDown.setAdapter(reminderAdapter);

      // getdata();

        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.list_item,list);
        binding.deptlist.setAdapter(adapter);

        hiddenView_debtDetails.setVisibility(View.VISIBLE);


        binding.savebtn.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {
                if (!binding.name.getText().toString().isEmpty() && !binding.sbalance.getText().toString().isEmpty() &&
                        !binding.mpayment.getText().toString().isEmpty() && !binding.apr.getText().toString().isEmpty())
                {

                    if (binding.checkBox.isChecked())
                    {
                        // data post to server Firebase from user input
                        userchildid = name.getText().toString()+sbalance.getText().toString()+mpayment.getText().toString()+apr.getText().toString();
                        writeNewUser(FirebaseAuth.getInstance().getUid(),
                                name.getText().toString(),sbalance.getText().toString(),
                                mpayment.getText().toString(),apr.getText().toString(),
                                category.getText().toString(),paydate.getText().toString(),remdate.getText().toString());

                    }

                    else
                    // check for condition error
                    {
                        binding.checkBox.setError("Agree check box");
                        binding.checkBox.requestFocus();

                    }




                }
                // if user can't input data to this form then validation
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



        DatePickerDialog.OnDateSetListener date = (view, year, month, day) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH,month);
            myCalendar.set(Calendar.DAY_OF_MONTH,day);
            updateLabel();
        };

        paydate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(addnew_deptdata.this,date,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel() {
        String myFormat="dd/mm/yy";
        SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.US);
        binding.paydate.setText(dateFormat.format(myCalendar.getTime()));

    }

    public void getdata(){

        mDatabase.child("Users data").child(FirebaseAuth.getInstance().getUid()).child(userchildid).child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    getdatamodel user = dataSnapshot.getValue(getdatamodel.class);
                    TextView sdebtname = findViewById(R.id.setdeptname);
//                    TextView sbalance = findViewById(R.id.setsbalance);
//                    TextView remdate = findViewById(R.id.setremdate);

                    String text = user.getTextname();

                    sdebtname.setText(user.getTextname());
//                    sbalance.setText(user.getStarting_balance());
//                    remdate.setText(user.getRemdate());
                    list.add(text);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });


    }



    //
    public void writeNewUser(String userId, String name, String sbalance,String mpayment,String apr, String category,String paydate,String remdate) {
        User user = new User(name, sbalance,mpayment,apr,category,paydate,remdate);



        mDatabase.child("Users data").child(userId).child(userchildid).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                displayAlertbox();
            }
        });
    }

    public void displayAlertbox()
    {
        new AlertDialog.Builder(this).setMessage("Using for Debt Freedom").setTitle("Thank You ")
                .setCancelable(true)
                .setNeutralButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton){
                            }
                        })
                .show();

    }



}

