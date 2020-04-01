package com.example.navdrawerexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TrainerBookingActivity extends AppCompatActivity {

    TextView tName;
    ImageView tImg;
    Spinner daySpinner, timeSpinner;
    Button backButton, bookButton;

    String trainerNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_booking);

        backButton = findViewById(R.id.trainerPageBackButton);
        bookButton = findViewById(R.id.trainerPageBookButton);

        tName = findViewById(R.id.trainerName);
        tImg = findViewById(R.id.trainerImg);

        tName.setText(getIntent().getStringExtra("trainerNameSelected"));

        daySpinner = findViewById(R.id.datespinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.datespinar, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(adapter);

        timeSpinner = findViewById(R.id.timespinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.timespinar, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSpinner.setAdapter(adapter2);

        //loading selected trainer's image
        trainerNum = getIntent().getStringExtra("trainerSelected");
        if (trainerNum.equals("1")) {
            tImg.setImageResource(R.drawable.trainer1);
        } else if (trainerNum.equals("2")) {
            tImg.setImageResource(R.drawable.trainer2);
        } else {
            tImg.setImageResource(R.drawable.trainer3);
        }

        //loading selected trainer's credentials
        tName.setText(getIntent().getStringExtra("trainerNameSelected"));

        //if user pushes back or book
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToTrainerList();
            }
        });

        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trainerConfirmLaunch();
            }
        });
    }

    public void backToTrainerList() {
        finish();
    }

    public void trainerConfirmLaunch() {
        Intent intent = new Intent(this, TrainerConfirmActivity.class);
        if (trainerNum.equals("1")) {
            intent.putExtra("trainerSelected", "1");
            intent.putExtra("trainerNameSelected", tName.getText().toString());
            intent.putExtra("trainerTimeSelected", timeSpinner.getSelectedItem().toString());
            intent.putExtra("trainerSelectedDay", daySpinner.getSelectedItem().toString());
        } else if (trainerNum.equals("2")) {
            intent.putExtra("trainerSelected", "2");
            intent.putExtra("trainerNameSelected", tName.getText().toString());
            intent.putExtra("trainerTimeSelected", timeSpinner.getSelectedItem().toString());
            intent.putExtra("trainerSelectedDay", daySpinner.getSelectedItem().toString());
        } else {
            intent.putExtra("trainerSelected", "3");
            intent.putExtra("trainerNameSelected", tName.getText().toString());
            intent.putExtra("trainerTimeSelected", timeSpinner.getSelectedItem().toString());
            intent.putExtra("trainerSelectedDay", daySpinner.getSelectedItem().toString());
        }
        startActivity(intent);
    }

}
