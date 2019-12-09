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

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL1 + " TEXT, " +
                COL2 + " TEXT, " +
                COL3 + " TEXT, " +
                COL4 + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addTableData(int col, String data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        switch (col){
            case 1:
                contentValues.put(COL1, data);
                break;
            case 2:
                contentValues.put(COL2, data);
                break;
            case 3:
                contentValues.put(COL3, data);
                break;
            case 4:
                contentValues.put(COL4, data);
                break;
        }
        Log.d(TAG, "addData: Adding " + data + " to " + TABLE_NAME + " in column " + col);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result == -1? false : true;
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery( query,null);
        return cursor;
    }
}
