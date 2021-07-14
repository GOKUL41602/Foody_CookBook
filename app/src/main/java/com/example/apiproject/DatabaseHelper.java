package com.example.apiproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "FAVOURITES";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_FOODNAME = "FOODNAME";
    public static final String COLUMN_FOODLINK ="FOODLINK";

    Context context;
    public DatabaseHelper(@Nullable Context context) {
        super(context, "foodyCookBook.db", null, 1);
        this.context=context;
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
        Toast.makeText(context, model.getFoodLink(), Toast.LENGTH_SHORT).show();
        long insert = db.insert(TABLE_NAME, null, cv);
        if (insert == -1) {
            return false;
        } else {

            return true;
        }
    }

    public List<FoodDatabaseModel> getAll()
    {
        List<FoodDatabaseModel> returnList=new ArrayList<>();

        String queryString="SELECT * FROM "+TABLE_NAME;

        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);

        if(cursor.moveToFirst())
        {
            do{
                int id=cursor.getInt(0);
                String foodName=cursor.getString(1);
                String foodLink=cursor.getString(2);
                FoodDatabaseModel model=new FoodDatabaseModel(id,foodName,foodLink);
                returnList.add(model);
            }while(cursor.moveToNext());
        }
        else
        {
        cursor.close();
        db.close();
        return returnList;
        }
        return returnList;
    }
}
