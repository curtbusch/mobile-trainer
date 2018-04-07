package com.example.mobiletrainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddToWorkoutActivity extends AppCompatActivity {
    private TextView txtTitle;
    private EditText txtSets;
    private EditText txtReps;
    private Button btnAddToWorkout;

    private int workoutId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_workout);

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();

        txtTitle = findViewById(R.id.txtTitle);
        txtSets = findViewById(R.id.txtSets);
        txtReps = findViewById(R.id.txtReps);
        btnAddToWorkout = findViewById(R.id.btnAddToWorkout);

        txtTitle.setText(bundle.getString("title"));
        workoutId = bundle.getInt("workoutId");

        btnAddToWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
