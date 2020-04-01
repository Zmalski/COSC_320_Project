package com.example.navdrawerexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TrainerPageActivity extends AppCompatActivity {

    TextView tName, tDesc, tSpec;
    ImageView tImg;
    Button backButton, bookButton;
    String trainerNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_page);

        tName = findViewById(R.id.trainerName);
        tDesc = findViewById(R.id.trainerDescription);
        tSpec = findViewById(R.id.trainerSpecializations);
        tImg = findViewById(R.id.trainerImg);
        backButton = findViewById(R.id.trainerPageBackButton);
        bookButton = findViewById(R.id.trainerPageBookButton);

        //loading selected trainer's image
        try {
            trainerNum = getIntent().getStringExtra("trainerSelected");
            if (trainerNum.equals("1")) {
                tImg.setImageResource(R.drawable.trainer1);
                tSpec.setText("- Cardio and pacing \n- Track activities \n- Walking");
            } else if (trainerNum.equals("2")) {
                tImg.setImageResource(R.drawable.trainer2);
                tSpec.setText("- Weight training\n- Abdominal Workouts \n- Bodybuilding");
            } else {
                tImg.setImageResource(R.drawable.trainer3);
                tSpec.setText("- CrossFit training\n- Extreme marathon training \n- Squats");
            }
        } catch (Exception e) {
            //continue as normal
        }

        //loading selected trainer's credentials
        tName.setText(getIntent().getStringExtra("trainerNameSelected"));
        tDesc.setText(getIntent().getStringExtra("trainerDescSelected"));

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
                trainerBookLaunch();
            }
        });
    }

    public void backToTrainerList() {
        finish();
    }

    public void trainerBookLaunch() {
        Intent intent = new Intent(this, TrainerBookingActivity.class);
        if (trainerNum.equals("1")) {
            intent.putExtra("trainerSelected", "1");
            intent.putExtra("trainerNameSelected", tName.getText().toString());
        } else if (trainerNum.equals("2")) {
            intent.putExtra("trainerSelected", "2");
            intent.putExtra("trainerNameSelected", tName.getText().toString());
        } else {
            intent.putExtra("trainerSelected", "3");
            intent.putExtra("trainerNameSelected", tName.getText().toString());
        }
        startActivity(intent);
    }
}


