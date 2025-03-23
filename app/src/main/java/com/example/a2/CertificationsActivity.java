package com.example.a2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class CertificationsActivity extends AppCompatActivity {
    EditText etCertName, etIssuedBy, etCertDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certifications);

        etCertName = findViewById(R.id.cert_name);
        etIssuedBy = findViewById(R.id.issued_by);
        etCertDate = findViewById(R.id.cert_date);

        Button btnSave = findViewById(R.id.save_btn);
        Button btnCancel = findViewById(R.id.cancel_btn);

        // Load saved certification data if it exists
        SharedPreferences prefs = getSharedPreferences("CV_DATA", MODE_PRIVATE);
        etCertName.setText(prefs.getString("certName", ""));
        etIssuedBy.setText(prefs.getString("certIssuedBy", ""));
        etCertDate.setText(prefs.getString("certDate", ""));

        btnSave.setOnClickListener(v -> {
            String certName = etCertName.getText().toString();
            String issuedBy = etIssuedBy.getText().toString();
            String certDate = etCertDate.getText().toString();

            String certification = certName + " by " + issuedBy + " (" + certDate + ")";

            getSharedPreferences("cv_data", MODE_PRIVATE)
                    .edit()
                    .putString("certification", certification)
                    .apply();

            finish();
        });


        btnCancel.setOnClickListener(v -> finish());
    }
}
