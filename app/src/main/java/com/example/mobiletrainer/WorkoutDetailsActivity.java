package com.example.mobiletrainer;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class WorkoutDetailsActivity extends AppCompatActivity {
    private ListView lstExercises;
    private Bundle bundle;

    private ArrayList<Exercise> exercises;

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_details);

        lstExercises = findViewById(R.id.lstExercises);


        exercises = new ArrayList<Exercise>(0);

        db = new DatabaseHelper(this);
    }

    public class WorkoutDetailsHandler extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            try{
                getAllWorkouts();
                return exercises;
            }
            catch (Exception e) {
                Toast.makeText(WorkoutDetailsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            lstExercises.setAdapter(new ExerciseAdapter(WorkoutDetailsActivity.this, exercises));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        bundle = new Bundle();
        bundle = getIntent().getExtras();

        WorkoutDetailsHandler handler = new WorkoutDetailsHandler();
        handler.execute();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.workouts_details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(WorkoutDetailsActivity.this, ExercisesActivity.class);
        intent.putExtra("workoutId", bundle.getInt("workoutId"));
        if (item.getItemId() == R.id.addWorkout) {
            startActivity(intent);
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void getAllWorkouts(){
        Cursor cursor = db.getExercises(bundle.getInt("workoutId"));

        for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
           exercises.add(new Exercise(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(5)));
        }
    }
}
