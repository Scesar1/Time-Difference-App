package com.example.timedifference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CalculateActivity extends AppCompatActivity {
    private final String[] time = {"AM", "PM"};
    private SharedPreferences myPrefs;
    private Integer state;
    private EditText startHour, startMinute, endHour, endMinute;
    private TextView diffHour, diffMinute;
    private ImageButton calcButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        Intent intent = getIntent();

        Context context = getApplicationContext();
        myPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        state = myPrefs.getInt("toggleState", 1);

        Spinner timeSelector1 = findViewById(R.id.timeSelector1);
        timeSelector1.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, time));

        Spinner timeSelector2 = findViewById(R.id.timeSelector2);
        timeSelector2.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, time));
        timeSelector2.setSelection(1);

        startHour = findViewById(R.id.hourText);
        startMinute = findViewById(R.id.minText);
        endHour = findViewById(R.id.hourText2);
        endMinute = findViewById(R.id.minText2);

        calcButton = findViewById(R.id.hourglassCalc);



        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour1 = Integer.parseInt(startHour.getText().toString());
                int min1 = Integer.parseInt(startMinute.getText().toString());

                int hour2 = Integer.parseInt(endHour.getText().toString());
                int min2 = Integer.parseInt(endMinute.getText().toString());

                if (hour1 > 24 || hour1 < 0) {
                    startHour.setText("00");
                    Toast.makeText(CalculateActivity.this, "INVALID NUMBER", Toast.LENGTH_SHORT).show();
                }
                if (hour2 > 24 || hour1 < 0) {
                    endHour.setText("00");
                    Toast.makeText(CalculateActivity.this, "INVALID NUMBER", Toast.LENGTH_SHORT).show();
                }
                if (min1 > 59 || min1 < 0) {
                    startMinute.setText("00");
                    Toast.makeText(CalculateActivity.this, "INVALID NUMBER", Toast.LENGTH_SHORT).show();
                }
                if (min2 > 59 || min2 < 0) {
                    endMinute.setText("00");
                    Toast.makeText(CalculateActivity.this, "INVALID NUMBER", Toast.LENGTH_SHORT).show();
                }

                getTimeDifference();
            }
        });


        if (state == 1) {
            timeSelector1.setVisibility(View.GONE);
            timeSelector2.setVisibility(View.GONE);
        }
    }

    public void getTimeDifference() {
        startHour = findViewById(R.id.hourText);
        startMinute = findViewById(R.id.minText);
        endHour = findViewById(R.id.hourText2);
        endMinute = findViewById(R.id.minText2);

        int hour1 = Integer.parseInt(startHour.getText().toString());
        int min1 = Integer.parseInt(startMinute.getText().toString());

        int hour2 = Integer.parseInt(endHour.getText().toString());
        int min2 = Integer.parseInt(endMinute.getText().toString());
        //Converts the time to minutes
        Integer minTotal1 = hour1 * 60 + min1;
        Integer minTotal2 = hour2 * 60 + min2;

        Integer hourResult = Math.abs(minTotal1 - minTotal2) / 60;
        Integer minuteResult = Math.abs(minTotal1 - minTotal2) % 60;




        diffHour = findViewById(R.id.hourText3);
        diffMinute = findViewById(R.id.minText3);

        String hourText = String.format("%02d", hourResult);
        String minuteText = String.format("%02d", minuteResult);

        diffHour.setText(hourText);
        diffMinute.setText(minuteText);

    }



}