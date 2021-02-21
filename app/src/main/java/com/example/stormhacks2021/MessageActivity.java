package com.example.stormhacks2021;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter.RecyclerViewClickListener listener;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("Users");
    private MyAdapter adapter;
    private ArrayList<UserModel> list;

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
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.tips:
                    startActivity(new Intent(getApplicationContext(), TipsActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.message:
                    return true;
            }
            return true;
        });

        recyclerView = findViewById(R.id.UsersRecyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        setRecyclerOnClick();
        adapter = new MyAdapter(this, list, listener);
        recyclerView.setAdapter(adapter);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    UserModel model = dataSnapshot.getValue(UserModel.class);
                    list.add(model);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        setListeners();
    }

    private void setRecyclerOnClick() {
        listener = new MyAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), MessageUserActivity.class);
                intent.putExtra("name", list.get(position).getName());
                startActivity(intent);
            }
        };
    }

    private void setListeners() {
        FloatingActionButton addBtn = findViewById(R.id.addUser);
        addBtn.setOnClickListener(item -> {
            Intent intent = new Intent(MessageActivity.this, NewUserActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
