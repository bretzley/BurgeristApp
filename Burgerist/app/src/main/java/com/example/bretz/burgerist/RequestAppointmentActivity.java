package com.example.bretz.burgerist;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.bretz.burgerist.Utils.DBHelper;

import java.util.Calendar;

public class RequestAppointmentActivity extends AppCompatActivity {
    EditText edtApptSelected, edtApptNotes;
    Button btnBookAppt;
    DatePicker datePicker;
    DBHelper db;
    Spinner spnTimeSlot;
    int day, month, year;
    static final int DATE_DIALOG_ID = 999;
    SpinnerAdapter timeSlots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_appointment);

        edtApptSelected = (EditText)findViewById(R.id.edtApptSelected);
        edtApptNotes = (EditText)findViewById(R.id.edtApptNotes);
        btnBookAppt = (Button)findViewById(R.id.btnBookAppt);
        datePicker = (DatePicker)findViewById(R.id.datePicker);

        spnTimeSlot = (Spinner)findViewById(R.id.spnTimeSlots);
        final Calendar calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);



        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                day  = datePicker.getDayOfMonth();
                month= datePicker.getMonth()+1;
                year = datePicker.getYear();
                String date = checkDigit(month)+"/"+checkDigit(day)+"/"+year;

                edtApptSelected.setText(date.toString());
            }
        });





        timeSlots = new SpinnerAdapter() {
            @Override
            public View getDropDownView(int i, View view, ViewGroup viewGroup) {
                return null;
            }

            @Override
            public void registerDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return 0;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                return null;
            }

            @Override
            public int getItemViewType(int i) {
                return 0;
            }

            @Override
            public int getViewTypeCount() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }
        };
        //


        db = new DBHelper(this);


        final RequestQueue queue = Volley.newRequestQueue(this);
        final String appoitmentAPI = "http://ec2-34-226-122-227.compute-1.amazonaws.com:2403/appointment";
        //final Context myContext = this;
        //Customer customer = getIntent().getParcelableExtra("data");

//        edtApptSelected.setText(new StringBuilder()
//                .append(month + 1).append("/").append(day).append("/")
//                .append(year).append(" "));

        //datePicker.init(year, month, day, null);



/*
        btnBookAppt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtApptSelected.getText() == null) {
                    makeText(getApplicationContext(), "Por favor seleccione una fecha para solicitar cita.", Toast.LENGTH_SHORT).show();
                } else {
                    final String date = datePicker.getMonth()+"/"+datePicker.getDayOfMonth()+"/"+datePicker.getYear();
                    final String timeSlot = timePicker.getHour()+":"+timePicker.getMinute();
                    final JsonArrayRequest jsonPostAppointmentRequest = new JsonArrayRequest
                            (Request.Method.POST, appoitmentAPI + date + timeSlot, null, new Response.Listener<JSONArray>() {
                                @Override
                                public void onResponse(JSONArray response) {
                                    if (response.length() > 0) {
                                        try {
                                            JSONObject rAppointment = response.getJSONObject(0);
                                            String id = rAppointment.getString("id");

                                            db.open();
                                            db.addAppointment(id, date, timeSlot, customer.getId());
                                            db.close();
                                            Intent intent = new Intent(getApplicationContext(), AppointmentDetailsActivity.class);
                                            startActivity(intent);

                                        } catch (JSONException e) {
                                            makeText(myContext, "No se pudo generar solicitud de cita.", Toast.LENGTH_SHORT).show();
                                        }
                                    } else
                                        makeText(getApplicationContext(), "No se pudo generar solicitud de cita.", Toast.LENGTH_SHORT).show();
                            }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    makeText(getApplicationContext(), "Oops, algo salio mal. Intente en un momento.", Toast.LENGTH_SHORT).show();
                                }
                            });

                    queue.add(jsonPostAppointmentRequest);
                }

            }
        });*/

    }





    public String checkDigit(int number)
    {
        return number<=9?"0"+number:String.valueOf(number);
    }
}
