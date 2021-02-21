package com.example.stormhacks2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Bottom navigation bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_bar);
        // Select current activity
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.search:
                    startActivity(new Intent(getApplicationContext(), MapActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.tips:
                    startActivity(new Intent(getApplicationContext(), TipsActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.message:
                    startActivity(new Intent(getApplicationContext(), MessageActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.home:
                    return true;
            }
            return true;
        });

    }
}