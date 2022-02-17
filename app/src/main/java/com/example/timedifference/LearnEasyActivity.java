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
import android.widget.ImageView;
import android.widget.TextView;

public class LearnEasyActivity extends AppCompatActivity{
    private Integer state;
    private SharedPreferences myPrefs;
    private Integer choiceDiff, correctChoice, selectedGlass;
    private Time startTime, endTime;
    private Button retryButton, nextButton, checkButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_easy);

        // Set retry and and next buttons to be invisible initially
        retryButton = findViewById(R.id.retryButton);
        retryButton.setVisibility(View.INVISIBLE);
        nextButton = findViewById(R.id.nextButton);
        nextButton.setVisibility(View.INVISIBLE);

        checkButton = findViewById(R.id.checkButton);
        checkButton.setEnabled(false);


        Context context = getApplicationContext();
        myPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        state = myPrefs.getInt("toggleState", 0);
        TextView startTimeView = findViewById(R.id.textView);
        TextView endTimeView = findViewById(R.id.textView2);
        correctChoice = 0;

        Random random = new Random();
        startTime = new Time(random.nextInt(23), random.nextInt(59));
        endTime = new Time(random.nextInt(23), random.nextInt(59));
        if (state == 0) {
            startTimeView.setText(startTime.toString());
            endTimeView.setText(endTime.toString());
        } else {
            String startTimeString = String.format("%2d:%02d", startTime.getHour(), startTime.getMin());
            String endTimeString = String.format("%2d:%02d", endTime.getHour(), endTime.getMin());
            startTimeView.setText(startTimeString);
            endTimeView.setText(endTimeString);
        }
        int diffHour = endTime.subtract(startTime).getHour();

        // Find what button is correct
        if (diffHour < 8) {
            correctChoice = 1;
        } else if (diffHour >= 8 && diffHour <= 16) {
            correctChoice = 2;
        } else {
            correctChoice = 3;
        }



        // Generate and display time


        //Calculate Time difference


        // Process user selection, highlight selected choice
        ImageButton smallGlass = findViewById(R.id.smallHourGlass);
        ImageButton medGlass = findViewById(R.id.mediumHourGlass);
        ImageButton largeGlass = findViewById(R.id.largeHourGlass);

        smallGlass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                smallGlass.setBackground(AppCompatResources.getDrawable(LearnEasyActivity.this, R.drawable.hourglass_select));
                medGlass.setBackgroundColor(Color.TRANSPARENT);
                largeGlass.setBackgroundColor(Color.TRANSPARENT);
                selectedGlass= 1;
                checkButton.setEnabled(true);
            }
        });

        medGlass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                medGlass.setBackground(AppCompatResources.getDrawable(LearnEasyActivity.this, R.drawable.hourglass_select));
                smallGlass.setBackgroundColor(Color.TRANSPARENT);
                largeGlass.setBackgroundColor(Color.TRANSPARENT);
                selectedGlass = 2;
                checkButton.setEnabled(true);
            }
        });

        largeGlass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                largeGlass.setBackground(AppCompatResources.getDrawable(LearnEasyActivity.this, R.drawable.hourglass_select));
                medGlass.setBackgroundColor(Color.TRANSPARENT);
                smallGlass.setBackgroundColor(Color.TRANSPARENT);
                selectedGlass = 3;
                checkButton.setEnabled(true);
            }
        });





        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView smallCorrectImage = findViewById(R.id.smallCorrectImage);
                ImageView smallWrongImage = findViewById(R.id.smallWrongImage);

                ImageView mediumCorrectImage = findViewById(R.id.mediumCorrectImage);
                ImageView mediumWrongImage = findViewById(R.id.mediumWrongImage);

                ImageView largeCorrectImage = findViewById(R.id.largeCorrectImage);
                ImageView largeWrongImage = findViewById(R.id.largeWrongImage);

                choiceDiff = correctChoice - selectedGlass;

                if (selectedGlass == 1 && correctChoice == 1) {
                    smallCorrectImage.setVisibility(View.VISIBLE);
                } else if (selectedGlass == 1) {
                    smallWrongImage.setVisibility(View.VISIBLE);
                }

                if (selectedGlass == 2 && correctChoice == 2) {
                    mediumCorrectImage.setVisibility(View.VISIBLE);
                } else if (selectedGlass == 2) {
                    mediumWrongImage.setVisibility(View.VISIBLE);
                }

                if (selectedGlass == 3 && correctChoice == 3) {
                    largeCorrectImage.setVisibility(View.VISIBLE);
                } else if (selectedGlass == 3) {
                    largeWrongImage.setVisibility(View.VISIBLE);
                }

                smallGlass.setEnabled(false);
                medGlass.setEnabled(false);
                largeGlass.setEnabled(false);


                checkButton.setVisibility(View.INVISIBLE);
                retryButton = findViewById(R.id.retryButton);
                nextButton = findViewById(R.id.nextButton);

                retryButton.setVisibility(View.VISIBLE);
                nextButton.setVisibility(View.VISIBLE);

            }
        });

        retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recreate();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLearnHardActivity();
            }
        });





    }

    private void startLearnHardActivity() {
        Intent intent = new Intent(LearnEasyActivity.this, LearnHardActivity.class);

        SharedPreferences.Editor peditor = myPrefs.edit();
        peditor.putInt("startTimeHour", startTime.getHour());
        peditor.putInt("startTimeMinute", startTime.getMin());
        peditor.putInt("endTimeHour", endTime.getHour());
        peditor.putInt("endTimeMinute", endTime.getMin());
        peditor.apply();

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
