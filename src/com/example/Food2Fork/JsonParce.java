package com.example.Food2Fork;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by YuGa on 1/18/16.
 */
//Parce received JSON data into array of recipes
public class JsonParce {
    public void parseJsonSearchRespone(String dataString) throws JSONException {
        //MainActivity.recipes;
        Recipe recipe;
        MainActivity.recipes.clear();
        JSONObject allData = new JSONObject(dataString);
        //allData = (dataString);
        int numberOfRecipes = allData.getInt("count");
        JSONArray allRecipes = new JSONArray();
        allRecipes = allData.getJSONArray("recipes");
        JSONObject oneRecipe = new JSONObject();
        //тепер з масиву рецептів мені потрібно циклом витягувати по одному рецепту і створювати об'єкт з рецептом Recipe
        //а його вже додавати в MainActivity.recipes;
        //Recipe(String recipeID, String recipeImageUrl, String recipeSourceUrl, String recipeF2fUrl, String recipeTitle,
        //        String recipePublisherName, String recipePublisherUrl, String recipeF2fSocialRank, int recipeOnPageNumber
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
        JSONObject detaliedRecipe;
        RecipeView.recipe= data.getJSONObject("recipe");



    }
}
