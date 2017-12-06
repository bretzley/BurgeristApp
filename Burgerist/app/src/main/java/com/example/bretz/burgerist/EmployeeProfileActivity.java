package com.example.bretz.burgerist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bretz.burgerist.Objects.Employee;
import com.example.bretz.burgerist.R;

public class EmployeeProfileActivity extends AppCompatActivity {

    private TextView txtFullName, txtEmployeeCode;
    private Button btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_profile);

        txtFullName = (TextView)findViewById(R.id.txtFullName);
        txtEmployeeCode = (TextView)findViewById(R.id.txtEmployeeCode);
        btnTest = (Button)findViewById(R.id.btnSeeAppointments);

        final Employee employee = getIntent().getParcelableExtra("data");

        txtFullName.setText(employee.getName() + " " + employee.getLastName());
        txtEmployeeCode.setText(employee.getEmployeeCode()+"");

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AppointmentActivity.class);
                intent.putExtra("data", employee);
                startActivity(intent);
            }
        });

    }
}
