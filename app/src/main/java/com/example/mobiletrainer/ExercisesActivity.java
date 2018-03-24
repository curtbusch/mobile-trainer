package com.example.mobiletrainer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ExercisesActivity extends AppCompatActivity {
    private ListView lstExercises;
    private ArrayList<Exercise> exercises;
    private boolean isLastPage = false;
    private ArrayAdapter adapter;

    //Remove
    private ArrayList<String> testList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);


        Toast.makeText(ExercisesActivity.this,"Loading Exercises...", Toast.LENGTH_SHORT).show();

        // Initialize listview and list of exercises
        lstExercises = findViewById(R.id.lstExercises);
        exercises = new ArrayList<Exercise>(10);
        //Remove
        testList = new ArrayList<String>(10);

        Log.d("islastpage", Boolean.toString(isLastPage));

        for(int i = 2; i < 26; i++) {
            String url = "https://wger.de/api/v2/exercise.json/?page=" + Integer.toString(i);
            OkHttpHandler httpHandler = new OkHttpHandler(i);
            httpHandler.execute(url);
        }
    }

    public class OkHttpHandler extends AsyncTask {
        OkHttpClient client = new OkHttpClient();
        private int count;

        public OkHttpHandler(int count){
            this.count = count;
        }

        @Override
        protected String doInBackground(Object[] params) {


            Request.Builder builder = new Request.Builder();
            builder.url(params[0].toString());
            Request request = builder.build();

            try {
                Response response = client.newCall(request).execute();
                Log.d("Responsecode", Integer.toString(response.code()));
                return response.body().string();
            }catch (Exception e){
                Toast.makeText(ExercisesActivity.this,"Request failed: unable to access api", Toast.LENGTH_SHORT).show()
                ;
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            parseResponse(o.toString());
            Log.d("curtis", Integer.toString(exercises.size()));
            Log.d("whatiscount", Integer.toString(count));

            if(count == 2) {
                adapter = new MyAdapter(ExercisesActivity.this, exercises);
                lstExercises.setAdapter(adapter);
            }
            else {
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void parseResponse(String response) {
        try{
            JSONObject json = new JSONObject(response);
            JSONArray results = (JSONArray) json.get("results");

            for(int i = 0; i < results.length(); i++) {
                JSONObject exercise = results.getJSONObject(i);
                Exercise exerciseToAdd = new Exercise(exercise.getString("name"), exercise.getString("description"), mapIdToCategory(exercise.getInt("category")));

                //remove
                String test = exercise.getString("name");
                testList.add(test);
                exercises.add(exerciseToAdd);

            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("parseresponseerror", e.getMessage());
        }
    }

    // The exercise is linked to it's category by ID so this method is used to get the name of the category for the exercise
    private String mapIdToCategory(int categoryId) {
        String categoryName = "";

        switch (categoryId) {
            case 10:
                categoryName = "Abs";
                break;
            case 8:
                categoryName = "Arms";
                break;
            case 12:
                categoryName = "Back";
                break;
            case 14:
                categoryName = "calves";
                break;
            case 11:
                categoryName = "Chest";
                break;
            case 9:
                categoryName = "Legs";
                break;
            case 13:
                categoryName = "Shoulders";
                break;
        }

        return categoryName;
    }
}
