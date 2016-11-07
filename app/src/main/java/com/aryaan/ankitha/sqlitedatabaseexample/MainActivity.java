package com.aryaan.ankitha.sqlitedatabaseexample;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    DbHelperAdapter dbHelperAdapter;
    EditText userName, Password,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText) findViewById(R.id.editText);
        Password = (EditText)findViewById(R.id.editText2);
        name = (EditText)findViewById(R.id.editText3);

        dbHelperAdapter = new DbHelperAdapter(this);
    }

    public void addUser(View view){

        String user = userName.getText().toString();
        String pass = Password.getText().toString();

        long id = dbHelperAdapter.insertData(user,pass);
        if (id < 0){
            Message.message(this,"Unsuccessful");
        }
        else {
            Message.message(this,"Data Has been added Successfully");
        }

    }

    public void viewDetails(View view){
        String data = dbHelperAdapter.getAllData();
        Message.message(this,data);
    }

    public void viewData(View view){

        String s1 = name.getText().toString();
        // Ankitha qwerty
        String sub1 = s1.substring(0,s1.indexOf(" "));
        String sub2 = s1.substring(s1.indexOf(" ")+1);
        String s2 = dbHelperAdapter.getData(sub1,sub2);
        Message.message(this,s2);
    }
}
