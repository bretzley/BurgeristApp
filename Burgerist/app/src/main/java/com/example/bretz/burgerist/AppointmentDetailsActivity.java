package com.example.bretz.burgerist;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bretz.burgerist.Objects.Appointment;
import com.example.bretz.burgerist.Objects.AppointmentDetail;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class AppointmentDetailsActivity extends AppCompatActivity {

    TextView txtNoContrato, txtFolio, txtFecha, txtHora, txtUbicacion, txtNotasUsuario;
    Button btnIniciarTerminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_details);
        Appointment apt = getIntent().getParcelableExtra("appointment");

        final RequestQueue queue = Volley.newRequestQueue(this);
        String apptDetailAPI = "http://ec2-34-226-122-227.compute-1.amazonaws.com:2403/appointmentdetails";
        String url = apptDetailAPI + "?AppointmentID=" + apt.getId();

        txtNoContrato = (TextView)findViewById(R.id.txtNoContrato);
        txtFolio = (TextView)findViewById(R.id.txtFolio);
        txtFecha = (TextView)findViewById(R.id.txtFecha);
        txtHora = (TextView)findViewById(R.id.txtHora);
        txtUbicacion = (TextView)findViewById(R.id.txtUbicacion);
        txtNotasUsuario = (TextView)findViewById(R.id.txtNotasUsuario);

        txtNoContrato.setText("No. de Contrato: " + apt.getCustomer().getContractNumber());
        txtFolio.setText("Folio: " + apt.getFolio());
        txtFecha.setText("Fecha: " + apt.getDate());
        txtHora.setText("Hora: " + apt.getTimeSlot());
        btnIniciarTerminar = (Button) findViewById(R.id.btnIniciarTerminar);

        final String updtApptDetailAPI = "http://ec2-34-226-122-227.compute-1.amazonaws.com:2403/appointmentdetails";

        final AppointmentDetail aptDetail = new AppointmentDetail();

        final JsonArrayRequest jsonGetCustomerRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if (response.length() > 0) {
                            try {
                                JSONObject apptDet = response.getJSONObject(0);
                                String id = apptDet.getString("id");
                                String apptID = apptDet.getString("AppointmentID");
                                String location = apptDet.has("Location") ? apptDet.getString("Location") : "";
                                String startTime = apptDet.has("StartTime") ? apptDet.getString("StartTime") : "";
                                String endTime = apptDet.has("EndTime") ? apptDet.getString("EndTime") : "";
                                String techNotes = apptDet.has("TechNotes") ? apptDet.getString("TechNotes") : "";
                                String customerNotes = apptDet.has("CustomerNotes") ? apptDet.getString("CustomerNotes") : "";
                                int rating = apptDet.has("Rating") ? apptDet.getInt("Rating") : 0;
                                boolean started = apptDet.has("Started") ? apptDet.getBoolean("Started") : false;
                                boolean finished = apptDet.has("Finished") ? apptDet.getBoolean("Finished") : false;
                                if(finished){
                                    btnIniciarTerminar.setVisibility(View.INVISIBLE);
                                }else if(started){
                                    btnIniciarTerminar.setText("Terminar");
                                }
                                txtUbicacion.setText("Ubicación: " + location);
                                txtNotasUsuario.setText("Notas: " + customerNotes);
                                aptDetail.setApptDetalID(id);
                                aptDetail.setApptID(apptID);
                                aptDetail.setLocation(location);
                                aptDetail.setStartTime(startTime);
                                aptDetail.setEndTime(endTime);
                                aptDetail.setTechNotes(techNotes);
                                aptDetail.setCustomerNotes(customerNotes);
                                aptDetail.setRating(rating);
                                aptDetail.setStarted(started);
                                aptDetail.setFinished(finished);
                            } catch (JSONException e) {
                                makeText(getApplicationContext(), "Algo salió mal, intente de nuevo.", LENGTH_SHORT).show();
                            }
                        } else
                            makeText(getApplicationContext(), "No se pudo obtener la información de la cita.", LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        makeText(getApplicationContext(), "Hay problemas de conexión, intente en un momento.", LENGTH_SHORT).show();
                    }
                });

        queue.add(jsonGetCustomerRequest);

        btnIniciarTerminar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                final JSONObject aptDetJSON = new JSONObject();

                if(btnIniciarTerminar.getText().toString().equals("Iniciar")){
                    try{
                        aptDetJSON.put("id", aptDetail.getApptDetalID());
                        aptDetJSON.put("Started", true);
                        aptDetJSON.put("StartTime", new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
                    }
                    catch (JSONException e){
                        makeText(getApplicationContext(), "Algo salió mal, intente de nuevo.", LENGTH_SHORT).show();
                    }
                    final JsonObjectRequest jsonUpdateCustomerRequest = new JsonObjectRequest
                            (Request.Method.PUT, updtApptDetailAPI, aptDetJSON, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    btnIniciarTerminar.setText("Terminar");
                                    //btnIniciarTerminar.setBackgroundColor(Color.RED);
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    makeText(getApplicationContext(), "Problemas de conexión, intente en un momento.", LENGTH_SHORT).show();
                                }
                            });

                    queue.add(jsonUpdateCustomerRequest);
                }else {
                    try {
                        aptDetJSON.put("id", aptDetail.getApptDetalID());
                        aptDetJSON.put("Finished", true);
                        aptDetJSON.put("EndTime", new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
                    } catch (JSONException e) {
                        makeText(getApplicationContext(), "Algo salió mal, intente de nuevo.", LENGTH_SHORT).show();
                    }

                    final JsonObjectRequest jsonUpdateCustomerRequest = new JsonObjectRequest
                            (Request.Method.PUT, updtApptDetailAPI, aptDetJSON, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    btnIniciarTerminar.setVisibility(View.INVISIBLE);
                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    makeText(getApplicationContext(), "Problemas de conexión, intente en un momento.", LENGTH_SHORT).show();
                                }
                            });

                    queue.add(jsonUpdateCustomerRequest);
                }
            }
        });
    }
}
