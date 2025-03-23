package com.example.a2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ImageView profileImage;
    private static final int PICK_IMAGE = 100;
    Uri selectedImageUri;

    String name = "", email = "", phone = "";
    String summary = "", education = "", experience = "";
    String certification = "", reference = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profileImage = findViewById(R.id.profile_image);

        // Load saved image from SharedPreferences if exists
        SharedPreferences prefs = getSharedPreferences("CV_DATA", MODE_PRIVATE);
        String imageUriStr = prefs.getString("profileImageUri", null);
        if (imageUriStr != null) {
            selectedImageUri = Uri.parse(imageUriStr);
            profileImage.setImageURI(selectedImageUri);
        }

        Button btnProfilePic = findViewById(R.id.btn_profile_picture);
        btnProfilePic.setOnClickListener(v -> openGallery());

        findViewById(R.id.btn_personal).setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, PersonalDetailsActivity.class)));

        findViewById(R.id.btn_summary).setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, SummaryActivity.class)));

        findViewById(R.id.btn_education).setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, EducationActivity.class)));

        findViewById(R.id.btn_experience).setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ExperienceActivity.class)));

        findViewById(R.id.btn_certifications).setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, CertificationsActivity.class)));

        findViewById(R.id.btn_references).setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, ReferencesActivity.class)));

        findViewById(R.id.btn_preview).setOnClickListener(v -> {

            Intent intent = new Intent(MainActivity.this, FinalActivity.class);
            intent.putExtra("name", prefs.getString("name", ""));
            intent.putExtra("email", prefs.getString("email", ""));
            intent.putExtra("phone", prefs.getString("phone", ""));
            intent.putExtra("summary", prefs.getString("summary", ""));
            intent.putExtra("education", prefs.getString("education", ""));
            intent.putExtra("experience", prefs.getString("experience", ""));
            intent.putExtra("certification", prefs.getString("certification", ""));
            intent.putExtra("reference", prefs.getString("reference", ""));
            intent.putExtra("profileImageUri", prefs.getString("profileImageUri", ""));

            startActivity(intent);
        });

    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            profileImage.setImageURI(selectedImageUri);

            // Save image URI in SharedPreferences
            SharedPreferences prefs = getSharedPreferences("CV_DATA", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("profileImageUri", selectedImageUri.toString());
            editor.apply();
        }
    }
}
