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
 * Created by tushtiw97 on 28/3/18.
 */

public class ServerResponse extends AsyncTask<String, Void, String> {

    Context context;
    String result;
    String msg;
    String tot;

    public ServerResponse(Context context, String msg, String tot){
        this.context = context;
        this.msg = msg;
        this.tot = tot;
    }

    protected String doInBackground(String... links){
        try {
            URL url = new URL(links[0]);
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(links[0]));
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
            Toast.makeText(context, "Error : " + e.toString(), Toast.LENGTH_LONG).show();
        }
        return result;
    }

    protected void onPostExecute(String result){
        Toast.makeText(context, "" + result, Toast.LENGTH_LONG).show();
        Intent i = new Intent(context,payment.class);
        i.putExtra("Total",tot);
        i.putExtra("Description",msg);
        context.startActivity(i);
    }
}
