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

        db.execSQL("CREATE TABLE \"users\" (\n" +
                "\t\"Name\"\tTEXT,\n" +
                "\t\"pwd\"\tTEXT,\n" +
                "\t\"uid\"\tINTEGER UNIQUE,\n" +
                "\tPRIMARY KEY(\"uid\" AUTOINCREMENT)\n" +
                ");");
        db.execSQL("CREATE TABLE \"admins\" (\n" +
                "\t\"Name\"\tTEXT,\n" +
                "\t\"aid\"\tINTEGER NOT NULL UNIQUE,\n" +
                "\t\"pwd\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"aid\" AUTOINCREMENT)\n" +
                ");");

    }
    DataClass(Context context)
    {
        super(context,DATABASE_NAME,null,1);
        db= SQLiteDatabase.openDatabase("/data/data/com.example.webarelyc/databases/instidine.db",null,SQLiteDatabase.OPEN_READWRITE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS"+ " items");
        db.execSQL("DROP TABLE IF EXISTS"+ " users");
        db.execSQL("DROP TABLE IF EXISTS"+ " admins");


    }

    void dbclose()
    {
        db.close();
    }

    Cursor getItems()
    {
        return db.rawQuery("select * from items",new String[] {});
    }

    int getItemID(String iname)
    {
       Cursor cur= db.rawQuery("select IID from items where Name = ?",new String[]{iname});
       if(cur != null && cur.getCount() > 0)
           cur.moveToFirst();
       return cur.getInt(0);
    }

    int uname_pwd_check(String uname,String pwd)
    {
        Cursor cur = db.rawQuery("select pwd from users where Name = ?",new String[]{uname});
        if(cur.getCount() > 0)
        {
            cur.moveToFirst();

            if(cur.getString(1).equals(pwd))
                return 1;
            else return 0;
        }
        else return -1;
    }
}
