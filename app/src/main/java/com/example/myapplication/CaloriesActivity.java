package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class CaloriesActivity extends AppCompatActivity {

    //Buttons
    private Button addCaloriesBtn;
    private Button changeCaloriesLimitBtn;
    private Button resetCurrentCaloriesBtn;

    //TextViews
    private TextView caloriesAmountTv;
    private TextView caloriesLimitTv;

    //Tiet's
    private TextInputEditText initialCaloriesTiet;
    private TextInputEditText caloriesLimitTiet;


    public int caloriesLimitInt;
    public int caloriesAmountInt;

    //Constants to Sharred Preferences
    public static final String SHARED_PREFS_LIMIT = "sharedPrefs";
    public static final String SHARED_PREFS_CURRENT_CALORIES = "sharedPrefs_1";
    public static final String TEXTCURRENTCALORIES = "text";
    public static final String TEXTLIMITCALORIES = "text_1";

    private String SP_LimitOfCalories;
    private String SP_CurrentCalories;



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
                saveDataCurrentCalories();

            }
        });
        caloriesAmountTv = (TextView) findViewById(R.id.currentCalories);
        caloriesLimitTv = (TextView) findViewById(R.id.caloriesLimitTv);

        //Edit your daily limit
        caloriesLimitTiet = findViewById(R.id.caloriesLimitTiet);


        //Reset o calories
        resetCurrentCaloriesBtn = findViewById(R.id.reset_current_calories_btn);
        resetCurrentCaloriesBtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                resetCurrentCalories();
            }
        });

        changeCaloriesLimitBtn = findViewById(R.id.change_calories_limit_btn);
        changeCaloriesLimitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeLimit();
                updateLimit();
                saveDataCaloriesLimit();
            }
        });

        loadDataCaloriesLimit();
        updateViewCaloriesLimit();
        loadDataCurrentCalories();
        updateCurrentCalories();
    }




    //Functions responsible for adding calories to current state
    private void updateCaloriesAmount() {
        caloriesAmountTv.setText(String.format("%s", caloriesAmountInt));
//        int currentCalories = Integer.parseInt(SP_CurrentCalories);
//        int LimitOfCalories = Integer.parseInt(SP_LimitOfCalories);
//            if(currentCalories>LimitOfCalories) {
//                Toast.makeText(this, "You exceed caloric demand! Eat less if you want to lose weight", Toast.LENGTH_LONG).show();
//           }
    }
    public void addCalories()
    {
        Editable value = initialCaloriesTiet.getText();
        if(value != null && !value.toString().isEmpty())
        {
            int sumToAdd = Integer.parseInt(value.toString());
            int i = Integer.parseInt(SP_CurrentCalories);

            if(caloriesAmountInt == 0) {
                caloriesAmountInt = caloriesAmountInt + sumToAdd + i;
            }
            else
            {
                caloriesAmountInt = sumToAdd + caloriesAmountInt;
            }
            return;
        }
        caloriesAmountInt = 0;
    }

    //Functions responsible for changing limits of calories
    private void updateLimit()
    {
        caloriesLimitTv.setText((String.format("%s", caloriesLimitInt)));

    }
    private void changeLimit()
    {
        Editable value = caloriesLimitTiet.getText();

        if(value !=null && !value.toString().isEmpty())
        {
            int limit = Integer.parseInt(value.toString());
            caloriesLimitInt = limit;
            return;
        }
        caloriesLimitInt = 0;
    }

    public void saveDataCaloriesLimit()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_LIMIT, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXTLIMITCALORIES, String.valueOf(caloriesLimitInt));
        editor.apply();

    }

    public void loadDataCaloriesLimit()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_LIMIT, MODE_PRIVATE);
        SP_LimitOfCalories = sharedPreferences.getString(TEXTLIMITCALORIES, "0");


    }

    public void updateViewCaloriesLimit()
    {
        caloriesLimitTv.setText(SP_LimitOfCalories);
    }


    public void saveDataCurrentCalories()
    {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_CURRENT_CALORIES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXTCURRENTCALORIES, String.valueOf(caloriesAmountInt));
        editor.apply();

    }

    public void loadDataCurrentCalories()
    {

        SharedPreferences sharedPreferences= getSharedPreferences(SHARED_PREFS_CURRENT_CALORIES, MODE_PRIVATE);
        SP_CurrentCalories = sharedPreferences.getString(TEXTCURRENTCALORIES, "0");

    }

    public void updateCurrentCalories()
    {
        caloriesAmountTv.setText(SP_CurrentCalories);
    }


    public void resetCurrentCalories()
    {
        final SharedPreferences sharedPrefs_1 = getSharedPreferences(SHARED_PREFS_CURRENT_CALORIES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs_1.edit();
        editor.clear().apply();
        SP_CurrentCalories = sharedPrefs_1.getString(TEXTCURRENTCALORIES, "0");
        caloriesAmountInt = 0;
        caloriesAmountTv.setText(SP_CurrentCalories);
    }

}
