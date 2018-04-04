package com.example.tushtiw97.transylvaniahotels;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class payment extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

//        Bundle bundle = getIntent().getExtras();
//
//        String description = bundle.getString("Description");
//        String total = bundle.getString("Total");
        Intent intent = getIntent();
        String total = intent.getStringExtra("Total");
        String description = intent.getStringExtra("Description");
        total = "\n\nTotal Cost = Rs. " + total;
        description += total;
        TextView textView = findViewById(R.id.textView);

        textView.setText(description);
    }

}
