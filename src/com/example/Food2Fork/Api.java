package com.example.Food2Fork;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;
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
 * Created by YuGa on 1/18/16.
 */
//receive data from server and transfer to JsonParce
public class Api extends  MainActivity{
    static String responeData;
    static String uri;
    static String getUrl = "http://food2fork.com/api/get?";
    static String searchUrl ="http://food2fork.com/api/search?";
    static int wichActivity;



    //%20 or + instead of space
    //Generate links
    public static String generateGetUri(String key, String recipeId){
        return uri = getUrl+"key="+key+"&rId="+recipeId;
    }
    public static String generateSearchUri(String key, String userRequest,String params){
        return searchUrl+"key="+key+userRequest+params;
    }


    public static String connect(String key, String userRequest,String params,MainActivity activity){
        uri = generateSearchUri(key,userRequest,params);
        new HttpAsyncTask(activity).execute(uri);
        wichActivity = 1;
        return uri;

    }
    public static void connect(String key, String recipeId, RecipeView activity){
        uri = generateGetUri(key, recipeId);
        new HttpAsyncTask(activity).execute(uri);
        wichActivity =2;

    }

    public static void connect(String previousUri, MainActivity activity){

        new HttpAsyncTask(activity).execute(previousUri);

    }

    public static String GET(String url){
        InputStream inputStream = null;
        String result = "";

        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null) {
                result = convertInputStreamToString(inputStream);
               // responeData = result;
            }
            else
                result = "Did not work!";

        } catch (Exception e) {
           // Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        String s;
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line;
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
       // MainActivity.pageNumberLabel.setText(result);
        return result;

    }

    private static class HttpAsyncTask extends AsyncTask<String, Void, String> {
        public MainActivity activity;
        public RecipeView recipeView;

        public HttpAsyncTask(MainActivity a)
        {
            this.activity = a;
        }
        public HttpAsyncTask(RecipeView a)
        {
            this.recipeView = a;
        }

        @Override
        protected String doInBackground(String... urls) {

            return GET(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
       @Override
        protected void onPostExecute(String result) {

           //MainActivity.pageNumberLabel.setText(result);
           // Toast.makeText( , "changed to grid view", Toast.LENGTH_SHORT).show();
          // if ()
           JsonParce parse = new JsonParce();
           switch (wichActivity) {
               case 1:
                   try {
                       parse.parseJsonSearchRespone(result);
                   } catch (JSONException e) {
                       e.printStackTrace();
                   }

                   activity.addToGridView();
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
