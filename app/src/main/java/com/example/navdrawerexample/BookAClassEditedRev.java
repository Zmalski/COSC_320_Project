package com.example.navdrawerexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BookAClassEditedRev extends AppCompatActivity {
    /**
     * -> Final Class Booking Screen <-
     *
     * Below variables are named
     */
    TextView memberNameEdited, memberNumberEdited, classEdited, selectedTimeTitleEdited, disclaimerEdited, memberNameEditedRev, memberNumberEditedRev, classEditedRev, selectedTimeEdited, selectedDateEdited;
    Button cancelClassEdited, editClassEdited, confirmClassEdited;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_a_class_edited_rev);

        /**
         * -> Variables are initialized <-
         *
         * Variables are initialized and are linked to their
         * xml parts done by findViewById();
         *
         * Intents are also set inorder to carry data from the
         * previous screen and set into a TextView by the
         * .setText();
         */

        memberNameEdited = findViewById(R.id.memberNameEdited);

        memberNumberEdited = findViewById(R.id.memberNumberEdited);


        memberNameEdited.setText("Name: ");
        memberNumberEdited.setText("Member ID: ");

        memberNameEditedRev = findViewById(R.id.memberNameEditedRev);

        memberNumberEditedRev = findViewById(R.id.memberNumberEditedRev);

        Intent i2 = getIntent();
        final String nameText = i2.getStringExtra("textbox1");
        memberNameEditedRev.setText(nameText);

        Intent ii2 = getIntent();
        String numberText = ii2.getStringExtra("textbox2");
        memberNumberEditedRev.setText(numberText);

        classEdited = findViewById(R.id.classEdited);
        classEdited.setText("Class Selected: ");

        classEditedRev = findViewById(R.id.classEditedRev);
        classEditedRev.setText(getIntent().getStringExtra("editClass"));


        selectedTimeTitleEdited = findViewById(R.id.selectedTimeTitleEdited);
        selectedTimeTitleEdited.setText("Selected Date and Time");

        /**
         * -> Spinner Values <-
         *
         * Spinner values entered by the user are found here
         * and caught from the previous activity using the
         * .setText(getIntent().getStringExtra()); which links the spinner by
         * String and sets it to the corresponding Text View
         *
         */
        selectedDateEdited = findViewById(R.id.selectedDateEdited);
        selectedDateEdited.setText(getIntent().getStringExtra("editDate"));

        selectedTimeEdited = findViewById(R.id.selectedTimeEdited);
        selectedTimeEdited.setText(getIntent().getStringExtra("editTime"));

        disclaimerEdited = findViewById(R.id.DisclaimerEdited);
        disclaimerEdited.setText("*NOTICE*: All Classes Are Held In GYM 001");

        /**
         * -> Buttons Are Set <-
         *
         * Buttons are initialized and set to the xml parts
         * using the findViewById();
         *
         * Also .setOnClickListener(); is used when the user clicked the
         * Button and either starts the activity or stops it
         *
         * In our case the buttons us .putExtra(); to pass values to the edit
         * screen, and the toast messages are used inorder to indicate
         * if the user has booked the class or canceled the booking all together
         *
         */
        confirmClassEdited = findViewById(R.id.confirmClassEdited);

        editClassEdited = findViewById(R.id.editClassEdited);

        cancelClassEdited = findViewById(R.id.cancelClassEdited);

        cancelClassEdited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent endBookEdited = new Intent(BookAClassEditedRev.this, MainActivity.class);
                Toast.makeText(getApplicationContext(),nameText + " Canceled Class Booking",Toast.LENGTH_SHORT).show();
                startActivity(endBookEdited);
            }
        });
        editClassEdited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editBookEdited = new Intent(BookAClassEditedRev.this, BookAClassEdit.class);
                editBookEdited.putExtra("textbox1", memberNameEditedRev.getText().toString());
                editBookEdited.putExtra("textbox2", memberNumberEditedRev.getText().toString());
                startActivity(editBookEdited);
            }
        });

        confirmClassEdited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent confirmBookEdited = new Intent(BookAClassEditedRev.this, MainActivity.class);
                Toast.makeText(getApplicationContext(),"You Are Booked " + nameText,Toast.LENGTH_SHORT).show();
                startActivity(confirmBookEdited);
            }
        });

    }


}
