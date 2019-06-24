package com.example.slimify;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by User on 2/28/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "users";

    private static final String COL0 = "ID";
    private static final String COL1 = "name";
    private static final String COL2 = "weight";


    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "("
                + COL0 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL1 + " TEXT,"
                + COL2 + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String name, String weight) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, name);
        contentValues.put(COL2, weight);

        Log.d(TAG, "addData: Adding " + name + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if data is inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getItemID(String name, String weight){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL0 + " FROM " + TABLE_NAME
                + " WHERE " + COL1 + " = '" + name + "'"
                + " AND " + COL2 + " = '" + weight + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /*public Cursor getName(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME
                + " WHERE " + COL0 + " = '" + id + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getWeight(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL2 + " FROM " + TABLE_NAME
                + " WHERE " + COL0 + " = '" + id + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }*/


    public void updateName(String newName, String newWeight, int id, String oldName, String oldWeight){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME
                + " SET " + COL1 + " = '" + newName + "'"
                //+ " AND " + COL2 + " = '" + newWeight + "'"
                + " WHERE " + COL0 + " = '" + id + "'"
                + " AND " + COL1 + " = '" + oldName + "'"
                + " AND " + COL2 + " = '" + oldWeight + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);
    }

    public void updateWeight(String newName, String newWeight, int id, String oldName, String oldWeight){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME
                //+ " SET " + COL1 + " = '" + newName + "'"
                + " SET " + COL2 + " = '" + newWeight + "'"
                + " WHERE " + COL0 + " = '" + id + "'"
                + " AND " + COL1 + " = '" + oldName + "'"
                + " AND " + COL2 + " = '" + oldWeight + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);
    }

    public void deleteName(int id, String name, String weight){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME
                + " WHERE " + COL0 + " = '" + id + "'"
                + " AND " + COL1 + " = '" + name + "'"
                + " AND " + COL2 + " = '" + weight + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }

}
























