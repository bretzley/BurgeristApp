package com.example.bretz.burgerist.Objects;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by bretz on 11/17/2017.
 */

public class Employee implements Parcelable{
    //Properties
    private String Id;
    private int EmployeeCode;
    private String Name;
    private String LastName;
    private String Email;
    private String Password;
    private int Phone;
    private String EmployeeImage;
    private Boolean Registered;

    //Constructors
    public Employee(){}

    public Employee(String Id, int EmployeeCode, String Name, String LastName, String Email, String Password, int Phone, String EmployeeImage, Boolean Registered){
        this.Id = Id;
        this.EmployeeCode = EmployeeCode;
        this.Name = Name;
        this.LastName = LastName;
        this.Email = Email;
        this.Password = Password;
        this.Phone  = Phone;
        this.EmployeeImage = EmployeeImage;
        this.Registered = Registered;
    }

    public Employee(JSONObject jCustomer){
        try{
            this.Id = jCustomer.getString("id");
            this.EmployeeCode = jCustomer.getInt("EmployeeNumber");
            this.Name = jCustomer.getString("Name");
            this.LastName = jCustomer.getString("LastName");;
            this.Email = jCustomer.getString("Email");;
            this.Password = jCustomer.getString("Password");;
            this.Phone = jCustomer.getInt("Phone");;
            this.EmployeeImage = jCustomer.getString("Image");;
            this.Registered = jCustomer.getBoolean("Registered");;
        }
        catch(JSONException e){

        }
    }

    //Getters
    public String getId() {
        return Id;
    }

    public int getEmployeeCode() {
        return EmployeeCode;
    }

    public String getName() {
        return Name;
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

    public int getPhone() {
        return Phone;
    }

    public String getEmployeeImage() {
        return EmployeeImage;
    }

    public Boolean getRegistered() {
        return Registered;
    }

    //Setters
    public void setId(String id) {
        Id = id;
    }

    public void setEmployeeCode(int employeeCode) {
        EmployeeCode = employeeCode;
    }

    public void setName(String name) {
        Name = name;
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

    public void setPhone(int phone) {
        Phone = phone;
    }

    public void setEmployeeImage(String employeeImage) {
        EmployeeImage = employeeImage;
    }

    public void setRegistered(Boolean registered) {
        Registered = registered;
    }

    //Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Id);
        dest.writeInt(EmployeeCode);
        dest.writeString(Name);
        dest.writeString(LastName);
        dest.writeString(Email);
        dest.writeString(Password);
        dest.writeInt(Phone);
        dest.writeString(EmployeeImage);
        dest.writeByte((byte) (Registered ? 1 : 0));
    }

    public Employee(Parcel in) {
        this.Id = in.readString();
        this.EmployeeCode = in.readInt();
        this.Name = in.readString();
        this.LastName = in.readString();
        this.Email = in.readString();
        this.Password = in.readString();
        this.Phone = in.readInt();
        this.EmployeeImage = in.readString();
        this.Registered = in.readByte() != 0;
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
