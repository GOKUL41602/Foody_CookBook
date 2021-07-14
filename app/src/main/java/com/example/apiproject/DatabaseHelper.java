package com.example.apiproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "FAVOURITES";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_FOODNAME = "FOODNAME";
    public static final String COLUMN_FOODLINK = "FOODLINK";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "foodyCookBook.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_FOODNAME + " TEXT," + COLUMN_FOODLINK + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOn(FoodDatabaseModel model) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_FOODNAME, model.getFoodName());
        cv.put(COLUMN_FOODLINK, model.getFoodLink());
        long insert = db.insert(TABLE_NAME, null, cv);
        if (insert == -1) {
            return false;
        } else {

            return true;
        }
    }
}
