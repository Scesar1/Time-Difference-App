package com.example.timedifference;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
import com.example.timedifference.Time;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class LearnEasyActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_easy);

        // Set retry and and next buttons to be invisible initially
        Button retryButton = findViewById(R.id.retryButton);
        retryButton.setVisibility(View.INVISIBLE);
        Button nextButton = findViewById(R.id.checkButton);
        nextButton.setVisibility(View.INVISIBLE);

        // Generate and display time
        Random random = new Random();
        Time startTime = new Time(random.nextInt(23), random.nextInt(59));
        Time endTime = new Time(random.nextInt(23), random.nextInt(59));
        TextView startTimeView = findViewById(R.id.textView);
        startTimeView.setText(startTime.toString());
        TextView endTimeView = findViewById(R.id.textView2);
        endTimeView.setText(endTime.toString());

        //Calculate Time difference
        int diffHour = endTime.subtract(startTime).getHour();

        // Find what button is correct
        int correctChoice = 0;

        if (diffHour < 8) {
            correctChoice = 1;
        } else if (diffHour >= 8 && diffHour <= 16) {
            correctChoice = 2;
        } else {
            correctChoice = 3;
        }

        // Process user selection, highlight selected choice
        ImageButton smallGlass = findViewById(R.id.smallHourGlass);
        ImageButton medGlass = findViewById(R.id.mediumHourGlass);
        ImageButton largeGlass = findViewById(R.id.largeHourGlass);

        smallGlass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
            int selectedGlass = 1;
            }
        });

        medGlass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                medGlass.getBackground().setColorFilter(0x77000000);
                int selectedGlass = 2;
            }
        });

        largeGlass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                int selectedGlass = 3;
            }
        });

        int choiceDiff = correctChoice - selectedGlass;




    }


}
