package com.example.mobiletrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

public class WorkoutDetailsActivity extends AppCompatActivity {
    private ListView lstExercises;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_details);

        lstExercises = findViewById(R.id.lstExercises);

        bundle = new Bundle();
        bundle = getIntent().getExtras();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.workouts_details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(WorkoutDetailsActivity.this, ExercisesActivity.class);
        intent.putExtra("workoutId", bundle.getString("workoutId"));
        if (item.getItemId() == R.id.addWorkout) {
            startActivity(intent);
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }
    }
}
