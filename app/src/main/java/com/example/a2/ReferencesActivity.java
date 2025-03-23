package com.example.a2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class ReferencesActivity extends AppCompatActivity {
    EditText etRefName, etDesignation, etOrganization, etContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_references);

        etRefName = findViewById(R.id.ref_name);
        etDesignation = findViewById(R.id.designation);
        etOrganization = findViewById(R.id.organization);
        etContact = findViewById(R.id.contact);

        Button btnSave = findViewById(R.id.save_btn);
        Button btnCancel = findViewById(R.id.cancel_btn);

        // Load previously saved data
        SharedPreferences prefs = getSharedPreferences("CV_DATA", MODE_PRIVATE);
        etRefName.setText(prefs.getString("refName", ""));
        etDesignation.setText(prefs.getString("refDesignation", ""));
        etOrganization.setText(prefs.getString("refOrganization", ""));
        etContact.setText(prefs.getString("refContact", ""));

        btnSave.setOnClickListener(v -> {
            String name = etRefName.getText().toString();
            String designation = etDesignation.getText().toString();
            String organization = etOrganization.getText().toString();
            String contact = etContact.getText().toString();

            String reference = name + ", " + designation + " at " + organization + "\nContact: " + contact;

            getSharedPreferences("cv_data", MODE_PRIVATE)
                    .edit()
                    .putString("reference", reference)
                    .apply();

            finish();
        });

        btnCancel.setOnClickListener(v -> finish());
    }
}
