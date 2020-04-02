package com.example.navdrawerexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class BookAClassEdit extends AppCompatActivity {
    TextView editMemberName, editMemberNumber, editFitnessClass, editName, editNumber, editSelectedTime;
    String editedClass = "";
    String editedDate = "";
    String editedTime = "";
    Button backToReview, confirmEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_a_class_edit);

        editMemberName = findViewById(R.id.editMemberName);
        editMemberName.setText("Name: ");

        editMemberNumber = findViewById(R.id.editMemberNumber);
        editMemberNumber.setText("Member ID: ");

        editName = findViewById(R.id.editName);

        editNumber = findViewById(R.id.editNumber);

        Intent i = getIntent();
        String nameText = i.getStringExtra("textbox1");
        editName.setText(nameText);

        Intent ii = getIntent();
        String numberText = ii.getStringExtra("textbox2");
        editNumber.setText(numberText);


        editFitnessClass = findViewById(R.id.editFitnessClass);
        editFitnessClass.setText("Select Class: ");

        editSelectedTime = findViewById(R.id.editSelectedTime);
        editSelectedTime.setText("Change Your Time: ");

        final Spinner editClassSpinner = (Spinner) findViewById(R.id.editClassSpinner);

        ArrayAdapter<CharSequence> a4 = ArrayAdapter.createFromResource(this, R.array.fitnessClasses, android.R.layout.simple_spinner_item);
        a4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editClassSpinner.setAdapter(a4);

        editClassSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                editedClass = editClassSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final Spinner editDateSpinner = (Spinner) findViewById(R.id.editDateSpinner);

        ArrayAdapter<CharSequence> a5 = ArrayAdapter.createFromResource(this, R.array.datespinar, android.R.layout.simple_spinner_item);
        a5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editDateSpinner.setAdapter(a5);

        editDateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                editedDate = editDateSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final Spinner editTimeSpinner = (Spinner) findViewById(R.id.editTimeSpinner);

        ArrayAdapter<CharSequence> a6 = ArrayAdapter.createFromResource(this, R.array.ClassTimes, android.R.layout.simple_spinner_item);
        a6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editTimeSpinner.setAdapter(a6);

        editTimeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                editedTime = editTimeSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        backToReview = findViewById(R.id.backToReview);

        backToReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            backToRev();
            }
        });

        confirmEdit = findViewById(R.id.confirmEdit);

        confirmEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent confirmEdit = new Intent(BookAClassEdit.this, BookAClassEditedRev.class);
            confirmEdit.putExtra("editClass", editedClass);
            confirmEdit.putExtra("editDate", editedDate);
            confirmEdit.putExtra("editTime", editedTime);

                confirmEdit.putExtra("textbox1", editName.getText().toString());
                confirmEdit.putExtra("textbox2", editNumber.getText().toString());

                startActivity(confirmEdit);
            }
        });
    }
    public void backToRev(){
        finish();
    }




}
