package com.example.tushtiw97.transylvaniahotels;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Laundry extends Activity {

    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laundry);

        Button submit = findViewById(R.id.button);

        context = this;

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText textView = findViewById(R.id.editText);
                int no = Integer.parseInt(textView.getText().toString());
                int price = 250;

                Intent i = getIntent();
                int id = i.getIntExtra("id",-1);

                CheckBox checkBox = findViewById(R.id.checkBox);
                CheckBox checkBox1 = findViewById(R.id.checkBox2);
                String msg = "Laundry selected";
                String pckid = "1";
                //msg += textView.getText().toString();
                if(checkBox.isChecked()){
                    price += 50;
                    msg += "\nDry Cleaning is Chosen";
                    pckid = "2";
                }
                if(checkBox1.isChecked()){
                    //price +=50;
                    msg += "\nSame day delivery";
                }

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String date = simpleDateFormat.format(Calendar.getInstance().getTime());

                Toast.makeText(Laundry.this, "Pack : " + pckid + "\nTotal : " + price + "\nDate : " + date, Toast.LENGTH_SHORT).show();
                new ServerResponse(context, msg, String.valueOf(price)).execute("http://hotel-transylvania.000webhostapp.com/avails-laundry.php?gid=" + String.valueOf(id) + "&pckid=" + pckid + "&date=" + date);

//                Intent intent = new Intent(getApplicationContext(),payment.class);
//                intent.putExtra("Description",msg);
//                intent.putExtra("Total",tot);

//                startActivity(intent);

            }
        });
    }

}
