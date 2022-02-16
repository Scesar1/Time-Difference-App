package com.example.timedifference;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import java.util.Random;
import com.example.timedifference.Time;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class LearnEasyActivity extends AppCompatActivity{
    private Integer state;
    private SharedPreferences myPrefs;
    private Integer choiceDiff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_easy);

        // Set retry and and next buttons to be invisible initially
        Button retryButton = findViewById(R.id.retryButton);
        retryButton.setVisibility(View.GONE);
        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setVisibility(View.GONE);

        Context context = getApplicationContext();
        myPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        state = myPrefs.getInt("toggleState", 0);
        TextView startTimeView = findViewById(R.id.textView);
        TextView endTimeView = findViewById(R.id.textView2);
        int correctChoice = 0;
        if (state == 0) {
            Random random = new Random();
            Time startTime = new Time(random.nextInt(23), random.nextInt(59));
            Time endTime = new Time(random.nextInt(23), random.nextInt(59));
            startTimeView.setText(startTime.toString());
            endTimeView.setText(endTime.toString());
            int diffHour = endTime.subtract(startTime).getHour();

            // Find what button is correct
            if (diffHour < 8) {
                correctChoice = 1;
            } else if (diffHour >= 8 && diffHour <= 16) {
                correctChoice = 2;
            } else {
                correctChoice = 3;
            }
        } else {
            Random random = new Random();
            int startTimeHour = random.nextInt(23);
            int startTimeMinute = random.nextInt(59);

            int endTimeHour = random.nextInt(23);
            int endTimeMinute = random.nextInt(59);


            startTimeView.setText(String.format("%2d:%02d", startTimeHour, startTimeMinute));
            endTimeView.setText(String.format("%2d:%02d", endTimeHour, endTimeMinute));

            int minTotal1 = startTimeHour * 60 + startTimeMinute;
            int minTotal2 = endTimeHour * 60 + endTimeMinute;

            int hourResult = Math.abs(minTotal1 - minTotal2) / 60;
            int minuteResult = Math.abs(minTotal1 - minTotal2) % 60;

            int diffTime = hourResult * 60 + minuteResult;
            if (diffTime < 480) {
                correctChoice = 1;
            } else if (diffTime <= 960) {
                correctChoice = 2;
            } else {
                correctChoice = 3;
            }
        }

        // Generate and display time


        //Calculate Time difference


        // Process user selection, highlight selected choice
        ImageButton smallGlass = findViewById(R.id.smallHourGlass);
        ImageButton medGlass = findViewById(R.id.mediumHourGlass);
        ImageButton largeGlass = findViewById(R.id.largeHourGlass);

        final int[] selectedGlass = {0};
        smallGlass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                smallGlass.setBackground(AppCompatResources.getDrawable(LearnEasyActivity.this, R.drawable.hourglass_select));
                medGlass.setBackgroundColor(Color.TRANSPARENT);
                largeGlass.setBackgroundColor(Color.TRANSPARENT);
                selectedGlass[0] = 1;
            }
        });

        medGlass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                medGlass.setBackground(AppCompatResources.getDrawable(LearnEasyActivity.this, R.drawable.hourglass_select));
                smallGlass.setBackgroundColor(Color.TRANSPARENT);
                largeGlass.setBackgroundColor(Color.TRANSPARENT);

                selectedGlass[0] = 2;
            }
        });

        largeGlass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                largeGlass.setBackground(AppCompatResources.getDrawable(LearnEasyActivity.this, R.drawable.hourglass_select));
                medGlass.setBackgroundColor(Color.TRANSPARENT);
                smallGlass.setBackgroundColor(Color.TRANSPARENT);
                selectedGlass[0] = 3;
            }
        });



        Button checkButton = findViewById(R.id.checkButton);
        int finalCorrectChoice = correctChoice;
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choiceDiff = finalCorrectChoice - selectedGlass[0];
                SharedPreferences.Editor peditor = myPrefs.edit();
                peditor.putInt("choice", choiceDiff);
                peditor.apply();
                startResultScreenActivity();
            }
        });



    }

    public void startResultScreenActivity() {
        Intent intent = new Intent(LearnEasyActivity.this, LearnEasyResultActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        if (choiceDiff == null) {
            choiceDiff = -1;
        }
        SharedPreferences.Editor peditor = myPrefs.edit();
        peditor.putInt("choice", choiceDiff);
        peditor.apply();
        super.onPause();
    }

    @Override
    protected void onStop() {
        if (choiceDiff == null) {
            choiceDiff = -1;
        }
        SharedPreferences.Editor peditor = myPrefs.edit();
        peditor.putInt("choice", choiceDiff);
        peditor.apply();

        super.onStop();
    }


}
