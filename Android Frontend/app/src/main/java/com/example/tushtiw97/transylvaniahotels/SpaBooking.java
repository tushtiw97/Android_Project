package com.example.tushtiw97.transylvaniahotels;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class SpaBooking extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spa_booking);

        Button submit = findViewById(R.id.button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView textView = findViewById(R.id.editText);
                TextView textView2 = findViewById(R.id.editText2);

                String msg = "Your Spa Appointment is fixed on ";
                msg += textView.getText().toString();
                msg += " at ";
                msg += textView2.getText().toString();
                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

}
