package com.aryaan.ankitha.sqlitedatabaseexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHelperAdapter  {

    DbHelper helper;

    public DbHelperAdapter(Context context) {
        helper = new DbHelper(context);
    }


    public long insertData(String name, String password){

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbHelper.NAME,name);
        contentValues.put(DbHelper.PASSWORD,password);
        long id = db.insert(DbHelper.TABLE_NAME,null,contentValues);
        return id;
    }

    public String getAllData(){
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {DbHelper.UID,DbHelper.NAME,DbHelper.PASSWORD};
        Cursor cursor = db.query(DbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()){
            int index = cursor.getColumnIndex(DbHelper.UID);
            int index1 = cursor.getColumnIndex(DbHelper.NAME);
            int index2 = cursor.getColumnIndex(DbHelper.PASSWORD);

            int cid = cursor.getInt(index);
            String name = cursor.getString(index1);
            String pass = cursor.getString(index2);
            buffer.append(cid+" "+name+" "+pass+"\n");
        }
        return buffer.toString();
    }

    public String getData(String name, String password){
        //SELECT uid FROM USERTABLE WHERE name = ? and password = ?
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = {DbHelper.UID};
        String[] selectionArgs = {name,password};
        Cursor cursor = db.query(DbHelper.TABLE_NAME,columns,DbHelper.NAME+" = ? AND "+ DbHelper.PASSWORD+" = ?",selectionArgs,null,null,null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()){
            int index0 = cursor.getColumnIndex(DbHelper.UID);
            int personId = cursor.getInt(index0);
            buffer.append(personId+"\n ");
        }
        return buffer.toString();
    }

    public int updateName(String oldName,String newName){
        //UPDATE USERTABLE SET Name = 'ankitha' WHERE Name = 'aryaan'
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbHelper.NAME,newName);
        String[] whereArgs = {oldName};
        int count = db.update(DbHelper.TABLE_NAME,values,DbHelper.NAME+" = ?",whereArgs);
        return count;
    }

    public int deleteRow(){
        //DELETE * FROM USERTABLE WHERE Name = 'aryaan'
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] whereArgs = {"Aryaan"};
        int count = db.delete(DbHelper.TABLE_NAME,DbHelper.NAME+" = ?",whereArgs);
        return count;
    }


    class DbHelper extends SQLiteOpenHelper{

        private static final String DATABASE_NAME = "userdatabase";
        private static final String TABLE_NAME = "USERTABLE";
        private static final int DATABASE_VERSION = 7;
        private static final String UID = "_id";
        private static final String NAME = "Name";
        private static final String PASSWORD = "Password";
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255), "+PASSWORD+" VARCHAR(255));";
        private static final String DROP_TABLE = "DROP TABLE "+TABLE_NAME;
        Context context;

        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
            Message.message(context,"Constructor called");
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            try {
                db.execSQL(CREATE_TABLE);
                Message.message(context,"on Create called");
            } catch (SQLException e) {
                Message.message(context,""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            try {
                Message.message(context,"on upgrade called");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch (SQLException e) {
                Message.message(context,""+e);
            }
        }

    }

}
