package com.example.bretz.burgerist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.bretz.burgerist.Objects.Customer;
import com.example.bretz.burgerist.Utils.DBHelper;

public class UserProfileActivity extends AppCompatActivity {

    private TextView txtUserName, txtUserContract, txtUserPhone, txtUserEmail;
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
        contract = getIntent().getStringExtra("Contract");
        email = getIntent().getStringExtra("Email");
        sessionCustomer = new Customer();

        sessionCustomer = db.getCustomerByContractNumber(contract);

        txtUserName.setText(sessionCustomer.getName() + " " + sessionCustomer.getLastName());
        txtUserContract.setText(sessionCustomer.getContractNumber());
        txtUserPhone.setText(sessionCustomer.getPhone());
        txtUserEmail.setText(sessionCustomer.getEmail());


    }


}