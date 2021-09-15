package com.example.sqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyDatabase extends SQLiteOpenHelper {
    //database name and version
    public static final String DB_NAME = "car";
    public static final int DB_VERSION = 1;

    //column names
    public static final String CAR_TABLE_NAME = "car";
    public static final String CAR_ID = "id";
    public static final String CAR_MODEL = "model";
    public static final String CAR_COLOR = "color";
    public static final String CAR_DPL = "distancePerLetter";

    //constructor
    public MyDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + CAR_TABLE_NAME + "(" + CAR_ID + " integer primary key AutoIncrement, " + CAR_MODEL + " text, " +
                "" + CAR_COLOR + " text, " + CAR_DPL + " real)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //insert new car
    public boolean addNewCar(Car car) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CAR_MODEL, car.getModel());
        values.put(CAR_COLOR, car.getColor());
        values.put(CAR_DPL, car.getDpl());
        long result = db.insert(CAR_TABLE_NAME, null, values);
        return result != -1;
    }

    //update car
    public boolean updateCar(Car car) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CAR_MODEL, car.getModel());
        values.put(CAR_COLOR, car.getColor());
        values.put(CAR_DPL, car.getDpl());
        String[] args = {car.getId()};
        int result = db.update(CAR_TABLE_NAME, values, "id=?", args);
        return result > 0;
    }

    //return #cars in the table
    public long getNumOfCars() {
        SQLiteDatabase db = getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db, CAR_TABLE_NAME);
    }

    //delete car
    boolean deleteCar(Car car) {
        SQLiteDatabase db = getWritableDatabase();
        String[] args = {car.getId()};
        int result = db.delete(CAR_TABLE_NAME, "id=?", args);
        return result > 0;
    }

    //return all cars
    public ArrayList<Car> getAllCars() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Car> cars = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + CAR_TABLE_NAME, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndex(CAR_ID));
                String model = cursor.getString(cursor.getColumnIndex(CAR_MODEL));
                String color = cursor.getString(cursor.getColumnIndex(CAR_COLOR));
                String dpl = cursor.getString(cursor.getColumnIndex(CAR_DPL));
                cars.add(new Car(id, model, color, dpl));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return cars;
    }

    //search for car
    public ArrayList<Car> searchForCar(String carModel) {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Car> cars = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from "+CAR_TABLE_NAME+" where "+CAR_MODEL+"=?",
                new String[]{carModel});
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String id = cursor.getString(cursor.getColumnIndex(CAR_ID));
                String model = cursor.getString(cursor.getColumnIndex(CAR_MODEL));
                String color = cursor.getString(cursor.getColumnIndex(CAR_COLOR));
                String dpl = cursor.getString(cursor.getColumnIndex(CAR_DPL));
                cars.add(new Car(id, model, color, dpl));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return cars;
    }
}
