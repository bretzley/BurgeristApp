package com.example.bretz.burgerist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bretz.burgerist.Objects.Customer;
import com.example.bretz.burgerist.Utils.DBHelper;

public class UserLoginActivity extends AppCompatActivity {

    private Button btnUserLog;
    private EditText edtUser, edtPass;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        db = new DBHelper(this);
        btnUserLog = (Button)findViewById(R.id.btnUserLogin);
        edtPass = (EditText)findViewById(R.id.edtLoginUserPass);
        edtUser = (EditText)findViewById(R.id.edtLoginUser);

        btnUserLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edtUser.getText()== null) {
                    Toast.makeText(getApplicationContext(), "Por favor ingresa tu usuario", Toast.LENGTH_SHORT).show();
                } else if (edtPass.getText()== null) {
                    Toast.makeText(getApplicationContext(), "Por favor ingresa tu contraseña", Toast.LENGTH_SHORT).show();
                }else {
                    try{
                        Customer c = db.getCustomerByContractNumber(edtUser.getText().toString());

                        if (edtUser.getText().toString().equals(c.getEmail()) && edtPass.getText().toString().equals(c.getPassword())){
                            Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
                            intent.putExtra("Contract", c.getContractNumber());
                            intent.putExtra("Email", c.getEmail());
                            startActivity(intent);
                        }
                        else Toast.makeText(getApplicationContext(), "Usuario o contraseña son INCORRECTOS", Toast.LENGTH_SHORT).show();
                    }catch (Exception e){}
                }
            }
        });
    }
}
