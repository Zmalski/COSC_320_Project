package com.example.navdrawerexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TrainerConfirmActivity extends AppCompatActivity {

    TextView confirmText;
    Button backButton, confirmButton;
    String tName;
    String trainerNum;
    String confirmBlurb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_confirm);

        tName = getIntent().getStringExtra("trainerNameSelected");
        trainerNum = getIntent().getStringExtra("trainerSelected");

        confirmText = findViewById(R.id.confirmWith);
        confirmButton = findViewById(R.id.trainerConfirmButton);
        backButton = findViewById(R.id.trainerPageBackButton);

        confirmBlurb = "You are going to be booked with " + getIntent().getStringExtra("trainerNameSelected") + " at: " + getIntent().getStringExtra("trainerTimeSelected") + " this " + getIntent().getStringExtra("trainerSelectedDay");

        confirmText.setText(confirmBlurb);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trainerBookLaunch();
                finish();
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookingsLaunch();
            }
        });
    }

    public void trainerBookLaunch() {
        Intent intent = new Intent(this, TrainerBookingActivity.class);
        if (trainerNum.equals("1")) {
            intent.putExtra("trainerSelected", "1");
            intent.putExtra("trainerNameSelected", tName);
        } else if (trainerNum.equals("2")) {
            intent.putExtra("trainerSelected", "2");
            intent.putExtra("trainerNameSelected", tName);
        } else {
            intent.putExtra("trainerSelected", "3");
            intent.putExtra("trainerNameSelected", tName);
        }
        startActivity(intent);
    }

    public void bookingsLaunch() {
        Intent intent = new Intent(this, MainActivity.class);
        if (trainerNum.equals("1")) {
            intent.putExtra("trainerSelected", "1");
            intent.putExtra("trainerNameSelected", tName);
            intent.putExtra("trainerTimeSelected", getIntent().getStringExtra("trainerTimeSelected"));
            intent.putExtra("trainerDaySelected", getIntent().getStringExtra("trainerSelectedDay"));
        } else if (trainerNum.equals("2")) {
            intent.putExtra("trainerSelected", "2");
            intent.putExtra("trainerNameSelected", tName);
            intent.putExtra("trainerTimeSelected", getIntent().getStringExtra("trainerTimeSelected"));
        } else {
            intent.putExtra("trainerSelected", "3");
            intent.putExtra("trainerNameSelected", tName);
            intent.putExtra("trainerTimeSelected", getIntent().getStringExtra("trainerTimeSelected"));
            intent.putExtra("trainerDaySelected", getIntent().getStringExtra("trainerSelectedDay"));
        }
        startActivity(intent);
    }
}
