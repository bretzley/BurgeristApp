package com.example.bretz.burgerist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.bretz.burgerist.Adapters.CustomerAdapter;
import com.example.bretz.burgerist.Objects.Customer;
import com.example.bretz.burgerist.Objects.Employee;
import com.example.bretz.burgerist.Utils.DBHelper;

import java.util.ArrayList;

public class RegisterAllActivity extends AppCompatActivity {

    EditText edtUserID, edtUserNumber, edtUserFName, edtUserMName, edtUserLName, edtUserEmail, edtUserPass, edtUserAdress, edtUserPhone;
    Button btnRegisterAny, btnHome;
    DBHelper db;
    ArrayList<Customer> customers;
    ArrayList<Employee> employees;
    RadioGroup rgRegister;
    RadioButton rbTecReg, rbUserReg;
    CustomerAdapter CustomerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_all);

        CustomerAdapter = new CustomerAdapter(this);
        edtUserID = (EditText) findViewById(R.id.edtUserId);
        edtUserNumber = (EditText) findViewById(R.id.edtUserNumber);
        edtUserFName = (EditText) findViewById(R.id.edtUserFName);
        edtUserMName = (EditText) findViewById(R.id.edtUserMName);
        edtUserLName = (EditText) findViewById(R.id.edtLName);
        edtUserEmail = (EditText) findViewById(R.id.edtUserEmail);
        edtUserPass = (EditText) findViewById(R.id.edtUserPass);
        edtUserAdress = (EditText) findViewById(R.id.edtUserAdress);
        edtUserPhone = (EditText) findViewById(R.id.edtUserPhone);
        btnRegisterAny = (Button) findViewById(R.id.btnRegisterAny);
        btnHome = (Button) findViewById(R.id.btnHome);
        rgRegister = (RadioGroup) findViewById(R.id.rgRegAny);
        rbTecReg = (RadioButton) findViewById(R.id.rbTecReg);
        rbUserReg = (RadioButton) findViewById(R.id.rbUserReg);
        final DBHelper db = new DBHelper(this);
        customers = new ArrayList<>();
        employees = new ArrayList<>();
        rbUserReg.setOnCheckedChangeListener(radioListener);
        rbTecReg.setOnCheckedChangeListener(radioListener);


        btnRegisterAny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserLoginActivity.class);
                db.open();

                //boolean checked = ((RadioButton) v).isChecked();
                //int userId = Integer.parseInt(edtUserID.getText().toString());
                //int userNum = Integer.parseInt(edtUserNumber.getText().toString());
                /*String userId = edtUserID.getText().toString();
                String userNum = edtUserNumber.getText().toString();
                String UserFName = edtUserFName.getText().toString();
                String UserMName = edtUserMName.getText().toString();
                String UserLName = edtUserLName.getText().toString();
                String UserEmail = edtUserEmail.getText().toString();
                String UserPass = edtUserPass.getText().toString();
                String UserAddress = edtUserAdress.getText().toString();
                //int UserPhone = Integer.parseInt(edtUserPhone.getText().toString());
                String UserPhone = edtUserPhone.getText().toString();


                Customer result = db.addCustomer(userId, userNum, UserFName, UserMName, UserLName, UserEmail, UserPass, UserAddress, UserPhone, "a");
                Toast.makeText(getApplicationContext(),"Data Saved",Toast.LENGTH_LONG).show();// TODO Auto-generated method stub*/


                /*if (edtUserID.getText() == null) {
                    Toast.makeText(getApplicationContext(), "Por favor ingresa el id del usuario", Toast.LENGTH_SHORT).show();
                } else if (edtUserNumber.getText() == null) {
                    switch (v.getId()) {
                        case R.id.rbUserReg:
                            if (checked) {
                                Toast.makeText(getApplicationContext(), "Por favor ingresa el numero de contrato", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case R.id.rbTecReg:
                            if (checked) {
                                Toast.makeText(getApplicationContext(), "Por favor ingresa el codigo del tecnico", Toast.LENGTH_SHORT).show();
                            }
                            break;
                    }
                } else if (edtUserFName.getText() == null) {
                    Toast.makeText(getApplicationContext(), "Por favor ingresa el primer nombre del usuario", Toast.LENGTH_SHORT).show();
                } else if (edtUserMName.getText() == null) {
                    Toast.makeText(getApplicationContext(), "Por favor ingresa el segundo nombre del usuario", Toast.LENGTH_SHORT).show();
                } else if (edtUserLName.getText() == null) {
                    Toast.makeText(getApplicationContext(), "Por favor ingresa el apellido del usuario", Toast.LENGTH_SHORT).show();
                } else if (edtUserEmail.getText() == null) {
                    Toast.makeText(getApplicationContext(), "Por favor ingresa el correo electronico", Toast.LENGTH_SHORT).show();
                } else if (edtUserPass.getText() == null) {
                    Toast.makeText(getApplicationContext(), "Por favor ingresa la contraseña", Toast.LENGTH_SHORT).show();
                } else if (edtUserPhone.getText() == null) {
                    Toast.makeText(getApplicationContext(), "Por favor ingresa el telefono del usuario", Toast.LENGTH_SHORT).show();
                }

                switch (v.getId()) {
                    case R.id.rbUserReg:
                        db.addCustomer(userId, userNum, UserFName, UserMName, UserLName, UserEmail, UserPass, UserAddress, UserPhone, "");
                        break;
                    /*case R.id.rbTecReg:
                        if (checked) {
                            db.addEmployee(userId, userNum, UserFName, UserMName, UserLName, UserEmail, UserPass, UserPhone, "");
                        }
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "Error! Seleccione el tipo de usuario para registrar.", Toast.LENGTH_LONG);
                        break;
                }*/
                //db.addCustomer(userId, userNum, UserFName, UserMName, UserLName, UserEmail, UserPass, UserAddress, UserPhone, "");
                db.close();

                /*customers = this.getIntent.getParcelableArrayListExtra("Parcel");
                fillCustomer(customers);*/

                startActivity(intent);
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    final CompoundButton.OnCheckedChangeListener radioListener =
            new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (!isChecked)
                        return;
                    if (buttonView.getId() == R.id.rbUserReg)
                        Toast.makeText(getApplicationContext(), "Seleccionaste registrar usuario tipo cliente", Toast.LENGTH_SHORT).show();
                    else if (buttonView.getId() == R.id.rbTecReg)
                        Toast.makeText(getApplicationContext(), "Seleccionaste registrar usuario tipo tecnico", Toast.LENGTH_SHORT).show();
                }
            };

    public void fillCustomer(ArrayList<Customer> customers){
        for(Customer comment : customers)
            CustomerAdapter.add(comment);
        CustomerAdapter.notifyDataSetChanged();
    }

    public void onBackPressed(){
        finish();
    }
}
