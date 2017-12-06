package com.example.bretz.burgerist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.bretz.burgerist.Objects.Customer;
import com.example.bretz.burgerist.Objects.Employee;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    RadioButton rbTec, rbUser;
    RadioGroup rgLogin;
    TextView txtRegister;
    EditText edtNumberLogin, edtPasswordLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rbUser = (RadioButton) findViewById(R.id.rbUser);
        rbTec = (RadioButton) findViewById(R.id.rbTec);
        rgLogin = (RadioGroup) findViewById(R.id.rgLogin);
        txtRegister = (TextView) findViewById(R.id.txtRegisterUsr);
        btnLogin = (Button) findViewById(R.id.btnMainLogin);
        edtNumberLogin = (EditText) findViewById(R.id.edtNumberLogin);
        edtPasswordLogin = (EditText) findViewById(R.id.edtPasswordLogin);

        rbUser.setOnCheckedChangeListener(radioListener);
        rbTec.setOnCheckedChangeListener(radioListener);
        rbUser.setChecked(true);

        final RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        final String customerAPI = "http://ec2-34-226-122-227.compute-1.amazonaws.com:2403/customer";
        final String employeeAPI = "http://ec2-34-226-122-227.compute-1.amazonaws.com:2403/employee";


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtNumberLogin.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Por favor, ingrese su número.", Toast.LENGTH_SHORT).show();
                } else if (edtPasswordLogin.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Por favor, ingrese su contraseña.", Toast.LENGTH_SHORT).show();
                } else {
                    final String inNumber = edtNumberLogin.getText().toString();
                    final String inPassword = edtPasswordLogin.getText().toString();
                    final String url = rbUser.isChecked() ? (customerAPI + "?ContractNumber=" + inNumber) : (employeeAPI + "?EmployeeNumber=" + inNumber);

                    final JsonArrayRequest jsonGetCustomerRequest = new JsonArrayRequest
                            (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                                @Override
                                public void onResponse(JSONArray response) {
                                    if (response.length() > 0) {
                                        try {
                                            JSONObject user = response.getJSONObject(0);
                                            String password = user.has("Password") ? user.getString("Password") : "";
                                            if (password != "" && password.equals(inPassword)) {
                                                Boolean registered = user.getBoolean("Registered");
                                                String name = user.getString("Name");
                                                String id = user.getString("id");
                                                int number = Integer.parseInt(inNumber);
                                                String lastName = user.getString("LastName");
                                                String email = user.has("Email") ? user.getString("Email") : "";
                                                int phone = user.getInt("Phone");
                                                String image = user.has("Image") ? user.getString("Image") : "";
                                                Intent intent;
                                                if(rbUser.isChecked()) {
                                                    intent = new Intent(getApplicationContext(), UserProfileActivity.class);
                                                    String address = user.getString("Address");
                                                    Customer customer = new Customer(id, number, name, lastName, email, password, address, phone, image, registered);
                                                    intent.putExtra("data", customer);
                                                    intent.putExtra("user", "customer");
                                                }else{
                                                    intent = new Intent(getApplicationContext(), EmployeeProfileActivity.class);
                                                    Employee employee = new Employee(id, number, name, lastName, email, password, phone, image, registered);
                                                    intent.putExtra("data", employee);
                                                    intent.putExtra("user", "employee");
                                                }
                                                startActivity(intent);
                                            } else
                                                makeText(getApplicationContext(),"Número o contraseña incorrectos.", LENGTH_SHORT).show();
                                        } catch (JSONException e) {
                                            makeText(getApplicationContext(), "Algo salió mal, intente de nuevo.", LENGTH_SHORT).show();
                                        }
                                    } else
                                        makeText(getApplicationContext(), "Número o contraseña incorrectos.", LENGTH_SHORT).show();
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    makeText(getApplicationContext(), "Hay problemas de conexión, intente en un momento.", LENGTH_SHORT).show();
                                }
                            });

                    queue.add(jsonGetCustomerRequest);
                }
            }
        });


        txtRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });

    }

    final CompoundButton.OnCheckedChangeListener radioListener = new CompoundButton.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                {
                    if (isChecked) {
                        if (buttonView.getId() == R.id.rbUser)
                            edtNumberLogin.setHint("Número de Contrato");
                        else
                            edtNumberLogin.setHint("Núsmero de Empleado");
                    }
                }
            };
}
