package com.example.bretz.burgerist.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

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
                    DBUtils.CUSTOMER_BASEID,
                    DBUtils.CUSTOMER_ID,
                    DBUtils.CUSTOMER_CONTRACT,
                    DBUtils.CUSTOMER_FIRSTNAME,
                    DBUtils.CUSTOMER_MIDDLENAME,
                    DBUtils.CUSTOMER_LASTNAME,
                    DBUtils.CUSTOMER_EMAIL,
                    DBUtils.CUSTOMER_PASSWORD,
                    DBUtils.CUSTOMER_ADDRESS,
                    DBUtils.CUSTOMER_PHONE,
                    DBUtils.CUSTOMER_IMAGE
            };

    private String[] EMPLOYEE_TABLE_COLUMNS =
            {
                    DBUtils.EMPLOYEE_BASEID,
                    DBUtils.EMPLOYEE_ID,
                    DBUtils.EMPLOYEE_CODE,
                    DBUtils.EMPLOYEE_FIRSTNAME,
                    DBUtils.EMPLOYEE_MIDDLENAME,
                    DBUtils.EMPLOYEE_LASTNAME,
                    DBUtils.EMPLOYEE_EMAIL,
                    DBUtils.EMPLOYEE_PASSWORD,
                    DBUtils.EMPLOYEE_PHONE,
                    DBUtils.EMPLOYEE_IMAGE
            };

    public DBHelper(Context context) {
        dbHelper = new DBUtils(context);
    }
    public void open() throws SQLException {
        try (SQLiteDatabase sqLiteDatabase = database = dbHelper.getWritableDatabase()) {
        }
    }
    public void close() {
        database.close();
    }

    public Customer addCustomer(int Id, int ContractNumber, String FirstName, String MiddleName, String LastName, String Email, String Password, String Address, int Phone, String CustomerImage) {
        ContentValues values = new ContentValues();
        values.put(DBUtils.CUSTOMER_ID, Id);
        values.put(DBUtils.CUSTOMER_CONTRACT, ContractNumber);
        values.put(DBUtils.CUSTOMER_FIRSTNAME, FirstName);
        values.put(DBUtils.CUSTOMER_MIDDLENAME, MiddleName);
        values.put(DBUtils.CUSTOMER_LASTNAME, LastName);
        values.put(DBUtils.CUSTOMER_EMAIL, Email);
        values.put(DBUtils.CUSTOMER_PASSWORD, Password);
        values.put(DBUtils.CUSTOMER_ADDRESS, Address );
        values.put(DBUtils.CUSTOMER_PHONE, Phone);
        values.put(DBUtils.CUSTOMER_IMAGE, CustomerImage);

        long customerId = database.insert(DBUtils.CUSTOMER_TABLE, null, values);
        Cursor cursor = database.query(DBUtils.CUSTOMER_TABLE, CUSTOMER_TABLE_COLUMNS,
                DBUtils.CUSTOMER_BASEID + " = " + customerId, null, null, null, null);
        cursor.moveToFirst();
        Customer customer = parseCustomer(cursor);
        cursor.close();
        return customer;
    }

    public Customer getCustomer(String Email) {
        Cursor cursor = database.query(DBUtils.CUSTOMER_TABLE, CUSTOMER_TABLE_COLUMNS,
                DBUtils.CUSTOMER_EMAIL + " = " + Email, null, null, null, null);
        cursor.moveToFirst();
        Customer customer = parseCustomer(cursor);
        cursor.close();
        return customer;
    }

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
        values.put(DBUtils.CUSTOMER_FIRSTNAME, oCustomer.getFirstName());
        values.put(DBUtils.CUSTOMER_MIDDLENAME, "");
        values.put(DBUtils.CUSTOMER_LASTNAME, oCustomer.getLastName());
        values.put(DBUtils.CUSTOMER_EMAIL, oCustomer.getEmail());
        values.put(DBUtils.CUSTOMER_PASSWORD, oCustomer.getPassword());
        values.put(DBUtils.CUSTOMER_ADDRESS, oCustomer.getAddress());
        values.put(DBUtils.CUSTOMER_PHONE, oCustomer.getPhone());
        values.put(DBUtils.CUSTOMER_IMAGE, oCustomer.getCustomerImage());

        return values;
    }

    public Employee addEmployee(int Id, int EmployeeCode, String FirstName, String MiddleName, String LastName, String Email, String Password, int Phone, String EmployeeImage) {
        ContentValues values = new ContentValues();
        values.put(DBUtils.EMPLOYEE_ID, Id);
        values.put(DBUtils.EMPLOYEE_CODE, EmployeeCode);
        values.put(DBUtils.EMPLOYEE_FIRSTNAME, FirstName);
        values.put(DBUtils.EMPLOYEE_MIDDLENAME, MiddleName);
        values.put(DBUtils.EMPLOYEE_LASTNAME, LastName);
        values.put(DBUtils.EMPLOYEE_EMAIL, Email);
        values.put(DBUtils.EMPLOYEE_PASSWORD, Password);
        values.put(DBUtils.EMPLOYEE_PHONE, Phone);
        values.put(DBUtils.EMPLOYEE_IMAGE, EmployeeImage);

        long employeeId = database.insert(DBUtils.EMPLOYEE_TABLE, null, values);
        Cursor cursor = database.query(DBUtils.EMPLOYEE_TABLE, EMPLOYEE_TABLE_COLUMNS,
                DBUtils.EMPLOYEE_BASEID + " = " + employeeId, null, null, null, null);
        cursor.moveToFirst();
        Employee employee = parseEmployee(cursor);
        cursor.close();
        return employee;
    }

    private Customer parseCustomer(Cursor cursor) {
        Customer customer = new Customer();
        customer.setId(cursor.getInt(cursor.getColumnIndex(DBUtils.CUSTOMER_ID)) + "");
        customer.setContractNumber(cursor.getInt(cursor.getColumnIndex(DBUtils.CUSTOMER_CONTRACT)) + "");
        customer.setFirstName(cursor.getString(cursor.getColumnIndex(DBUtils.CUSTOMER_FIRSTNAME)));
        customer.setMiddleName(cursor.getString(cursor.getColumnIndex(DBUtils.CUSTOMER_MIDDLENAME)));
        customer.setLastName(cursor.getString(cursor.getColumnIndex(DBUtils.CUSTOMER_LASTNAME)));
        customer.setEmail(cursor.getString(cursor.getColumnIndex(DBUtils.CUSTOMER_EMAIL)));
        customer.setPassword(cursor.getString(cursor.getColumnIndex(DBUtils.CUSTOMER_PASSWORD)));
        customer.setAddress(cursor.getString(cursor.getColumnIndex(DBUtils.CUSTOMER_ADDRESS)));
        customer.setPhone(cursor.getString(cursor.getColumnIndex(DBUtils.CUSTOMER_PHONE+ "")));
        customer.setCustomerImage(cursor.getString(cursor.getColumnIndex(DBUtils.CUSTOMER_IMAGE)));
        return customer;
    }

    private Employee parseEmployee(Cursor cursor) {
        Employee employee = new Employee();
        employee.setId(cursor.getInt(cursor.getColumnIndex(DBUtils.EMPLOYEE_ID)) + "");
        employee.setEmployeeCode(cursor.getInt(cursor.getColumnIndex(DBUtils.EMPLOYEE_CODE))+ "");
        employee.setFirstName(cursor.getString(cursor.getColumnIndex(DBUtils.EMPLOYEE_FIRSTNAME)));
        employee.setMiddleName(cursor.getString(cursor.getColumnIndex(DBUtils.EMPLOYEE_MIDDLENAME)));
        employee.setLastName(cursor.getString(cursor.getColumnIndex(DBUtils.EMPLOYEE_LASTNAME)));
        employee.setEmail(cursor.getString(cursor.getColumnIndex(DBUtils.EMPLOYEE_EMAIL)));
        employee.setPassword(cursor.getString(cursor.getColumnIndex(DBUtils.EMPLOYEE_PASSWORD)));
        employee.setPhone(cursor.getInt(cursor.getColumnIndex(DBUtils.EMPLOYEE_PHONE))+ "");
        employee.setEmployeeImage(cursor.getString(cursor.getColumnIndex(DBUtils.EMPLOYEE_IMAGE)));
        return employee;
    }
}
