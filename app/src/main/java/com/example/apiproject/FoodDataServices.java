package com.example.apiproject;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FoodDataServices {

    String foodName;
    int min = 52771;
    int max = 52854;
    Context context;

    public FoodDataServices(Context context) {
        this.context = context;
    }

    public interface SearchFoodListener {
        void onError(String message);

        void onResponse(List<FoodDto> searchFoodList);
    }

    public interface RandomFoodRespone {
        void onError(String message);

        void onResponse(List<FoodDto> randomFoodList);
    }

    public void getSearchedFood(String foodName,SearchFoodListener searchFoodListener) {
        List<FoodDto> searchedFood=new ArrayList<>();
        String url = "https://www.themealdb.com/api/json/v1/1/search.php?s="+foodName;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("meals");
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    FoodDto foodDto=new FoodDto();

                    foodDto.setIdMeal(jsonObject.getInt("idMeal"));
                    foodDto.setStrMeal(jsonObject.getString("strMeal"));
                    foodDto.setStrDrinkAlternate(jsonObject.getString("strDrinkAlternate"));
                    foodDto.setStrCategory(jsonObject.getString("strCategory"));
                    foodDto.setStrArea(jsonObject.getString("strArea"));
                    foodDto.setStrInstruction(jsonObject.getString("strInstructions"));
                    foodDto.setStrMealThumb(jsonObject.getString("strMealThumb"));
                    foodDto.setStrTags(jsonObject.getString("strTags"));
                    foodDto.setStrYoutube(jsonObject.getString("strYoutube"));
                    foodDto.setStrIngredient1(jsonObject.getString("strIngredient1"));
                    foodDto.setStrIngredient2(jsonObject.getString("strIngredient2"));
                    foodDto.setStrIngredient3(jsonObject.getString("strIngredient3"));
                    foodDto.setStrIngredient4(jsonObject.getString("strIngredient4"));
                    foodDto.setStrIngredient5(jsonObject.getString("strIngredient5"));
                    foodDto.setStrIngredient6(jsonObject.getString("strIngredient6"));
                    foodDto.setStrIngredient7(jsonObject.getString("strIngredient7"));
                    foodDto.setStrIngredient8(jsonObject.getString("strIngredient8"));
                    foodDto.setStrIngredient9(jsonObject.getString("strIngredient9"));
                    foodDto.setStrIngredient10(jsonObject.getString("strIngredient10"));
                    foodDto.setStrIngredient11(jsonObject.getString("strIngredient11"));
                    foodDto.setStrIngredient12(jsonObject.getString("strIngredient12"));
                    foodDto.setStrIngredient13(jsonObject.getString("strIngredient13"));
                    foodDto.setStrIngredient14(jsonObject.getString("strIngredient14"));
                    foodDto.setStrIngredient15(jsonObject.getString("strIngredient15"));
                    foodDto.setStrIngredient16(jsonObject.getString("strIngredient16"));
                    foodDto.setStrIngredient17(jsonObject.getString("strIngredient17"));
                    foodDto.setStrIngredient18(jsonObject.getString("strIngredient18"));
                    foodDto.setStrIngredient19(jsonObject.getString("strIngredient19"));
                    foodDto.setStrIngredient20(jsonObject.getString("strIngredient20"));
                    foodDto.setStrMeasure1(jsonObject.getString("strMeasure1"));
                    foodDto.setStrMeasure2(jsonObject.getString("strMeasure2"));
                    foodDto.setStrMeasure3(jsonObject.getString("strMeasure3"));
                    foodDto.setStrMeasure4(jsonObject.getString("strMeasure4"));
                    foodDto.setStrMeasure5(jsonObject.getString("strMeasure5"));
                    foodDto.setStrMeasure6(jsonObject.getString("strMeasure6"));
                    foodDto.setStrMeasure7(jsonObject.getString("strMeasure7"));
                    foodDto.setStrMeasure8(jsonObject.getString("strMeasure8"));
                    foodDto.setStrMeasure9(jsonObject.getString("strMeasure9"));
                    foodDto.setStrMeasure10(jsonObject.getString("strMeasure10"));
                    foodDto.setStrMeasure11(jsonObject.getString("strMeasure11"));
                    foodDto.setStrMeasure12(jsonObject.getString("strMeasure12"));
                    foodDto.setStrMeasure13(jsonObject.getString("strMeasure13"));
                    foodDto.setStrMeasure14(jsonObject.getString("strMeasure14"));
                    foodDto.setStrMeasure15(jsonObject.getString("strMeasure15"));
                    foodDto.setStrMeasure16(jsonObject.getString("strMeasure16"));
                    foodDto.setStrMeasure17(jsonObject.getString("strMeasure17"));
                    foodDto.setStrMeasure18(jsonObject.getString("strMeasure18"));
                    foodDto.setStrMeasure19(jsonObject.getString("strMeasure19"));
                    foodDto.setStrMeasure20(jsonObject.getString("strMeasure20"));
                    foodDto.setStrSource(jsonObject.getString("strSource"));
                    foodDto.setStrImageSource(jsonObject.getString("strImageSource"));
                    foodDto.setStrCreativeCommonsConfirmed(jsonObject.getString("strCreativeCommonsConfirmed"));
                    foodDto.setDateModified(jsonObject.getString("dateModified"));
                    searchedFood.add(foodDto);
                    searchFoodListener.onResponse(searchedFood );


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Something Wrong", Toast.LENGTH_SHORT).show();
            }
        });

        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    public void getRandomFoodList(RandomFoodRespone respone) {
        List<FoodDto> randomFoodList = new ArrayList<FoodDto>();

        for (int j = 0; j < 15; j++) {
            int id = (int) (Math.random() * (max - min + 1) + min);
            String url = "https://www.themealdb.com/api/json/v1/1/lookup.php?i=" + id;
            List<FoodDto> food = new ArrayList<FoodDto>();
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONArray foodDtoList = response.getJSONArray("meals");
                        JSONObject jsonObject = foodDtoList.getJSONObject(0);
                        FoodDto foodDto = new FoodDto();

                        foodDto.setIdMeal(jsonObject.getInt("idMeal"));
                        foodDto.setStrMeal(jsonObject.getString("strMeal"));
                        foodDto.setStrDrinkAlternate(jsonObject.getString("strDrinkAlternate"));
                        foodDto.setStrCategory(jsonObject.getString("strCategory"));
                        foodDto.setStrArea(jsonObject.getString("strArea"));
                        foodDto.setStrInstruction(jsonObject.getString("strInstructions"));
                        foodDto.setStrMealThumb(jsonObject.getString("strMealThumb"));
                        foodDto.setStrTags(jsonObject.getString("strTags"));
                        foodDto.setStrYoutube(jsonObject.getString("strYoutube"));
                        foodDto.setStrIngredient1(jsonObject.getString("strIngredient1"));
                        foodDto.setStrIngredient2(jsonObject.getString("strIngredient2"));
                        foodDto.setStrIngredient3(jsonObject.getString("strIngredient3"));
                        foodDto.setStrIngredient4(jsonObject.getString("strIngredient4"));
                        foodDto.setStrIngredient5(jsonObject.getString("strIngredient5"));
                        foodDto.setStrIngredient6(jsonObject.getString("strIngredient6"));
                        foodDto.setStrIngredient7(jsonObject.getString("strIngredient7"));
                        foodDto.setStrIngredient8(jsonObject.getString("strIngredient8"));
                        foodDto.setStrIngredient9(jsonObject.getString("strIngredient9"));
                        foodDto.setStrIngredient10(jsonObject.getString("strIngredient10"));
                        foodDto.setStrIngredient11(jsonObject.getString("strIngredient11"));
                        foodDto.setStrIngredient12(jsonObject.getString("strIngredient12"));
                        foodDto.setStrIngredient13(jsonObject.getString("strIngredient13"));
                        foodDto.setStrIngredient14(jsonObject.getString("strIngredient14"));
                        foodDto.setStrIngredient15(jsonObject.getString("strIngredient15"));
                        foodDto.setStrIngredient16(jsonObject.getString("strIngredient16"));
                        foodDto.setStrIngredient17(jsonObject.getString("strIngredient17"));
                        foodDto.setStrIngredient18(jsonObject.getString("strIngredient18"));
                        foodDto.setStrIngredient19(jsonObject.getString("strIngredient19"));
                        foodDto.setStrIngredient20(jsonObject.getString("strIngredient20"));
                        foodDto.setStrMeasure1(jsonObject.getString("strMeasure1"));
                        foodDto.setStrMeasure2(jsonObject.getString("strMeasure2"));
                        foodDto.setStrMeasure3(jsonObject.getString("strMeasure3"));
                        foodDto.setStrMeasure4(jsonObject.getString("strMeasure4"));
                        foodDto.setStrMeasure5(jsonObject.getString("strMeasure5"));
                        foodDto.setStrMeasure6(jsonObject.getString("strMeasure6"));
                        foodDto.setStrMeasure7(jsonObject.getString("strMeasure7"));
                        foodDto.setStrMeasure8(jsonObject.getString("strMeasure8"));
                        foodDto.setStrMeasure9(jsonObject.getString("strMeasure9"));
                        foodDto.setStrMeasure10(jsonObject.getString("strMeasure10"));
                        foodDto.setStrMeasure11(jsonObject.getString("strMeasure11"));
                        foodDto.setStrMeasure12(jsonObject.getString("strMeasure12"));
                        foodDto.setStrMeasure13(jsonObject.getString("strMeasure13"));
                        foodDto.setStrMeasure14(jsonObject.getString("strMeasure14"));
                        foodDto.setStrMeasure15(jsonObject.getString("strMeasure15"));
                        foodDto.setStrMeasure16(jsonObject.getString("strMeasure16"));
                        foodDto.setStrMeasure17(jsonObject.getString("strMeasure17"));
                        foodDto.setStrMeasure18(jsonObject.getString("strMeasure18"));
                        foodDto.setStrMeasure19(jsonObject.getString("strMeasure19"));
                        foodDto.setStrMeasure20(jsonObject.getString("strMeasure20"));
                        foodDto.setStrSource(jsonObject.getString("strSource"));
                        foodDto.setStrImageSource(jsonObject.getString("strImageSource"));
                        foodDto.setStrCreativeCommonsConfirmed(jsonObject.getString("strCreativeCommonsConfirmed"));
                        foodDto.setDateModified(jsonObject.getString("dateModified"));
                        randomFoodList.add(foodDto);
                        respone.onResponse(randomFoodList);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


            MySingleton.getInstance(context).addToRequestQueue(request);
        }


    }

}
