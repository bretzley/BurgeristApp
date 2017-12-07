package com.example.bretz.burgerist;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.bretz.burgerist.Objects.Customer;
import com.example.bretz.burgerist.Utils.DBHelper;

import org.json.JSONArray;
import org.json.JSONObject;

public class EditProfileActivity extends AppCompatActivity
{

    Button btnEdtProfile;
    EditText edtPrEmail, edtPrPassword, edtPrAddress, edtPrPhone;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        btnEdtProfile = (Button)findViewById(R.id.btnEdtProfile);
        edtPrEmail = (EditText)findViewById(R.id.edtPrEmail);
        edtPrPassword = (EditText)findViewById(R.id.edtPrPassword);
        edtPrAddress = (EditText)findViewById(R.id.edtPrAddress);
        edtPrPhone = (EditText)findViewById(R.id.edtPrPhone);

        Customer customer = getIntent().getParcelableExtra("data");

        edtPrEmail.setText(customer.getEmail());
        edtPrPassword.setText(customer.getPassword());
        edtPrAddress.setText(customer.getAddress());
        edtPrPhone.setText(customer.getPhone());

        db = new DBHelper(this);

        final RequestQueue queue = Volley.newRequestQueue(this);
        final String customerAPI = "http://ec2-34-226-122-227.compute-1.amazonaws.com:2403/customer";
        final Context myContext = this;

        btnEdtProfile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {


                final JsonArrayRequest jsonGetCustomerRequest = new JsonArrayRequest
                        (Request.Method.GET, customerAPI, null, new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response)
                            {
                                    try {
                                        JSONObject rCustomer = response.getJSONObject(0);

                                        String updatedEmail = edtPrEmail.getText().toString();
                                        String updatedPassword = edtPrPassword.getText().toString();
                                        String updatedAddress = edtPrAddress.getText().toString();
                                        String updatedPhone = edtPrPhone.getText().toString();

                                        JSONObject customerJSONObject = new JSONObject();
                                        customerJSONObject.put("Email", updatedEmail);
                                        customerJSONObject.put("Password", updatedPassword);
                                        customerJSONObject.put("Address", updatedAddress);
                                        customerJSONObject.put("Phone", updatedPhone);


                                    } catch (Exception e) {
                                    }

                            }


                        });
            }
        });

    }
}
