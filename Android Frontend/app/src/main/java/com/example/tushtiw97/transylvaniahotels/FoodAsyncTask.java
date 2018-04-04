package com.example.tushtiw97.transylvaniahotels;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

/**
 * Created by tushtiw97 on 5/4/18.
 */

public class FoodAsyncTask extends AsyncTask<String, Void, String> {

    private Context context;
    private String msg;
    private String total;
    private String result;

    public FoodAsyncTask(Context context, String msg, String total){
        this.context = context;
        this.msg = msg;
        this.total = total;
    }

    @Override
    protected String doInBackground(String... strings) {
        try{
            URL url = new URL(strings[0]);
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(strings[0]));
            HttpResponse response = client.execute(request);
            BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuffer sb = new StringBuffer("");
            String line = "";

            while((line = in.readLine()) != null){
                sb.append(line);
            }

            in.close();
            result = sb.toString();
        }
        catch(Exception e){
            Toast.makeText(context, "Error : " + e.toString(), Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        //super.onPostExecute(s);
        if(result.equals("Cannot insert into orders table")){
            Toast.makeText(context, "Cannot place your order. Please try again!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "" + result, Toast.LENGTH_SHORT).show();
            Intent i = new Intent(context, payment.class);
            i.putExtra("Total", total);
            i.putExtra("Description",msg);
            context.startActivity(i);
        }
    }
}
