package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;


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


    private int mCaloriesToAdd;
    private int mCaloriesTotal;
    private int mCaloriesLimit;



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

        setup();
    }

    private void setup() {
        // inicjalizujesz wszystko z preferencji oprocz wpisanego
        // ustawiasz widoki na podstawie wartosci
    }



    public void addCalories()
    {
        Editable value = initialCaloriesTiet.getText();
        if(value != null && !value.toString().isEmpty())
        {
            int sumToAdd = Integer.parseInt(value.toString());
            int newCaloriesAmount = sumToAdd + caloriesAmountInt;
            caloriesAmountInt = newCaloriesAmount;
            initialCaloriesTiet.setText("");

            if (caloriesAmountInt > caloriesLimitInt) {

                Toast.makeText(this, "You exceed caloric demand! Eat less if you want to lose weight", Toast.LENGTH_SHORT).show();
            }
            caloriesAmountTv.setText(String.valueOf(caloriesAmountInt));
        }
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
            Toast.makeText(this, "This field cannot be empty", Toast.LENGTH_SHORT).show();

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
        caloriesLimitInt = Integer.parseInt(SP_LimitOfCalories);
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
        String currentAmount = sharedPreferences.getString(TEXTCURRENTCALORIES, "0");
        caloriesAmountInt = Integer.parseInt(currentAmount);
    }

    public void updateCurrentCalories()
    {
        caloriesAmountTv.setText(String.valueOf(caloriesAmountInt));
    }
    public void resetCurrentCalories()
    {
        final SharedPreferences sharedPrefs_1 = getSharedPreferences(SHARED_PREFS_CURRENT_CALORIES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs_1.edit();
        editor.putString(TEXTCURRENTCALORIES, "0");
        editor.apply();
        caloriesAmountInt = 0;
        caloriesAmountTv.setText(String.valueOf(caloriesAmountInt));
        initialCaloriesTiet.setText("");
    }

}
