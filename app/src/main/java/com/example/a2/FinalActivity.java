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

    TextView tvCvPreview;
    ImageView ivProfileImage;
    String fullCv = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        tvCvPreview = findViewById(R.id.tv_cv_preview);
        ivProfileImage = findViewById(R.id.iv_profile_preview);
        Button btnShare = findViewById(R.id.btn_share);

        SharedPreferences prefs = getSharedPreferences("cv_data", MODE_PRIVATE);

        String name = prefs.getString("name", "N/A");
        String email = prefs.getString("email", "N/A");
        String phone = prefs.getString("phone", "N/A");
        String summary = prefs.getString("summary", "N/A");
        String education = prefs.getString("education", "N/A");
        String experience = prefs.getString("experience", "N/A");
        String certification = prefs.getString("certification", "N/A");
        String reference = prefs.getString("reference", "N/A");


        // Set profile image or fallback to default from drawable
        String imageUriStr = null;
        ivProfileImage.setImageResource(R.drawable.profile_pic); // fallback image

        // Format CV text
        fullCv = "Name: " + name + "\n" +
                "Email: " + email + "\n" +
                "Phone: " + phone + "\n\n" +
                "Summary:\n" + summary + "\n\n" +
                "Education:\n" + education + "\n\n" +
                "Experience:\n" + experience + "\n\n" +
                "Certifications:\n" + certification + "\n\n" +
                "References:\n" + reference + "\n\n";

        tvCvPreview.setText(fullCv);

        btnShare.setOnClickListener(v -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, fullCv);
            startActivity(Intent.createChooser(shareIntent, "Share CV via"));
        });
    }
}
