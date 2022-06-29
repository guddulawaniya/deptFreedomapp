package com.example.debtfreedomapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

    ImageButton arrow_debtDetails;
    DatabaseReference mDatabase;
    LinearLayout hiddenView_debtDetails;
    ArrayList<String> list = new ArrayList<>();
    AutoCompleteTextView actv_categoryDropdown;
    AutoCompleteTextView reninderDropDown;
    DrawerLayout drawerLayout;
    Dialog dialog;
    ImageView menuimage;
    String userchildid;
    Button close;
    Button okay;
    ImageButton arrow;
    ImageButton arrow2;
    ImageButton arrow3;
    ImageButton arrow4;
    LinearLayout hiddenView;
    LinearLayout hiddenView2;
    LinearLayout hiddenView3;
    LinearLayout hiddenView4;
    com.example.debtfreedomapp.databinding.ActivityAddnewDeptdataBinding binding;

    // Syntax of declaration of variable


    final Calendar myCalendar = Calendar.getInstance();

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
        binding = com.example.debtfreedomapp.databinding.ActivityAddnewDeptdataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

// Binding of variable  Syntax //


        mDatabase = FirebaseDatabase.getInstance().getReference();
        actv_categoryDropdown = findViewById(R.id.category);
        reninderDropDown = findViewById(R.id.remdate);
        arrow_debtDetails = findViewById(R.id.arrow_debtDetails);
        drawerLayout = findViewById(R.id.drawer);
        menuimage = findViewById(R.id.Menu);
        hiddenView_debtDetails = findViewById(R.id.hiddenView_debtDetails);
        arrow = findViewById(R.id.arrow_button);
        arrow2 = findViewById(R.id.arrow_button2);
        close = findViewById(R.id.close);
        arrow3 = findViewById(R.id.arrow_button3);
        arrow4 = findViewById(R.id.arrow_button4);
        hiddenView = findViewById(R.id.hidden_view);
        hiddenView2 = findViewById(R.id.hidden_view2);
        hiddenView3 = findViewById(R.id.hidden_view3);
        hiddenView4 = findViewById(R.id.hidden_view4);
         okay = findViewById(R.id.okaybuttondialogbox);

        EditText name = findViewById(R.id.name);
        EditText sbalance = findViewById(R.id.sbalance);
        EditText mpayment = findViewById(R.id.mpayment);
        EditText apr = findViewById(R.id.apr);
        EditText category = findViewById(R.id.category);
        TextView paydate = findViewById(R.id.paydate);
        EditText remdate = findViewById(R.id.remdate);


        ArrayAdapter<String> categoryAdapter = new ArrayAdapter(addnew_deptdata.this, R.layout.list_item, categories);
        actv_categoryDropdown.setAdapter(categoryAdapter);

        ArrayAdapter<String> reminderAdapter = new ArrayAdapter(addnew_deptdata.this, R.layout.list_item, reminder);
        reninderDropDown.setAdapter(reminderAdapter);

        //custom dialog box

        dialog = new Dialog(addnew_deptdata.this);
        dialog.setContentView(R.layout.custom_dialogbox);
//        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background_dialog_box));
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);


//        okay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//
//            }
//        });


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });

        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (hiddenView.getVisibility() == View.VISIBLE) {
                    hiddenView.setVisibility(View.GONE);
                    arrow.setImageResource(R.drawable.right_arrow);

                } else {
                    hiddenView.setVisibility(View.VISIBLE);
                    arrow.setImageResource(R.drawable.down_arrow);

                }
            }
        });

        arrow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // If the CardView is already expanded, set its visibility
                // to gone and change the expand less icon to expand more.
                if (hiddenView2.getVisibility() == View.VISIBLE) {

                    // The transition of the hiddenView is carried out
                    // by the TransitionManager class.
                    // Here we use an object of the AutoTransition
                    // Class to create a default transition.
                    //*TransitionManager.beginDelayedTransition(cardView,
                    // new AutoTransition());*//*
                    hiddenView2.setVisibility(View.GONE);
                    arrow2.setImageResource(R.drawable.right_arrow);

                }

                // If the CardView is not expanded, set its visibility
                // to visible and change the expand more icon to expand less.
                else {

                    //*TransitionManager.beginDelayedTransition(cardView,
                    // new AutoTransition());*//*
                    hiddenView2.setVisibility(View.VISIBLE);
                    arrow2.setImageResource(R.drawable.down_arrow);

                }
            }
        });


        arrow3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (hiddenView3.getVisibility() == View.VISIBLE) {


                    hiddenView3.setVisibility(View.GONE);
                    arrow3.setImageResource(R.drawable.right_arrow);

                } else {


                    hiddenView3.setVisibility(View.VISIBLE);
                    arrow3.setImageResource(R.drawable.down_arrow);

                }
            }
        });


        arrow4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (hiddenView4.getVisibility() == View.VISIBLE) {


                    hiddenView4.setVisibility(View.GONE);
                    arrow4.setImageResource(R.drawable.right_arrow);

                } else {


                    hiddenView4.setVisibility(View.VISIBLE);
                    arrow4.setImageResource(R.drawable.down_arrow);

                }
            }
        });


        menuimage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                drawerLayout.openDrawer(Gravity.RIGHT);
//                auth.signOut();
//                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
//                startActivity(intent);

            }
        });


        getdata();

//        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.list_item,list);
//        binding.deptlist.setAdapter(adapter);

        hiddenView_debtDetails.setVisibility(View.VISIBLE);


        binding.savebtn.setOnClickListener(new View.OnClickListener() {


            @Override

            public void onClick(View view) {
                if (!binding.name.getText().toString().isEmpty() && !binding.sbalance.getText().toString().isEmpty() &&
                        !binding.mpayment.getText().toString().isEmpty() && !binding.apr.getText().toString().isEmpty()) {


                    if (binding.checkBox.isChecked()) {

                        // data post to server Firebase from user input
                        userchildid = name.getText().toString() + sbalance.getText().toString() + mpayment.getText().toString() + apr.getText().toString();
                        writeNewUser(FirebaseAuth.getInstance().getUid(),
                                name.getText().toString(), sbalance.getText().toString(),
                                mpayment.getText().toString(), apr.getText().toString(),
                                category.getText().toString(), paydate.getText().toString(), remdate.getText().toString());
                        dialog.show();


                    } else
                    // check for condition error
                    {
                        displayAlertbox();

                    }


                }
                // if user can't input data to this form then validation
                else if (binding.name.getText().toString().isEmpty()) {
                    binding.name.setError("Enter the Name");
                    binding.name.requestFocus();
                } else if (binding.sbalance.getText().toString().isEmpty()) {
                    binding.sbalance.setError("Required starting balance");
                    binding.sbalance.requestFocus();

                } else if (binding.mpayment.getText().toString().isEmpty()) {
                    binding.mpayment.setError("Enter the Minimum Payment");
                    binding.mpayment.requestFocus();

                } else if (binding.apr.getText().toString().isEmpty()) {
                    binding.apr.setError("Enter the APR");
                    binding.apr.requestFocus();

                } else if (binding.checkBox.getText().toString().isEmpty()) {
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

                } else {

                    hiddenView_debtDetails.setVisibility(View.VISIBLE);
                    arrow_debtDetails.setImageResource(R.drawable.down_arrow);

                }
            }
        });


        DatePickerDialog.OnDateSetListener date = (view, year, month, day) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, day);
            updateLabel();
        };

        paydate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(addnew_deptdata.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }



    private void updateLabel() {
        String myFormat = "dd/mm/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        binding.paydate.setText(dateFormat.format(myCalendar.getTime()));

    }

    public void getdata() {

        mDatabase.child("Users data").child(FirebaseAuth.getInstance().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    getdatamodel user = dataSnapshot.getValue(getdatamodel.class);
                    TextView sdebtname = findViewById(R.id.setdeptname);
                    TextView sbalance = findViewById(R.id.setsbalance);
                    TextView remdate = findViewById(R.id.setremdate);

                    sdebtname.setText(user.getDeptname());
                    sbalance.setText(user.getSbalance());
                    remdate.setText(user.getRemdate());

//                    String text = user.getTextname();
//                    if (!user.getDeptname().isEmpty() && !user.getSbalance().isEmpty() && !user.getRemdate().isEmpty())
//                    {
//
//                        sdebtname.setText(user.getDeptname());
//                        sbalance.setText(user.getSbalance());
//                        remdate.setText(user.getRemdate());
//                    }
//                    else
//                    {
//                        sdebtname.setText("");
//                        sbalance.setText("");
//                        remdate.setText("");
//                    }


//                    list.add(text);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });


    }


    //
    public void writeNewUser(String userId, String name, String sbalance, String mpayment, String apr, String category, String paydate, String remdate) {
        User user = new User(name, sbalance, mpayment, apr, category, paydate, remdate);


        mDatabase.child("Users data").child(userId).child(userchildid).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(addnew_deptdata.this, "data insert successfully", Toast.LENGTH_SHORT).show();
//

            }
        });
    }


    public void displayAlertbox() {

        AlertDialog.Builder dialogalesrt = new AlertDialog.Builder(this);
        dialogalesrt.setTitle("Please Click The Check Box");
        dialogalesrt.setNegativeButton("ok", null);
        dialogalesrt.show();

    }


}

