package com.example.apiproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class Favourites extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FavRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        recyclerView = findViewById(R.id.favourites_recView);

        DatabaseHelper helper = new DatabaseHelper(Favourites.this);
        List<FoodDatabaseModel> foodList = helper.getAll();
        adapter = new FavRecyclerAdapter(Favourites.this, foodList);

        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }
}