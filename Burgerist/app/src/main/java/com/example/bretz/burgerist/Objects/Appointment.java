package com.example.bretz.burgerist.Objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by bretz on 11/24/2017.
 */

public class Appointment implements Parcelable{
    private String AptID;
    private String date;
    private String timeSlotID;
    private String customerID;


    public Appointment(Parcel in) {
        this.AptID = in.readString();
        this.date = in.readString();
        this.timeSlotID = in.readString();
        this.customerID = in.readString();
    }

    public Appointment(){}

    public Appointment(String AptId, String date, String timeSlotID, String customerID){
        this.AptID = AptId;
        this.date = date;
        this.timeSlotID = timeSlotID;
        this.customerID = customerID;
    }
    public void setAptID(String aptID) {
        AptID = aptID;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTimeSlotID(String timeSlotID) {
        this.timeSlotID = timeSlotID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getAptID() {
        return AptID;
    }

    public String getDate() {
        return date;
    }

    public String getTimeSlotID() {
        return timeSlotID;
    }

    public String getCustomerID() {
        return customerID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(AptID);
        dest.writeString(date);
        dest.writeString(timeSlotID);
        dest.writeString(customerID);
       ;
    }

    public static final Parcelable.Creator<Appointment> CREATOR = new Parcelable.Creator<Appointment>() {
        @Override
        public Appointment createFromParcel(Parcel in) {
            return new Appointment(in);
        }

        @Override
        public Appointment[] newArray(int size) {
            return new Appointment[size];
        }
    };
}
