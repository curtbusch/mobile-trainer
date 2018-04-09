package com.example.mobiletrainer;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ExercisesActivity extends AppCompatActivity {
    private ListView lstExercises;
    private ArrayList<Exercise> exercises;
    private boolean isLastPage = false;
    private ArrayAdapter adapter;
    private int apiPageNumber;

    private Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);


        Toast.makeText(ExercisesActivity.this,"Loading Exercises...", Toast.LENGTH_SHORT).show();

        // Initialize listview and list of exercises
        lstExercises = findViewById(R.id.lstExercises);
        exercises = new ArrayList<Exercise>(10);

        bundle = new Bundle();
        bundle = getIntent().getExtras();

        apiPageNumber = 2;

        String url = "https://wger.de/api/v2/exercise.json/?page=" + Integer.toString(apiPageNumber);
        OkHttpHandler httpHandler = new OkHttpHandler(apiPageNumber);
        httpHandler.execute(url);

        // increase page number to load in more exercises from the next page
        apiPageNumber++;

        // If user scrolls to end hit api again
        lstExercises.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE
                        && (lstExercises.getLastVisiblePosition() - lstExercises.getHeaderViewsCount() -
                        lstExercises.getFooterViewsCount()) >= (adapter.getCount() - 1)) {

                    Toast.makeText(ExercisesActivity.this, "Loading more exercises", Toast.LENGTH_SHORT).show();
                    // Now your listview has hit the bottom
                    String url = "https://wger.de/api/v2/exercise.json/?page=" + Integer.toString(apiPageNumber);
                    OkHttpHandler httpHandler = new OkHttpHandler(apiPageNumber);
                    httpHandler.execute(url);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                //nothing
            }
        });
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
                Toast.makeText(ExercisesActivity.this,"Request failed: unable to access api", Toast.LENGTH_SHORT).show();
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

            // If count = 2 (Page where the first exercises start in the api, create adapter)
            if(count == 2) {
                adapter = new ExerciseAdapter(ExercisesActivity.this, exercises);
                lstExercises.setAdapter(adapter);

                // On list view item selected
                lstExercises.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Exercise selectedExercise = exercises.get(position);

                        Intent intent = new Intent(ExercisesActivity.this, ExerciseDetailsActivity.class);
                        // Add name, category and descrition to intent to show on details page
                        intent.putExtra("name", selectedExercise.getName());
                        intent.putExtra("description", selectedExercise.getDescription());
                        intent.putExtra("category", selectedExercise.getCategory());
                        intent.putExtra("workoutId", bundle.getInt("workoutId"));
                        // Start exercise detail activity
                        startActivity(intent);
                    }
                });
            }
            else {
                // Update list view with next page of api
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

                if(exercise.getString("description").replaceAll("\\<[^>]*>","").trim() != "")
                {
                    Exercise exerciseToAdd = new Exercise(exercise.getString("name"), exercise.getString("description").replaceAll("\\<[^>]*>", ""), mapIdToCategory(exercise.getInt("category")));

                    exercises.add(exerciseToAdd);
                }

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
