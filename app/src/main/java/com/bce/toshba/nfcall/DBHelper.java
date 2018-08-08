package com.bce.toshba.nfcall;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context, String dbName)
    {
        super(context, dbName , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE NFCINFO ";
        sql += "(ID INTEGER PRIMARY KEY AUTOINCREMENT,";
        sql += "KEY TEXT,";
        sql += "VALUE TEXT)";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void Insert(String key, String value){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("KEY", key);
        values.put("VALUE", value);
        db.insert("NFCINFO", null, values);
    }

    public List<String> List(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> list = new ArrayList<>();
        Cursor rs = db.rawQuery("SELECT * FROM NFCINFO", null);
        rs.moveToFirst();
        while(rs.isAfterLast() == false){
            list.add(rs.getString(rs.getColumnIndex("VALUE")));
            rs.moveToNext();
        }
        return list;
    }

    public void Delete(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("NFCINFO", "KEY = ?", new String[]{name});
        db.close();
    }
}
