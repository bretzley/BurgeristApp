package com.example.bretz.burgerist;

import android.content.Context;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bretz.burgerist.Objects.Customer;
import com.example.bretz.burgerist.Utils.DBHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.widget.Toast.*;

public class RegisterActivity extends AppCompatActivity {

    TextView txtLogin;
    EditText edtUserContract, edtUserEmail, edtUserPass;
    Button btnRegisterUser;
    DBHelper db;
    ArrayList<Customer> customers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtUserContract = (EditText) findViewById(R.id.edtUserContract);
        edtUserEmail = (EditText) findViewById(R.id.edtUserEmail);
        edtUserPass = (EditText) findViewById(R.id.edtUserPass);
        btnRegisterUser = (Button) findViewById(R.id.btnRegisterUser);
        txtLogin = (TextView) findViewById(R.id.txtLogin);

        db = new DBHelper(this);
        customers = new ArrayList<>();

        edtUserEmail.setEnabled(true);
        edtUserPass.setEnabled(true);
        btnRegisterUser.setVisibility(View.VISIBLE);

        final RequestQueue queue = Volley.newRequestQueue(this);
        final String customerAPI = "http://ec2-34-226-122-227.compute-1.amazonaws.com:2403/customer";
        final Context myContext = this;

        btnRegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtUserContract.getText().toString().equals("")) {
                    makeText(getApplicationContext(), "Por favor, escriba su número de contrato.", LENGTH_SHORT).show();
                } else if (edtUserEmail.getText().toString().equals("")) {
                    makeText(getApplicationContext(), "Por favor, escriba su correo.", LENGTH_SHORT).show();
                } else if (edtUserPass.getText().toString().equals("")) {
                    makeText(getApplicationContext(), "Por favor, escriba su contraseña.", LENGTH_SHORT).show();
                } else {
                    final String inContractNumber = edtUserContract.getText().toString();
                    final String url = customerAPI + "?ContractNumber=" + inContractNumber;
                    final JsonArrayRequest jsonGetCustomerRequest = new JsonArrayRequest
                            (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                                @Override
                                public void onResponse(JSONArray response) {
                                    if (response.length() > 0) {
                                        try {
                                            JSONObject rCustomer = response.getJSONObject(0);
                                            Boolean registered = rCustomer.getBoolean("Registered");
                                            String name = rCustomer.getString("Name");
                                            if (!registered) {
                                                String id = rCustomer.getString("id");

                                                String inEmail = edtUserEmail.getText().toString();
                                                String inPassword = edtUserPass.getText().toString();

                                                JSONObject customerJSONObject = new JSONObject();
                                                customerJSONObject.put("Email", inEmail);
                                                customerJSONObject.put("Password", inPassword);
                                                customerJSONObject.put("Registered", true);
                                                customerJSONObject.put("id", id);

                                                final JsonObjectRequest jsonUpdateCustomerRequest = new JsonObjectRequest
                                                        (Request.Method.PUT, customerAPI, customerJSONObject, new Response.Listener<JSONObject>() {
                                                            @Override
                                                            public void onResponse(JSONObject response) {
                                                                try {
                                                                    makeText(getApplicationContext(), "Bienvenido, " + response.getString("Name") + "!", LENGTH_SHORT).show();
                                                                    startLoginActivity();
                                                                } catch (JSONException e) {
                                                                    makeText(getApplicationContext(), "Algo salió mal, intenta de nuevo. :(", LENGTH_SHORT);
                                                                }
                                                            }
                                                        }, new Response.ErrorListener() {
                                                            @Override
                                                            public void onErrorResponse(VolleyError error) {
                                                                makeText(getApplicationContext(), "Problemas de conexión, intente en un momento.", LENGTH_SHORT).show();
                                                            }
                                                        });

                                                queue.add(jsonUpdateCustomerRequest);

                                            } else {
                                                makeText(getApplicationContext(), name + ", ya está registrado. Por favor, inice sesión.", LENGTH_SHORT).show();
                                            }
                                        } catch (JSONException e) {
                                            makeText(getApplicationContext(), "Algo salió mal, intente de nuevo.", LENGTH_SHORT).show();
                                        }
                                    } else
                                        makeText(getApplicationContext(), "El número de contrato no existe.", LENGTH_SHORT).show();
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    makeText(getApplicationContext(), "Problemas de conexión, intente en un momento.", LENGTH_LONG).show();
                                }
                            });

                    queue.add(jsonGetCustomerRequest);
                }
            }
        });

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLoginActivity();
            }
        });
    }

    private void startLoginActivity() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }
}
