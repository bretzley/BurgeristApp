package com.example.bretz.burgerist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.bretz.burgerist.Adapters.AppointmentAdapter;

public class AppointmentActivity extends AppCompatActivity {

    ListView lstAppointments;
    AppointmentAdapter apptAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);
        lstAppointments = (ListView)findViewById(R.id.lstAppts);
        lstAppointments.setAdapter(apptAdapter);

        //Intent intent = new Intent(getApplicationContext(), AppointmentDetails.class);
    }


}
