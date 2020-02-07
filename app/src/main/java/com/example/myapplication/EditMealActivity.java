package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class EditMealActivity extends AppCompatActivity {

    EditText et;
    String text = "";
    Bundle bundle = new Bundle();
    private String path = Environment.getExternalStorageDirectory().toString() + "/MacroChecker";
    private final int MEMORY_ACCESS = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Aktywnosc", "OnCreate");
        setContentView(R.layout.activity_edit_meal);

        et = (EditText) findViewById(R.id.editText);
        et.setText(bundle.getString("et"));
        if(ActivityCompat.shouldShowRequestPermissionRationale(EditMealActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){}
        else
        {
            ActivityCompat.requestPermissions(EditMealActivity.this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, MEMORY_ACCESS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case MEMORY_ACCESS:
                if((grantResults.length>0)&&grantResults[0]== PackageManager.PERMISSION_GRANTED)
                {

                }
                else
                {
                    Toast.makeText(this, "Jesli nie zostanie wyrazona zgoda na dostep do pamieci, nie bedzie mozliwosci zapisania pliku", Toast.LENGTH_SHORT).show();
                }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_save){
            createDir();
            createFile();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onStart() {
        Log.d("Aktywnosc", "OnStart");
        super.onStart();

        et = (EditText) findViewById(R.id.editText);
        et.setText(bundle.getString("et")); //Przechowywanie tekstu w obiekcie bundle
    }


    @Override
    protected void onResume() {
        Log.d("Aktywnosc", "onResume");
        super.onResume();

        text = et.getText().toString();
        bundle.putString("et", et.getText().toString());
    }

    @Override
    protected void onPause() {
        Log.d("Aktywnosc", "OnStart");
        text = et.getText().toString();
        bundle.putString("et", et.getText().toString());
        super.onPause();
    }

    @Override
    protected void onRestart() {
        Log.d("Aktywnosc", "OnRestart");
        super.onRestart();
    }

    @Override
    protected void onStop() {
        Log.d("Aktywnosc", "OnStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("Aktywnosc", "OnDestroy");
        super.onDestroy();
    }
    public void createDir()
    {
        File folder = new File(path);
        if(!folder.exists())
        {
            try
            {
                folder.mkdir();
            }
            catch (Exception e)
            {
                Toast.makeText(this, "Problem z utworzeniem folderu", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void createFile()
    {
        File file = new File(path+"/"+"meals"+System.currentTimeMillis()+".txt");
        FileOutputStream fOut;
        OutputStreamWriter myOutWriter;
        try
        {
            fOut = new FileOutputStream(file);
            myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append(et.getText());
            myOutWriter.close();
            Toast.makeText(this, "Zapisano!!!", Toast.LENGTH_SHORT).show();
            fOut.close();
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Problem z zapisem pliku", Toast.LENGTH_SHORT).show();
        }
    }
}
