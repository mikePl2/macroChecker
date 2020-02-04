package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;

public class ArchiveOfCaloriesActivity extends AppCompatActivity {

    public static final String SHARED_PREFS_CURRENT_CALORIES = "sharedPrefs_1";
    public static final String TEXTCURRENTCALORIES = "text";



    private TextView viewOfCurrentCaloriesTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive_of_calories);
        showValueOfCalories();
    }


    private void showValueOfCalories()
    {
        SharedPreferences sharedPreferences= getSharedPreferences(SHARED_PREFS_CURRENT_CALORIES, MODE_PRIVATE);
        String currentAmount = sharedPreferences.getString(TEXTCURRENTCALORIES, "0");
        viewOfCurrentCaloriesTv = (TextView) findViewById(R.id.archiveViewOfCurrentCaloriesTv);
        viewOfCurrentCaloriesTv.setText(currentAmount);
    }
}
