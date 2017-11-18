package com.example.bretz.burgerist.Objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by bretz on 11/17/2017.
 */

public class Employee implements Parcelable{
    private String EmployeeId;
    private String Id;
    private String EmployeeCode;
    private String FirstName;
    private String MiddleName;
    private String LastName;
    private String Email;
    private String Password;
    private String Phone;
    private String EmployeeImage;


    public Employee(Parcel in) {
        this.EmployeeId = in.readString();
        this.Id = in.readString();
        this.EmployeeCode = in.readString();
        this.FirstName = in.readString();
        this.MiddleName = in.readString();
        this.LastName = in.readString();
        this.Email = in.readString();
        this.Password = in.readString();
        this.Phone = in.readString();
        this.EmployeeImage = in.readString();
    }

    public Employee(){}

    public Employee(String EmployeeId, String Id, String EmployeeCode, String FirstName, String MiddleName, String LastName, String Email, String Password, String Phone, String EmployeeImage){
        this.EmployeeId = EmployeeId;
        this.Id = Id;
        this.EmployeeCode = EmployeeCode;
        this.FirstName = FirstName;
        this.MiddleName = MiddleName;
        this.LastName = LastName;
        this.Email = Email;
        this.Password = Password;
        this.Phone  = Phone;
        this.EmployeeImage = EmployeeImage;
    }

    public String getEmployeeId() {
        return EmployeeId;
    }

    public String getId() {
        return Id;
    }

    public String getEmployeeCode() {
        return EmployeeCode;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public String getPhone() {
        return Phone;
    }

    public String getEmployeeImage() {
        return EmployeeImage;
    }

    public void setEmployeeId(String employeeId) {
        EmployeeId = employeeId;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setEmployeeCode(String employeeCode) {
        EmployeeCode = employeeCode;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public void setEmployeeImage(String employeeImage) {
        EmployeeImage = employeeImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(EmployeeId);
        dest.writeString(Id);
        dest.writeString(EmployeeCode);
        dest.writeString(FirstName);
        dest.writeString(MiddleName);
        dest.writeString(LastName);
        dest.writeString(Email);
        dest.writeString(Password);
        dest.writeString(Phone);
        dest.writeString(EmployeeImage);
    }

    public static final Creator<Employee> CREATOR = new Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel in) {
            return new Employee(in);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };
}
