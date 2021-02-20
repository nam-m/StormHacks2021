package com.example.stormhacks2021;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        // Bottom navigation bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_bar);
        // Select current activity
        bottomNavigationView.setSelectedItemId(R.id.message);

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
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.message:
                    return true;
            }
            return true;
        });

        setAddButton();
    }

    private void setAddButton() {
        Button addBtn = findViewById(R.id.addUser);
        addBtn.setOnClickListener(item ->{
            Intent intent = new Intent(MessageActivity.this,NewUserActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
