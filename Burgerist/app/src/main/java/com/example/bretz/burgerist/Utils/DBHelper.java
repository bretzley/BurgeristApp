package com.example.bretz.burgerist.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.bretz.burgerist.Objects.Appointment;
import com.example.bretz.burgerist.Objects.Customer;
import com.example.bretz.burgerist.Objects.Employee;

/**
 * Created by bretz on 11/17/2017.
 */

public class DBHelper {

    private DBUtils dbHelper;
    private SQLiteDatabase database;
    private String[] CUSTOMER_TABLE_COLUMNS =
            {
                    DBUtils.CUSTOMER_ID,
                    DBUtils.CUSTOMER_CONTRACT,
                    DBUtils.CUSTOMER_NAME,
                    DBUtils.CUSTOMER_LASTNAME,
                    DBUtils.CUSTOMER_EMAIL,
                    DBUtils.CUSTOMER_PASSWORD,
                    DBUtils.CUSTOMER_ADDRESS,
                    DBUtils.CUSTOMER_PHONE,
                    DBUtils.CUSTOMER_IMAGE,
                    DBUtils.CUSTOMER_REGISTERED
            };

    private String[] EMPLOYEE_TABLE_COLUMNS =
            {
                    DBUtils.EMPLOYEE_ID,
                    DBUtils.EMPLOYEE_CODE,
                    DBUtils.EMPLOYEE_NAME,
                    DBUtils.EMPLOYEE_LASTNAME,
                    DBUtils.EMPLOYEE_EMAIL,
                    DBUtils.EMPLOYEE_PASSWORD,
                    DBUtils.EMPLOYEE_PHONE,
                    DBUtils.EMPLOYEE_IMAGE,
                    DBUtils.EMPLOYEE_REGISTERED
            };

    private String[] APPOINTMENT_TABLE_COLUMNS =
            {
                    DBUtils.APPOINTMENT_ID,
                    DBUtils.APPOINTMENT_DATE,
                    DBUtils.APPOINTMENT_TIMESLOTID,
                    DBUtils.APPOINTMENT_CUSTOMERID

            };


    public DBHelper(Context context) {
        dbHelper = new DBUtils(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        database.close();
    }

    public Customer addCustomer(String Id, int ContractNumber, String Name, String LastName, String Email, String Password, String Address, int Phone, String CustomerImage, Boolean Registered) {
        ContentValues values = new ContentValues();
        values.put(DBUtils.CUSTOMER_ID, Id);
        values.put(DBUtils.CUSTOMER_CONTRACT, ContractNumber);
        values.put(DBUtils.CUSTOMER_NAME, Name);
        values.put(DBUtils.CUSTOMER_LASTNAME, LastName);
        values.put(DBUtils.CUSTOMER_EMAIL, Email);
        values.put(DBUtils.CUSTOMER_PASSWORD, Password);
        values.put(DBUtils.CUSTOMER_ADDRESS, Address );
        values.put(DBUtils.CUSTOMER_PHONE, Phone);
        values.put(DBUtils.CUSTOMER_IMAGE, CustomerImage);
        values.put(DBUtils.CUSTOMER_REGISTERED, Registered ? 1 : 0);

        database.insert(DBUtils.CUSTOMER_TABLE, null, values);

        Cursor cursor = database.query(DBUtils.CUSTOMER_TABLE,
                CUSTOMER_TABLE_COLUMNS,
                DBUtils.CUSTOMER_ID + " =?",
                new String[]{Id}, null, null, null);
        cursor.moveToFirst();
        Customer customer = parseCustomer(cursor);
        cursor.close();
        return customer;
    }

    public Appointment addAppointment(String ID, String date, String TimeSlotID, String CustomerID) {
        ContentValues values = new ContentValues();
        values.put(DBUtils.APPOINTMENT_ID, ID);
        values.put(DBUtils.APPOINTMENT_DATE, date);
        values.put(DBUtils.APPOINTMENT_TIMESLOTID, TimeSlotID);
        values.put(DBUtils.APPOINTMENT_CUSTOMERID, CustomerID);


        database.insert(DBUtils.APPOINTMENT_TABLE, null, values);

        Cursor cursor = database.query(DBUtils.APPOINTMENT_TABLE,
                APPOINTMENT_TABLE_COLUMNS,
                DBUtils.CUSTOMER_ID + " =?",
                new String[]{ID}, null, null, null);
        cursor.moveToFirst();
        Appointment appointment = parseAppointment(cursor);
        cursor.close();
        return appointment;
    }

    public Customer getCustomerByContractNumber(String ContractNumber) {
        Cursor cursor = database.query(DBUtils.CUSTOMER_TABLE, CUSTOMER_TABLE_COLUMNS,
                DBUtils.CUSTOMER_CONTRACT + " = " + ContractNumber, null, null, null, null);
        cursor.moveToFirst();
        Customer customer = parseCustomer(cursor);
        cursor.close();
        return customer;
    }

    public boolean customerExistsByContractNumber(String contractNumber) {
        Cursor cursor = database.query(DBUtils.CUSTOMER_TABLE,
                CUSTOMER_TABLE_COLUMNS,
                DBUtils.CUSTOMER_CONTRACT + " =?",
                new String[] {contractNumber}, null, null, null);
        int x = cursor.getCount();
        cursor.close();
        return x > 0;
    }

    /*
    public String registerCustomer(int ContranctNumber, String Email, String Password){
        String message = "";
        Cursor cursor = database.query(DBUtils.CUSTOMER_TABLE,
                CUSTOMER_TABLE_COLUMNS,
                DBUtils.CUSTOMER_CONTRACT + " = " + ContranctNumber,
                null, null, null, null);
        if (cursor.getCount() == 1) {
            cursor.moveToFirst();
            Customer c = parseCustomer(cursor);
            if (c.getContractNumber().equals(ContranctNumber + "")) {
                c.setEmail(Email);
                c.setPassword(Password);
                database.update(DBUtils.CUSTOMER_TABLE, getCustomerValues(c), DBUtils.CUSTOMER_ID + " = " + c.getId(), null);
                message = "Success";
            } else {
                message = "Contract Number Doesn't Exist";
            }
        } else {
            message = "Contract Number Doesn't Exist";
        }
        cursor.close();
        return message;
    }

    private ContentValues getCustomerValues(Customer oCustomer) {
        ContentValues values = new ContentValues();
        values.put(DBUtils.CUSTOMER_ID, oCustomer.getId());
        values.put(DBUtils.CUSTOMER_CONTRACT, oCustomer.getContractNumber());
        values.put(DBUtils.CUSTOMER_NAME, oCustomer.getName());
        values.put(DBUtils.CUSTOMER_LASTNAME, oCustomer.getLastName());
        values.put(DBUtils.CUSTOMER_EMAIL, oCustomer.getEmail());
        values.put(DBUtils.CUSTOMER_PASSWORD, oCustomer.getPassword());
        values.put(DBUtils.CUSTOMER_ADDRESS, oCustomer.getAddress());
        values.put(DBUtils.CUSTOMER_PHONE, oCustomer.getPhone());
        values.put(DBUtils.CUSTOMER_IMAGE, oCustomer.getCustomerImage());
        values.put(DBUtils.CUSTOMER_REGISTERED, oCustomer.getRegistered());

        return values;
    }
    */

    public Employee addEmployee(String Id, int EmployeeCode, String Name, String LastName, String Email, String Password, int Phone, String EmployeeImage, Boolean Registered) {
        ContentValues values = new ContentValues();
        values.put(DBUtils.EMPLOYEE_ID, Id);
        values.put(DBUtils.EMPLOYEE_CODE, EmployeeCode);
        values.put(DBUtils.EMPLOYEE_NAME, Name);
        values.put(DBUtils.EMPLOYEE_LASTNAME, LastName);
        values.put(DBUtils.EMPLOYEE_EMAIL, Email);
        values.put(DBUtils.EMPLOYEE_PASSWORD, Password);
        values.put(DBUtils.EMPLOYEE_PHONE, Phone);
        values.put(DBUtils.EMPLOYEE_IMAGE, EmployeeImage);
        values.put(DBUtils.EMPLOYEE_REGISTERED, Registered ? 1 : 0);

        long employeeId = database.insert(DBUtils.EMPLOYEE_TABLE, null, values);
        Cursor cursor = database.query(DBUtils.EMPLOYEE_TABLE, EMPLOYEE_TABLE_COLUMNS,
                DBUtils.EMPLOYEE_ID + " = " + employeeId, null, null, null, null);
        cursor.moveToFirst();
        Employee employee = parseEmployee(cursor);
        cursor.close();
        return employee;
    }

    private Customer parseCustomer(Cursor cursor) {
        Customer customer = new Customer();
        customer.setId(cursor.getString(cursor.getColumnIndex(DBUtils.CUSTOMER_ID)));
        customer.setContractNumber(cursor.getInt(cursor.getColumnIndex(DBUtils.CUSTOMER_CONTRACT)));
        customer.setName(cursor.getString(cursor.getColumnIndex(DBUtils.CUSTOMER_NAME)));
        customer.setLastName(cursor.getString(cursor.getColumnIndex(DBUtils.CUSTOMER_LASTNAME)));
        customer.setEmail(cursor.getString(cursor.getColumnIndex(DBUtils.CUSTOMER_EMAIL)));
        customer.setPassword(cursor.getString(cursor.getColumnIndex(DBUtils.CUSTOMER_PASSWORD)));
        customer.setAddress(cursor.getString(cursor.getColumnIndex(DBUtils.CUSTOMER_ADDRESS)));
        customer.setPhone(cursor.getInt(cursor.getColumnIndex(DBUtils.CUSTOMER_PHONE)));
        customer.setCustomerImage(cursor.getString(cursor.getColumnIndex(DBUtils.CUSTOMER_IMAGE)));
        customer.setRegistered(cursor.getInt(cursor.getColumnIndex(DBUtils.CUSTOMER_REGISTERED)) == 1 ? true : false);
        return customer;
    }

    private Employee parseEmployee(Cursor cursor) {
        Employee employee = new Employee();
        employee.setId(cursor.getString(cursor.getColumnIndex(DBUtils.EMPLOYEE_ID)));
        employee.setEmployeeCode(cursor.getInt(cursor.getColumnIndex(DBUtils.EMPLOYEE_CODE)));
        employee.setFirstName(cursor.getString(cursor.getColumnIndex(DBUtils.EMPLOYEE_NAME)));
        employee.setLastName(cursor.getString(cursor.getColumnIndex(DBUtils.EMPLOYEE_LASTNAME)));
        employee.setEmail(cursor.getString(cursor.getColumnIndex(DBUtils.EMPLOYEE_EMAIL)));
        employee.setPassword(cursor.getString(cursor.getColumnIndex(DBUtils.EMPLOYEE_PASSWORD)));
        employee.setPhone(cursor.getInt(cursor.getColumnIndex(DBUtils.EMPLOYEE_PHONE)));
        employee.setEmployeeImage(cursor.getString(cursor.getColumnIndex(DBUtils.EMPLOYEE_IMAGE)));
        return employee;
    }

    private Appointment parseAppointment(Cursor cursor) {
        Appointment appointment = new Appointment();
        appointment.setAptID(cursor.getString(cursor.getColumnIndex(DBUtils.APPOINTMENT_ID)));
        appointment.setDate(cursor.getString(cursor.getColumnIndex(DBUtils.APPOINTMENT_DATE)));
        appointment.setTimeSlotID(cursor.getString(cursor.getColumnIndex(DBUtils.APPOINTMENT_TIMESLOTID)));
        appointment.setCustomerID(cursor.getString(cursor.getColumnIndex(DBUtils.APPOINTMENT_CUSTOMERID)));
        return appointment;
    }
}
