package com.example.mobiletrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ExerciseDetailsActivity extends AppCompatActivity {
    private TextView txtName;
    private TextView txtDescription;
    private TextView txtCategory;
    private Button btnAddToWorkout;

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_details);

        txtName = findViewById(R.id.txtName);
        txtDescription = findViewById(R.id.txtDescription);
        txtCategory = findViewById(R.id.txtCategory);
        btnAddToWorkout = findViewById(R.id.btnAddToWorkout);

        bundle = new Bundle();
        bundle =  getIntent().getExtras();

        String name = bundle.getString("name");

        txtName.setText(bundle.getString("name"));
        txtDescription.setText(bundle.getString("description"));
        txtCategory.setText(bundle.getString("category"));

        if(!bundle.getBoolean("alreadyAdded")) {
            btnAddToWorkout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ExerciseDetailsActivity.this, AddToWorkoutActivity.class);
                    intent.putExtra("title", bundle.getString("name"));
                    intent.putExtra("description", bundle.getString("description"));
                    intent.putExtra("category", bundle.getString("category"));
                    intent.putExtra("workoutId", bundle.getInt("workoutId"));
                    startActivity(intent);
                }
            });
        }
        else{
            btnAddToWorkout.setVisibility(View.INVISIBLE);
        }
    }
}
