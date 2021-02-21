package com.example.stormhacks2021.tips;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stormhacks2021.HomeActivity;
import com.example.stormhacks2021.MapActivity;
import com.example.stormhacks2021.MessageActivity;
import com.example.stormhacks2021.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TipsActivity extends AppCompatActivity {
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);

        setNavigationView();
        List<String> tips = Arrays.asList(getResources().getStringArray(R.array.tips));

        index = new Random().nextInt(tips.size());

        Log.d("index", String.valueOf(index));

        TextView tipsTextView = findViewById(R.id.tip_TextView);
        tipsTextView.setText(tips.get(index));
        tipsTextView.setMovementMethod(LinkMovementMethod.getInstance());

        FloatingActionButton backBtn = findViewById(R.id.fab_tips_back);
        FloatingActionButton fwdBtn = findViewById(R.id.fab_tips_forward);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index > 0) {
                    index--;
                }
                tipsTextView.setText(tips.get(index));
            }
        });

        fwdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index < tips.size() - 1) {
                    index++;
                }
                tipsTextView.setText(tips.get(index));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    private void setNavigationView() {
        // Bottom navigation bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation_bar);
        // Select current activity
        bottomNavigationView.setSelectedItemId(R.id.tips);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.search:
                    startActivity(new Intent(getApplicationContext(), MapActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.message:
                    startActivity(new Intent(getApplicationContext(), MessageActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.tips:
                    return true;
            }
            return true;
        });
    }
}
