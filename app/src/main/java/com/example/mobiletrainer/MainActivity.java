package com.example.mobiletrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper myDb;
    private Button btnMyWorkouts;
    private Button btnAllExercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        btnMyWorkouts = findViewById(R.id.btnMyWorkouts);
        btnAllExercises = findViewById(R.id.btnAllExercises);

        btnMyWorkouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent workoutIntent = new Intent(MainActivity.this, WorkoutActivity.class);
                MainActivity.this.startActivity(workoutIntent);
            }
        });

        btnAllExercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent exerciseIntent = new Intent(MainActivity.this, WorkoutActivity.class);
                MainActivity.this.startActivity(exerciseIntent);
            }
        });
    }
}
