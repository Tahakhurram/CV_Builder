package com.example.a2;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
public class SplashActivity extends AppCompatActivity{
    private static int SPLASH_TIME_OUT = 2500; // 2.5 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        ImageView logo = findViewById(R.id.logo);
        Animation fadein = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        logo.startAnimation(fadein);

        new Handler().postDelayed(() -> {
            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }, SPLASH_TIME_OUT);
        }

}
