package com.example.assignment4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME = "movies_table";
    private static final String COL0 = "ID";
    private static final String COL1 = "Title";
    private static final String COL2 = "Description";
    private static final String COL3 = "Year";
    private static final String COL4 = "Rating";
    private static final String COL5 = "Video_Code";
    private static final String COL6 = "Image_URL";

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL1 + " TEXT, " +
                COL2 + " TEXT, " +
                COL3 + " INTEGER, " +
                COL4 + " REAL, " +
                COL5 + " TEXT, " +
                COL6 + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String title, String desc, Integer year, Integer rating, String videoCode, String imageURL){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL1, title);
        contentValues.put(COL2, desc);
        contentValues.put(COL3, year);
        contentValues.put(COL4, rating);
        contentValues.put(COL5, videoCode);
        contentValues.put(COL6, imageURL);

        long result = db.insert(TABLE_NAME, null, contentValues);
        return result == -1? false : true;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery( query,null);
        return cursor;
    }

    public Integer deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] {id});
    }

    public void updateRecord(String id, float rating){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL4, rating);
        db.update(TABLE_NAME, cv, "id="+id, null);
    }
}
