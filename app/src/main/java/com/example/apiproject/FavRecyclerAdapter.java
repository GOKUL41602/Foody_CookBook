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

import java.util.ArrayList;
import java.util.List;

public class FavRecyclerAdapter extends RecyclerView.Adapter<FavRecyclerAdapter.ViewHolder> {

    List<FoodDatabaseModel> foodList = new ArrayList<>();

    Context context;

    public FavRecyclerAdapter(Context context, List<FoodDatabaseModel> list) {
        this.context = context;
        foodList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.food_format, parent, false);
        return new FavRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavRecyclerAdapter.ViewHolder holder, int position) {
        FoodDatabaseModel model = foodList.get(position);
        holder.foodTitle.setText(model.getFoodName());
        String link = model.getFoodLink();
        Picasso.with(context).load(link).into(holder.foodImage);
        holder.favourites.setImageResource(R.drawable.ic_favourite);
        holder.favourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.favourites.setSelected(holder.favourites.isSelected());
                if (holder.favourites.isPressed()) {
                    holder.favourites.setImageResource(R.drawable.ic_un_favourite);
                    try {
                        DatabaseHelper helper = new DatabaseHelper(context);
                        FoodDatabaseModel model1 = foodList.get(position);
                        helper.deleteOne(model1);
                        foodList.remove(position);
                        refreshList(position);
                    } catch (Exception e) {
                    }

                }
            }
        });
        holder.foodImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("foodName", model.getFoodName());
                context.startActivity(intent);
            }
        });

    }

    public void refreshList(int position) {
        notifyItemRemoved(position);
        notifyDataSetChanged();
        notifyAll();
    }

    @Override
    public int getItemCount() {
        return foodList.size();
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
