package com.example.a2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class ExperienceActivity extends AppCompatActivity {
    EditText etJobTitle, etCompany, etDuration, etDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);

        etJobTitle = findViewById(R.id.et_job_title);
        etCompany = findViewById(R.id.et_company);
        etDuration = findViewById(R.id.et_duration);
        etDescription = findViewById(R.id.et_description);

        Button btnSave = findViewById(R.id.save_btn);
        Button btnCancel = findViewById(R.id.cancel_btn);

        // Load saved experience if available
        SharedPreferences prefs = getSharedPreferences("CV_DATA", MODE_PRIVATE);
        etJobTitle.setText(prefs.getString("jobTitle", ""));
        etCompany.setText(prefs.getString("company", ""));
        etDuration.setText(prefs.getString("duration", ""));
        etDescription.setText(prefs.getString("description", ""));

        btnSave.setOnClickListener(v -> {
            String jobTitle = etJobTitle.getText().toString();
            String company = etCompany.getText().toString();
            String duration = etDuration.getText().toString();
            String description = etDescription.getText().toString();

            String experience = jobTitle + " at " + company + " (" + duration + ")\n" + description;

            getSharedPreferences("cv_data", MODE_PRIVATE)
                    .edit()
                    .putString("experience", experience)
                    .apply();

            finish();
        });

        btnCancel.setOnClickListener(v -> finish());
    }
}
