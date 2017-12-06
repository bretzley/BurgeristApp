package com.example.bretz.burgerist;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    TextView txtNoContrato, txtFolio, txtFecha, txtHora, txtUbicacion, txtNotasUsuario, txtStatus;
    Button btnIniciarTerminar, btnSaveNotes;
    EditText edtTechNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_details);
        final Appointment apt = getIntent().getParcelableExtra("appointment");

        final RequestQueue queue = Volley.newRequestQueue(this);

        txtNoContrato = (TextView)findViewById(R.id.txtNoContrato);
        txtFolio = (TextView)findViewById(R.id.txtFolio);
        txtFecha = (TextView)findViewById(R.id.txtFecha);
        txtHora = (TextView)findViewById(R.id.txtHora);
        txtUbicacion = (TextView)findViewById(R.id.txtUbicacion);
        txtNotasUsuario = (TextView)findViewById(R.id.txtNotasUsuario);
        txtStatus = (TextView)findViewById(R.id.txtStatus);
        btnIniciarTerminar = (Button) findViewById(R.id.btnIniciarTerminar);
        btnSaveNotes = (Button)findViewById(R.id.btnSaveNotes);
        edtTechNotes = (EditText)findViewById(R.id.edtTechNotes);

        txtNoContrato.setText("No. de Contrato: " + apt.getCustomer().getContractNumber());
        txtFolio.setText("Folio: " + apt.getFolio());
        txtFecha.setText("Fecha: " + apt.getDate());
        txtHora.setText("Hora: " + apt.getTimeSlot());

        if(apt.getAptDetail().getFinished()){
            txtStatus.setText("Status: Terminado");
            btnIniciarTerminar.setVisibility(View.GONE);
        }else if(apt.getAptDetail().getStarted()){
            btnIniciarTerminar.setText("Terminar");
            txtStatus.setText("Status: En proceso");
        }else
            txtStatus.setText("Status: Pendiente");

        txtUbicacion.setText("Ubicación: " + apt.getAptDetail().getLocation());
        txtNotasUsuario.setText("Notas: " + apt.getAptDetail().getCustomerNotes());

        final String updtApptDetailAPI = "http://ec2-34-226-122-227.compute-1.amazonaws.com:2403/appointmentdetails";

        btnIniciarTerminar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                final JSONObject aptDetJSON = new JSONObject();

                if(btnIniciarTerminar.getText().toString().equals("Iniciar")){
                    try{
                        aptDetJSON.put("id", apt.getAptDetail().getApptDetalID());
                        aptDetJSON.put("Started", true);
                        aptDetJSON.put("StartTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                    }
                    catch (JSONException e){
                        makeText(getApplicationContext(), "Algo salió mal, intente de nuevo.", LENGTH_SHORT).show();
                    }
                    final JsonObjectRequest jsonUpdateCustomerRequest = new JsonObjectRequest
                            (Request.Method.PUT, updtApptDetailAPI, aptDetJSON, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    btnIniciarTerminar.setText("Terminar");
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
                        aptDetJSON.put("id", apt.getAptDetail().getApptDetalID());
                        aptDetJSON.put("Finished", true);
                        aptDetJSON.put("EndTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                    } catch (JSONException e) {
                        makeText(getApplicationContext(), "Algo salió mal, intente de nuevo.", LENGTH_SHORT).show();
                    }

                    final JsonObjectRequest jsonUpdateCustomerRequest = new JsonObjectRequest
                            (Request.Method.PUT, updtApptDetailAPI, aptDetJSON, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    btnIniciarTerminar.setVisibility(View.GONE);
                                    btnSaveNotes.setVisibility(View.VISIBLE);
                                    edtTechNotes.setVisibility(View.VISIBLE);
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

        btnSaveNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String techNotes = edtTechNotes.getText().toString();
                if(techNotes.equals("")){
                    finish();
                }else{
                    final JSONObject aptDetJSON = new JSONObject();
                    try {
                        aptDetJSON.put("id", apt.getAptDetail().getApptDetalID());
                        aptDetJSON.put("TechNotes", techNotes);
                    } catch (JSONException e) {
                        makeText(getApplicationContext(), "Algo salió mal, intente de nuevo.", LENGTH_SHORT).show();
                    }

                    final JsonObjectRequest jsonUpdateCustomerRequest = new JsonObjectRequest
                            (Request.Method.PUT, updtApptDetailAPI, aptDetJSON, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    finish();
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
