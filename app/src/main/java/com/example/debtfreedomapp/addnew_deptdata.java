package com.example.debtfreedomapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.debtfreedomapp.databinding.ActivityAddnewDeptdataBinding;

public class addnew_deptdata extends AppCompatActivity {

    ActivityAddnewDeptdataBinding binding;
    ImageButton arrow_debtDetails;
    LinearLayout hiddenView_debtDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddnewDeptdataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        arrow_debtDetails = findViewById(R.id.arrow_debtDetails);
        hiddenView_debtDetails = findViewById(R.id.hiddenView_debtDetails);


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
}