package com.example.navdrawerexample.ui.bookingHome;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.navdrawerexample.MainActivity;
import com.example.navdrawerexample.R;
import com.example.navdrawerexample.bookFacility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class bookingHomeFragment extends Fragment {

    area[] place = new area[7];
    TextView area1;
    TextView area2;
    TextView area3;

    int pos;
    int spot;
    int day;
    TextView[] grid = new TextView[18];
    Button book;
    String time;
    String availa = "";


    // This is used for fragment
    private bookingHomeViewModel bookingHomeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Fragment code
        bookingHomeViewModel =
                ViewModelProviders.of(this).get(bookingHomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //End of fragment code

//code to get current day of the week, where 'day' var is used to set datespinner
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        int today = calendar.get(Calendar.DAY_OF_WEEK);
        switch (today) {
            case Calendar.SUNDAY:
                day = 0;
                break;
            case Calendar.MONDAY:
                day = 1;
                break;
            case Calendar.TUESDAY:
                day = 2;
                break;
            case Calendar.WEDNESDAY:
                day = 3;
                break;
            case Calendar.THURSDAY:
                day = 4;
                break;
            case Calendar.FRIDAY:
                day = 5;
                break;
            case Calendar.SATURDAY:
                day = 6;
                break;
        }

        //creates array of girdx names
        String[] gridnames = new String[18];
        int x = 1;
        for (int i = 0; i < gridnames.length; i++) {
            gridnames[i] = "grid" + x;
            x++;
        }
        //associates all java textView Objects with the corresponding xml layout counterpart
        int temp;
        for (int i = 0; i < gridnames.length; i++) {
            temp = getResources().getIdentifier(gridnames[i], "id", getActivity().getPackageName());
            grid[i] = root.findViewById(temp);
        }

        book = root.findViewById(R.id.button4);
        area1 = root.findViewById(R.id.area1);
        area2 = root.findViewById(R.id.area2);
        area3 = root.findViewById(R.id.area3);

        spot = 0;


        //loading spinners with content from res
        final Spinner datespinner = root.findViewById(R.id.datespinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.datespinar, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        datespinner.setAdapter(adapter);

        final Spinner areaspinner = root.findViewById(R.id.areaspinner);
        ArrayAdapter<CharSequence> adapterTwo = ArrayAdapter.createFromResource(getActivity(), R.array.areaspinar, android.R.layout.simple_spinner_item);
        adapterTwo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        areaspinner.setAdapter(adapterTwo);

        //setting date spinner to display current day
        datespinner.setSelection(day);

        //creates simulated availability blocks
        createSchedules();

        // listener for book button, the spot var corresponds to the current area spinner selection and pos indicates the
        //respective area under each facility (ex spot = pool, pos = lap lanes)
        // each combination of pos and spot indicates a distinct choice which is used to set the temp var
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (availa.equals("BOOKED") || availa.equals("CLOSED")) {
                    Toast.makeText(getActivity(), "BLOCK NOT AVAILABLE", Toast.LENGTH_SHORT).show();
                } else {
                    String temp = "";
                    if (pos == 1 && spot == 0) {
                        temp = "free weights";
                    }
                    if (pos == 2 && spot == 0) {
                        temp = "spin zone";
                    }
                    if (pos == 3 && spot == 0) {
                        temp = "resistance";
                    }
                    if (pos == 1 && spot == 1) {
                        temp = "lap lanes";
                    }
                    if (pos == 2 && spot == 1) {
                        temp = "kids";
                    }
                    if (pos == 3 && spot == 1) {
                        temp = "hot spots";
                    }
                    if (pos == 1 && spot == 2) {
                        temp = "basketball";
                    }
                    if (pos == 2 && spot == 2) {
                        temp = "volleyball";
                    }
                    if (pos == 3 && spot == 2) {
                        temp = "badminton";
                    }
                    // the facility, specific area under the facility, time slot and day is passed to the book facility activity
                    Bundle extras = new Bundle();
                    extras.putString("place", areaspinner.getSelectedItem().toString());
                    extras.putString("day", datespinner.getSelectedItem().toString());
                    extras.putString("time", time);
                    extras.putString("activity", temp);
                    Intent facility = new Intent(getActivity(), bookFacility.class);
                    facility.putExtras(extras);
                    startActivity(facility);
                }
            }
        });

        //each grid textview (from table layout) is associated with a listener via a for loop
        //depending on the grid textview's position in the table its on click event will set the pos var
        // to the corresponding facility area (ex "free weights" or the column selection)
        // also by using the textviews relative position the time var is set to the corresponding time block on the table
        for (int i = 0; i < 18; i++) {
            final int finali = i;
            grid[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    grid[finali].setTextColor(Color.RED);
                    if (finali == 0 || finali == 3 || finali == 6 || finali == 9 || finali == 12 || finali == 15) {
                        pos = 1;
                    }
                    if (finali == 1 || finali == 4 || finali == 7 || finali == 10 || finali == 13 || finali == 16) {
                        pos = 2;
                    }
                    if (finali == 2 || finali == 5 || finali == 8 || finali == 11 || finali == 14 || finali == 17) {
                        pos = 3;
                    }
                    if (finali < 3) {
                        time = "8-10";
                    }
                    if (finali < 6 && finali > 2) {
                        time = "10-12";
                    }
                    if (finali < 9 && finali > 5) {
                        time = "12-14";
                    }
                    if (finali < 12 && finali > 8) {
                        time = "14-16";
                    }
                    if (finali < 15 && finali > 11) {
                        time = "14-18";
                    }
                    if (finali < 18 && finali > 14) {
                        time = "18-20";
                    }
                    availa = (String) grid[finali].getText();
                    for (int i = 0; i < 18; i++) {
                        if (i != finali) {
                            grid[i].setTextColor(Color.BLACK);
                        }
                    }
                }
            });
        }


        // listener for date spinner, for each selection a distinct table is loaded
        datespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (datespinner.getSelectedItem().toString()) {
                    case "Sunday":
                        day = 0;
                        for (int i = 0; i < 18; i++) {
                            grid[i].setText(place[day].areas[spot].day[i]);
                        }
                        break;
                    case "Monday":
                        day = 1;
                        for (int i = 0; i < 18; i++) {
                            grid[i].setText(place[day].areas[spot].day[i]);
                        }
                        break;
                    case "Tuesday":
                        day = 2;
                        for (int i = 0; i < 18; i++) {
                            grid[i].setText(place[day].areas[spot].day[i]);
                        }
                        break;
                    case "Wednesday":
                        day = 3;
                        for (int i = 0; i < 18; i++) {
                            grid[i].setText(place[day].areas[spot].day[i]);
                        }
                        break;
                    case "Thursday":
                        day = 4;
                        for (int i = 0; i < 18; i++) {
                            grid[i].setText(place[day].areas[spot].day[i]);
                        }
                        break;
                    case "Friday":
                        day = 5;
                        for (int i = 0; i < 18; i++) {
                            grid[i].setText(place[day].areas[spot].day[i]);
                        }
                        break;
                    case "Saturday":
                        day = 6;
                        for (int i = 0; i < 18; i++) {
                            grid[i].setText(place[day].areas[spot].day[i]);
                        }
                        break;


                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                datespinner.setSelection(day);
                for (int i = 0; i < 18; i++) {
                    String temp = "grid" + i;
                    grid[i].setText(place[day].areas[spot].day[i]);
                }
            }
        });

        // listener for area spinner again for each selection a distinct table is loaded
        // so for every datespiiner areaspinner combination a distinct table is loaded
        areaspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (areaspinner.getSelectedItem().toString()) {
                    case "Fitness Room":
                        spot = 0;
                        area1.setText("free weights");
                        area2.setText("spin zone");
                        area3.setText("resistance");
                        for (int i = 0; i < 18; i++) {
                            String temp = "grid" + i;
                            grid[i].setText(place[day].areas[spot].day[i]);
                        }
                        break;
                    case "Pool":
                        spot = 1;
                        area1.setText("lap lanes");
                        area2.setText("kids");
                        area3.setText("hot spots");
                        for (int i = 0; i < 18; i++) {
                            String temp = "grid" + i;
                            grid[i].setText(place[day].areas[spot].day[i]);
                        }
                        break;
                    case "Gymnasium":
                        spot = 2;
                        area1.setText("basketball");
                        area2.setText("volleyball");
                        area3.setText("badminton");
                        for (int i = 0; i < 18; i++) {
                            String temp = "grid" + i;
                            grid[i].setText(place[day].areas[spot].day[i]);
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });






        datespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (datespinner.getSelectedItem().toString()) {
                    case "Sunday":
                        day = 0;
                        for (int i = 0; i < 18; i++) {
                            grid[i].setText(place[day].areas[spot].day[i]);
                        }
                        break;
                    case "Monday":
                        day = 1;
                        for (int i = 0; i < 18; i++) {
                            grid[i].setText(place[day].areas[spot].day[i]);
                        }
                        break;
                    case "Tuesday":
                        day = 2;
                        for (int i = 0; i < 18; i++) {
                            grid[i].setText(place[day].areas[spot].day[i]);
                        }
                        break;
                    case "Wednesday":
                        day = 3;
                        for (int i = 0; i < 18; i++) {
                            grid[i].setText(place[day].areas[spot].day[i]);
                        }
                        break;
                    case "Thursday":
                        day = 4;
                        for (int i = 0; i < 18; i++) {
                            grid[i].setText(place[day].areas[spot].day[i]);
                        }
                        break;
                    case "Friday":
                        day = 5;
                        for (int i = 0; i < 18; i++) {
                            grid[i].setText(place[day].areas[spot].day[i]);
                        }
                        break;
                    case "Saturday":
                        day = 6;
                        for (int i = 0; i < 18; i++) {
                            grid[i].setText(place[day].areas[spot].day[i]);
                        }
                        break;


                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                datespinner.setSelection(day);
                for (int i = 0; i < 18; i++) {
                    String temp = "grid" + i;
                    grid[i].setText(place[day].areas[spot].day[i]);
                }
            }
        });

        areaspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (areaspinner.getSelectedItem().toString()) {
                    case "Fitness Room":
                        spot = 0;
                        area1.setText("free weights");
                        area2.setText("spin zone");
                        area3.setText("resistance");
                        for (int i = 0; i < 18; i++) {
                            String temp = "grid" + i;
                            grid[i].setText(place[day].areas[spot].day[i]);
                        }
                        break;
                    case "Pool":
                        spot = 1;
                        area1.setText("lap lanes");
                        area2.setText("kids");
                        area3.setText("hot spots");
                        for (int i = 0; i < 18; i++) {
                            String temp = "grid" + i;
                            grid[i].setText(place[day].areas[spot].day[i]);
                        }
                        break;
                    case "Gymnasium":
                        spot = 2;
                        area1.setText("basketball");
                        area2.setText("volleyball");
                        area3.setText("badminton");
                        for (int i = 0; i < 18; i++) {
                            String temp = "grid" + i;
                            grid[i].setText(place[day].areas[spot].day[i]);
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return root;

    }

    public void createSchedules() {
        for (int i = 0; i < 7; i++) {
            place[i] = new area();
        }
    }


}


class schedule {
    String[] avai = {"OPEN", "CLOSED", "BOOKED", "OPEN"};
    String[] day = new String[24];

    public schedule() {
        for (int i = 0; i < 18; i++) {
            day[i] = avai[(int) (Math.random() * avai.length)];
        }
    }
}

class area {
    schedule[] areas = new schedule[3];

    public area() {
        for (int i = 0; i < 3; i++) {
            areas[i] = new schedule();
        }
    }
}