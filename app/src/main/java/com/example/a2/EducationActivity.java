package com.example.a2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class EducationActivity extends AppCompatActivity {
    EditText etDegree, etInstitution, etStartYear, etEndYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);

        etDegree = findViewById(R.id.et_degree);
        etInstitution = findViewById(R.id.et_institution);
        etStartYear = findViewById(R.id.et_start_year);
        etEndYear = findViewById(R.id.et_end_year);

        Button btnSave = findViewById(R.id.save_btn);
        Button btnCancel = findViewById(R.id.cancel_btn);

        // Load previously saved data
        SharedPreferences prefs = getSharedPreferences("CV_DATA", MODE_PRIVATE);
        etDegree.setText(prefs.getString("degree", ""));
        etInstitution.setText(prefs.getString("institution", ""));
        etStartYear.setText(prefs.getString("startYear", ""));
        etEndYear.setText(prefs.getString("endYear", ""));

        btnSave.setOnClickListener(v -> {
            String degree = etDegree.getText().toString();
            String institution = etInstitution.getText().toString();
            String startYear = etStartYear.getText().toString();
            String endYear = etEndYear.getText().toString();

            String education = degree + " at " + institution + " (" + startYear + "-" + endYear + ")";

            getSharedPreferences("cv_data", MODE_PRIVATE)
                    .edit()
                    .putString("education", education)
                    .apply();

            finish();
        });


        btnCancel.setOnClickListener(v -> finish());
    }
}
