package com.example.webarelyc;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DataClass extends SQLiteOpenHelper {

    SQLiteDatabase db;

    public  static final String DATABASE_NAME = "instidine.db";



    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE \"items\" (\n" +
                "\t\"Name\"\tINTEGER,\n" +
                "\t\"IID\"\tINTEGER NOT NULL UNIQUE,\n" +
                "\t\"Availibility\"\tINTEGER,\n" +
                "\t\"type\"\tTEXT,\n" +
                "\t\"Price\"\tINTEGER,\n" +
                "\tPRIMARY KEY(\"IID\" AUTOINCREMENT)\n" +
                ");",new String[] {});

    }
    DataClass(Context context)
    {
        super(context,DATABASE_NAME,null,1);
        db= SQLiteDatabase.openDatabase("/data/data/com.example.webarelyc/databases/instidine.db",null,SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    Cursor getItems()
    {
        return db.rawQuery("select * from items",new String[] {});
    }

    int getItemID(String iname)
    {
       Cursor cur= db.rawQuery("select IID from items where Name = ?",new String[]{iname});
       return Integer.parseInt(cur.getString(0));
    }
}
