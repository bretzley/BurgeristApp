package com.example.bretz.burgerist.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by bretz on 11/17/2017.
 */

public class DBUtils extends SQLiteOpenHelper{

    public static final String DATABASE_NAME ="Burgerista";
    public static final int DATABASE_VERSION = 1;

    //-----------------CUSTOMER TABLE---------------------------
    public static final String CUSTOMER_TABLE="Customer";
    public static final String CUSTOMER_BASEID = "baseId";
    public static final String CUSTOMER_ID = "CustomerId";
    public static final String CUSTOMER_CONTRACT = "ContractNumber";
    public static final String CUSTOMER_FIRSTNAME = "FirstName";
    public static final String CUSTOMER_MIDDLENAME = "MiddleName";
    public static final String CUSTOMER_LASTNAME = "LastName";
    public static final String CUSTOMER_EMAIL = "Email";
    public static final String CUSTOMER_PASSWORD = "Password";
    public static final String CUSTOMER_ADDRESS = "Address";
    public static final String CUSTOMER_PHONE = "Phone";
    public static final String CUSTOMER_IMAGE = "CustomerImage";

    //-----------------EMPLOYEE TABLE---------------------------
    public static final String EMPLOYEE_TABLE ="Employee";
    public static final String EMPLOYEE_BASEID = "baseId";
    public static final String EMPLOYEE_ID = "EmployeeId";
    public static final String EMPLOYEE_CODE = "EmployeeCode";
    public static final String EMPLOYEE_FIRSTNAME = "FirstName";
    public static final String EMPLOYEE_MIDDLENAME = "MiddleName";
    public static final String EMPLOYEE_LASTNAME = "LastName";
    public static final String EMPLOYEE_EMAIL = "Email";
    public static final String EMPLOYEE_PASSWORD = "Password";
    public static final String EMPLOYEE_PHONE = "Phone";
    public static final String EMPLOYEE_IMAGE = "EmployeeImage";



    public static final String CREATE_CUSTOMERS =
            "CREATE TABLE "+ CUSTOMER_TABLE + "(" +
                    CUSTOMER_BASEID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CUSTOMER_ID + " INTEGER, " +
                    CUSTOMER_CONTRACT + " INTEGER, " +
                    CUSTOMER_FIRSTNAME + " TEXT," +
                    CUSTOMER_MIDDLENAME + " TEXT," +
                    CUSTOMER_LASTNAME + " TEXT," +
                    CUSTOMER_EMAIL + " TEXT," +
                    CUSTOMER_PASSWORD + " TEXT," +
                    CUSTOMER_ADDRESS + " TEXT," +
                    CUSTOMER_PHONE + " INTEGER," +
                    CUSTOMER_IMAGE + " TEXT)";

    public static final String CREATE_EMPLOYEES =
            "CREATE TABLE "+ EMPLOYEE_TABLE + "(" +
                    EMPLOYEE_BASEID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    EMPLOYEE_ID + " INTEGER, " +
                    EMPLOYEE_CODE + " INTEGER, " +
                    EMPLOYEE_FIRSTNAME + " TEXT," +
                    EMPLOYEE_MIDDLENAME + " TEXT," +
                    EMPLOYEE_LASTNAME + " TEXT," +
                    EMPLOYEE_EMAIL + " TEXT," +
                    EMPLOYEE_PASSWORD + " TEXT," +
                    EMPLOYEE_PHONE + " INTEGER," +
                    EMPLOYEE_IMAGE + " TEXT)";

    public DBUtils(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CUSTOMERS);
        db.execSQL(CREATE_EMPLOYEES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CUSTOMER_TABLE + "");
        db.execSQL("DROP TABLE IF EXISTS " + EMPLOYEE_TABLE + "");
        onCreate(db);
    }
}
