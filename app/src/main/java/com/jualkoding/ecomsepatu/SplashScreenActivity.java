package com.jualkoding.ecomsepatu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.jualkoding.ecomsepatu.ui.home.HomeActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent goHome = new Intent(SplashScreenActivity.this, HomeActivity.class);
                startActivity(goHome);
                finish();
            }
        }, 1000);
    }
}