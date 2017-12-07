package com.example.bretz.burgerist.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bretz.burgerist.Objects.Appointment;
import com.example.bretz.burgerist.R;

/**
 * Created by bretz on 11/24/2017.
 */

public class AppointmentAdapter extends ArrayAdapter<Appointment> {
    public AppointmentAdapter(Context context) {
        super(context, R.layout.appointment_layout, R.id.txtFolio);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View oView = super.getView(position, convertView, parent);

        TextView txtFolio = (TextView) oView.findViewById(R.id.txtFolio);
        TextView txtCustomer = (TextView) oView.findViewById(R.id.txtCustomer);
        TextView txtDate = (TextView) oView.findViewById(R.id.txtDate);
        TextView txtTimeFrame = (TextView) oView.findViewById(R.id.txtTimeFrame);
        TextView txtStatus = (TextView) oView.findViewById(R.id.txtStatus);


        Appointment oAppointment = this.getItem(position);

        txtFolio.setText("Folio: " + oAppointment.getFolio());
        txtCustomer.setText("Cliente: " + oAppointment.getCustomer().getName());
        txtDate.setText("Fecha: " + oAppointment.getDate().toString());
        txtTimeFrame.setText("Horario: " + oAppointment.getTimeSlot());
        String status="";
        if(oAppointment.getAptDetail().getFinished())
            status = "Terminado";
        else if(oAppointment.getAptDetail().getStarted())
            status = "En proceso";
        else
            status = "Pendiente";

        txtStatus.setText("Status: " + status);

        return oView;
    }
}