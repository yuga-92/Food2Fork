package com.example.Food2Fork;

/**
 * Just recipe objects
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

    public String getRecipeID() {
        return recipeID;
    }

    public String getRecipeTitle() {
        return recipeTitle;
    }

}

