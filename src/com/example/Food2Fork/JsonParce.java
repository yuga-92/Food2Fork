package com.example.Food2Fork;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by YuGa on 1/18/16.
 */
//Parce received JSON data into array of recipes
    //there are small data to parce so i implement this just to make it convenient
public class JsonParce {
    public void parseJsonSearchRespone(String dataString) throws JSONException {
        Recipe recipe;
        MainActivity.recipes.clear();
        JSONObject allData = new JSONObject(dataString);
        int numberOfRecipes = allData.getInt("count");
        JSONArray allRecipes;
        allRecipes = allData.getJSONArray("recipes");
        JSONObject oneRecipe;
        for (int i = 0; i<= numberOfRecipes; i++) {
            oneRecipe = allRecipes.getJSONObject(i);
            recipe = new Recipe(oneRecipe.getString("recipe_id"),
                    oneRecipe.getString("image_url"),
                    oneRecipe.getString("source_url"),
                    oneRecipe.getString("f2f_url"),
                    oneRecipe.getString("title"),
                    oneRecipe.getString("publisher"),
                    oneRecipe.getString("publisher_url"),
                    oneRecipe.getString("social_rank"),
                    3);
            MainActivity.recipes.add(recipe);
        }
    }
    public void parseJsonGetRespone(String dataString) throws JSONException {
        JSONObject data= new JSONObject(dataString);
        RecipeView.recipe= data.getJSONObject("recipe");
    }
}
