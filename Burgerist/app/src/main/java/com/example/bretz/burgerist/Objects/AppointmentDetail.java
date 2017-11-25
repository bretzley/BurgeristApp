package com.example.bretz.burgerist.Objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by bretz on 11/24/2017.
 */

public class AppointmentDetail implements Parcelable{
    private String apptDetalID;
    private String apptID;
    private String startTime;
    private String endTime;
    private String techNotes;
    private String customerNotes;
    private String employeeID;
    private String rating;

    public AppointmentDetail(Parcel in) {
        this.apptDetalID = in.readString();
        this.apptID = in.readString();
        this.startTime = in.readString();
        this.endTime = in.readString();
        this.techNotes = in.readString();
        this.customerNotes = in.readString();
        this.employeeID = in.readString();
        this.rating = in.readString();
    }

    public AppointmentDetail(){}

    public AppointmentDetail(String apptDetalID, String apptID, String startTime, String endTime, String techNotes, String customerNotes, String employeeID, String rating){
        this.apptDetalID = apptDetalID;
        this.apptID = apptID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.techNotes = techNotes;
        this.customerNotes = customerNotes;
        this.employeeID = employeeID;
        this.rating = rating;
    }

    public void setApptDetalID(String apptDetalID) {
        this.apptDetalID = apptDetalID;
    }

    public void setApptID(String apptID) {
        this.apptID = apptID;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setTechNotes(String techNotes) {
        this.techNotes = techNotes;
    }

    public void setCustomerNotes(String customerNotes) {
        this.customerNotes = customerNotes;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getApptDetalID() {
        return apptDetalID;
    }

    public String getApptID() {
        return apptID;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getTechNotes() {
        return techNotes;
    }

    public String getCustomerNotes() {
        return customerNotes;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public String getRating() {
        return rating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(apptDetalID);
        dest.writeString(apptID);
        dest.writeString(startTime);
        dest.writeString(endTime);
        dest.writeString(techNotes);
        dest.writeString(customerNotes);
        dest.writeString(employeeID);
        dest.writeString(rating);
    }

    public static final Parcelable.Creator<AppointmentDetail> CREATOR = new Parcelable.Creator<AppointmentDetail>() {
        @Override
        public AppointmentDetail createFromParcel(Parcel in) {
            return new AppointmentDetail(in);
        }

        @Override
        public AppointmentDetail[] newArray(int size) {
            return new AppointmentDetail[size];
        }
    };
}
