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

    /**
     * -> Below is the initializing of variables <-
     *
     * Text View and etc are named in order to link
     * them to the xml parts
     */
    TextView selectedClassView, selectedClassTitle, selectDateandTime, classDate, classTime, Disclaimer, TitleSelectedClassEdit, TitleSelectedClassEditNumber;
    Button timeClassNext, timeClassBack;
    String time = "";
    String date = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_a_class_time);

        // Below is where the EditText values are going to be
        // and the member name and id that was entered by the
        // user is going to go
        TitleSelectedClassEdit = findViewById(R.id.TitleSelectedClassEdit);
        TitleSelectedClassEditNumber = findViewById(R.id.TitleSelectedClassEditNumber);

        // Intents that catches the values entered by the
        // user and placed in the xml TextView
        Intent i = getIntent();
        String nameText = i.getStringExtra("textbox1");
        TitleSelectedClassEdit.setText(nameText);

        Intent ii = getIntent();
        String Number = ii.getStringExtra("textbox2");
        TitleSelectedClassEditNumber.setText(Number);

        /**
         * -> Below variables are linked to xml parts <-
         *
         * TextViews and etc are linked by the findViewById()
         * and setText() sets the value that should be shown to
         * the user
         */
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

        // Spinner String values are added in by
        // linking String values to the corresponding spinner
        // and it will show the user the options that can be clicked
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.ClassTimes, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classTimeSpinner.setAdapter(adapter2);

        /**
         * -> Below is .setOnItemSelectedListener part <-
         *
         * It is where the Spinner value chosen by the user and is passed
         * to the next activity and shown as the user selection
         */
        classTimeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              time = classTimeSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Spinner is linked to the xml values and
        // Spinner String values are added in by
        // linking String values to the corresponding spinner
        // and it will show the user the options that can be clicked
        final Spinner classDateSpinner = (Spinner) findViewById(R.id.classDateSpinner);

        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this, R.array.datespinar, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classDateSpinner.setAdapter(adapter3);

        /**
         * -> Below is .setOnItemSelectedListener part <-
         *
         * It is where the Spinner value chosen by the user and is passed
         * to the next activity and shown as the user selection
         */

        classDateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                date = classDateSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /**
         * -> Below is where buttons are initialized and data is passed <-
         *
         * The back and next button is initialized and the
         * setOnClickListener() is where when the button is clicked it will start the next
         * activity or finish the activity and Intents will aid in passing
         * the data entered by the user.
         */
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

    //will end the activity that is running
    public void backToClassBooking(){
        finish();
    }
}
