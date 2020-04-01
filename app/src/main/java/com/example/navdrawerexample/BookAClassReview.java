package com.example.navdrawerexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class BookAClassReview extends AppCompatActivity {

    TextView memberNameRev, memberNumberRev, classRev, selectedTimeTitle, disclaimerRev, memberNameRevEdit, memberNumberRevEdit, classRevEdit, selectedTimeRev, selectedDateRev;
    Button confirm, edit, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_a_class_review);


        memberNameRevEdit = findViewById(R.id.memberNameRevEdit);

        memberNumberRevEdit = findViewById(R.id.memberNumberRevEdit);

        Intent i = getIntent();
        String nameText = i.getStringExtra("textbox1");
        memberNameRevEdit.setText(nameText);

        Intent ii = getIntent();
        String numberText = ii.getStringExtra("textbox2");
        memberNumberRevEdit.setText(numberText);

        memberNameRev = findViewById(R.id.memberNameRev);
        memberNameRev.setText("Name:");

        memberNumberRev = findViewById(R.id.memberNumberRev);
        memberNumberRev.setText("Member ID:");

        classRev = findViewById(R.id.classRev);
        classRev.setText("Class Selected");

        selectedTimeTitle = findViewById(R.id.selectedTimeTitle);
        selectedTimeTitle.setText("Selected Date and Time");

        disclaimerRev = findViewById(R.id.DisclaimerRev);
        disclaimerRev.setText("*NOTICE*: All Classes Are Held In GYM 001");

        confirm = findViewById(R.id.confirmClass);

        edit = findViewById(R.id.editClass);

        cancel = findViewById(R.id.cancelClass);



    }
}
