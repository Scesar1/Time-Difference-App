package com.example.timedifference;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = "LOG";
    private SharedPreferences myPrefs;
    private Integer state;
    private RadioGroup rgClockMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = getApplicationContext();
        myPrefs = PreferenceManager.getDefaultSharedPreferences(context);


        Button calculateButton = findViewById(R.id.calcButton);
        rgClockMode = findViewById(R.id.toggle);

        rgClockMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case (R.id.military):
                        state = 1;
                        break;
                    case (R.id.standard):
                        state = 0;
                        break;
                    default:
                        break;
                }
            }
        });

        calculateButton.setOnClickListener(view -> startCalculateActivity());
    }

    public void startCalculateActivity() {
        Intent intent = new Intent(this, CalculateActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onStart() {
        super.onStart();

        state = myPrefs.getInt("toggleState", 1);
        rgClockMode = findViewById(R.id.toggle);
        if (state == 1) {
            rgClockMode.check(R.id.military);
        } else {
            rgClockMode.check(R.id.standard);
        }

    }

    @Override
    protected void onPause() {

        SharedPreferences.Editor peditor = myPrefs.edit();
        peditor.putInt("toggleState", state);
        peditor.apply();
        super.onPause();
    }

    @Override
    protected void onStop() {
        SharedPreferences.Editor peditor = myPrefs.edit();
        peditor.putInt("toggleState", state);
        peditor.apply();

        super.onStop();
    }

    @Override
    protected void onDestroy() {
        SharedPreferences.Editor peditor = myPrefs.edit();
        peditor.putInt("toggleState", state);
        peditor.apply();

        super.onDestroy();
    }
}