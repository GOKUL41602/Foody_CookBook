package com.example.apiproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
        View view= LayoutInflater.from(context).inflate(R.layout.food_format,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  FoodDtoAdapter.ViewHolder holder, int position) {
        FoodDto foodDto=foodsList.get(position);
        holder.foodTitle.setText(foodDto.getStrMeal());
        Picasso.with(context)
                .load(foodDto.getStrMealThumb())
                .resize(300,300)
                .into(holder.foodImage);

    }

    @Override
    public int getItemCount() {
        return foodsList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView foodTitle,foodDesc,foodCategory,foodArea;
        private ImageView foodImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodTitle=itemView.findViewById(R.id.foodName);
            foodImage=itemView.findViewById(R.id.foodImage);
        }
    }
}
