package com.example.tushtiw97.transylvaniahotels;

import android.content.Context;
import android.content.Intent;
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

public class TourismPackage extends Activity {

    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourism_package);


        context = this;

        final String[] tour = { "The Explorer (Rs. 1000 per person)", "X-Treme Safari (Rs. 2500 per person)", "Love Birds (Rs. 1800 per person)","Adventurer Pack (Rs. 3000 per person)"};

        final Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String > arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,tour);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        Button submit = findViewById(R.id.button);

        Intent i = getIntent();
        final int id = i.getIntExtra("id",-1);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textView = findViewById(R.id.editText);
                int no = Integer.parseInt(textView.getText().toString());
                String s = spinner.getSelectedItem().toString();
                String msg="",tot="";
                String pckid = "";

                if(s.equals(tour[0])){
                    msg = "\"The Explorer\" Package Chosen";
                    tot = Integer.toString(no*1000);
                    pckid = "1";
                }

                else if(s.equals(tour[1])){
                    msg = "\"X-Treme\" Package Chosen";
                    tot = Integer.toString(no*2500);
                    pckid = "2";
                }

                else if(s.equals(tour[2])){
                    msg = "\"Love Birds\" Package Chosen";
                    tot = Integer.toString(no*1800);
                    pckid = "3";
                }

                else if(s.equals(tour[3])) {
                    msg = "\"Adventurer Pack\" Package Chosen";
                    tot = Integer.toString(no*3000);
                    pckid = "4";
                }


                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String date = simpleDateFormat.format(Calendar.getInstance().getTime());

                new ServerResponse(context, msg, tot).execute("http://hotel-transylvania.000webhostapp.com/buys-pack.php?gid=" + id + "&pckid=" + pckid + "&date=" + date);

//                Intent intent = new Intent(getApplicationContext(),payment.class);
//                intent.putExtra("Description",msg);
//                intent.putExtra("Total",tot);
//
//                startActivity(intent);
            }
        });


    }

}
