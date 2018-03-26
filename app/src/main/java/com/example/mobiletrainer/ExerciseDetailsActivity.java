package com.example.mobiletrainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ExerciseDetailsActivity extends AppCompatActivity {
    private TextView txtName;
    private TextView txtDescription;
    private TextView txtCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_details);

        txtName = findViewById(R.id.txtName);
        txtDescription = findViewById(R.id.txtDescription);
        txtCategory = findViewById(R.id.txtCategory);

        Bundle bundle = new Bundle();
        bundle =  getIntent().getExtras();

        txtName.setText(bundle.getString("name"));
        txtDescription.setText(bundle.getString("description"));
        txtCategory.setText(bundle.getString("category"));
    }
}
