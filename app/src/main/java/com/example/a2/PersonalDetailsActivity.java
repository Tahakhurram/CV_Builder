package com.example.a2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.appcompat.app.AppCompatActivity;

public class PersonalDetailsActivity extends AppCompatActivity {

    EditText etName, etEmail, etPhone;
    RadioGroup rgGender;
    RadioButton rbMale, rbFemale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_info);

        etName = findViewById(R.id.full_name);
        etEmail = findViewById(R.id.email);
        etPhone = findViewById(R.id.phone_number);
        rgGender = findViewById(R.id.gender);
        rbMale = findViewById(R.id.male_op);
        rbFemale = findViewById(R.id.female_op);

        Button btnSave = findViewById(R.id.save_btn);
        Button btnCancel = findViewById(R.id.cancel_btn);

        // Load saved data if exists
        SharedPreferences prefs = getSharedPreferences("CV_DATA", MODE_PRIVATE);
        etName.setText(prefs.getString("name", ""));
        etEmail.setText(prefs.getString("email", ""));
        etPhone.setText(prefs.getString("phone", ""));
        String gender = prefs.getString("gender", "");
        if (gender.equals("Male")) {
            rbMale.setChecked(true);
        } else if (gender.equals("Female")) {
            rbFemale.setChecked(true);
        }

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String email = etEmail.getText().toString();
            String phone = etPhone.getText().toString();

            getSharedPreferences("cv_data", MODE_PRIVATE)
                    .edit()
                    .putString("name", name)
                    .putString("email", email)
                    .putString("phone", phone)
                    .apply();

            finish();
        });


        btnCancel.setOnClickListener(v -> finish());
    }
}
