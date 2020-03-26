package com.example.cosc_320_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TrainerListActivity extends AppCompatActivity {

    //initialzing views to be linked
    TextView t1Name, t2Name, t3Name, t1Desc, t2Desc, t3Desc;
    Button backButton;
    View t1View, t2View, t3View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainer_list);

        //linking id values
        t1Name = findViewById(R.id.trainer1Name);
        t2Name = findViewById(R.id.trainer2Name);
        t3Name = findViewById(R.id.trainer3Name);

        t1Desc = findViewById(R.id.trainer1Desc);
        t2Desc = findViewById(R.id.trainer2Desc);
        t3Desc = findViewById(R.id.trainer3Desc);

        backButton = findViewById(R.id.trainerListBackButton);

        t1View = findViewById(R.id.trainer1View);
        t2View = findViewById(R.id.trainer2View);
        t3View = findViewById(R.id.trainer3View);

        //setting text and info
        t1Name.setText("Dinesh Gosh");
        t2Name.setText("Suzanne Reeves");
        t3Name.setText("Winona Rivers");

        t1Desc.setText("I specialize in cardio fitness, including running, sprints, track activities and walking");
        t2Desc.setText("My specializations lie in weightlifting, bodybuilding and core workouts");
        t3Desc.setText("I have been a Crossfit trainer for over 5 years and specialize in extreme workouts");

        //on clicking each trainer
        t1View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t1View.setAlpha((float) 0.75);

            }
        });

        t2View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t2View.setAlpha((float) 0.75);

            }
        });

        t3View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t3View.setAlpha((float) 0.75);

            }
        });


    }

    public void trainerPageLaunch() {
        Intent intent = new Intent(this, TrainerListActivity.class);
        startActivity(intent);
    }
}
