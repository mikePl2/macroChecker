package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.FileOutputStream;


public class CaloriesActivity extends AppCompatActivity {

    //Buttons
    private Button addCaloriesBtn;
    private Button changeCaloriesLimitBtn;

    //TextViews
    private TextView caloriesAmountTv;
    private TextView caloriesLimitTv;

    //Tiet's
    private TextInputEditText initialCaloriesTiet;
    private TextInputEditText caloriesLimitTiet;



    private int caloriesLimit;
    private int caloriesAmount;

    //Paths to files with current_calories and caloriesLimit//
    private String pathCurrentCalories = Environment.getExternalStorageDirectory().toString() + "/MacroChecker/CurrentCalories.txt";
    private String pathCaloriesLimit = Environment.getExternalStorageDirectory().toString() + "/MacroChecker/CaloriesLimit.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories);

        //Edit current calories
        initialCaloriesTiet = findViewById(R.id.initialCaloriesAmountTiet);
        addCaloriesBtn = (Button) findViewById(R.id.add_calories);
        addCaloriesBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                addCalories();
                updateCaloriesAmount();
            }
        });
        caloriesAmountTv = (TextView) findViewById(R.id.currentCalories);

        //Edit your daily limit
        caloriesLimitTiet = findViewById(R.id.caloriesLimitTiet);
        changeCaloriesLimitBtn = findViewById(R.id.change_calories_limit_btn);
        changeCaloriesLimitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeLimit();
                updateLimit();
            }
        });
        caloriesLimitTv = (TextView) findViewById(R.id.caloriesLimitTv);

    }

    //Functions responsible for adding calories to current state
    private void updateCaloriesAmount() {
        caloriesAmountTv.setText(String.format("%s calories", caloriesAmount));
            if(caloriesAmount>caloriesLimit) {
                Toast.makeText(this, "You exceed caloric demand! Eat less if you want to lose weight", Toast.LENGTH_LONG).show();
            }

    }
    public void addCalories()
    {
        Editable value = initialCaloriesTiet.getText();
        if(value != null && !value.toString().isEmpty())
        {
            int sumToAdd = Integer.parseInt(value.toString());
            caloriesAmount = sumToAdd + caloriesAmount;
            return;
        }
        caloriesAmount = 0;
    }



    //Functions responsible for changing limits of calories
    private void updateLimit()
    {
        caloriesLimitTv.setText((String.format("%s calories", caloriesLimit)));
    }

    private void changeLimit()
    {
        Editable value = caloriesLimitTiet.getText();

        if(value !=null && !value.toString().isEmpty())
        {
            int limit = Integer.parseInt(value.toString());
            caloriesLimit = limit;
            return;
        }
        caloriesLimit = 0;
    }


    // Application response in the case of different variants


}
