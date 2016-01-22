package com.example.Food2Fork;

import android.os.AsyncTask;
import android.util.Log;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * receive data from server and transfers to JsonParce
 */

public class Api extends MainActivity {
    static String uri;
    static String getUrl = "http://food2fork.com/api/get?";
    static String searchUrl = "http://food2fork.com/api/search?";
    static int wichActivity;

    public static String generateGetUri(String key, String recipeId) {
        return uri = getUrl + "key=" + key + "&rId=" + recipeId;
    }

    public static String generateSearchUri(String key, String userRequest, String params) {
        return searchUrl + "key=" + key + userRequest + params;
    }

    public static String connect(String key, String userRequest, String params, MainActivity activity) {
        uri = generateSearchUri(key, userRequest, params);
        new HttpAsyncTask(activity).execute(uri);
        wichActivity = 1;
        return uri;

    }

    public static void connect(String key, String recipeId, RecipeView activity) {
        uri = generateGetUri(key, recipeId);
        new HttpAsyncTask(activity).execute(uri);
        wichActivity = 2;

    }

    public static String GET(String url) {
        InputStream inputStream;
        String result = "";

        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
            inputStream = httpResponse.getEntity().getContent();
            if (inputStream != null) {
                result = convertInputStreamToString(inputStream);
            } else
                result = "Didn`t work...";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        String result = "";
        while ((line = bufferedReader.readLine()) != null)
            result += line;
        inputStream.close();
        return result;
    }

    private static class HttpAsyncTask extends AsyncTask<String, Void, String> {
        public MainActivity activity;
        public RecipeView recipeView;
        public HttpAsyncTask(MainActivity a) {
            this.activity = a;
        }
        public HttpAsyncTask(RecipeView a) {
            this.recipeView = a;
        }
        @Override
        protected String doInBackground(String... urls) {
            return GET(urls[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            //Used callback method in asynctask...
            JsonParce parse = new JsonParce();
            switch (wichActivity) {
                case 1:
                    try {
                        parse.parseJsonSearchRespone(result);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    activity.addToView();
                    break;
                case 2:
                    try {
                        parse.parseJsonGetRespone(result);
                        recipeView.showRecipe();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
            }
        }
    }
}
