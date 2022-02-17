package com.example.timedifference;
import java.util.Random;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.SeekBar;
import android.widget.TextView;
import android.media.Image;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class LearnHardActivity extends AppCompatActivity {
    private SharedPreferences myPrefs;
    private TextView hourView, minView;
    private Integer state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_hard);

        Context context = getApplicationContext();
        myPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        int startTimeHour = myPrefs.getInt("startTimeHour", 0);
        int startTimeMinute = myPrefs.getInt("startTimeMinute", 0);

        int endTimeHour = myPrefs.getInt("endTimeHour", 0);
        int endTimeMinute = myPrefs.getInt("endTimeMinute", 0);

        state = myPrefs.getInt("toggleState", 0);
        Time startTime = new Time(startTimeHour, startTimeMinute);
        Time endTime = new Time(endTimeHour, endTimeMinute);



        TextView startTimeView = findViewById(R.id.textView9);
        TextView endTimeView = findViewById(R.id.textView10);
        if (state == 0) {
            startTimeView.setText(startTime.toString());
            endTimeView.setText(endTime.toString());
        } else {
            String startTimeString = String.format("%2d:%02d", startTime.getHour(), startTime.getMin());
            String endTimeString = String.format("%2d:%02d", endTime.getHour(), endTime.getMin());
            startTimeView.setText(startTimeString);
            endTimeView.setText(endTimeString);
        }


        SeekBar hourBar = findViewById(R.id.seekBar1);
        SeekBar minBar = findViewById(R.id.seekBar2);
        hourView = findViewById(R.id.textView13);
        minView = findViewById(R.id.textView14);

        hourBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            Integer pval = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pval = progress;
                hourView.setText(pval.toString());
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //write custom code to on start progress
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        minBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            Integer pval = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pval = progress;
                minView.setText(pval.toString());
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Button checkButton = findViewById(R.id.button);

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkButton.setVisibility(View.INVISIBLE);
                int selectedHour = hourBar.getProgress();
                int selectedMin = minBar.getProgress();

                TextView resultView = findViewById(R.id.textView15);

                if (selectedHour == (endTime.subtract(startTime)).getHour() && selectedMin == (endTime.subtract(startTime)).getMin()) {
                    resultView.setText("CORRECT!");
                } else {
                    resultView.setText("INCORRECT!");
                }
            }
        });


    }





}