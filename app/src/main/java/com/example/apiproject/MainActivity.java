package com.example.apiproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button searchBtn, backBtn;
    private SearchView searchView;
    private RelativeLayout filteredRelLayout;
    private ScrollView scrollView;
    private int foodIDForDB;
    private String searchText, foodName, foodTitleForDB, foodImageUrlForDB;
    private ImageButton favButton;
    private ProgressBar progressBar;
    private TextView foodInstructions, foodTitle, ingredient1, ingredient2, ingredient3, ingredient4, ingredient5, ingredient6, ingredient7, ingredient8, ingredient9, ingredient10;
    private ImageView foodImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();

        try {
            foodName = getIntent().getStringExtra("foodName");
        } catch (Exception e) {
            foodName = null;
        }

        if (foodName == null) {
            backBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    progressBar.setVisibility(View.GONE);
                    filteredRelLayout.setVisibility(View.GONE);
                    scrollView.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            });

            searchBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    progressBar.setVisibility(View.VISIBLE);
                    showSpecificFood(searchView.getQuery().toString());
                    if (filteredRelLayout.getVisibility() == View.VISIBLE) {
                        if (foodTitle.getText().equals("Food Name")) {
                            Toast.makeText(MainActivity.this, "Dish Unavailable", Toast.LENGTH_SHORT).show();
                            onStartOperation();
                        }
                    }
                }
            });

            favButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    favButton.setSelected(!favButton.isSelected());
                    if (favButton.isPressed()) {
                        favButton.setImageResource(R.drawable.ic_favourite);
                        Toast.makeText(MainActivity.this, "Added to Favourites", Toast.LENGTH_SHORT).show();
                        FoodDatabaseModel model = new FoodDatabaseModel(foodIDForDB, foodTitleForDB, foodImageUrlForDB);
                        DatabaseHelper helper = new DatabaseHelper(MainActivity.this);
                        boolean success = helper.addOn(model);
                    } else {
                        favButton.setImageResource(R.drawable.ic_un_favourite);
                        DatabaseHelper helper = new DatabaseHelper(MainActivity.this);
                        FoodDatabaseModel model = new FoodDatabaseModel(foodIDForDB, foodTitleForDB, foodImageUrlForDB);
                        boolean success = helper.addOn(model);
                        try {
                            helper.deleteOne(model);
                        } catch (Exception e) {
                        }
                        Toast.makeText(MainActivity.this, "Removed to Favourites", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            onStartOperation();

        } else {
            progressBar.setVisibility(View.VISIBLE);
            showSpecificFood(foodName);


            backBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    filteredRelLayout.setVisibility(View.GONE);
                    scrollView.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    onStartOperation();
                }
            });

            favButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    favButton.setSelected(!favButton.isSelected());
                    if (favButton.isPressed()) {
                        favButton.setImageResource(R.drawable.ic_favourite);
                        Toast.makeText(MainActivity.this, "Added to Favourites", Toast.LENGTH_SHORT).show();
                        FoodDatabaseModel model = new FoodDatabaseModel(foodIDForDB, foodTitleForDB, foodImageUrlForDB);
                        DatabaseHelper helper = new DatabaseHelper(MainActivity.this);
                        boolean success = helper.addOn(model);
                    } else {
                        favButton.setImageResource(R.drawable.ic_un_favourite);
                        DatabaseHelper helper = new DatabaseHelper(MainActivity.this);
                        FoodDatabaseModel model = new FoodDatabaseModel(foodIDForDB, foodTitleForDB, foodImageUrlForDB);
                        boolean success = helper.addOn(model);
                        try {
                            helper.deleteOne(model);
                        } catch (Exception e) {
                        }
                        Toast.makeText(MainActivity.this, "Removed to Favourites", Toast.LENGTH_SHORT).show();
                    }

                }
            });

            searchBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showSpecificFood(searchView.getQuery().toString());
                    if (filteredRelLayout.getVisibility() == View.VISIBLE) {
                        if (foodTitle.getText().equals("Food Name")) {
                            Toast.makeText(MainActivity.this, "Dish Unavailable", Toast.LENGTH_SHORT).show();
                            onStartOperation();
                        }
                    }

                }
            });
        }
    }

    private void initializeViews() {
        recyclerView = findViewById(R.id.recyclerView);
        searchBtn = findViewById(R.id.searchBtn);
        backBtn = findViewById(R.id.homePage_backBtn);
        searchView = findViewById(R.id.searchView);
        scrollView = findViewById(R.id.scrollView);
        filteredRelLayout = findViewById(R.id.filteredRelLayout);
        foodTitle = findViewById(R.id.homePage_foodName);
        foodInstructions = findViewById(R.id.homePage_instruction);
        ingredient1 = findViewById(R.id.homePage_ingredient1);
        ingredient2 = findViewById(R.id.homePage_ingredient2);
        ingredient3 = findViewById(R.id.homePage_ingredient3);
        ingredient4 = findViewById(R.id.homePage_ingredient4);
        ingredient5 = findViewById(R.id.homePage_ingredient5);
        ingredient6 = findViewById(R.id.homePage_ingredient6);
        ingredient7 = findViewById(R.id.homePage_ingredient7);
        ingredient8 = findViewById(R.id.homePage_ingredient8);
        ingredient9 = findViewById(R.id.homePage_ingredient9);
        ingredient10 = findViewById(R.id.homePage_ingredient10);

        foodImage = findViewById(R.id.homePage_foodImage);

        progressBar = findViewById(R.id.progressbar);
        favButton = findViewById(R.id.homePage_fav);
    }

    public void onStartOperation() {

        FoodDataServices foodDataServices = new FoodDataServices(MainActivity.this);

        foodDataServices.getRandomFoodList(new FoodDataServices.RandomFoodRespone() {
            @Override
            public void onError(String message) {
                Toast.makeText(MainActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(List<FoodDto> randomFoodList) {
                filteredRelLayout.setVisibility(View.GONE);
                scrollView.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                FoodDtoAdapter adapter = new FoodDtoAdapter(randomFoodList, MainActivity.this);
                recyclerView.setAdapter(adapter);
                recyclerView.setNestedScrollingEnabled(false);
            }
        });
    }

    public void showSpecificFood(String foodName) {
        progressBar.setVisibility(View.GONE);
        filteredRelLayout.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        FoodDataServices foodDataServices = new FoodDataServices(MainActivity.this);
        foodDataServices.getSearchedFood(foodName, new FoodDataServices.SearchFoodListener() {
            @Override
            public void onError(String message) {

            }

            @Override
            public void onResponse(List<FoodDto> searchFoodList) {
                FoodDto foodDto = searchFoodList.get(0);
                foodTitle.setText(foodDto.getStrMeal());
                Picasso.with(MainActivity.this)
                        .load(foodDto.getStrMealThumb())
                        .into(foodImage);
                ingredient1.setText(foodDto.getStrMeasure1() + " " + foodDto.getStrIngredient1());
                ingredient2.setText(foodDto.getStrMeasure2() + " " + foodDto.getStrIngredient2());
                ingredient3.setText(foodDto.getStrMeasure3() + " " + foodDto.getStrIngredient3());
                ingredient4.setText(foodDto.getStrMeasure4() + " " + foodDto.getStrIngredient4());
                ingredient5.setText(foodDto.getStrMeasure5() + " " + foodDto.getStrIngredient5());
                ingredient6.setText(foodDto.getStrMeasure6() + " " + foodDto.getStrIngredient6());
                ingredient7.setText(foodDto.getStrMeasure7() + " " + foodDto.getStrIngredient7());
                ingredient8.setText(foodDto.getStrMeasure8() + " " + foodDto.getStrIngredient8());
                ingredient9.setText(foodDto.getStrMeasure9() + " " + foodDto.getStrIngredient9());
                foodInstructions.setText(foodDto.getStrInstruction());

                foodTitleForDB = foodDto.getStrMeal();
                foodImageUrlForDB = foodDto.getStrMealThumb();
                foodIDForDB = foodDto.getIdMeal();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_button, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.favourites:
                Intent intent = new Intent(MainActivity.this, Favourites.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}