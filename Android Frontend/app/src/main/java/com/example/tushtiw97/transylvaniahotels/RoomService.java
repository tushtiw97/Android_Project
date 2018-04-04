package com.example.tushtiw97.transylvaniahotels;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

public class RoomService extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_service);

        Button submit = findViewById(R.id.button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePicker timePicker = findViewById(R.id.timePicker);
                int hour  = timePicker.getCurrentHour();
                int min = timePicker.getCurrentMinute();
                String msg = "Room Cleaners will come at ";
                msg += Integer.toString(hour);
                msg += ":";
                msg += Integer.toString(min);
                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
            }
        });
    }

}
