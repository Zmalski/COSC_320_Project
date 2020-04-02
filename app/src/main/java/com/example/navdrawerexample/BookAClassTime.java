package com.example.navdrawerexample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BookAClassTime extends AppCompatActivity {

    TextView selectedClassView, selectedClassTitle, selectDateandTime, classDate, classTime, Disclaimer, TitleSelectedClassEdit, TitleSelectedClassEditNumber;
    Button timeClassNext, timeClassBack;
    String time = "";
    String date = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_a_class_time);
        TitleSelectedClassEdit = findViewById(R.id.TitleSelectedClassEdit);
        TitleSelectedClassEditNumber = findViewById(R.id.TitleSelectedClassEditNumber);

        Intent i = getIntent();
        String nameText = i.getStringExtra("textbox1");
        TitleSelectedClassEdit.setText(nameText);

        Intent ii = getIntent();
        String Number = ii.getStringExtra("textbox2");
        TitleSelectedClassEditNumber.setText(Number);


        selectedClassTitle = findViewById(R.id.TitleSelectedClass);
        selectedClassTitle.setText("Has selected:");

        selectedClassView = findViewById(R.id.selectedClass);
        selectedClassView.setText(getIntent().getStringExtra("selectedClass"));

        selectDateandTime = findViewById(R.id.selectDateandTime);
        selectDateandTime.setText("Select Date and Time");

        classDate = findViewById(R.id.classDate);
        classDate.setText("Date");

        classTime = findViewById(R.id.classTime);
        classTime.setText("Time");

        Disclaimer = findViewById(R.id.Disclaimer);
        Disclaimer.setText("*NOTICE* All Classes Will Last 1.5 hrs From Selected Time");

        final Spinner classTimeSpinner = (Spinner) findViewById(R.id.classTimeSpinner);


        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.ClassTimes, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classTimeSpinner.setAdapter(adapter2);

        classTimeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              time = classTimeSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final Spinner classDateSpinner = (Spinner) findViewById(R.id.classDateSpinner);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.datespinar, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classDateSpinner.setAdapter(adapter3);

        classDateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                date = classDateSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        timeClassBack = findViewById(R.id.fitnessTimeBack);

        timeClassBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            backToClassBooking();
            }
        });

        timeClassNext = findViewById(R.id.fitnesstimeNext);

        timeClassNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            Intent nextBtn = new Intent(BookAClassTime.this, BookAClassReview.class);
            nextBtn.putExtra("selectedClass", selectedClassView.getText().toString());

                nextBtn.putExtra("selectedDate", date);
                nextBtn.putExtra("selectedTime", time);

            nextBtn.putExtra("textbox1", TitleSelectedClassEdit.getText().toString());
            nextBtn.putExtra("textbox2", TitleSelectedClassEditNumber.getText().toString());
            startActivity(nextBtn);
            }
        });
    }

    public void backToClassBooking(){
        finish();
    }
}
