package com.example.stormhacks2021;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private List<Exposure> exposures;
    private static ExposureManager instance;

    @RequiresApi(api = Build.VERSION_CODES.O)
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
        instance = ExposureManager.getInstance();
        fillExposures();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void fillExposures() {
        InputStream inputStream = getResources().openRawResource(R.raw.coviddata);
        BufferedReader reader = new BufferedReader( new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        String line = "";
        try{
            List<Exposure> nexposures = new ArrayList<>();
            while ((line = reader.readLine()) != null){
                String[] attributes = line.split(",");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                LocalDate date = LocalDate.parse(attributes[3],formatter);
                Exposure exposure = new Exposure(attributes[0],attributes[1],attributes[2],date);
                nexposures.add(exposure);
            }
            instance.setExposures(nexposures);
        }catch (IOException e){
            Log.wtf("error reading data for fraser health ->" + line,e);
        }
    }
}