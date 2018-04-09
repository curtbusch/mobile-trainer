package com.example.mobiletrainer;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

public class WorkoutActivity extends AppCompatActivity {
    private DatabaseHelper db;
    private ListView lstWorkouts;
    private ArrayList<Workout> workouts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        db = new DatabaseHelper(this);
        lstWorkouts = findViewById(R.id.lstWorkouts);
        workouts = new ArrayList<Workout>(10);

        WorkoutHandler handler = new WorkoutHandler();
        handler.execute();
    }


    public class WorkoutHandler extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                getAllWorkouts();
                return workouts;
            }catch (Exception e){
                Log.d("curtis", e.getMessage());
                //Toast.makeText(WorkoutActivity.this,"Could not fetch workout records", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            lstWorkouts.setAdapter(new WorkoutAdapter(WorkoutActivity.this, workouts));

            lstWorkouts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Workout selectedWorkout = workouts.get(position);

                    Intent intent = new Intent(WorkoutActivity.this, WorkoutDetailsActivity.class);
                    intent.putExtra("workoutId", selectedWorkout.getId());

                    startActivity(intent);
                }
            });
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.workouts_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(WorkoutActivity.this, NewWorkoutActivity.class);
        if (item.getItemId() == R.id.addWorkout) {
            startActivity(intent);
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void getAllWorkouts() {
        //Toast.makeText(this, "In get all workouts", Toast.LENGTH_SHORT).show();
        Cursor cursor = db.getWorkouts();

        //Toast.makeText(WorkoutActivity.this, Integer.toString(cursor.getCount()), Toast.LENGTH_SHORT).show();

        // populate workout arraylist with workouts from database
        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            // The Cursor is now set to the right position
            workouts.add(new Workout(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)));
        }
    }
}
