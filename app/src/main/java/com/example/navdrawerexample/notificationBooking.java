package com.example.navdrawerexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class notificationBooking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_booking);
        TextView notificationTitle = findViewById(R.id.title);
        notificationTitle.setText(Html.fromHtml("<u>Notifications</u>"));
    }

    public void cancel(View view) {
        finish();
    }

    public void book(View view) {
        Toast.makeText(this, "Booking Confirmed!",
                Toast.LENGTH_LONG).show();
        finish();
    }

    public void share(View view) {

    }
}
