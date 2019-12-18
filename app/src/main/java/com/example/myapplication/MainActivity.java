package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
        {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    public void click(View view){
        Intent intent;
        switch (view.getId())
        {
            case R.id.dailyCaloriesBtn:
                intent = new Intent(MainActivity.this, CaloriesActivity.class);
                startActivity(intent);
                break;
            case R.id.coachBtn:
                intent = new Intent(MainActivity.this, CoachActivity.class);
                startActivity(intent);
                break;
        }

    }
}
