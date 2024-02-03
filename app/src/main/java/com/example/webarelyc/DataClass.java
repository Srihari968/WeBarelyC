package com.example.webarelyc;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;


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
        db.execSQL("CREATE TABLE \"orders\" (\n" +
                "\t\"oid\"\tINTEGER UNIQUE,\n" +
                "\t\"orderstring\"\tTEXT,\n" +
                "\t\"received\"\tINTEGER,\n" +
                "\t\"uid\"\tINTEGER,\n" +
                "\tPRIMARY KEY(\"oid\" AUTOINCREMENT)\n" +
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
        db.execSQL("DROP TABLE IF EXISTS"+ " orders");


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

    void addOrder(int uid,String orderstring)
    {
        db.execSQL("INSERT INTO orders(uid, orderstring, received) values(?,?,0)",new String[]{Integer.toString(uid),orderstring});
    }

    int getPrice(int iid)
    {
        Cursor cur = db.rawQuery("select Price from items where iid = ?",new String[]{Integer.toString(iid)});
        if(cur.getCount()>0)
        {
            cur.moveToFirst();
            return cur.getInt(0);
        }
        return -1;
    }

    Cursor getAllCurrentOrders()
    {
        Cursor cur = db.rawQuery("select * from orders where received = 0",new String[]{});
        if(cur.getCount()>0)
        {
           // cur.moveToFirst();
            return cur;
        }
        return new Cursor() {
            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public int getPosition() {
                return 0;
            }

            @Override
            public boolean move(int offset) {
                return false;
            }

            @Override
            public boolean moveToPosition(int position) {
                return false;
            }

            @Override
            public boolean moveToFirst() {
                return false;
            }

            @Override
            public boolean moveToLast() {
                return false;
            }

            @Override
            public boolean moveToNext() {
                return false;
            }

            @Override
            public boolean moveToPrevious() {
                return false;
            }

            @Override
            public boolean isFirst() {
                return false;
            }

            @Override
            public boolean isLast() {
                return false;
            }

            @Override
            public boolean isBeforeFirst() {
                return false;
            }

            @Override
            public boolean isAfterLast() {
                return false;
            }

            @Override
            public int getColumnIndex(String columnName) {
                return 0;
            }

            @Override
            public int getColumnIndexOrThrow(String columnName) throws IllegalArgumentException {
                return 0;
            }

            @Override
            public String getColumnName(int columnIndex) {
                return null;
            }

            @Override
            public String[] getColumnNames() {
                return new String[0];
            }

            @Override
            public int getColumnCount() {
                return 0;
            }

            @Override
            public byte[] getBlob(int columnIndex) {
                return new byte[0];
            }

            @Override
            public String getString(int columnIndex) {
                return null;
            }

            @Override
            public void copyStringToBuffer(int columnIndex, CharArrayBuffer buffer) {

            }

            @Override
            public short getShort(int columnIndex) {
                return 0;
            }

            @Override
            public int getInt(int columnIndex) {
                return 0;
            }

            @Override
            public long getLong(int columnIndex) {
                return 0;
            }

            @Override
            public float getFloat(int columnIndex) {
                return 0;
            }

            @Override
            public double getDouble(int columnIndex) {
                return 0;
            }

            @Override
            public int getType(int columnIndex) {
                return 0;
            }

            @Override
            public boolean isNull(int columnIndex) {
                return false;
            }

            @Override
            public void deactivate() {

            }

            @Override
            public boolean requery() {
                return false;
            }

            @Override
            public void close() {

            }

            @Override
            public boolean isClosed() {
                return false;
            }

            @Override
            public void registerContentObserver(ContentObserver observer) {

            }

            @Override
            public void unregisterContentObserver(ContentObserver observer) {

            }

            @Override
            public void registerDataSetObserver(DataSetObserver observer) {

            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver observer) {

            }

            @Override
            public void setNotificationUri(ContentResolver cr, Uri uri) {

            }

            @Override
            public Uri getNotificationUri() {
                return null;
            }

            @Override
            public boolean getWantsAllOnMoveCalls() {
                return false;
            }

            @Override
            public void setExtras(Bundle extras) {

            }

            @Override
            public Bundle getExtras() {
                return null;
            }

            @Override
            public Bundle respond(Bundle extras) {
                return null;
            }
        };
    }

    String getUName(int uid)
    {
        Cursor cur = db.rawQuery("select * from users where uid = ?",new String[]{Integer.toString(uid)});
        if(cur.getCount()>0)
        {
            cur.moveToFirst();
            return cur.getString(0);
        }
        return null;
    }

    String getItemName(int iid)
    {
        Cursor cur = db.rawQuery("select Name from items where iid = ?",new String[]{Integer.toString(iid)});
        if(cur.getCount()>0)
        {
            cur.moveToFirst();
            return cur.getString(0);
        }
        return null;
    }

    void setFinished(int oid)
    {
        db.execSQL("UPDATE orders SET received = 1 where oid = ?",new String[]{Integer.toString(oid)});
    }

    void addItem(String name, int price, int avail)
    {
        db.execSQL("INSERT INTO items(Name,Price,Availibility) VALUES(?,?,?)",new String[]{name,Integer.toString(price),Integer.toString(avail)});
    }
}
