package com.example.timedifference;
import java.util.Random;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_hard);

        /*
        Random random = new Random();
        Time startTime = new Time(random.nextInt(23), random.nextInt(59));
        Time endTime = new Time(random.nextInt(23), random.nextInt(59));
        */



        TextView startTimeView = findViewById(R.id.textView9);

        startTimeView.setText(startTime.toString());
        TextView endTimeView = findViewById(R.id.textView10);
        endTimeView.setText(endTime.toString());

        SeekBar hourBar = findViewById(R.id.seekBar1);
        SeekBar minBar = findViewById(R.id.seekBar2);
        TextView hourView = findViewById(R.id.textView13);
        TextView minView = findViewById(R.id.textView14);

        hourBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int pval = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pval = progress;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //write custom code to on start progress
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                hourView.setText(pval);
            }
        });

        minBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int pval = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pval = progress;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //write custom code to on start progress
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                minView.setText(pval);
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
