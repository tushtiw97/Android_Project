package com.example.tushtiw97.transylvaniahotels;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class SpaBooking extends Activity {

    private Context context;
    private String[] SpaPacks = {"Weekend Reliever (Rs. 1500)","Mid-day Booster (Rs. 800)","Family Pack (Rs. 2500)","Honeymoon Pack (Rs. 1800)"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spa_booking);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, SpaPacks);
        Spinner s = findViewById(R.id.spinner2);
        s.setAdapter(adapter);

        this.context = this;

        Intent i = getIntent();
        final int id = i.getIntExtra("id",-1);

        Button submit = findViewById(R.id.button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText textView = findViewById(R.id.editText);
                EditText textView2 = findViewById(R.id.editText2);

                Spinner s = findViewById(R.id.spinner2);
                String pckid = "";
                String pack = s.getSelectedItem().toString();
                String message = "";

                int total = 0;

                if(pack.equals(SpaPacks[0])){
                    pckid = "1";
                    total = 1500;
                    message = "\"Weekend Reliever\" pack chosen";
                }
                else if(pack.equals(SpaPacks[1])){
                    pckid = "2";
                    total = 800;
                    message = "\"Mid-day Booster\" pack chosen";
                }
                else if(pack.equals(SpaPacks[2])){
                    pckid = "3";
                    total = 2500;
                    message = "\"Family Pack\" pack chosen";
                }
                else if(pack.equals(SpaPacks[3])){
                    pckid = "4";
                    total = 1800;
                    message = "\"Honeymoon Pack\" pack chosen";
                }

                String date = textView.getText().toString().substring(6) + "-" + textView.getText().toString().substring(3,5) + "-" + textView.getText().toString().substring(0,2);
                String time = textView2.getText().toString();

                String msg = "Trying to fetch the requested slot for Spa Pack " + pckid + "\n";
                msg += date;
                msg += " at ";
                msg += time;
                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();

                new SpaAsyncTask(context, message, String.valueOf(total)).execute("http://hotel-transylvania.000webhostapp.com/book-spa.php?gid=" + id + "&pckid=" + pckid + "&date=" + date + "&time=" + time);
            }
        });
    }

}
