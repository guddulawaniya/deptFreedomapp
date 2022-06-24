package com.example.debtfreedomapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class profile_activity extends AppCompatActivity {


    ImageView addDebt,menuimage;
    DrawerLayout drawerLayout;
    FirebaseAuth auth;
    Button close;
    Button logout;
    ImageButton arrow;
    ImageButton arrow2;
    ImageButton arrow3;
    ImageButton arrow4;
    LinearLayout hiddenView;
    LinearLayout hiddenView2;
    LinearLayout hiddenView3;
    LinearLayout hiddenView4;
    //CardView cardView;

    AutoCompleteTextView currencySysmbolDropdown;


    String[] currency_symbols = {
            "$",
            "₹",
            "£",

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Syntax of Binding

        currencySysmbolDropdown = findViewById(R.id.actv_currencySymbol);
        menuimage = findViewById(R.id.Menu);
        addDebt  = findViewById(R.id.addDebt);
        drawerLayout = findViewById(R.id.drawer);
        close = findViewById(R.id.close);
        logout = findViewById(R.id.logout);
        arrow = findViewById(R.id.arrow_button);
        arrow2 = findViewById(R.id.arrow_button2);
        arrow3 = findViewById(R.id.arrow_button3);
        arrow4 = findViewById(R.id.arrow_button4);
        hiddenView = findViewById(R.id.hidden_view);
        hiddenView2 = findViewById(R.id.hidden_view2);
        hiddenView3 = findViewById(R.id.hidden_view3);
        hiddenView4 = findViewById(R.id.hidden_view4);


        ArrayAdapter<String> adapter = new ArrayAdapter(profile_activity.this, R.layout.list_item, currency_symbols);
        currencySysmbolDropdown.setAdapter(adapter);


        auth=FirebaseAuth.getInstance();

        menuimage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                drawerLayout.openDrawer(Gravity.RIGHT);
                /*auth.signOut();
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
*/
            }
        });



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

                }
                else {
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

                }


                else {


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

                }


                else {


                    hiddenView4.setVisibility(View.VISIBLE);
                    arrow4.setImageResource(R.drawable.down_arrow);

                }
            }
        });



        addDebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile_activity.this, addnew_deptdata.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                auth.signOut();
                Intent intent = new Intent(profile_activity.this, signup_Activity.class);
                startActivity(intent);

            }
        });


    }

}