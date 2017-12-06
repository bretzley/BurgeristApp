package com.example.bretz.burgerist.Objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by bretz on 11/24/2017.
 */

public class AppointmentDetail implements Parcelable{
    //Properties
    private String apptDetalID;
    private String apptID;
    private String location;
    private String startTime;
    private String endTime;
    private String techNotes;
    private String customerNotes;
    private int rating;
    private boolean started;
    private boolean finished;

    //Constructors
    public AppointmentDetail(){}

    public AppointmentDetail(String apptDetalID, String apptID, String location, String startTime, String endTime, String techNotes, String customerNotes, int rating, boolean started, boolean finished){
        this.apptDetalID = apptDetalID;
        this.apptID = apptID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.techNotes = techNotes;
        this.customerNotes = customerNotes;
        this.location = location;
        this.rating = rating;
        this.started = started;
        this.finished = finished;
    }

    //Setters
    public void setApptDetalID(String apptDetalID) {
        this.apptDetalID = apptDetalID;
    }

    public void setApptID(String apptID) {
        this.apptID = apptID;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }


    //Getters
    public String getApptDetalID() {
        return apptDetalID;
    }

    public String getApptID() {
        return apptID;
    }

    public String getLocation() {
        return location;
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

    public int getRating() {
        return rating;
    }

    public boolean getStarted() {
        return started;
    }

    public boolean getFinished() {
        return finished;
    }

    //Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(apptDetalID);
        dest.writeString(apptID);
        dest.writeString(location);
        dest.writeString(startTime);
        dest.writeString(endTime);
        dest.writeString(techNotes);
        dest.writeString(customerNotes);
        dest.writeInt(rating);
        dest.writeByte((byte) (started ? 1 : 0));
        dest.writeByte((byte) (finished ? 1 : 0));
    }

    public AppointmentDetail(Parcel in) {
        this.apptDetalID = in.readString();
        this.apptID = in.readString();
        this.location = in.readString();
        this.startTime = in.readString();
        this.endTime = in.readString();
        this.techNotes = in.readString();
        this.customerNotes = in.readString();
        this.rating = in.readInt();
        this.started = in.readByte() != 0;
        this.finished = in.readByte() != 0;
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
