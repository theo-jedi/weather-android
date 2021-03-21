package com.theost.weatherapp.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "wavenote-local.db";

    public static final String[] TABLES = new String[] {
            "Алмазный", "Западный", "Курортный", "Лесной", "Научный",
            "Полярный", "Портовый", "Приморский", "Садовый", "Северный",
            "Степной", "Таежный", "Южный"
    };

    public static final String COL_0 = "MONTH";
    public static final String COL_1 = "WEEK";
    public static final String COL_2 = "DAY";
    public static final String COL_3 = "TEMP";

    private Context context;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    public boolean isInitialized() {
        return !this.getDatabaseName().isEmpty();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (String table : TABLES) {
            db.execSQL("CREATE TABLE IF NOT EXISTS " + table + " (" + COL_0 + " INTEGER PRIMARY KEY, " + COL_1 + " INTEGER NOT NULL, " + COL_2 + " INTEGER NOT NULL, " + COL_3 + " REAL NOT NULL)");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (String table : TABLES) {
            db.execSQL("DROP TABLE IF EXISTS " + table);
        }
        onCreate(db);
    }

    @Override
    protected void finalize() throws Throwable {
        this.close();
        super.finalize();
    }

    public void importData(String fileName) throws IOException {
        SQLiteDatabase db = this.getWritableDatabase();
        InputStreamReader inputStreamReader = new InputStreamReader(context.getAssets().open("filename.csv"));
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;
        String tableName = fileName.replace(".json", "");
        String columns = COL_0 + ", " + COL_1 + ", " + COL_2 + ", " + COL_3;
        String str1 = "INSERT INTO " + tableName + " (" + columns + ") values(";
        String str2 = ");";
        db.beginTransaction();
        while ((line = bufferedReader.readLine()) != null) {
            StringBuilder sb = new StringBuilder(str1);
            String[] str = line.split(",");
            sb.append(str[0]).append(",");
            sb.append(str[1]).append(",");
            sb.append(str[2]).append(",");
            sb.append(str[3]);
            sb.append(str2);
            db.execSQL(sb.toString());
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public Cursor getMonthData(String city, int month) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result;
        try {
            result = db.rawQuery("SELECT " + COL_3 + " FROM " + city + " WHERE " + COL_0 + "=" + month, null);
        } catch (Exception e) {
            e.printStackTrace();
            result = null;
        }
        return result;
    }

    public Cursor getWeekData(String city, int month, int week) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result;
        try {
            result = db.rawQuery("SELECT " + COL_3 + " FROM " + city + " WHERE " + COL_0 + "=" + month + " AND " + COL_1 + "=" + week, null);
        } catch (Exception e) {
            e.printStackTrace();
            result = null;
        }
        return result;
    }

    public Cursor getDayData(String city, int month, int day) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result;
        try {
            result = db.rawQuery("SELECT " + COL_3 + " FROM " + city + " WHERE " + COL_0 + "=" + month + " AND " + COL_2 + "=" + day, null);
        } catch (Exception e) {
            e.printStackTrace();
            result = null;
        }
        return result;
    }

}