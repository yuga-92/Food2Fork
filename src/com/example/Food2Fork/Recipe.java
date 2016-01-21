package com.example.Food2Fork;

/**
 * Created by YuGa on 1/18/16.
 */
public class Recipe {
    private String recipeImageUrl;
    private String recipeID;
    private String recipeSourceUrl;
    private String recipeF2fUrl;
    private String recipeTitle;
    private String recipePublisherName;
    private String recipePublisherUrl;
    private String recipeF2fSocialRank;
    private int recipeOnPageNumber;
    private String recipeIngredients;

    public Recipe(String recipeImageUrl, String recipeID, String recipeSourceUrl, String recipeF2fUrl, String recipeTitle, String recipePublisherName, String recipePublisherUrl, String recipeF2fSocialRank, String recipeIngredients) {
        this.recipeImageUrl = recipeImageUrl;
        this.recipeID = recipeID;
        this.recipeSourceUrl = recipeSourceUrl;
        this.recipeF2fUrl = recipeF2fUrl;
        this.recipeTitle = recipeTitle;
        this.recipePublisherName = recipePublisherName;
        this.recipePublisherUrl = recipePublisherUrl;
        this.recipeF2fSocialRank = recipeF2fSocialRank;
        this.recipeIngredients = recipeIngredients;
    }

    public Recipe(String recipeID, String recipeImageUrl, String recipeSourceUrl, String recipeF2fUrl, String recipeTitle, String recipePublisherName, String recipePublisherUrl, String recipeF2fSocialRank, int recipeOnPageNumber) {
        this.recipeID = recipeID;
        this.recipeImageUrl = recipeImageUrl;
        this.recipeSourceUrl = recipeSourceUrl;
        this.recipeF2fUrl = recipeF2fUrl;
        this.recipeTitle = recipeTitle;
        this.recipePublisherName = recipePublisherName;
        this.recipePublisherUrl = recipePublisherUrl;
        this.recipeF2fSocialRank = recipeF2fSocialRank;
        this.recipeOnPageNumber = recipeOnPageNumber;
    }

    public String getRecipeImageUrl() {
        return recipeImageUrl;
    }

    public void setRecipeImageUrl(String recipeImageUrl) {
        this.recipeImageUrl = recipeImageUrl;
    }

    public String getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(String recipeID) {
        this.recipeID = recipeID;
    }

    public String getRecipeSourceUrl() {
        return recipeSourceUrl;
    }

    public void setRecipeSourceUrl(String recipeSourceUrl) {
        this.recipeSourceUrl = recipeSourceUrl;
    }

    public String getRecipeF2fUrl() {
        return recipeF2fUrl;
    }

    public void setRecipeF2fUrl(String recipeF2fUrl) {
        this.recipeF2fUrl = recipeF2fUrl;
    }

    public String getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(String recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public int getRecipeOnPageNumber() {
        return recipeOnPageNumber;
    }

    public void setRecipeOnPageNumber(int recipeOnPageNumber) {
        this.recipeOnPageNumber = recipeOnPageNumber;
    }

    public String getRecipeF2fSocialRank() {
        return recipeF2fSocialRank;
    }

    public void setRecipeF2fSocialRank(String recipeF2fSocialRank) {
        this.recipeF2fSocialRank = recipeF2fSocialRank;
    }

    public String getRecipePublisherUrl() {
        return recipePublisherUrl;
    }

    public void setRecipePublisherUrl(String recipePublisherUrl) {
        this.recipePublisherUrl = recipePublisherUrl;
    }

    public String getRecipePublisherName() {
        return recipePublisherName;
    }

    public void setRecipePublisherName(String recipePublisherName) {
        this.recipePublisherName = recipePublisherName;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

    public void setRecipeTitle(String recipeTitle) {
        this.recipeTitle = recipeTitle;
    }
}

