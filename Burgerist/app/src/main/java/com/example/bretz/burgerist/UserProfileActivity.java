package com.example.bretz.burgerist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bretz.burgerist.Adapters.AppointmentAdapter;
import com.example.bretz.burgerist.Objects.Appointment;
import com.example.bretz.burgerist.Objects.Customer;
import com.example.bretz.burgerist.Objects.Employee;
import com.example.bretz.burgerist.Utils.DBHelper;

public class UserProfileActivity extends AppCompatActivity {

    private TextView txtUserName, txtUserContract, txtUserPhone, txtUserEmail;
    private Button btnSeePastAppointments, btnSeeAppointments, btnCreateAppointment;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        txtUserName = (TextView) findViewById(R.id.txtUserName);
        txtUserContract = (TextView) findViewById(R.id.txtUserContract);
        txtUserPhone = (TextView) findViewById(R.id.txtUserPhone);
        txtUserEmail = (TextView) findViewById(R.id.txtUserEmail);

        db = new DBHelper(this);

        final Customer customer = getIntent().getParcelableExtra("data");

        txtUserName.setText(customer.getName() + " " + customer.getLastName());
        txtUserContract.setText(customer.getContractNumber() + "");
        txtUserPhone.setText(customer.getPhone() + "");
        txtUserEmail.setText(customer.getEmail());

        btnSeePastAppointments = (Button) findViewById(R.id.btnSeePastApptointments);
        btnSeeAppointments = (Button) findViewById(R.id.btnSeeAppointments);
        btnCreateAppointment = (Button) findViewById(R.id.btnCreateAppointment);

        btnSeePastAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AppointmentUserActivity.class);
                intent.putExtra("type", "past");
                intent.putExtra("customer", customer);
                startActivity(intent);
            }
        });

        btnSeeAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AppointmentUserActivity.class);
                intent.putExtra("type", "future");
                intent.putExtra("customer", customer);
                startActivity(intent);
            }
        });

        btnCreateAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RequestAppointmentActivity.class);
                intent.putExtra("customer", customer);
                startActivity(intent);
            }
        });

    }
}