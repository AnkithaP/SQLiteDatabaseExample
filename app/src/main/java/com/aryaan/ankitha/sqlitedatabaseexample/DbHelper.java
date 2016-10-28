package com.aryaan.ankitha.sqlitedatabaseexample;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ankitha on 10/28/2016.
 */
public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "userdatabase";
    private static final String TABLE_NAME = "USERTABLE";
    private static final int DATABASE_VERSION = 6;
    private static final String UID = "_id";
    private static final String NAME = "Name";
    private static final String EMAIL = "Email";
    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255), "+EMAIL+" VARCHAR(255));";
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
