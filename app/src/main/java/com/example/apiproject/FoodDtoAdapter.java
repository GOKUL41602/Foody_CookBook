package com.example.apiproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        holder.foodCategory.setText(foodDto.getStrCategory());
        holder.foodArea.setText(foodDto.getStrArea());
        holder.foodDesc.setText(foodDto.getStrInstruction());
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
            foodTitle=itemView.findViewById(R.id.foodTitle);
            foodDesc=itemView.findViewById(R.id.foodDesc);
            foodArea=itemView.findViewById(R.id.foodArea);
            foodCategory=itemView.findViewById(R.id.foodCategory);
        }
    }
}
