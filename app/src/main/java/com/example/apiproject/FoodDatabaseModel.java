package com.example.apiproject;

public class FoodDatabaseModel {

    private int id;
    private String foodName;
    private String foodLink;

    public FoodDatabaseModel() {
    }

    public FoodDatabaseModel(int id, String foodName, String foodLink) {
        this.id = id;
        this.foodName = foodName;
        this.foodLink = foodLink;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodLink() {
        return foodLink;
    }

    public void setFoodLink(String foodLink) {
        this.foodLink = foodLink;
    }

    @Override
    public String toString() {
        return "FoodDatabaseModel{" +
                "id=" + id +
                ", foodName='" + foodName + '\'' +
                ", foodLink='" + foodLink + '\'' +
                '}';
    }
}
