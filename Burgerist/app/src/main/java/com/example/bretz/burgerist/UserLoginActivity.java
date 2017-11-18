package com.example.bretz.burgerist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserLoginActivity extends AppCompatActivity {

    private Button btnUserLog;
    private EditText edtUser, edtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

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
                }

                if (edtUser.getText().toString().equals("user") && edtPass.getText().toString().equals("pass")){
                    Toast.makeText(getApplicationContext(),"Usuario o contraseña son INCORRECTOS", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
                    startActivity(intent);

                }
                else Toast.makeText(getApplicationContext(), "Usuario o contraseña son INCORRECTOS", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
