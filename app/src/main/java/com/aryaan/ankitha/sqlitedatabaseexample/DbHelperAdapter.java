package com.aryaan.ankitha.sqlitedatabaseexample;

import android.content.ContentValues;
import android.content.Context;
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
