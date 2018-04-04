package com.example.tushtiw97.transylvaniahotels;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.EditText;
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
 * Created by tushtiw97 on 27/3/18.
 */

public class RetrieveData extends AsyncTask<String, Void, String> {

    Context context;
    String serverResponse;
    EditText t1;

    public RetrieveData(Context context){
        this.context = context;
    }

    public RetrieveData(Context context, EditText t1){
        this.context = context;
        this.t1 = t1;
    }

    @Override
    protected String doInBackground(String... links){
        final String link = links[0];
        try {
            URL url = new URL(link);
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(link));
            HttpResponse response = client.execute(request);
            BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuffer sb = new StringBuffer("");
            String currentLine = "";

            while((currentLine = in.readLine()) != null){
                sb.append(currentLine);
            }
            in.close();
            serverResponse = sb.toString();
            //return serverResponse;
        }
        catch(Exception e){
            Toast.makeText(context, "Exception : " + e.toString(),Toast.LENGTH_LONG).show();
        }
        return serverResponse;
    }

    protected void onPostExecute(String result){
        if(serverResponse.equals("login")){
            int id = Integer.parseInt(t1.getText().toString().substring(9));
            String username = t1.getText().toString().substring(0,9);
            Intent i = new Intent(context, home_page.class);
            i.putExtra("id",id);
            i.putExtra("username",username);
            context.startActivity(i);
        }
        else {
            Toast.makeText(context, serverResponse, Toast.LENGTH_LONG).show();
        }
    }
}
