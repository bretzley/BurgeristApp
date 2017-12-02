package com.example.bretz.burgerist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.bretz.burgerist.Objects.Customer;
import com.example.bretz.burgerist.Objects.Employee;
import com.example.bretz.burgerist.Utils.DBHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class UserLoginActivity extends AppCompatActivity {

    private Button btnUserLog;
    private EditText edtUser, edtPass;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        final String user = getIntent().getStringExtra("user");

        db = new DBHelper(this);
        btnUserLog = (Button) findViewById(R.id.btnUserLogin);
        edtPass = (EditText) findViewById(R.id.edtLoginUserPass);
        edtUser = (EditText) findViewById(R.id.edtLoginUser);

        if (user.equals("employee"))
            edtUser.setHint("Numero de Empleado");

        final Context myContext = this;
        final RequestQueue queue = Volley.newRequestQueue(myContext);
        final String customerAPI = "http://ec2-34-226-122-227.compute-1.amazonaws.com:2403/customer";
        final String employeeAPI = "http://ec2-34-226-122-227.compute-1.amazonaws.com:2403/employee";

        btnUserLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtUser.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Por favor ingrese su numero.", Toast.LENGTH_SHORT).show();
                } else if (edtPass.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Por favor ingrese su contraseña.", Toast.LENGTH_SHORT).show();
                } else {
                    final String _number = edtUser.getText().toString();
                    final String passwordInput = edtPass.getText().toString();
                    final String url = (user.equals("employee") ? (employeeAPI + "?EmployeeNumber=" ) : (customerAPI + "?ContractNumber=" )) + _number;

                    final JsonArrayRequest jsonGetCustomerRequest = new JsonArrayRequest
                            (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                                @Override
                                public void onResponse(JSONArray response) {
                                    if (response.length() > 0) {
                                        try {
                                            JSONObject rCustomer = response.getJSONObject(0);
                                            String password = rCustomer.has("Password") ? rCustomer.getString("Password") : "";
                                            if (password != "" && password.equals(passwordInput)) {
                                                Boolean registered = rCustomer.getBoolean("Registered");
                                                String name = rCustomer.getString("Name");
                                                String id = rCustomer.getString("id");
                                                int number = Integer.parseInt(_number);
                                                String lastName = rCustomer.getString("LastName");
                                                String email = rCustomer.has("Email") ? rCustomer.getString("Email") : "";
                                                int phone = rCustomer.getInt("Phone");
                                                String image = rCustomer.has("Image") ? rCustomer.getString("Image") : "";
                                                Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
                                                if(user.equals("customer")) {
                                                    String address = rCustomer.getString("Address");
                                                    Customer c = new Customer(id, number, name, lastName, email, password, address, phone, image, registered);
                                                    intent.putExtra("data", c);
                                                }else {
                                                    Employee e = new Employee(id, number, name, lastName, email, password, phone, image, registered);
                                                    intent.putExtra("data", e);
                                                }
                                                startActivity(intent);
                                            } else
                                                makeText(myContext, "Numero o contraseña incorrectos.", LENGTH_SHORT).show();
                                        } catch (JSONException e) {
                                            makeText(myContext, "Algo salio mal, intente de nuevo.", LENGTH_SHORT).show();
                                        }
                                    } else
                                        makeText(getApplicationContext(), "Numero o contraseña incorrectos.", LENGTH_SHORT).show();
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    makeText(getApplicationContext(), "Oops, algo salio mal. Intente en un momento.", LENGTH_SHORT).show();
                                }
                            });

                    queue.add(jsonGetCustomerRequest);
                }
            }
        });
    }
}