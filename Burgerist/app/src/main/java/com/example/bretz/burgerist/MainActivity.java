package com.example.bretz.burgerist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
            //, btnRegister;
    RadioButton rbTec, rbUser;
    RadioGroup rgLogin;
    TextView txtRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = (Button) findViewById(R.id.btnMainLogin);
        //btnRegister = (Button) findViewById(R.id.btnRegister);
        rbUser = (RadioButton) findViewById(R.id.rbUser);
        rbTec = (RadioButton) findViewById(R.id.rbTec);
        rgLogin = (RadioGroup) findViewById(R.id.rgLogin);
        txtRegister = (TextView) findViewById(R.id.txtRegisterUsr);
        rbUser.setOnCheckedChangeListener(radioListener);
        rbTec.setOnCheckedChangeListener(radioListener);

        rbUser.setChecked(true);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin= new Intent(getApplicationContext(), UserLoginActivity.class);
                if (rbUser.isChecked())
                    intentLogin.putExtra("user", "customer");
                else
                    intentLogin.putExtra("user", "employee");
                startActivity(intentLogin);
            }
        });


        txtRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
        /*btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

    }

    final CompoundButton.OnCheckedChangeListener radioListener = new CompoundButton.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                {
                    if (!isChecked) {
                        return;
                        /*if (buttonView.getId() == R.id.rbUser) {

                        } else if (buttonView.getId() == R.id.rbTec) {

                        }else{}*/
                    }
                }
            };
}
