package com.example.bretz.burgerist.Objects;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by bretz on 11/17/2017.
 */

public class Customer implements Parcelable{
    private String CustomerId;
    private String Id;
    private String ContractNumber;
    private String FirstName;
    private String MiddleName;
    private String LastName;
    private String Email;
    private String Password;
    private String Address;
    private String Phone;
    private String CustomerImage;


    public Customer(Parcel in) {
        this.CustomerId = in.readString();
        this.Id = in.readString();
        this.ContractNumber = in.readString();
        this.FirstName = in.readString();
        this.MiddleName = in.readString();
        this.LastName = in.readString();
        this.Email = in.readString();
        this.Password = in.readString();
        this.Address = in.readString();
        this.Phone = in.readString();
        this.CustomerImage = in.readString();
    }

    public Customer(){}

    public Customer(String CustomerId, String Id, String ContractNumber, String FirstName, String MiddleName, String LastName, String Email, String Password, String Address, String Phone, String CustomerImage){
        this.CustomerId = CustomerId;
        this.Id = Id;
        this.ContractNumber = ContractNumber;
        this.FirstName = FirstName;
        this.MiddleName = MiddleName;
        this.LastName = LastName;
        this.Email = Email;
        this.Password = Password;
        this.Address = Address;
        this.Phone  = Phone;
        this.CustomerImage = CustomerImage;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public String getId() {
        return Id;
    }

    public String getContractNumber() {
        return ContractNumber;
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

    public String getAddress() {
        return Address;
    }

    public String getPhone() {
        return Phone;
    }

    public String getCustomerImage() {
        return CustomerImage;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setContractNumber(String contractNumber) {
        ContractNumber = contractNumber;
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

    public void setAddress(String address) {
        Address = address;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public void setCustomerImage(String customerImage) {
        CustomerImage = customerImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(CustomerId);
        dest.writeString(Id);
        dest.writeString(ContractNumber);
        dest.writeString(FirstName);
        dest.writeString(MiddleName);
        dest.writeString(LastName);
        dest.writeString(Email);
        dest.writeString(Password);
        dest.writeString(Address);
        dest.writeString(Phone);
        dest.writeString(CustomerImage);
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
