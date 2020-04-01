package com.example.navdrawerexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class bookFacility extends AppCompatActivity {

    TextView day;
    TextView time;
    TextView place;
    TextView activty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_facility);

        day = findViewById(R.id.day);
        time = findViewById(R.id.time);
        place = findViewById(R.id.place);
        activty = findViewById(R.id.activity);
        day.setText((String) getIntent().getExtras().get("day"));
        time.setText((String) getIntent().getExtras().get("time"));
        place.setText((String) getIntent().getExtras().get("place"));
        activty.setText((String) getIntent().getExtras().get("activity"));
    }
}
