package com.example.tushtiw97.transylvaniahotels;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class home_page extends Activity {

    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Intent i = getIntent();
//        int id = Integer.parseInt(i.getStringExtra("id"));
        id = i.getIntExtra("id", -1);
        String username = i.getStringExtra("username");

        Log.i("Homepage", String.valueOf(id));
        Log.i("Homepage", username);
        SQLiteDatabase db = openOrCreateDatabase("hotel",MODE_PRIVATE,null);
        db.execSQL("insert into user values (" + String.valueOf(id) + ",'" + username + "');");
        Button orderFood = findViewById(R.id.button);
        orderFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SelectItems.class);
                startActivity(intent);
            }
        });

        Button gym = findViewById(R.id.button2);
        gym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),gym.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

        Button roomService = findViewById(R.id.button3);
        roomService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RoomService.class);
                startActivity(intent);
            }
        });

        Button spa = findViewById(R.id.button4);
        spa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SpaBooking.class);
                startActivity(intent);
            }
        });

        Button tour = findViewById(R.id.button5);
        tour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),TourismPackage.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

        Button laundry = findViewById(R.id.button6);
        laundry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Laundry.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });


    }

}
