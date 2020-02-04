package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ArchiveOfCaloriesActivity extends AppCompatActivity {

    public static final String SHARED_PREFS_LIMIT_CURRENT = "SpCurrentCaloriesLimitOfCalories";
    public static final String KEY_CURRENT_CALORIES = "current";;



    private TextView viewOfCurrentCaloriesTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive_of_calories);
        showValueOfCalories();
    }


    private void showValueOfCalories()
    {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String today = formatter.format(date);


        SharedPreferences sharedPreferences= getSharedPreferences(SHARED_PREFS_LIMIT_CURRENT, MODE_PRIVATE);
        String currentAmount = sharedPreferences.getString(KEY_CURRENT_CALORIES, "0");
        viewOfCurrentCaloriesTv = (TextView) findViewById(R.id.archiveViewOfCurrentCaloriesTv);
        String DateWithCalories = "That day "+today + " You ate " + currentAmount + " calories";
        viewOfCurrentCaloriesTv.setText(DateWithCalories);
    }
}
