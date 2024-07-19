package com.example.inventorypayrollmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "employeeDB";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_EMPLOYEES = "employees";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DOB = "dob";
    public static final String COLUMN_POSITION = "position";
    public static final String COLUMN_JOIN_DATE = "join_date";
    public static final String COLUMN_BASE_SALARY = "base_salary";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_EMPLOYEES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_DOB + " TEXT,"
                + COLUMN_POSITION + " TEXT,"
                + COLUMN_JOIN_DATE + " TEXT,"
                + COLUMN_BASE_SALARY + " REAL)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEES);
        onCreate(db);
    }

    public boolean addEmployee(String name, String dob, String position, String joinDate, double baseSalary) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DOB, dob);
        values.put(COLUMN_POSITION, position);
        values.put(COLUMN_JOIN_DATE, joinDate);
        values.put(COLUMN_BASE_SALARY, baseSalary);

        long result = db.insert(TABLE_EMPLOYEES, null, values);
        return result != -1;
    }

    public Cursor getEmployee(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_EMPLOYEES, null, COLUMN_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
    }

    public Cursor getAllEmployees() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_EMPLOYEES, null, null, null, null, null, null);
    }

    public boolean updateEmployee(int id, String name, String dob, String position, String joinDate, double baseSalary) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DOB, dob);
        values.put(COLUMN_POSITION, position);
        values.put(COLUMN_JOIN_DATE, joinDate);
        values.put(COLUMN_BASE_SALARY, baseSalary);

        int result = db.update(TABLE_EMPLOYEES, values, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    public boolean deleteEmployee(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABLE_EMPLOYEES, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        return result > 0;
    }
}
