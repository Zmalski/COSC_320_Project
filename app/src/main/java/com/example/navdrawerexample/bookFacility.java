package com.example.navdrawerexample;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class bookFacility extends AppCompatActivity {

    TextView day;
    TextView time;
    TextView facility;
    TextView area;
    LinearLayout scrollLayout;
    Boolean scrollPopulated;
    ScrollView scrollArea;
    RelativeLayout innerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_facility);

        // Initialize views & variables
        day = findViewById(R.id.day);
        time = findViewById(R.id.time);
        facility = findViewById(R.id.place);
        area = findViewById(R.id.activity);
        scrollLayout = findViewById(R.id.scrollLayout);
        scrollArea = findViewById(R.id.scroller);
        //Initially invisible
        scrollArea.setVisibility(View.INVISIBLE);
        final TextView equipTitle = findViewById(R.id.equipTitle);
        equipTitle.setVisibility(View.INVISIBLE);
        final TextView finePrint = findViewById(R.id.finePrint);
        finePrint.setVisibility(View.INVISIBLE);
        final Switch equipSwitch = findViewById(R.id.equipSwitch);
        final Switch ballBirdieSwitch = findViewById(R.id.ballSwitch);
        final TextView racketText = findViewById(R.id.racketText);
        final TextView shoes_lifejacketText = findViewById(R.id.shoes_lifejacketText);
        final Spinner racketSpinner = findViewById(R.id.racketSpinner);
        final Spinner shoesSpinner = findViewById(R.id.shoesSpinner);
        final Spinner numPeople = findViewById(R.id.numPeopleSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.numberPeople, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numPeople.setAdapter(adapter);
        ArrayAdapter<CharSequence> equipAdapter = ArrayAdapter.createFromResource(this,
                R.array.equipment, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        racketSpinner.setAdapter(equipAdapter);
        shoesSpinner.setAdapter(equipAdapter);
        scrollPopulated = false;

        // Set time to a more readable format
        String t = (String) getIntent().getExtras().get("time");
        String[] splitTime = t.split("-");
        t = splitTime[0] + ":00 - " + splitTime[1] + ":00";

        // Capitalize first letter of the area
        String a = (String) getIntent().getExtras().get("activity");
        a = a.substring(0, 1).toUpperCase() + a.substring(1);

        final String bookTime = t;
        final String bookArea = a;
        final String bookDay = (String) getIntent().getExtras().get("day");
        final String bookFacility = (String) getIntent().getExtras().get("place");

        // Update Views
        day.setText(bookDay);
        time.setText(bookTime);
        facility.setText(bookFacility);
        area.setText(bookArea);

        // Set listener on the switch for rental equipment to dynamically populate scroll area
        equipSwitch.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            // Displays hidden rental equipment screen that changes depending on chosen activity
            public void onClick(View view) {
                if (equipSwitch.isChecked()) {
                    equipTitle.setVisibility(View.VISIBLE);
                    scrollArea.setVisibility(View.VISIBLE);
                    finePrint.setVisibility(View.VISIBLE);
                    if (!scrollPopulated) {
                        assert bookFacility != null;
                        if (bookFacility.toLowerCase().equals("gymnasium")) {
                            if (bookArea.toLowerCase().equals("volleyball")) {
                                racketText.setVisibility(View.GONE);
                                racketSpinner.setVisibility(View.GONE);
                                ballBirdieSwitch.setText("Volleyball: ");
                            } else if (bookArea.toLowerCase().equals("badminton")) {
                                ballBirdieSwitch.setText("BYOB (Bring Your Own Birdies?)");
                                ballBirdieSwitch.setChecked(true);
                            } else if (bookArea.toLowerCase().equals("basketball")) {
                                racketText.setVisibility(View.GONE);
                                racketSpinner.setVisibility(View.GONE);
                                ballBirdieSwitch.setText("Basketball: ");
                            }
                        } else if (bookFacility.toLowerCase().equals("pool")) {
                            racketText.setVisibility(View.GONE);
                            racketSpinner.setVisibility(View.GONE);
                            shoes_lifejacketText.setText("Number of lifejackets: ");
                            ballBirdieSwitch.setVisibility(View.GONE);
                        } else if (bookFacility.toLowerCase().equals("fitness room")) {
                            racketText.setVisibility(View.GONE);
                            racketSpinner.setVisibility(View.GONE);
                            ballBirdieSwitch.setVisibility(View.GONE);
                        }
                        scrollPopulated = true;
                    }
                } else {
                    equipTitle.setVisibility(View.INVISIBLE);
                    scrollArea.setVisibility(View.INVISIBLE);
                    finePrint.setVisibility(View.INVISIBLE);

                }
            }
        });

    }

    public void cancel(View view) {
        finish();
    }

    public void next(View view) {
        Intent notification = new Intent(bookFacility.this, notificationBooking.class);
        startActivity(notification);
        finish();
    }
}
