package com.example.bretz.burgerist.Objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by bretz on 11/17/2017.
 */

public class Customer implements Parcelable{
    private String Id;
    private int ContractNumber;
    private String Name;
    private String LastName;
    private String Email;
    private String Password;
    private String Address;
    private int Phone;
    private String CustomerImage;
    private Boolean Registered;

    public Customer(Parcel in) {
        this.Id = in.readString();
        this.ContractNumber = in.readInt();
        this.Name = in.readString();
        this.LastName = in.readString();
        this.Email = in.readString();
        this.Password = in.readString();
        this.Address = in.readString();
        this.Phone = in.readInt();
        this.CustomerImage = in.readString();
        this.Registered = in.readByte() != 0;
    }

    public Customer(){}

    public Customer(String Id, int ContractNumber, String Name, String LastName, String Email, String Password, String Address, int Phone, String CustomerImage, Boolean Registered){
        this.Id = Id;
        this.ContractNumber = ContractNumber;
        this.Name = Name;
        this.LastName = LastName;
        this.Email = Email;
        this.Password = Password;
        this.Address = Address;
        this.Phone  = Phone;
        this.CustomerImage = CustomerImage;
        this.Registered = Registered;
    }

    public String getId() {
        return Id;
    }

    public int getContractNumber() {
        return ContractNumber;
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

    public String getAddress() {
        return Address;
    }

    public int getPhone() {
        return Phone;
    }

    public String getCustomerImage() {
        return CustomerImage;
    }

    public Boolean getRegistered() {
        return Registered;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setContractNumber(int contractNumber) {
        ContractNumber = contractNumber;
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

    public void setAddress(String address) {
        Address = address;
    }

    public void setPhone(int phone) {
        Phone = phone;
    }

    public void setCustomerImage(String customerImage) {
        CustomerImage = customerImage;
    }

    public void setRegistered(Boolean registered) {
        Registered = registered;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Id);
        dest.writeInt(ContractNumber);
        dest.writeString(Name);
        dest.writeString(LastName);
        dest.writeString(Email);
        dest.writeString(Password);
        dest.writeString(Address);
        dest.writeInt(Phone);
        dest.writeString(CustomerImage);
        dest.writeByte((byte) (Registered ? 1 : 0));
    }

    public static final Creator<Customer> CREATOR = new Creator<Customer>() {
        @Override
        public Customer createFromParcel(Parcel in) {
            return new Customer(in);
        }

        @Override
        public Customer[] newArray(int size) {
            return new Customer[size];
        }
    };
}
