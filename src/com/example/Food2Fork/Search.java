package com.example.Food2Fork;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Search recipe
 */
public class Search extends MainActivity{

    public MainActivity activity;
    public String userInput;

    public Search(MainActivity activity)
    {
        this.activity = activity;
    }
    public void onSearchClick(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.search_dialog, null);
        dialogBuilder.setView(dialogView);
        final EditText edt = (EditText) dialogView.findViewById(R.id.edit1);
        dialogBuilder.setTitle("Search");
        dialogBuilder.setMessage("Enter recipe name or ingredients (separate ingredients with commas)");
        dialogBuilder.setPositiveButton("Search", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                userInput = edt.getText().toString();
                searchRecipe(userInput);
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }
    public void searchRecipe(String userInput){
        String userInputparced = userInput.replaceAll(" ", "%20");
        api.connect(apiKey,"&q="+userInputparced,"&page="+pageSearchNumber,activity);
    }

}
