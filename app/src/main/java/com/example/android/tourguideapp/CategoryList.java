package com.example.android.tourguideapp;

public class CategoryList {

    /**
     * String resource ID for the head text of each category
     */
    private int mHeadTextId;

    /**
     * Image resource ID for the picture of each category
     */
    private int mCategoryImageId;

    /**
     * String resource ID for the description of each category
     */
    private int mCategoryDescriptionId;

    /**
     * String resource ID for the description of playlist button
     */
    private int mCategoryEnter;

    /**
     * Image resource ID for the playlist ImageButton
     */
    private int mCategoryButton;

    /**
     * Create a new CategoryList object.
     *
     * @param headTextId            is the string resource Id for head text
     * @param categoryImageId       is the image resource Id for image
     * @param categoryDescriptionId is the string resource Id for description
     * @param categoryEnter         is the string resource Id for description of playlist button
     * @param categoryButton        is the image resource Id for playlist button
     */
    public CategoryList(int headTextId, int categoryImageId, int categoryDescriptionId, int categoryEnter, int categoryButton) {

        mHeadTextId = headTextId;
        mCategoryImageId = categoryImageId;
        mCategoryDescriptionId = categoryDescriptionId;
        mCategoryEnter = categoryEnter;
        mCategoryButton = categoryButton;

    }

    /**
     * Get the string resource ID for the head text of the categories.
     */
    public int getHeadTextId() {
        return mHeadTextId;
    }

    /**
     * Get the image resource ID for the image of the categories.
     */
    public int getCategoryImageId() {
        return mCategoryImageId;
    }

    /**
     * Get the string resource ID for the description of the categories.
     */
    public int getCategoryDescriptionId() {
        return mCategoryDescriptionId;
    }

    /**
     * Get the string resource ID for the description of playlist button.
     */
    public int getmCategoryEnter() {
        return mCategoryEnter;
    }

    /**
     * Get the image resource ID for playlist ImageButton.
     */
    public int getmCategoryButton() {
        return mCategoryButton;
    }

}
