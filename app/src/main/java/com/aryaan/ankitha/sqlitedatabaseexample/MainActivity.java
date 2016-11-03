package com.aryaan.ankitha.sqlitedatabaseexample;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    DbHelperAdapter dbHelperAdapter;
    EditText userName, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText) findViewById(R.id.editText);
        Password = (EditText)findViewById(R.id.editText2);

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
}
