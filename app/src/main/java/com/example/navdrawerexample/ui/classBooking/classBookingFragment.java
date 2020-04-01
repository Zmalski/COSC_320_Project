package com.example.navdrawerexample.ui.classBooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.navdrawerexample.BookAClassTime;
import com.example.navdrawerexample.R;

public class classBookingFragment extends Fragment {

    TextView membername, memberNumber, fitnessClassTitle, fitnessClassLabel;
    EditText editMemberName, editMemberNumber;

    Button nextbutton;
    Spinner fitnessClassSpinner;
    View fitnessClassView;

    String SelectedClass = "";

    private classBookingViewModel classBookingViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Fragment code
        classBookingViewModel =
                ViewModelProviders.of(this).get(classBookingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_class_booking, container, false);
        // End of fragment code


        membername = root.findViewById(R.id.MemberName);
        membername.setText("Member Name:");

        memberNumber = root.findViewById(R.id.MemberNumber);
        memberNumber.setText("Member Number:");

        fitnessClassLabel = root.findViewById(R.id.fitnessClassLabel);
        fitnessClassLabel.setText("Choose a Fitness Class Below:");

        fitnessClassTitle = root.findViewById(R.id.fitnessClassTitle);
        fitnessClassTitle.setText("Fitness Class Booking");

        editMemberName = root.findViewById(R.id.MemberNameEdit);

        editMemberNumber = root.findViewById(R.id.MemberNumberEdit);

        nextbutton = root.findViewById(R.id.fitnessClassNextButton);


        fitnessClassSpinner = root.findViewById(R.id.fitnessClassSpinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.fitnessClasses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fitnessClassSpinner.setAdapter(adapter);

        fitnessClassSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SelectedClass = fitnessClassSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextBut = new Intent(getActivity(), BookAClassTime.class);
                nextBut.putExtra("selectedClass", SelectedClass);
                nextBut.putExtra("textbox1", editMemberName.getText().toString());
                startActivity(nextBut);
            }
        });

        //selecting class from spinner


        return root;
    }
}
