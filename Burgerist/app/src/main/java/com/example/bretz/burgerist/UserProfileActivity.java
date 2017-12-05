package com.example.bretz.burgerist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.bretz.burgerist.Objects.Appointment;
import com.example.bretz.burgerist.Objects.Customer;
import com.example.bretz.burgerist.Objects.Employee;
import com.example.bretz.burgerist.Utils.DBHelper;

public class UserProfileActivity extends AppCompatActivity {

    private TextView txtUserName, txtUserContract, txtUserPhone, txtUserEmail, btnTest;
    DBHelper db;
    String contract, email;
    Customer sessionCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        txtUserName = (TextView)findViewById(R.id.txtUserName);
        txtUserContract = (TextView)findViewById(R.id.txtUserContract);
        txtUserPhone = (TextView)findViewById(R.id.txtUserPhone);
        txtUserEmail = (TextView)findViewById(R.id.txtUserEmail);

        db = new DBHelper(this);

        String user = getIntent().getStringExtra("user");
        if(user.equals("customer")) {
            Customer customer = getIntent().getParcelableExtra("data");

            txtUserName.setText(customer.getName());
            txtUserContract.setText(customer.getContractNumber()+"");
            txtUserPhone.setText(customer.getPhone()+"");
            txtUserEmail.setText(customer.getEmail());
        }else{
            Employee employee = getIntent().getParcelableExtra("data");

            txtUserName.setText(employee.getName());
            txtUserContract.setText(employee.getEmployeeCode()+"");
            txtUserPhone.setText(employee.getPhone()+"");
            txtUserEmail.setText(employee.getEmail());
        }

        btnTest = (TextView)findViewById(R.id.btnTest);

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AppointmentActivity.class);
                startActivity(i);
            }
        });

    }


}