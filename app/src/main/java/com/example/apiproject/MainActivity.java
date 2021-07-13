package com.example.apiproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button searchBtn,backBtn;
    private SearchView searchView;
    private RelativeLayout filteredRelLayout;
    private ScrollView scrollView;
    private String searchText;
    private TextView foodInstructions, foodTitle, ingredient1, ingredient2, ingredient3, ingredient4, ingredient5, ingredient6, ingredient7, ingredient8, ingredient9, ingredient10;
    private ImageView foodImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        searchBtn = findViewById(R.id.searchBtn);
        backBtn=findViewById(R.id.homePage_backBtn);
        searchView = findViewById(R.id.searchView);
        scrollView=findViewById(R.id.scrollView);
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


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                filteredRelLayout.setVisibility(View.GONE);
                scrollView.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        });
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filteredRelLayout.setVisibility(View.VISIBLE);
                scrollView.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                FoodDataServices foodDataServices = new FoodDataServices(MainActivity.this);
                foodDataServices.getSearchedFood(new FoodDataServices.SearchFoodListener() {
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
                        ingredient1.setText(foodDto.getStrIngredient1());
                        ingredient2.setText(foodDto.getStrIngredient2());
                        ingredient3.setText(foodDto.getStrIngredient3());
                        ingredient4.setText(foodDto.getStrIngredient4());
                        ingredient5.setText(foodDto.getStrIngredient5());
                        ingredient6.setText(foodDto.getStrIngredient6());
                        ingredient7.setText(foodDto.getStrIngredient7());
                        ingredient8.setText(foodDto.getStrIngredient8());
                        ingredient9.setText(foodDto.getStrIngredient9());
                        foodInstructions.setText(foodDto.getStrInstruction());
                    }
                });
            }
        });


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
                recyclerView.setVisibility(View.VISIBLE);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                FoodDtoAdapter adapter = new FoodDtoAdapter(randomFoodList, MainActivity.this);
                recyclerView.setAdapter(adapter);
                recyclerView.setNestedScrollingEnabled(false);
            }
        });

    }
}