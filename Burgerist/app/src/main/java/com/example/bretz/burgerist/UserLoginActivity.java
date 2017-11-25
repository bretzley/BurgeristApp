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

        btnUserLog = (Button)findViewById(R.id.btnUserLogin);
        edtPass = (EditText)findViewById(R.id.edtLoginUserPass);
        edtUser = (EditText)findViewById(R.id.edtLoginUser);
        db = new DBHelper(this);

        btnUserLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);

                if (edtUser.getText()== null) {
                    Toast.makeText(getApplicationContext(), "Por favor ingresa tu usuario", Toast.LENGTH_SHORT).show();
                } else if (edtPass.getText()== null) {
                    Toast.makeText(getApplicationContext(), "Por favor ingresa tu contraseña", Toast.LENGTH_SHORT).show();
                }

                String user = edtUser.getText().toString();
                String pass = edtPass.getText().toString();
                Customer cus = new Customer();

                try {cus = db.getCustomer(user);}catch(Exception e){
                    Toast.makeText(getApplicationContext(), "El usuario no existe!", Toast.LENGTH_SHORT).show();
                }

                if (user.equals(cus.getEmail()) && pass.equals(cus.getPassword())){
                    Toast.makeText(getApplicationContext(),"Bienvenido!", Toast.LENGTH_SHORT).show();
                    startActivity(intent);

                }
                else Toast.makeText(getApplicationContext(), "Usuario o contraseña son INCORRECTOS", Toast.LENGTH_SHORT).show();

                //startActivity(intent);
            }
        });
    }
}
