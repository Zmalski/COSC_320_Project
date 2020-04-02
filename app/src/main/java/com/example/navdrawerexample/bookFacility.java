package com.example.navdrawerexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

public class bookFacility extends AppCompatActivity {

    TextView day;
    TextView time;
    TextView facility;
    TextView area;
    LinearLayout scrollArea;
    Boolean scrollPopulated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_facility);

        // Initialize views
        day = findViewById(R.id.day);
        time = findViewById(R.id.time);
        facility = findViewById(R.id.place);
        area = findViewById(R.id.activity);
        scrollArea = findViewById(R.id.scrollLayout);
        scrollPopulated = false;

        // Set time to a more readable format
        String t = (String) getIntent().getExtras().get("time");
        String[] splitTime = t.split("-");
        t = splitTime[0] + ":00 - " + splitTime[1] + ":00";

        // Capitalize first letter of the area
        String a = (String) getIntent().getExtras().get("activity");
        a = a.substring(0, 1).toUpperCase() + a.substring(1);

        // Update Views
        day.setText((String) getIntent().getExtras().get("day"));
        time.setText(t);
        facility.setText((String) getIntent().getExtras().get("place"));
        area.setText(a);

        final Switch equipSwitch = findViewById(R.id.equipSwitch);

        equipSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (equipSwitch.isChecked()) {
                    //TODO: Display hidden Equipment rental screen
                    if (scrollPopulated == false) {
                        for (int i = 0; i < 50; i++) {
                            TextView textView = new TextView(bookFacility.this);
                            textView.setText("Test" + i);
                            scrollArea.addView(textView);
                        }
                        scrollPopulated = true;
                    }
                }

            }
        });

    }

    public void cancel(View view) {
        finish();
    }
}
