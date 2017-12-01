package com.example.bretz.burgerist;

import android.content.Context;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class RegisterActivity extends AppCompatActivity {

    EditText edtUserContract, edtUserEmail, edtUserPass;
    Button btnRegisterUser, btnLookForContractNo ;
    DBHelper db;
    ArrayList<Customer> customers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtUserContract = (EditText) findViewById(R.id.edtUserContract);
        edtUserEmail = (EditText) findViewById(R.id.edtUserEmail);
        edtUserPass = (EditText) findViewById(R.id.edtUserPass);
        btnRegisterUser = (Button) findViewById(R.id.btRegisterUser);
        btnLookForContractNo = (Button) findViewById(R.id.btnLookForContractNo);
        db = new DBHelper(this);
        customers = new ArrayList<>();

        edtUserEmail.setEnabled(false);
        edtUserPass.setEnabled(false);
        //edtUserEmail.setEnabled(true);
        //edtUserPass.setEnabled(true);
        btnRegisterUser.setVisibility(View.INVISIBLE);

        final RequestQueue queue = Volley.newRequestQueue(this);
        final String customerAPI = "http://ec2-34-226-122-227.compute-1.amazonaws.com:2403/customer";
        final Context myContext = this;

        btnLookForContractNo.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (edtUserContract.getText() == null) {
                    Toast.makeText(getApplicationContext(), "Por favor, escribe un numero de contrato.", Toast.LENGTH_SHORT).show();
                }else {
                    /*db.open();
                    db.addCustomer("a", 1, "a", "a", "a", "a", "a", 1, "a", true);
                    db.close();*/

                    final String contractNumber = edtUserContract.getText().toString();
                    final String cByContractNo = "?ContractNumber=";
                    final JsonArrayRequest jsonGetCustomerRequest = new JsonArrayRequest
                            (Request.Method.GET, customerAPI + cByContractNo + contractNumber, null, new Response.Listener<JSONArray>() {
                                @Override
                                public void onResponse(JSONArray response) {
                                    if (response.length() > 0) {
                                        try {
                                            JSONObject rCustomer = response.getJSONObject(0);
                                            Boolean registered = rCustomer.getBoolean("Registered");
                                            String name = rCustomer.getString("Name");
                                            if (!registered) {
                                                String id = rCustomer.getString("id");
                                                int contractNo = Integer.parseInt(contractNumber);
                                                String lastName = rCustomer.getString("LastName");
                                                String email = rCustomer.getString("Email");
                                                String password = rCustomer.getString("Password");
                                                String address = rCustomer.getString("Address");
                                                int phone = rCustomer.getInt("Phone");
                                                String image = rCustomer.getString("Image");
                                                db.open();
                                                db.addCustomer(id, contractNo, name, lastName, email, password, address, phone, image, registered);
                                                db.close();
                                            } else
                                                Toast.makeText(getApplicationContext(), name + " ya esta registrado.", Toast.LENGTH_SHORT).show();
                                        } catch (JSONException e) {
                                            Toast.makeText(myContext, "El numero de contrato no existe.", Toast.LENGTH_SHORT).show();
                                        }
                                    } else
                                        Toast.makeText(getApplicationContext(), "El numero de contrato no existe. No response", Toast.LENGTH_SHORT).show();
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getApplicationContext(), "Oops, algo salio mal. Intente en un momento.", Toast.LENGTH_SHORT).show();
                                }
                            });

                    queue.add(jsonGetCustomerRequest);


                    btnRegisterUser.setVisibility(View.VISIBLE);
                    btnLookForContractNo.setVisibility(View.INVISIBLE);
                    edtUserContract.setEnabled(false);
                    edtUserEmail.setEnabled(true);
                    edtUserPass.setEnabled(true);
                    /*Boolean exists = db.customerExistsByContractNumber(contractNumber);
                    db.close();
                    if(exists){
                        edtUserContract.setEnabled(false);
                        edtUserEmail.setEnabled(true);
                        edtUserPass.setEnabled(true);
                        btnLookForContractNo.setVisibility(View.INVISIBLE);
                        btnRegisterUser.setVisibility(View.VISIBLE);
                    }*/
                }
            }
        });

        btnRegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtUserEmail.getText() == null) {
                    Toast.makeText(getApplicationContext(), "Por favor, escribe un correo.", Toast.LENGTH_SHORT).show();
                } else if (edtUserPass.getText() == null) {
                    Toast.makeText(getApplicationContext(), "Por favor, escribe una contraseña.", Toast.LENGTH_SHORT).show();
                } else {
                    final String contractNumber = edtUserContract.getText().toString();
                    db.open();
                    Boolean exists = db.customerExistsByContractNumber(contractNumber);
                    final Customer customer = db.getCustomerByContractNumber(contractNumber);
                    db.close();
                    if(exists) {
                        final String email = edtUserEmail.getText().toString();
                        final String password = edtUserPass.getText().toString();

                        final JSONObject customerJSONObject = new JSONObject();
                        try {
                            customerJSONObject.put("Email", email);
                            customerJSONObject.put("Password", password);
                            customerJSONObject.put("Registered", true);
                            customerJSONObject.put("id", customer.getId());
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Oops, algo salio mal. Intenta en un momento.", Toast.LENGTH_SHORT);
                        }

                        final JsonObjectRequest jsonUpdateCustomerRequest = new JsonObjectRequest
                                (Request.Method.POST, customerAPI, customerJSONObject, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        Toast.makeText(getApplicationContext(), "Bienvenido, " + customer.getName() + "!", Toast.LENGTH_SHORT).show();
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(getApplicationContext(), "El registro no pudo ser completado, intente en un momento.", Toast.LENGTH_SHORT).show();
                                    }
                                });

                        queue.add(jsonUpdateCustomerRequest);
                    } else {
                        Toast.makeText(getApplicationContext(), "El registro todavia no esta completo, intenta en unos segundos.", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}
