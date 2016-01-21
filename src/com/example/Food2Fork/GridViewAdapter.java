package com.example.Food2Fork;

/**
 * Created by YuGa on 1/19/16.
 */
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

public class GridViewAdapter extends BaseAdapter{
    private Context context;
    static ArrayList <Recipe> recipe = new ArrayList<>();
    static String imageUrl;
    private Map<String, Bitmap> mImages;
    ViewHolder holder = null;


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



//           new DownloadImageTask(holder.imageView)
//               .execute(imageUrl);
           //holder.imageView.setImageResource(R.drawable.food);
//    new DownloadImageTask(holder.imageView)
//            .execute(recipe.get(position).getRecipeImageUrl());


        } else {
            holder = (ViewHolder) customView.getTag();
        }

   // imageUrl = recipe.get(position).getRecipeImageUrl();
    //holder.textView.setText(recipeItem.getRecipeTitle());
    final String url = recipe.get(position).getRecipeImageUrl();
    if (mImages.get(url) != null)
        holder.imageView.setImageBitmap(mImages.get(url));
    else {
        new DownloadImageTask(holder.imageView)
                .execute(recipe.get(position).getRecipeImageUrl());

    }
        holder.textView.setText(recipeItem.getRecipeTitle());
//    final String url = recipe.get(position).getRecipeImageUrl();
//    if (mImages.get(url) != null)
//        holder.imageView.setImageBitmap(mImages.get(url));
//    else
//        new DownloadImageTask(holder.imageView)
//           .execute(recipe.get(position).getRecipeImageUrl());
//        holder.imageView.setImageResource(R.drawable.food);
//    new DownloadImageTask(holder.imageView)
//            .execute(recipe.get(position).getRecipeImageUrl());

        return customView;
    }

//    private Bitmap getImageBitmap(String url) {
//        Bitmap bm = null;
//        try {
//            URL aURL = new URL(url);
//            URLConnection conn = aURL.openConnection();
//            conn.connect();
//            InputStream is = conn.getInputStream();
//            BufferedInputStream bis = new BufferedInputStream(is);
//            bm = BitmapFactory.decodeStream(bis);
//            bis.close();
//            is.close();
//        } catch (IOException e) {
//            //Log.e(, "Error getting bitmap", e);
//        }
//        return bm;
//    }

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


public static class ViewHolder {
    ImageView imageView;
    TextView textView;
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
}
