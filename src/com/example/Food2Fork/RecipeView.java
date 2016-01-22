package com.example.Food2Fork;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Second activity that used to view detalied info about recipe
 */

public class RecipeView extends Activity {
    static JSONObject recipe;
    String recipeID;
    TextView recipeName;
    TextView authorName;
    TextView rank;
    ListView lv;
    Api api;
    String apiKey;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_view);
        lv = (ListView) findViewById(R.id.listView);
        recipe = new JSONObject();
        apiKey = getResources().getString(R.string.api_key);
        recipeID = getIntent().getStringExtra("recipeF2Fid");
        recipeName = (TextView) findViewById(R.id.recipe_name);
        authorName = (TextView) findViewById(R.id.author_name);
        rank = (TextView) findViewById(R.id.rank);
        api.connect(apiKey, recipeID, this);
    }

    void showRecipe() throws JSONException {
        recipeName.setText(recipe.getString("title"));
        authorName.setText(recipe.getString("publisher"));
        rank.setText("Rank: " + recipe.getString("social_rank"));
        JSONArray ingredients;
        ingredients = recipe.getJSONArray("ingredients");
        String ingreds[] = new String[ingredients.length()];
        for (int i = 0; i < ingredients.length(); i++) {
            ingreds[i] = ingredients.getString(i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, ingreds);
        lv.setAdapter(adapter);
        ImageView iv = (ImageView) findViewById(R.id.recipe_image);
        new DownloadImageTask(iv)
                .execute(recipe.getString("image_url"));
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;
        String url;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            url = urldisplay;
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);

            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
