package com.example.apiproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class FoodDtoAdapter extends RecyclerView.Adapter<FoodDtoAdapter.ViewHolder> {


    List<FoodDto> foodsList;
    Context context;

    public FoodDtoAdapter(List<FoodDto> foodsList, Context context) {
        this.foodsList = foodsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.food_format, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodDtoAdapter.ViewHolder holder, int position) {
        FoodDto foodDto = foodsList.get(position);
        holder.foodTitle.setText(foodDto.getStrMeal());
        Picasso.with(context)
                .load(foodDto.getStrMealThumb())
                .resize(300, 300)
                .into(holder.foodImage);
        holder.foodImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("foodName", foodDto.getStrMeal());
                context.startActivity(intent);
            }
        });
        holder.favourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.favourites.setSelected(!holder.favourites.isSelected());
                if (holder.favourites.isPressed()) {
                    holder.favourites.setImageResource(R.drawable.ic_favourite);
                    Toast.makeText(context, "Added to Favourites", Toast.LENGTH_SHORT).show();
                    FoodDatabaseModel model = new FoodDatabaseModel(foodDto.getIdMeal(), holder.foodTitle.getText().toString(), foodDto.getStrMealThumb());
                    Toast.makeText(context, model.getFoodLink(), Toast.LENGTH_SHORT).show();
                    DatabaseHelper helper = new DatabaseHelper(context);
                    boolean success = helper.addOn(model);
                    Toast.makeText(context, "Success " + success, Toast.LENGTH_SHORT).show();
                } else {
                    holder.favourites.setImageResource(R.drawable.ic_un_favourite);
                    Toast.makeText(context, "Removed to Favourites", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView foodTitle, foodDesc, foodCategory, foodArea;
        private ImageView foodImage;
        private ImageButton favourites;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodTitle = itemView.findViewById(R.id.foodName);
            foodImage = itemView.findViewById(R.id.foodImage);
            favourites = itemView.findViewById(R.id.favouritesBtn);
        }
    }
}
