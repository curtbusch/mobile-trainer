package com.example.mobiletrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddToWorkoutActivity extends AppCompatActivity {
    private DatabaseHelper db;

    private TextView txtTitle;
    private EditText txtSets;
    private EditText txtReps;
    private Button btnAddToWorkout;

    private Bundle bundle;

    private int workoutId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_workout);

        bundle = new Bundle();
        bundle = getIntent().getExtras();

        db = new DatabaseHelper(this);

        txtTitle = findViewById(R.id.txtTitle);
        txtSets = findViewById(R.id.txtSets);
        txtReps = findViewById(R.id.txtReps);
        btnAddToWorkout = findViewById(R.id.btnAddToWorkout);

        txtTitle.setText(bundle.getString("title"));
        workoutId = bundle.getInt("workoutId");

        btnAddToWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtSets.getText().toString().matches("") || txtReps.getText().toString().matches("")) {
                    Toast.makeText(AddToWorkoutActivity.this, "Must set sets and reps!", Toast.LENGTH_SHORT).show();
                }
                else {
                    boolean isInserted = db.insertExercise(bundle.getString("title"), bundle.getString("description"), bundle.getString("category"),
                                        Integer.parseInt(txtSets.getText().toString()), Integer.parseInt(txtReps.getText().toString()), workoutId, 0);

                    Log.d("exerciseInsert", "exercise name: " + bundle.getString("title"));
                    Log.d("exerciseInsert", "exercise description: " + bundle.getString("description"));
                    Log.d("exerciseInsert", "exercise category: " + bundle.getString("category"));
                    Log.d("exerciseInsert", "exercise sets: " + txtSets.getText().toString());
                    Log.d("exerciseInsert", "exercise reps: " + txtReps.getText().toString());
                    Log.d("exerciseInsert", "exercise workoutId: " + Integer.toString(workoutId));

                    if(isInserted) {
                        Intent intent = new Intent(AddToWorkoutActivity.this, WorkoutDetailsActivity.class);
                        intent.putExtra("workoutId", workoutId);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(AddToWorkoutActivity.this, "Exercise could not be added to workout!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
