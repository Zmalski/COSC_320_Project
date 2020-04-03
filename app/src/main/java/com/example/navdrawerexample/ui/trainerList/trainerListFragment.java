package com.example.navdrawerexample.ui.trainerList;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.navdrawerexample.R;
import com.example.navdrawerexample.TrainerPageActivity;

public class trainerListFragment extends Fragment {

    private trainerListViewModel trainerListViewModel;

    //initialzing views to be linked
    TextView t1Name, t2Name, t3Name, t1Desc, t2Desc, t3Desc;
    Button backButton;
    View t1View, t2View, t3View;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Fragment code
        trainerListViewModel =
                ViewModelProviders.of(this).get(trainerListViewModel.class);
        View root = inflater.inflate(R.layout.fragment_trainer_list, container, false);
        //End of fragment code


        //linking id values
        t1Name = root.findViewById(R.id.trainer1Name);
        t2Name = root.findViewById(R.id.trainer2Name);
        t3Name = root.findViewById(R.id.trainer3Name);

        t1Desc = root.findViewById(R.id.trainer1Desc);
        t2Desc = root.findViewById(R.id.trainer2Desc);
        t3Desc = root.findViewById(R.id.trainer3Desc);

        //backButton = root.findViewById(R.id.trainerListBackButton);

        t1View = root.findViewById(R.id.trainer1View);
        t2View = root.findViewById(R.id.trainer2View);
        t3View = root.findViewById(R.id.trainer3View);


        //setting text and info
        t1Name.setText("Dinesh Gosh");
        t2Name.setText("Suzanne Reeves");
        t3Name.setText("Winona Rivers");

        t1Desc.setText("I specialize in cardio fitness, including running, sprints, track activities and walking");
        t2Desc.setText("My specializations lie in weightlifting, bodybuilding and core workouts");
        t3Desc.setText("I have been a Crossfit trainer for over 5 years and specialize in extreme workouts");

        //on clicking each trainer
        t1View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t1View.setAlpha((float) 0.75);
                trainerPageLaunch(1);
            }
        });

        t2View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t2View.setAlpha((float) 0.75);
                trainerPageLaunch(2);
            }
        });

        t3View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t3View.setAlpha((float) 0.75);
                trainerPageLaunch(3);
            }
        });

        //clicking back button
        /* backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backHome();
            }
        });*/

        return root;
    }

    // Code added by Zach to set the alphas back to default upon clicking "back" button
    public void onResume() {
        super.onResume();
        t1View.setAlpha((float) 0.25);
        t2View.setAlpha((float) 0.25);
        t3View.setAlpha((float) 0.25);
    }


    public void trainerPageLaunch(int trainer) {
        Intent intent = new Intent(getActivity(), TrainerPageActivity.class);
        if (trainer == 1) {
            intent.putExtra("trainerSelected", "1");
            intent.putExtra("trainerNameSelected", t1Name.getText().toString());
            intent.putExtra("trainerDescSelected", t1Desc.getText().toString());
        } else if (trainer == 2) {
            intent.putExtra("trainerSelected", "2");
            intent.putExtra("trainerNameSelected", t2Name.getText().toString());
            intent.putExtra("trainerDescSelected", t2Desc.getText().toString());
        } else {
            intent.putExtra("trainerSelected", "3");
            intent.putExtra("trainerNameSelected", t3Name.getText().toString());
            intent.putExtra("trainerDescSelected", t3Desc.getText().toString());
        }
        startActivity(intent);
    }
    /*
    public void backHome() {
        Intent intent = new Intent(getACtivi, MainActivity.class);
        startActivity(intent);
    }
    */
}
