package com.example.a2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class FinalActivity extends AppCompatActivity {

    TextView tvCvPreview, tvName, tvEmail, tvPhone;
    ImageView ivProfileImage;
    String fullCv = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        // Initialize views
        tvCvPreview = findViewById(R.id.tv_cv_preview);
        ivProfileImage = findViewById(R.id.final_profile_image);
        Button btnShare = findViewById(R.id.btn_share);
        tvName = findViewById(R.id.tv_name);
        tvPhone = findViewById(R.id.tv_phone);
        tvEmail = findViewById(R.id.tv_email);

        // Retrieve data from SharedPreferences
        SharedPreferences prefs = getSharedPreferences("cv_data", MODE_PRIVATE);
        String name = prefs.getString("name", "N/A");
        String email = prefs.getString("email", "N/A");
        String phone = prefs.getString("phone", "N/A");
        String summary = prefs.getString("summary", "");
        String education = prefs.getString("education", "");
        String experience = prefs.getString("experience", "");
        String certification = prefs.getString("certification", "");
        String reference = prefs.getString("reference", "");
        String imageUriStr = prefs.getString("profile_image_uri", null);

        // Set personal data
        tvName.setText(name);
        tvPhone.setText(phone);
        tvEmail.setText(email);

        // Load profile image from URI or fallback
        if (imageUriStr != null) {
            try {
                Uri imageUri = Uri.parse(imageUriStr);
                ivProfileImage.setImageURI(imageUri);
            } catch (Exception e) {
                ivProfileImage.setImageResource(R.drawable.profile_pic); // fallback
            }
        } else {
            ivProfileImage.setImageResource(R.drawable.profile_pic);
        }

        // Build the remaining CV text (excluding name, phone, email)
        if (!summary.isEmpty()) fullCv += "Summary:\n" + summary + "\n\n";
        if (!education.isEmpty()) fullCv += "Education:\n" + education + "\n\n";
        if (!experience.isEmpty()) fullCv += "Experience:\n" + experience + "\n\n";
        if (!certification.isEmpty()) fullCv += "Certifications:\n" + certification + "\n\n";
        if (!reference.isEmpty()) fullCv += "References:\n" + reference + "\n\n";

        tvCvPreview.setText(fullCv.trim());

        // Share CV text
        btnShare.setOnClickListener(v -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, fullCv);
            startActivity(Intent.createChooser(shareIntent, "Share CV via"));
        });
    }
}
