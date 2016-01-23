package com.example.Food2Fork;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
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
    //I know that this works slow, but i wanted to implement this task using standart libraries
    // we can use picasso library or etc. to speed up this process
    //for present time
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
    if (mImages.get(url) != null) //this is needed to scrolling in gridview not reloads pictures
        holder.imageView.setImageBitmap(mImages.get(url));
    else {
        new DownloadImageTask(holder.imageView)
                .execute(recipe.get(position).getRecipeImageUrl());

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
            mImages.put(url, result);
        }
    }
}
