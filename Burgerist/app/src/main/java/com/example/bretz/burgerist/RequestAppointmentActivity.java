package com.example.bretz.burgerist;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.bretz.burgerist.Utils.DBHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class RequestAppointmentActivity extends AppCompatActivity {
    EditText edtApptSelected, edtApptNotes;
    Button btnBookAppt;
    DatePicker datePicker;
    TimePicker timePicker;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_appointment);

        edtApptSelected = (EditText)findViewById(R.id.edtApptSelected);
        edtApptNotes = (EditText)findViewById(R.id.edtApptNotes);
        btnBookAppt = (Button)findViewById(R.id.btnBookAppt);
        datePicker = (DatePicker)findViewById(R.id.datePicker);
        timePicker = (TimePicker)findViewById(R.id.timePicker);
        int day  = datePicker.getDayOfMonth();
        int month= datePicker.getMonth() + 1;
        int year = datePicker.getYear();
        db = new DBHelper(this);

        final RequestQueue queue = Volley.newRequestQueue(this);
        final String appoitmentAPI = "http://ec2-34-226-122-227.compute-1.amazonaws.com:2403/appointment";
        final Context myContext = this;


        DatePickerDialog datePickerDialog = new DatePickerDialog(getApplicationContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // dd.MM.yyyy
                edtApptSelected.setText(dayOfMonth + "." + (monthOfYear + 1) + "." + year);
            }
        }, year, month, day);
        datePickerDialog.show();


        btnBookAppt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtApptSelected.getText() == null) {
                    makeText(getApplicationContext(), "Por favor seleccione una fecha para solicitar cita.", LENGTH_SHORT).show();
                } else {
                    final String date = datePicker.getMonth()+"/"+datePicker.getDayOfMonth()+"/"+datePicker.getYear();
                    final String timeSlot = timePicker.getHour()+":"+timePicker.getMinute();
                    final JsonArrayRequest jsonPostAppointmentRequest = new JsonArrayRequest
                            (Request.Method.POST, appoitmentAPI + date + timeSlot, null, new Response.Listener<JSONArray>() {
                                @Override
                                public void onResponse(JSONArray response) {
                                    if (response.length() > 0) {
                                        try {
                                            JSONObject rAppointment = response.(0);
                                            String id = rAppointment.getString("id");

                                            db.open();
                                            db.addAppointment(id, date, timeSlot, customerId);
                                            db.close();
                                            Intent intent = new Intent(getApplicationContext(), AppointmentDetailsActivity.class);
                                            startActivity(intent);

                                        } catch (JSONException e) {
                                            makeText(myContext, "No se pudo generar solicitud de cita.", LENGTH_SHORT).show();
                                        }
                                    } else
                                        makeText(getApplicationContext(), "No se pudo generar solicitud de cita.", LENGTH_SHORT).show();
                            }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    makeText(getApplicationContext(), "Oops, algo salio mal. Intente en un momento.", LENGTH_SHORT).show();
                                }
                            });

                    queue.add(jsonPostAppointmentRequest);
                }

            }
        });

    }


    /*public void setOnDateChangedListener (DatePicker datePicker, int day, int month, int year) {
        this.datePicker = datePicker;
        day  = datePicker.getDayOfMonth();
        month= datePicker.getMonth() + 1;
        year = datePicker.getYear();
        String date = checkDigit(month)+"/"+checkDigit(day)+"/"+year;

        edtApptSelected.setText(date.toString());
    }*/



    public String checkDigit(int number)
    {
        return number<=9?"0"+number:String.valueOf(number);
    }
}
