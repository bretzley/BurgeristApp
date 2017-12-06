package com.example.bretz.burgerist.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by bretz on 11/24/2017.
 */

public class Appointment implements Parcelable{

    //Properties
    private String id;
    private String date;
    private String timeSlot;
    private Customer customer;
    private Employee employee;
    private AppointmentDetail aptDetail;
    private String folio;

    //Constructors
    public Appointment(){}

    public Appointment(String id, String date, String timeSlot, Customer customer, Employee employee, AppointmentDetail aptDetail, String folio){
        this.id = id;
        this.date = date;
        this.timeSlot = timeSlot;
        this.customer = customer;
        this.employee = employee;
        this.aptDetail = aptDetail;
        this.folio = folio;
    }

    //Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setEmployee(Employee employee) { this.employee = employee; }

    public void setAptDetail(AppointmentDetail aptDetail) { this.aptDetail = aptDetail; }

    public void setFolio(String folio) { this.folio = folio; }

    //Getters
    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Employee getEmployee() { return employee; }

    public AppointmentDetail getAptDetail() { return aptDetail; }

    public String getFolio() { return  folio; }

    //Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(date);
        dest.writeString(timeSlot);
        dest.writeParcelable(customer, flags);
        dest.writeParcelable(employee, flags);
        dest.writeParcelable(aptDetail, flags);
        dest.writeString(folio);
    }

    protected Appointment(Parcel in) {
        this.id = in.readString();
        this.date = in.readString();
        this.timeSlot = in.readString();
        this.customer = in.readParcelable(getClass().getClassLoader());
        this.employee = in.readParcelable(getClass().getClassLoader());
        this.aptDetail = in.readParcelable(getClass().getClassLoader());
        this.folio = in.readString();
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
