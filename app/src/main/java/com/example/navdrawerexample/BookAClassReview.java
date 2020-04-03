package com.example.navdrawerexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BookAClassReview extends AppCompatActivity {

    /**
     * -> Booking Review Screen <-
     *
     * Below is where all the Text View and etc is
     * initialized
     */
    TextView memberNameRev, memberNumberRev, classRev, selectedTimeTitle, disclaimerRev, memberNameRevEdit, memberNumberRevEdit, classRevEdit, selectedTimeRev, selectedDateRev;
    Button confirm, edit, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_a_class_review);

        /**
         * -> Below data is grabbed <-
         *
         * The Text View is initialized and the intents setText()
         * to the user input value when they typed in the edit text on
         * the classBookingFragment Screen
         */
        memberNameRevEdit = findViewById(R.id.memberNameRevEdit);

        memberNumberRevEdit = findViewById(R.id.memberNumberRevEdit);

        Intent i = getIntent();
        final String nameText = i.getStringExtra("textbox1");
        memberNameRevEdit.setText(nameText);

        Intent ii = getIntent();
        String numberText = ii.getStringExtra("textbox2");
        memberNumberRevEdit.setText(numberText);

        /**
         * -> Below more Variables are Initialized <-
         *
         * Below the variables are initialized and set to
         * their corresponding xml values and
         * also the spinner values are brought up by previous intents from the
         * last activity.
         *
         * this is done by setText(getIntent().setStringExtra("<string_value>"));
         * where it pulls from the previous page and sets the textbox to the value
         * set by the user.
         */
        memberNameRev = findViewById(R.id.memberNameRev);
        memberNameRev.setText("Name: ");

        memberNumberRev = findViewById(R.id.memberNumberRev);
        memberNumberRev.setText("Member ID: ");

        classRev = findViewById(R.id.classRev);
        classRev.setText("Class Selected: ");

        classRevEdit = findViewById(R.id.classRevEdit);
        classRevEdit.setText(getIntent().getStringExtra("selectedClass"));


        selectedTimeTitle = findViewById(R.id.selectedTimeTitle);
        selectedTimeTitle.setText("Selected Date and Time");

        selectedDateRev = findViewById(R.id.selectedDateRev);
        selectedDateRev.setText(getIntent().getStringExtra("selectedDate"));

        selectedTimeRev = findViewById(R.id.selectedTimeRev);
        selectedTimeRev.setText(getIntent().getStringExtra("selectedTime"));

        disclaimerRev = findViewById(R.id.DisclaimerRev);
        disclaimerRev.setText("*NOTICE*: All Classes Are Held In GYM 001");

        confirm = findViewById(R.id.confirmClass);

        edit = findViewById(R.id.editClass);

        cancel = findViewById(R.id.cancelClass);

        /**
         * -> Below Buttons function is set <-
         *
         * .setOnClickListener is used when the button is pressed it will do
         * something. For all buttons a Intent is used inorder to navigate
         * Activities or stop them.
         *
         * Toast messages are used to indicate if a booking has been canceled or
         * been confirmed
         *
         * .putExtra sends the values to the edit booking
         * activity in order keep the user entry since a database wasnt used.
         *
         */
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent endBook = new Intent(BookAClassReview.this, MainActivity.class);
                Toast.makeText(getApplicationContext(),nameText + " Canceled Class Booking",Toast.LENGTH_SHORT).show();
                startActivity(endBook);
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editBook = new Intent(BookAClassReview.this, BookAClassEdit.class);
                editBook.putExtra("textbox1", memberNameRevEdit.getText().toString());
                editBook.putExtra("textbox2", memberNumberRevEdit.getText().toString());
                startActivity(editBook);
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent confirmBook = new Intent(BookAClassReview.this, MainActivity.class);
                Toast.makeText(getApplicationContext(),"You Are Booked " + nameText,Toast.LENGTH_SHORT).show();
                startActivity(confirmBook);
            }
        });

    }

}
