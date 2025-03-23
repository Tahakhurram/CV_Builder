package com.example.a2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class SummaryActivity extends AppCompatActivity {
    EditText etSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        etSummary = findViewById(R.id.summary_text);
        Button btnSave = findViewById(R.id.save_btn);
        Button btnCancel = findViewById(R.id.cancel_btn);

        // Load previously saved summary (if any)
        SharedPreferences prefs = getSharedPreferences("CV_DATA", MODE_PRIVATE);
        etSummary.setText(prefs.getString("summary", ""));

        btnSave.setOnClickListener(v -> {
            String summary = etSummary.getText().toString();
            getSharedPreferences("cv_data", MODE_PRIVATE)
                    .edit()
                    .putString("summary", summary)
                    .apply();

            finish(); // No need to go to MainActivity
        });


        btnCancel.setOnClickListener(v -> finish());
    }
}
