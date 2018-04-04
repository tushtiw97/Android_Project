package com.example.tushtiw97.transylvaniahotels;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLData;
import java.sql.SQLInput;

public class MainActivity extends Activity {
    String[] username = {"Nidhesh","Tushar","TusUdaTiw14","NidmnaPer15"};
    String[] password = {"password","password","1","4"};

    int size = 4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase db = openOrCreateDatabase("hotel",MODE_PRIVATE,null);
        //db.execSQL("drop table user");
        db.execSQL("create table if not exists user (id int, username varchar(20));");
        final Context context = this;
        Button orderFood = findViewById(R.id.button);
        orderFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText textView = findViewById(R.id.editText);
                EditText textView1 = findViewById(R.id.editText2);
//                int flag = 0;
//                for(int i=0;i<size;i++) {
//                    if (username[i].equals(textView.getText().toString())&& password[i].equals(textView1.getText().toString())) {
//                        flag = 1;
//                        Intent intent = new Intent(getApplicationContext(), home_page.class);
//                        startActivity(intent);
//                    }
//                }
//                if(flag==0){
//                    Toast.makeText(getApplicationContext(),"Authentication Error",Toast.LENGTH_SHORT).show();
//                }
                String user = textView.getText().toString();
                String pass = textView1.getText().toString();
                new RetrieveData(context,textView).execute("http://hotel-transylvania.000webhostapp.com/login.php?user=" + user + "&pass=" + pass);
            }
        });
    }
}
