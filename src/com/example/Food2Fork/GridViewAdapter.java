package com.example.Food2Fork;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/** Custom gridview adapter that used to hold data into list*/
public class GridViewAdapter extends BaseAdapter{
    static ArrayList <Recipe> recipe = new ArrayList<>();
    private Context context;
    private Map<String, Bitmap> mImages; //used to hold images with their links

    public GridViewAdapter(Context context, ArrayList <Recipe> recipe) {
        this.context = context;
        this.recipe = recipe;
        mImages = new HashMap<String, Bitmap>();
    }
@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View customView = convertView;
        final Recipe recipeItem  = recipe.get(position);
        final ViewHolder holder;
       if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater li = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            customView = li.inflate(R.layout.gv_main, null);
            holder.textView = (TextView) customView.findViewById(R.id.grid_item_label);
            holder.imageView = (ImageView) customView.findViewById(R.id.recipe_image);
            customView.setTag(holder);
        } else {
            holder = (ViewHolder) customView.getTag();
        }
    final String url = recipe.get(position).getRecipeImageUrl();
    if (mImages.get(url) != null) //this is needed to scrolling in gridview not reload the pictures
        holder.imageView.setImageBitmap(mImages.get(url));
    else {
        Picasso.with(context).load(url).into(holder.imageView);
    }
        holder.textView.setText(recipeItem.getRecipeTitle());
        return customView;
    }

    @Override
    public int getCount() {
        return recipe.size();
    }

    @Override
    public Object getItem(int position) {
        return recipe.get(position);
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

public static class ViewHolder {
    //for gridview elements display correctly with scrolling
    ImageView imageView;
    TextView textView;
}



}
