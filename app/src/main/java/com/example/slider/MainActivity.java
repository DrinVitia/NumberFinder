package com.example.slider;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.content.res.ColorStateList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private TextView textView2;
    private TextView resultTextView;
    private int randomNumber;
    private final int MAX_PROGRESS = 100;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);
        textView2 = findViewById(R.id.textView2);
        resultTextView = findViewById(R.id.textView4);

        generateRandomNumber();
        seekBar.setThumbTintList(ColorStateList.valueOf(Color.parseColor("#1F1F47")));
        seekBar.setProgressBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#1F1F47")));


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                checkSeekBarProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        // Set NewNumber button click listener
        Button NewNumber = findViewById(R.id.button);
        NewNumber.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#1F1F47")));
        NewNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateRandomNumber();
                checkSeekBarProgress(seekBar.getProgress());
            }
        });
    }

    // Method to generate a new random number and update textView2
    private void generateRandomNumber() {
        randomNumber = new Random().nextInt(MAX_PROGRESS + 1);
        textView2.setText(String.valueOf(randomNumber));
    }

    // Method to check the proximity of the SeekBar's progress to the random number
    @SuppressLint("SetTextI18n")
    private void checkSeekBarProgress(int progress) {
        // Check if the random number is within the range of SeekBar's value (0 to 100)
        if (randomNumber >= 0 && randomNumber <= MAX_PROGRESS) {
            // Update the result text based on whether the SeekBar's value matches the random number
            if (progress == randomNumber) {
                resultTextView.setText("Selected correctly!");
            } else {
                resultTextView.setText("Not selected correctly");
            }
        } else {
            resultTextView.setText("Random number is not within the range of SeekBar (0 to 100)");
        }
    }
}
