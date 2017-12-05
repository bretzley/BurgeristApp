package com.example.bretz.burgerist.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by bretz on 11/17/2017.
 */

public class DBUtils extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Burgerista.db";
    public static final int DATABASE_VERSION = 14;

    //-----------------CUSTOMER TABLE---------------------------
    public static final String CUSTOMER_TABLE = "Customer";
    public static final String CUSTOMER_ID = "id";
    public static final String CUSTOMER_CONTRACT = "ContractNumber";
    public static final String CUSTOMER_NAME = "Name";
    public static final String CUSTOMER_LASTNAME = "LastName";
    public static final String CUSTOMER_EMAIL = "Email";
    public static final String CUSTOMER_PASSWORD = "Password";
    public static final String CUSTOMER_ADDRESS = "Address";
    public static final String CUSTOMER_PHONE = "Phone";
    public static final String CUSTOMER_IMAGE = "CustomerImage";
    public static final String CUSTOMER_REGISTERED = "Registered";

    //-----------------EMPLOYEE TABLE---------------------------
    public static final String EMPLOYEE_TABLE = "Employee";
    public static final String EMPLOYEE_ID = "id";
    public static final String EMPLOYEE_CODE = "EmployeeCode";
    public static final String EMPLOYEE_NAME = "Name";
    public static final String EMPLOYEE_LASTNAME = "LastName";
    public static final String EMPLOYEE_EMAIL = "Email";
    public static final String EMPLOYEE_PASSWORD = "Password";
    public static final String EMPLOYEE_PHONE = "Phone";
    public static final String EMPLOYEE_IMAGE = "EmployeeImage";
    public static final String EMPLOYEE_REGISTERED = "Registered";

    //-----------------APPOINTMENT TABLE---------------------------
    public static final String APPOINTMENT_TABLE = "Appointment";
    public static final String APPOINTMENT_ID = "id";
    public static final String APPOINTMENT_DATE = "Date";
    public static final String APPOINTMENT_TIMESLOTID = "TimeSlotID";
    public static final String APPOINTMENT_CUSTOMERID = "CustomerID";
    public static final String APPOINTMENT_EMPLOYEEID = "EmployeeID";

    //-----------------APPOINTMENT DETAILS TABLE---------------------------
    public static final String APPTDET_TABLE = "AppointmentDetail";
    public static final String APPTDET_ID = "id";
    public static final String APPTDET_APPTID = "AppointmentID";
    public static final String APPTDET_START = "StartTime";
    public static final String APPTDET_END = "EndTime";
    public static final String APPTDETE_TECHNOTES = "TechNotes";
    public static final String APPTDET_CUSTOMERNOTES = "CustomerNotes";
    public static final String APPTDET_RATING = "Rating";

    //-----------------TIME SLOT TABLE---------------------------
    public static final String TIMESLOT_TABLE = "TimeSlot";
    public static final String TIMESLOT_ID = "id";
    public static final String TIMESLOT_TIMEFRAME = "TimeFrame";


    public static final String CREATE_CUSTOMERS =
            "CREATE TABLE " + CUSTOMER_TABLE + "(" +
                    CUSTOMER_ID + " TEXT, " +
                    CUSTOMER_CONTRACT + " INTEGER, " +
                    CUSTOMER_NAME + " TEXT, " +
                    CUSTOMER_LASTNAME + " TEXT, " +
                    CUSTOMER_EMAIL + " TEXT, " +
                    CUSTOMER_PASSWORD + " TEXT, " +
                    CUSTOMER_ADDRESS + " TEXT, " +
                    CUSTOMER_PHONE + " INTEGER, " +
                    CUSTOMER_IMAGE + " TEXT, " +
                    CUSTOMER_REGISTERED + " INTEGER)";

    public static final String CREATE_EMPLOYEES =
            "CREATE TABLE " + EMPLOYEE_TABLE + "(" +
                    EMPLOYEE_ID + " TEXT, " +
                    EMPLOYEE_CODE + " INTEGER, " +
                    EMPLOYEE_NAME + " TEXT, " +
                    EMPLOYEE_LASTNAME + " TEXT, " +
                    EMPLOYEE_EMAIL + " TEXT, " +
                    EMPLOYEE_PASSWORD + " TEXT, " +
                    EMPLOYEE_PHONE + " INTEGER, " +
                    EMPLOYEE_IMAGE + " TEXT, " +
                    EMPLOYEE_REGISTERED + " INTEGER)";

    public static final String CREATE_APPOINTMENT =
            "CREATE TABLE " + APPOINTMENT_TABLE + "(" +
                    APPOINTMENT_ID + " TEXT, " +
                    APPOINTMENT_DATE + " TEXT, " +
                    APPOINTMENT_TIMESLOTID + " TEXT, " +
                    APPOINTMENT_CUSTOMERID + " TEXT, " +
                    APPOINTMENT_EMPLOYEEID + " TEXT)";

    public static final String CREATE_APPTDET =
            "CREATE TABLE " + APPTDET_TABLE + "(" +
                    APPTDET_ID + " TEXT, " +
                    APPTDET_APPTID + " TEXT, " +
                    APPTDET_START + " TEXT, " +
                    APPTDET_END + " TEXT, " +
                    APPTDETE_TECHNOTES + " TEXT, " +
                    APPTDET_CUSTOMERNOTES + " TEXT, " +
                    APPTDET_RATING + " INTEGER)";

    public static final String CREATE_TIMESLOT =
            "CREATE TABLE " + TIMESLOT_TABLE + "(" +
                    TIMESLOT_ID + " TEXT, " +
                    TIMESLOT_TIMEFRAME + " TEXT)";

    public DBUtils(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CUSTOMERS);
        db.execSQL(CREATE_EMPLOYEES);
        db.execSQL(CREATE_APPOINTMENT);
        db.execSQL(CREATE_APPTDET);
        db.execSQL(CREATE_TIMESLOT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CUSTOMER_TABLE + "");
        db.execSQL("DROP TABLE IF EXISTS " + EMPLOYEE_TABLE + "");
        db.execSQL("DROP TABLE IF EXISTS " + APPOINTMENT_TABLE + "");
        db.execSQL("DROP TABLE IF EXISTS " + APPTDET_TABLE + "");
        db.execSQL("DROP TABLE IF EXISTS " + TIMESLOT_TABLE + "");
        onCreate(db);
    }
}
