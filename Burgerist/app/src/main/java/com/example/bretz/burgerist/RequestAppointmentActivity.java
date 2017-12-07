package com.example.bretz.burgerist;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bretz.burgerist.Objects.Customer;
import com.example.bretz.burgerist.Objects.TimeSlot;
import com.example.bretz.burgerist.Utils.DBHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class RequestAppointmentActivity extends AppCompatActivity {
    EditText edtApptNotes;
    TextView txtFecha;
    Button btnBookAppt, btnChangeDate, btnPickDate;
    DatePicker datePicker;
    LinearLayout layoutPickDate, layoutBookAppt;
    DBHelper db;
    Spinner spnTimeSlot;
    int day, month, year;
    //SpinnerAdapter timeSlots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_appointment);

        txtFecha = (TextView) findViewById(R.id.txtFecha);
        edtApptNotes = (EditText) findViewById(R.id.edtApptNotes);
        btnBookAppt = (Button) findViewById(R.id.btnBookAppt);
        btnChangeDate = (Button) findViewById(R.id.btnChangeDate);
        btnPickDate = (Button) findViewById(R.id.btnPickDate);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        spnTimeSlot = (Spinner) findViewById(R.id.spnTimeSlots);
        layoutPickDate = (LinearLayout) findViewById(R.id.layoutPickDate);
        layoutBookAppt = (LinearLayout) findViewById(R.id.layoutBookAppt);

        datePicker.setMinDate(System.currentTimeMillis() - 1000 + 86400000);

        final RequestQueue queue = Volley.newRequestQueue(this);
        final String appoitmentAPI = "http://ec2-34-226-122-227.compute-1.amazonaws.com:2403/appointment";
        final String apptDetailAPI = "http://ec2-34-226-122-227.compute-1.amazonaws.com:2403/appointmentdetails";

        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                day = datePicker.getDayOfMonth();
                month = datePicker.getMonth() + 1;
                year = datePicker.getYear();
                String date = year + "-" + checkDigit(month) + "-" + checkDigit(day);

                txtFecha.setText(date.toString());
            }
        });

        db = new DBHelper(this);
        db.open();
        ArrayList<String> lTimeSlots = db.getAllTimeSlots();
        db.close();

        String[] arraySpinner = lTimeSlots.toArray(new String[lTimeSlots.size()]);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        spnTimeSlot.setAdapter(adapter);

        btnPickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutPickDate.setVisibility(View.GONE);
                layoutBookAppt.setVisibility(View.VISIBLE);
            }
        });

        btnChangeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutPickDate.setVisibility(View.VISIBLE);
                layoutBookAppt.setVisibility(View.GONE);
            }
        });

        btnBookAppt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Customer customer = getIntent().getParcelableExtra("customer");
                JSONObject jApptDetail = new JSONObject();
                try {
                    jApptDetail.put("Location", customer.getAddress());
                    jApptDetail.put("CustomerNotes",edtApptNotes.getText().toString());
                }catch (JSONException e){
                    makeText(getApplicationContext(), "Algo salió mal, intente de nuevo.", LENGTH_SHORT);
                }

                final JsonObjectRequest jsonCreateApptDetailRequest = new JsonObjectRequest
                        (Request.Method.POST, apptDetailAPI, jApptDetail, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                JSONObject jAppointment = new JSONObject();
                                try {
                                    jAppointment.put("Date",txtFecha.getText().toString());
                                    db.open();
                                    jAppointment.put("TimeSlotID", db.getTimeSlotIdByTimeFrame(spnTimeSlot.getSelectedItem().toString()));
                                    db.close();
                                    jAppointment.put("CustomerID", customer.getId());
                                    jAppointment.put("ApptDetID", response.getString("id"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                final JsonObjectRequest jsonCreateApptRequest = new JsonObjectRequest
                                        (Request.Method.POST, appoitmentAPI, jAppointment, new Response.Listener<JSONObject>() {
                                            @Override
                                            public void onResponse(JSONObject response) {
                                                makeText(getApplicationContext(), "Cita creada.", LENGTH_SHORT).show();
                                                finish();
                                            }
                                        }, new Response.ErrorListener() {
                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                makeText(getApplicationContext(), "Problemas de conexión, intente en un momento.", LENGTH_SHORT).show();
                                            }
                                        });

                                queue.add(jsonCreateApptRequest );

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                makeText(getApplicationContext(), "Problemas de conexión, intente en un momento.", LENGTH_SHORT).show();
                            }
                        });

                queue.add(jsonCreateApptDetailRequest);
            }
        });

    }


    public String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }
}