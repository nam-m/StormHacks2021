package com.example.stormhacks2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class NewUserActivity extends AppCompatActivity {
    private EditText mName, mEmail, mInterests;
    private Button submitBtn;

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("Users");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

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
                    startActivity(new Intent(getApplicationContext(), MessageActivity.class));
                    overridePendingTransition(0,0);
                    return true;
            }
            return true;
        });

        mName = findViewById(R.id.nameEditText);
        mEmail = findViewById(R.id.emailEditText);
        mInterests = findViewById(R.id.interestsEditText);
        submitBtn = findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mName.getText().toString();
                String email = mEmail.getText().toString();
                String interests = mInterests.getText().toString();

                HashMap<String , String> userMap = new HashMap<>();
                userMap.put("name", name);
                userMap.put("email", email);
                userMap.put("interests", interests);

                root.push().setValue(userMap);

                Context context = getApplicationContext();
                CharSequence text = "Submitted!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                finish();
            }
        });
    }
}