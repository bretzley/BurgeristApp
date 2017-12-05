package com.example.bretz.burgerist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bretz.burgerist.Objects.Appointment;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class AppointmentDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_details);
        Appointment apt = getIntent().getParcelableExtra("appointment");
        makeText(getApplicationContext(), apt.getTimeSlot(), LENGTH_SHORT).show();
    }
}
