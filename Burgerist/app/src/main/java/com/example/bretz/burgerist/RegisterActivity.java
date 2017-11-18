package com.example.bretz.burgerist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bretz.burgerist.Objects.Customer;
import com.example.bretz.burgerist.Utils.DBHelper;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    EditText edtUserContract, edtUserEmail, edtUserPass;
    Button btnRegisterUser ;
    DBHelper db;
    ArrayList<Customer> customers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtUserContract = (EditText) findViewById(R.id.edtUserContract);
        edtUserEmail = (EditText) findViewById(R.id.edtUserEmail);
        edtUserPass = (EditText) findViewById(R.id.edtUserPass);
        btnRegisterUser = (Button) findViewById(R.id.btRegisterUser);
        db = new DBHelper(this);
        customers = new ArrayList<>();

        btnRegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtUserContract.getText() == null) {
                    Toast.makeText(getApplicationContext(), "Please write a contract number", Toast.LENGTH_SHORT).show();
                } else if (edtUserEmail.getText() == null) {
                    Toast.makeText(getApplicationContext(), "Please write an email", Toast.LENGTH_SHORT).show();
                } else if (edtUserPass.getText() == null) {
                    Toast.makeText(getApplicationContext(), "Please write a password", Toast.LENGTH_SHORT).show();
                } else {
                    int contractNumber = Integer.parseInt(edtUserContract.getText().toString());
                    String email = edtUserEmail.getText().toString();
                    String password = edtUserPass.getText().toString();
                    String message = "";

                    db.open();
                    message = db.registerCustomer(contractNumber, email, password);
                    db.close();

                    if(message == "Success"){
                        Intent intent = new Intent(getApplicationContext(), UserLoginActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
