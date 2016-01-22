package com.example.Food2Fork;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import java.util.*;

/**
 * I know that some code not as well as possible, but with some things i have not worked before
 * and learned it during writing the program code
 */

public class MainActivity extends Activity {

    public static String apiKey;
    static ArrayList <Recipe> recipes = new ArrayList <Recipe>();
    GridView gv;
    static String sortingMode = "r";
    static int pageNumber=1;
    static int pageSearchNumber =1;
    static TextView pageNumberLabel;
    static Api api;
    boolean ifSearch = false;
    Button prevButton;
    Button nextButton;
    Search search;
    boolean isGridViev = false;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        api = new Api();
        prevButton = (Button) findViewById(R.id.prevPageButton);
        nextButton = (Button) findViewById(R.id.nextPageButton);
        pageNumberLabel = (TextView) findViewById(R.id.pageLabel);
        apiKey = getResources().getString(R.string.api_key); //API key
        viewType();
        search = new Search(this);
        if (pageNumber<1) prevButton.setEnabled(false);
    }

    private void viewType() {
        //in future i can implement this method to switch between GridView and ListView instead of just change gridview columns
            gv = (GridView) findViewById(R.id.gridView);
            gv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id){
                    Toast.makeText(getBaseContext(),
                            "Recipe " + (position + 1) +" selected",
                            Toast.LENGTH_SHORT).show();
                    Intent inent = new Intent(v.getContext(), RecipeView.class);
                    inent.putExtra("recipeF2Fid", GridViewAdapter.recipe.get(position).getRecipeID());
                    startActivity(inent);
                }
            });
        doConnection();
    }


    private void doConnection() {
        api.connect(apiKey,"","&page="+pageNumber+"&sort="+sortingMode,this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this, "Changed to list view", Toast.LENGTH_SHORT).show();
              gv.setNumColumns(1);
                isGridViev = false;
                viewType();
                return true;
            case R.id.item2:
                Toast.makeText(this, "changed to grid view", Toast.LENGTH_SHORT).show();
                gv.setNumColumns(2);
                isGridViev = true;
                viewType();
                return true;
            case R.id.item3:
                 ifSearch = true;
                search.onSearchClick();
                return true;
            case R.id.item4:
                Toast.makeText(this, "Top rated", Toast.LENGTH_SHORT).show();
                ifSearch = false;
                sortingMode = "r";
                doConnection();
                return true;
            case R.id.item5:
                Toast.makeText(this, "Trending", Toast.LENGTH_SHORT).show();
                 ifSearch = false;
                sortingMode = "t";
                doConnection();

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void addToView(){
        gv.invalidateViews();
        gv.setAdapter(new GridViewAdapter(this,  recipes));

    }

    public void prevButtonClick(View v){
        if (!ifSearch) {
            if (pageNumber > 1) pageNumber--;
            doConnection();
        }
        else {
            if (pageSearchNumber > 1) pageSearchNumber--;
                search.searchRecipe(search.userInput);
        }

    }
    public void nextButtonClick(View v){
        if (!ifSearch) {
            pageNumber++;
            doConnection();
        } else {
            pageSearchNumber++;
            search.searchRecipe(search.userInput);
        }
    }
}
