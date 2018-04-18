package com.example.android.tourguideapp;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemList implements Parcelable {

    //creator - used when un-parceling our parcle (creating the object)
    public static final Creator<ItemList> CREATOR = new Creator<ItemList>() {

        @Override
        public ItemList createFromParcel(Parcel parcel) {
            return new ItemList(parcel);
        }

        @Override
        public ItemList[] newArray(int size) {
            return new ItemList[0];
        }
    };
    /**
     * Image resource ID for the item
     */
    private int mItemListImageId;
    /**
     * String resource ID for the item's name
     */
    private int mItemNameId;
    /**
     * String resource ID for the item's description
     */
    private int mItemDescriptionId;
    /**
     * String resource ID for the item's image description
     */
    private int mItemImageDescription;

    /**
     * Create a new ItemList object.
     *
     * @param itemListImageId      is the image resource ID for the item
     * @param itemNameId           is the string resource ID for the item's name
     * @param itemDescriptionId    is the string resource ID for the item's description
     * @param itemImageDescription is the string resource ID for the item's image description
     */
    public ItemList(int itemListImageId, int itemNameId, int itemDescriptionId, int itemImageDescription) {

        this.mItemListImageId = itemListImageId;
        this.mItemNameId = itemNameId;
        this.mItemDescriptionId = itemDescriptionId;
        this.mItemImageDescription = itemImageDescription;

    }

    //read and set saved values from parcel
    private ItemList(Parcel parcel) {

        mItemListImageId = parcel.readInt();
        mItemNameId = parcel.readInt();
        mItemDescriptionId = parcel.readInt();
        mItemImageDescription = parcel.readInt();

    }

    /**
     * Get the image resource ID for item's image
     */
    public int getmItemListImageId() {
        return mItemListImageId;
    }

    /**
     * Get the string resource ID for the item's name
     */
    public int getmItemNameId() {
        return mItemNameId;
    }

    /**
     * Get the string resource ID for the item's image description
     */
    public int getmItemDescriptionId() {
        return mItemDescriptionId;
    }

    /**
     * Get the string resource ID for the item's description
     */
    public int getmItemImageDescription() {
        return mItemImageDescription;
    }

    //write the values to parcel for storage
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(mItemListImageId);
        dest.writeInt(mItemNameId);
        dest.writeInt(mItemDescriptionId);
        dest.writeInt(mItemImageDescription);
    }

    //return hashcode of object
    public int describeContents() {
        return hashCode();
    }

}

