package com.example.bretz.burgerist.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bretz.burgerist.Objects.Customer;
import com.example.bretz.burgerist.R;

/**
 * Created by bretz on 11/17/2017.
 */

public class CustomerAdapter extends ArrayAdapter<Customer>{
    public CustomerAdapter(Context context) {
        super(context,  R.layout.appointment_layout, R.id.txtFolio);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View oView = super.getView(position, convertView, parent);

        TextView txtName = (TextView) oView.findViewById(R.id.txtUserName);
        TextView txtContract = (TextView) oView.findViewById(R.id.txtUserContract);
        TextView txtPhone = (TextView) oView.findViewById(R.id.txtUserPhone);
        TextView txtEmail = (TextView) oView.findViewById(R.id.txtUserEmail);

        final Customer customer = this.getItem(position);
        txtName.setText(customer.getName().toString() + " " + customer.getLastName().toString());
        txtContract.setText(customer.getContractNumber() + "");
        txtPhone.setText(customer.getPhone() + "");
        txtEmail.setText(customer.getEmail().toString());
        return oView;
    }
}
