package com.example.bretz.burgerist.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by bretz on 11/17/2017.
 */

public class DBUtils extends SQLiteOpenHelper{

    public static final String DATABASE_NAME ="Burgerista";
    public static final int DATABASE_VERSION = 3;

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

    //-----------------APPOINTMENT TABLE---------------------------
    public static final String APPOINTMENT_TABLE ="Appointment";
    public static final String APPOINTMENT_BASEID ="ApptID";
    public static final String APPOINTMENT_DATE = "date";
    public static final String APPOINTMENT_TIMESLOTID = "timeSlotID";
    public static final String APPOINTMENT_CUSTOMERID = "customerID";

    //-----------------APPOINTMENT DETAILS TABLE---------------------------
    public static final String APPTDET_TABLE ="AppointmentDetail";
    public static final String APPTDET_BASEID ="apptDetalID";
    public static final String APPTDET_APPTID = "apptID";
    public static final String APPTDET_START = "startTime";
    public static final String APPTDET_END = "endTime";
    public static final String APPTDETE_TECHNOTES = "techNotes";
    public static final String APPTDET_CUSTOMERNOTES = "customerNotes";
    public static final String APPTDET_EMPLOYEEID = "employeeID";
    public static final String APPTDET_RATING = "rating";


    public static final String CREATE_CUSTOMERS =
            "CREATE TABLE "+ CUSTOMER_TABLE + "(" +
                    //CUSTOMER_BASEID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CUSTOMER_ID + " TEXT, " +
                    CUSTOMER_CONTRACT + " TEXT, " +
                    CUSTOMER_FIRSTNAME + " TEXT," +
                    CUSTOMER_MIDDLENAME + " TEXT," +
                    CUSTOMER_LASTNAME + " TEXT," +
                    CUSTOMER_EMAIL + " TEXT," +
                    CUSTOMER_PASSWORD + " TEXT," +
                    CUSTOMER_ADDRESS + " TEXT," +
                    CUSTOMER_PHONE + " TEXT," +
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

    public static final String CREATE_APPOINTMENT =
            "CREATE TABLE "+ APPOINTMENT_TABLE + "(" +
                    APPOINTMENT_BASEID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    APPOINTMENT_DATE + " DATETIME, " +
                    APPOINTMENT_TIMESLOTID + " INTEGER, " +
                    APPOINTMENT_CUSTOMERID + " INTEGER)";

    public static final String CREATE_APPTDET =
            "CREATE TABLE "+ APPTDET_TABLE + "(" +
                    APPTDET_BASEID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    APPTDET_APPTID + " INTEGER, " +
                    APPTDET_START + " DATETIME, " +
                    APPTDET_END+ " DATETIME," +
                    APPTDETE_TECHNOTES + " TEXT," +
                    APPTDET_CUSTOMERNOTES + " TEXT," +
                    APPTDET_EMPLOYEEID + " INTEGER," +
                    APPTDET_RATING + " INTEGER)";

    public DBUtils(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CUSTOMERS);
        db.execSQL(CREATE_EMPLOYEES);
        db.execSQL(CREATE_APPOINTMENT);
        db.execSQL(CREATE_APPTDET);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CUSTOMER_TABLE + "");
        db.execSQL("DROP TABLE IF EXISTS " + EMPLOYEE_TABLE + "");
        db.execSQL("DROP TABLE IF EXISTS " + APPOINTMENT_TABLE + "");
        db.execSQL("DROP TABLE IF EXISTS " + APPTDET_TABLE + "");
        onCreate(db);
    }
}
