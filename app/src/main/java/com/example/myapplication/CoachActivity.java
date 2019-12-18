package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CoachActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach);


        FloatingActionButton addMeal = (FloatingActionButton) findViewById(R.id.addMealBtn);
        addMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CoachActivity.this, EditMealActivity.class);
                startActivity(intent);
            }
        });
    }
    public void coachClick(View view)
    {
        Intent intent = null;
        switch (view.getId())
        {
            case R.id.MealsBtn:
                intent = new Intent(CoachActivity.this, MealsActivity.class);
                startActivity(intent);
                break;
            case R.id.countdownBtn:
                intent = new Intent(CoachActivity.this, MealNotificationActivity.class);
                startActivity(intent);
                break;
        }
    }
}
