package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.charset.Charset;

public class MealsActivity extends AppCompatActivity {

    private TextView fiches;
    private String path = Environment.getExternalStorageDirectory().toString() + "/MacroChecker/";
    private String interlude = "\n\n\n----------------------------------------------------------------------------------\n\n\n";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiches);
        fiches = (TextView) findViewById(R.id.fichesTV);
        fiches.setText(getAllContent());
    }

    private String getAllContent()
        {
        try{
            File file = new File(path);
            String[] paths = file.list(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String filename) {
                    return filename.endsWith(".txt");
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
