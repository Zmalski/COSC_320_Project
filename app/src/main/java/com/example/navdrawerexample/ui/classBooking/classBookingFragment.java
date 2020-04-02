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
    /**
     * Class Booking Main Screen Done Jivraj Grewal.
     * Taskbar Done By Zach and Evan.
     * ---------------*-----------------
     *
     * Below initializing Text Views, Edit Text, Buttons, Spinners and String variables.
     */
    TextView membername, memberNumber, fitnessClassTitle, fitnessClassLabel;
    EditText editMemberName, editMemberNumber;

    Button nextbutton;
    Spinner fitnessClassSpinner;


    String SelectedClass = "";

    private classBookingViewModel classBookingViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Fragment code
        classBookingViewModel =
                ViewModelProviders.of(this).get(classBookingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_class_booking, container, false);
        // End of fragment code

        /**
         * -> Below is where all variables are linked with their xml parts. <-
         *
         * .setText() is used to change the text whenever the activity is started
         * and .findViewById() is used to link the TextViews and etc to their
         * corresponding xml parts.
         */

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

        /**
         * -> Here is where the spinner data is added in <-
         *
         * R.array links the String values to the spinner so
         * whenever the spinner is clicked it will show the String values.
         *
         * setOnItemSelectedListener is used to whenever the user clicks a
         * specific value and passes it to the next activity when clicked.
         */

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

        /**
         * -> Button.setOnClickListener <-
         *
         * This is where the data is passed to the
         * next activity when the button is clicked
         * using the intent button.
         */
        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextBut = new Intent(getActivity(), BookAClassTime.class);
                nextBut.putExtra("selectedClass", SelectedClass);
                nextBut.putExtra("textbox1", editMemberName.getText().toString());
                nextBut.putExtra("textbox2", editMemberNumber.getText().toString());
                startActivity(nextBut);
            }
        });



        return root;
    }
}
