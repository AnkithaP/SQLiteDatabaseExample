package com.aryaan.ankitha.sqlitedatabaseexample;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by ankitha on 10/28/2016.
 */
public class Message {
    public static void message(Context context,String message){
        Toast.makeText(context,message, Toast.LENGTH_SHORT).show();
    }
}
