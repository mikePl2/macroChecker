package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MealNotificationActivity extends AppCompatActivity {
    EditText title, body, value;
    Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_notification_activity);
        title = (EditText) findViewById(R.id.notifyTitleET);
        body = (EditText) findViewById(R.id.notifyBodyET);
        value = (EditText) findViewById(R.id.notifyValueET);
        start = (Button) findViewById(R.id.countdownStartBtn);
    }
    public void countdownClick(View view) {
        long startTime = Long.parseLong(value.getText().toString());
        final String startT = value.getText().toString();
        startTime = startTime*1000;
        new CountDownTimer(startTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                title.setEnabled(false);
                body.setEnabled(false);
                start.setEnabled(false);
                value.setEnabled(false);
                value.setText(""+millisUntilFinished/1000);
            }
            @Override
            public void onFinish() {
                createNotifi();
                title.setEnabled(true);
                body.setEnabled(true);
                start.setEnabled(true);
                value.setEnabled(true);
                value.setText(startT);
            }
        }.start();
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void createNotifi()
    {
        Notification notifi = new Notification.Builder(this).
                setContentTitle(title.getText())
                .setContentText(body.getText())
                .setSmallIcon(R.drawable.ic_meal)
                .build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notifi);
    }
}
