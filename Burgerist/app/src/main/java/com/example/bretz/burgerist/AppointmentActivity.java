package com.example.bretz.burgerist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.bretz.burgerist.Adapters.AppointmentAdapter;
import com.example.bretz.burgerist.Objects.Appointment;
import com.example.bretz.burgerist.Objects.Customer;
import com.example.bretz.burgerist.Objects.Employee;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class AppointmentActivity extends AppCompatActivity {

    ListView lstAppointments;
    AppointmentAdapter apptAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        lstAppointments = (ListView)findViewById(R.id.lstAppts);
        apptAdapter = new AppointmentAdapter(this);
        lstAppointments.setAdapter(apptAdapter);

        final ArrayList<Appointment> appointments = new ArrayList<>();

        final RequestQueue queue = Volley.newRequestQueue(this);
        final String customerAPI = "http://ec2-34-226-122-227.compute-1.amazonaws.com:2403/appointment";
        final Context myContext = this;
        final String eByEmployeeID = "?EmployeeID=";
        //employeeCode = getExtra from intent
        final String _employeeID = "c294b222160018d2";

        final JsonArrayRequest jsonGetCustomerRequest = new JsonArrayRequest
                (Request.Method.GET, customerAPI + eByEmployeeID + _employeeID, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                            try {
                                apptAdapter.clear();
                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject appointment = response.getJSONObject(i);
                                    String id = appointment.getString("id");
                                    String date = appointment.getString("Date");
                                    String timeSlot = appointment.getString("TimeSlotID");
                                    JSONObject jCustomer = appointment.getJSONObject("CustomerID");
                                    Customer customer = new Customer(jCustomer);
                                    JSONObject jEmployee = appointment.getJSONObject("EmployeeID");
                                    Employee employee = new Employee(jEmployee);
                                    Appointment apt = new Appointment(id, date, timeSlot, customer, employee);
                                    appointments.add(apt);
                                    apptAdapter.add(apt);
                                }
                                apptAdapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                makeText(myContext, "Algo salio mal, intente de nuevo.", LENGTH_SHORT).show();
                            }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        makeText(myContext, "Oops, algo salio mal. Intente en un momento.", LENGTH_SHORT).show();
                    }
                });

        queue.add(jsonGetCustomerRequest);



        lstAppointments.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Appointment apt = appointments.get(i);
                Intent intent = new Intent(getApplicationContext(), AppointmentDetailsActivity.class);
                intent.putExtra("appointment", apt);
                startActivity(intent);
            }
        });

    }


}
