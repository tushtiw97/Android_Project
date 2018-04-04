package com.example.tushtiw97.transylvaniahotels;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class gym extends Activity {

    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym);

        final String[] gymPackage = {"Weekly Pack (Rs. 750)", "Monthly Pack (Rs. 1500)", "Pay-Per-Use (Rs. 125)"};
        final Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,gymPackage);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        Intent i = getIntent();
        id = i.getIntExtra("id",-1);

        Button submit = findViewById(R.id.button1);
        //final SQLiteDatabase db = openOrCreateDatabase("hotel",MODE_PRIVATE,null);
        final Context context = this;
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView textView = findViewById(R.id.editText);
                TextView textView1 = findViewById(R.id.editText2);
                int age,weight;
                age = Integer.parseInt(textView.getText().toString());
                weight = Integer.parseInt(textView1.getText().toString());
                if(age<18||age>60){
                    Toast.makeText(getApplicationContext(),"Age Criteria Not Satisfied",Toast.LENGTH_SHORT).show();
                }
                else if(weight<40){
                    Toast.makeText(getApplicationContext(),"Weight Criteria Not Satisfied",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(getApplicationContext(),payment.class);
                    String msg = "",tot = "";
                    String s = spinner.getSelectedItem().toString();

                    if(s.equals(gymPackage[0])){
                        msg = "Weekly Pack Chosen";
                        tot = "750";
//                        Cursor c = db.rawQuery("select max(id) from user",null);
//                        c.moveToNext();
//                        int id = Integer.parseInt(c.getString(0));
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String d = simpleDateFormat.format(Calendar.getInstance().getTime());
                        Toast.makeText(context, "Pack : Weekly\nTotal : 750\nDate : " + d.toString(), Toast.LENGTH_SHORT).show();
                        new ServerResponse(context,msg,tot).execute("http://hotel-transylvania.000webhostapp.com/avails-gym.php?gid=" + id + "&pckid=1&date=" + d.toString());
                    }

                    else if(s.equals(gymPackage[1])){
                        msg = "Monthly Pack Chosen";
                        tot = "1500";
//                        Cursor c = db.rawQuery("select max(id) from user",null);
//                        c.moveToNext();
//                        int id = Integer.parseInt(c.getString(0));
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String d = simpleDateFormat.format(Calendar.getInstance().getTime());
                        Toast.makeText(context, "Pack : Monthly\nTotal : 1500\nDate : " + d.toString(), Toast.LENGTH_SHORT).show();
                        //Toast.makeText(context, d, Toast.LENGTH_SHORT).show();
                        new ServerResponse(context,msg,tot).execute("http://hotel-transylvania.000webhostapp.com/avails-gym.php?gid=" + id + "&pckid=2&date=" + d.toString());
                    }

                    else if(s.equals(gymPackage[2])){
                        msg = "Pay-Per-Use Pack Chosen";
                        tot = "125";
//                        Cursor c = db.rawQuery("select max(id) from user",null);
//                        c.moveToNext();
//                        int id = Integer.parseInt(c.getString(0));
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String d = simpleDateFormat.format(Calendar.getInstance().getTime());
                        Toast.makeText(context, "Pack : Pay\nTotal : 125\nDate : " + d.toString(), Toast.LENGTH_SHORT).show();
                        new ServerResponse(context,msg,tot).execute("http://hotel-transylvania.000webhostapp.com/avails-gym.php?gid=" + id + "&pckid=3&date=" + d.toString());
                    }

//                    intent.putExtra("Description",msg);
//                    intent.putExtra("Total",tot);

//                    startActivity(intent);
                }
            }
        });

    }

}
