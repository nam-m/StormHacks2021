package com.example.stormhacks2021;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

        FloatingActionButton backBtn = findViewById(R.id.fab_tips_back);
        FloatingActionButton fwdBtn = findViewById(R.id.fab_tips_forward);

        if (index == 0) {
            backBtn.setVisibility(View.INVISIBLE);
        }

        if (index == tips.size() - 1) {
            fwdBtn.setVisibility(View.INVISIBLE);
        }

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index > 0) {
                    index--;
                }

                tipsTextView.setText(tips.get(index));

                if (fwdBtn.getVisibility() == View.INVISIBLE) {
                    fwdBtn.setVisibility(View.VISIBLE);
                }

                if (index == 0) {
                    backBtn.setVisibility(View.INVISIBLE);
                }

                fwdBtn.invalidate();

            }
        });

        fwdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index < tips.size() - 1) {
                    index++;
                }

                tipsTextView.setText(tips.get(index));

                if (backBtn.getVisibility() == View.INVISIBLE) {
                    backBtn.setVisibility(View.VISIBLE);
                }

                if (index == tips.size() - 1) {
                    fwdBtn.setVisibility(View.INVISIBLE);
                }

                backBtn.invalidate();

            }
        });
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
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.message:
                    startActivity(new Intent(getApplicationContext(), MessageActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.tips:
                    return true;
            }
            return true;
        });
    }
}
