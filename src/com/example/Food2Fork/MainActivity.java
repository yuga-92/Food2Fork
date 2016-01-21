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

public class MainActivity extends Activity {

    public static String apiKey;
    static ArrayList <Recipe> recipes = new ArrayList <Recipe>();
    static GridView gv;
    static String sortingMode = "r";
    static int pageNumber=1;
    static int pageSearchNumber =1;
    static TextView pageNumberLabel;
    static Api api;
    boolean ifSearch = false;
    Button prevButton;
    Button nextButton;
    Search search;
    String uri;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        api = new Api();
        prevButton = (Button) findViewById(R.id.prevPageButton);
        nextButton = (Button) findViewById(R.id.nextPageButton);
        pageNumberLabel = (TextView) findViewById(R.id.pageLabel);
        apiKey = getResources().getString(R.string.api_key); //API key
        gv = (GridView) findViewById(R.id.gridView);
        search = new Search(this);
        uri=api.connect(apiKey,"","&page="+pageNumber+"&sort="+sortingMode,this);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent,
                                    View v, int position, long id)
            {
                Toast.makeText(getBaseContext(),
                        "Recipe " + (position + 1) +" selected",
                        Toast.LENGTH_SHORT).show();
                Intent inent = new Intent(v.getContext(), RecipeView.class);
                inent.putExtra("recipeF2Fid", GridViewAdapter.recipe.get(position).getRecipeID());
                startActivity(inent);
            }
        });
        if (pageNumber<1) prevButton.setEnabled(false);


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
                return true;
            case R.id.item2:
                Toast.makeText(this, "changed to grid view", Toast.LENGTH_SHORT).show();
                gv.setNumColumns(2);
                gv.setHorizontalSpacing(1);
                gv.setVerticalSpacing(5);
                return true;
            case R.id.item3:
                 ifSearch = true;
                search.onSearchClick();
                return true;
            case R.id.item4:
                Toast.makeText(this, "Top rated", Toast.LENGTH_SHORT).show();
                ifSearch = false;
                sortingMode = "r";
                uri =api.connect(apiKey,"","&page="+pageNumber+"&sort="+sortingMode,this);
                return true;
            case R.id.item5:
                Toast.makeText(this, "Trending", Toast.LENGTH_SHORT).show();
                 ifSearch = false;
                sortingMode = "t";
                uri = api.connect(apiKey,"","&page="+pageNumber+"&sort="+sortingMode,this);
             //This is just for me to set GridView in right way
//                <ImageView
//                android:id="@+id/grid_item_image"
//                android:layout_width="100px"
//                android:layout_height="100px"
//                android:layout_marginRight="20px"
//                android:src="@drawable/food" android:layout_alignParentTop="true" android:layout_centerHorizontal="true">
//                </ImageView>
//
//                <TextView
//                android:id="@+id/grid_item_label"
//                android:layout_width="wrap_content"
//                android:layout_height="wrap_content"
//                android:text="@+id/label"
//                android:textSize="40px"
//                android:layout_below="@+id/grid_item_image" android:layout_centerHorizontal="true">
//                </TextView>

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void addToGridView (){
        gv.invalidateViews();
        gv.setAdapter(new GridViewAdapter(gv.getContext(), recipes));
    }

    public void prevButtonClick(View v){
        if (!ifSearch) {
            if (pageNumber > 1) pageNumber--;
            uri = api.connect(apiKey, "", "&page=" + pageNumber + "&sort=" + sortingMode, this);
        }
        else {
            if (pageSearchNumber > 1) pageSearchNumber--;
                search.searchRecipe(search.userInput);
        }

    }
    public void nextButtonClick(View v){
        if (!ifSearch) {
            pageNumber++;
            uri = api.connect(apiKey, "", "&page=" + pageNumber + "&sort=" + sortingMode, this);
        } else {
            pageSearchNumber++;
            search.searchRecipe(search.userInput);
        }
    }
}
