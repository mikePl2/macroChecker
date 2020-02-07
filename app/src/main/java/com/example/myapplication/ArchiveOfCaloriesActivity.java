package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ArchiveOfCaloriesActivity extends AppCompatActivity {


    private String path = Environment.getExternalStorageDirectory().toString() + "/MacroChecker/";
    private String interlude = "\n\n\n----------------------------------------------------------------------------------\n\n\n";



    private TextView viewOfCurrentCaloriesTv;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive_of_calories);
        viewOfCurrentCaloriesTv = (TextView) findViewById(R.id.archiveViewOfCurrentCaloriesTv);
        viewOfCurrentCaloriesTv.setText(getAllContentFromArchive());
    }

    private String getAllContentFromArchive()
    {
        try{
            File file = new File(path);
            String[] paths = file.list(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String filename) {
                    return filename.startsWith("archive");
                }
            });
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<paths.length; i++)
            {
                sb.append(Files.toString(new File(path+paths[i]), Charsets.UTF_8));
                sb.append(interlude);
            }
            return sb.toString();
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Problemy z plikami!!!", Toast.LENGTH_SHORT).show();
            return "";
        }

    }
}
