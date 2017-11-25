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
        super(context, R.layout.profile_rows, R.id.txtTitleAppt);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View oView = super.getView(position, convertView, parent);
        TextView txtApptId = (TextView) oView.findViewById(R.id.txtApptId);
        TextView txtDateAppt = (TextView) oView.findViewById(R.id.txtDateAppt);


        final Appointment appointment = this.getItem(position);
        txtApptId.setText(appointment.getAptID().toString()+"");
        txtDateAppt.setText(appointment.getDate().toString() + "");
        return oView;
    }
}