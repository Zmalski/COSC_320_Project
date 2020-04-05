package com.example.navdrawerexample.ui.myBookings;

import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.navdrawerexample.MainActivity;
import com.example.navdrawerexample.R;
import com.example.navdrawerexample.bookFacility;
import com.example.navdrawerexample.ui.bookingHome.bookingHomeViewModel;
import com.example.navdrawerexample.ui.classBooking.classBookingViewModel;

public class myBookings extends Fragment {

    private MyBookingsViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel =
                ViewModelProviders.of(this).get(MyBookingsViewModel.class);
        View root = inflater.inflate(R.layout.my_bookings_fragment, container, false);


        //The following code is for the booked areas section
        // Very hardcoded part for setting the edit buttons on areas
        Button[] editAreaButtons = new Button[]{root.findViewById(R.id.editAreaButton1), root.findViewById(R.id.editAreaButton2), root.findViewById(R.id.editAreaButton3)};
        for (int i = 0; i < 3; i++) { // This loop sets the tags for extracting data from buttons onClick() and set button listeners
            TextView tag = new TextView(getActivity());
            tag.setText("Pool,Sunday,16-18,lap lanes");
            if (i == 1)
                tag.setText("Gymnasium,Thursday,12-14,basketball");
            else if (i == 2)
                tag.setText("Gymnasium,Saturday,8-12,badminton");
            editAreaButtons[i].setTag(tag);
            editAreaButtons[i].setOnClickListener(new View.OnClickListener() {
                //Method splits the tag by comma and sends through the extras to the facility booking class
                public void onClick(View v) {
                    TextView tagText = (TextView) v.getTag();
                    String[] splitString = tagText.getText().toString().split(",");
                    Bundle extras = new Bundle();
                    extras.putString("place", splitString[0]);
                    extras.putString("day", splitString[1]);
                    extras.putString("time", splitString[2]);
                    extras.putString("activity", splitString[3]);
                    Intent facility = new Intent(getActivity(), bookFacility.class);
                    facility.putExtras(extras);
                    startActivity(facility);
                }
            });
        }

        // Very hardcoded part for setting the cancel buttons on the areas
        Button[] cancelAreaButtons = new Button[]{root.findViewById(R.id.cancelAreaButton1), root.findViewById(R.id.cancelAreaButton2), root.findViewById(R.id.cancelAreaButton3)};
        final LinearLayout[] entries = new LinearLayout[]{root.findViewById(R.id.entry3), root.findViewById(R.id.entry2), root.findViewById(R.id.entry1)};
        for (int i = 0; i < 3; i++) { // loop through buttons to set listeners
            final int finalI = i;
            cancelAreaButtons[i].setOnClickListener(new View.OnClickListener() {
                //Method splits the tag by comma and sends through the extras to the facility booking class
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setCancelable(true);
                    builder.setTitle("Confirmation");
                    builder.setMessage("Are you sure you would like to cancel this booking?");
                    builder.setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) { // If yes,  remove view.

                                    ((ViewManager) entries[finalI].getParent()).removeView(entries[finalI]);
                                    // NOTE: This is only a temporary removal, for prototype purposes
                                    Toast.makeText(getActivity(), "Booking Cancelled!",
                                            Toast.LENGTH_LONG).show();
                                }
                            });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // If user clicks no, do nothing
                        }
                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
        }
        //End of the booked areas section


        return root;

    }
}
