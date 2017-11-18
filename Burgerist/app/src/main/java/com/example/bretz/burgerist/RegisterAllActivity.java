package com.example.bretz.burgerist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bretz.burgerist.Objects.Customer;
import com.example.bretz.burgerist.Objects.Employee;
import com.example.bretz.burgerist.Utils.DBHelper;

import java.util.ArrayList;

public class RegisterAllActivity extends AppCompatActivity {

    EditText edtUserContract, edtUserEmail, edtUserPass;
    Button btnRegisterUser;
    DBHelper db;
    ArrayList<Customer> customers;
    ArrayList<Employee> employees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_all);


        edtUserContract = (EditText)findViewById(R.id.edtUserContract);
        edtUserEmail = (EditText)findViewById(R.id.edtUserEmail);
        edtUserPass = (EditText)findViewById(R.id.edtUserPass);
        btnRegisterUser = (Button)findViewById(R.id.btRegisterUser);
        db = new DBHelper(this);
        customers = new ArrayList<>();

        btnRegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserLoginActivity.class);
                db.open();
                int id = customers.size()+1;
                int contract = Integer.parseInt(edtUserContract.getText().toString());
                String email = edtUserEmail.getText().toString();
                String pass = edtUserPass.getText().toString();
                String name = "Test";
                String middle = "TesMiddle";
                String last = "TesMiddle";
                //db.getCustomer();
                db.close();

                if (edtUserContract.getText()== null) {
                    Toast.makeText(getApplicationContext(), "Por favor ingresa tu numero de contrato", Toast.LENGTH_SHORT).show();
                } else if (edtUserEmail.getText()== null) {
                    Toast.makeText(getApplicationContext(), "Por favor ingresa tu correo electronico", Toast.LENGTH_SHORT).show();
                } else if (edtUserPass.getText()== null) {
                    Toast.makeText(getApplicationContext(), "Por favor ingresa tu contraseña", Toast.LENGTH_SHORT).show();
                }


                db.addCustomer(id, contract, name, middle, last, email, pass, "TEST adresss", 34634, "blah");
                startActivity(intent);
            }
        });
    }
}
