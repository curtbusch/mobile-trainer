package com.example.mobiletrainer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ExercisesActivity extends AppCompatActivity {
    public String url = "http://ip.jsontest.com/";

    private ListView lstExercises;
    private ArrayList<Exercise> exercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        // Initialize listview and list of exercises
        //lstExercises = findViewById(R.id.lstExercises);
        //exercises = new ArrayList<Exercise>(10);

        // Set okhttp handler
        OkHttpHandler okHttpHandler = new OkHttpHandler();
        okHttpHandler.execute(url);
    }

    public class OkHttpHandler extends AsyncTask {
        OkHttpClient client = new OkHttpClient();

        @Override
        protected String doInBackground(Object[] params) {
            Request.Builder builder = new Request.Builder();
            builder.url(params[0].toString());
            Request request = builder.build();

            try {
                Response response = client.newCall(request).execute();
                Log.d("responsebod" ,response.body().toString());
                return response.body().string();
            }catch (Exception e){
                e.printStackTrace();
                Log.d("handlerexcpetion", e.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            Log.d("allison", "sdfdf");
            parseResponse(o.toString());
        }
    }

    private void parseResponse(String response) {
        try{
            JSONObject json = new JSONObject(response);
            Log.d("curtis", "Artist name: " + json.getString("ip"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
