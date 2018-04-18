package com.example.android.tourguideapp;

import android.os.Parcel;
import android.os.Parcelable;

public class ItemListAddresses implements Parcelable {

    //creator - used when un-parceling our parcle (creating the object)
    public static final Creator<ItemListAddresses> CREATOR = new Creator<ItemListAddresses>() {

        @Override
        public ItemListAddresses createFromParcel(Parcel parcel) {
            return new ItemListAddresses(parcel);
        }

        @Override
        public ItemListAddresses[] newArray(int size) {
            return new ItemListAddresses[0];
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
     * String resource ID for the item's image decription
     */
    private int mItemImageDescription;
    /**
     * String resource ID for the item's address
     */
    private int mItemAddress;
    /**
     * String resource ID for the item's webpage
     */
    private int mItemWebpage;
    /**
     * String resource ID for the item's email address
     */
    private int mItemEmail;
    /**
     * String resource ID for the item's phone number
     */
    private int mItemPhone;

    /**
     * Create a new ItemListAddress object.
     *
     * @param itemListImageId      is the image resource ID for the item
     * @param itemNameId           is the string resource ID for the item's name
     * @param itemImageDescription is the string resource ID for the item's image description
     * @param itemAddress          is the string resource ID for the item's address
     * @param itemWebpage          is the string resource ID for the item's webpage
     * @param itemEmail            is the string resource ID for the item's email
     * @param itemPhone            is the string resource ID for the item's phone
     */
    public ItemListAddresses(int itemListImageId, int itemNameId, int itemImageDescription, int itemAddress, int itemWebpage, int itemEmail, int itemPhone) {

        this.mItemListImageId = itemListImageId;
        this.mItemNameId = itemNameId;
        this.mItemImageDescription = itemImageDescription;
        this.mItemAddress = itemAddress;
        this.mItemWebpage = itemWebpage;
        this.mItemEmail = itemEmail;
        this.mItemPhone = itemPhone;

    }

    //read and set saved values from parcel
    private ItemListAddresses(Parcel parcel) {

        mItemListImageId = parcel.readInt();
        mItemNameId = parcel.readInt();
        mItemImageDescription = parcel.readInt();
        mItemAddress = parcel.readInt();
        mItemWebpage = parcel.readInt();
        mItemEmail = parcel.readInt();
        mItemPhone = parcel.readInt();

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
     * Get the string resource ID for the item's description
     */
    public int getmItemImageDescription() {
        return mItemImageDescription;
    }

    /**
     * Get the string resource ID for the item's address
     */
    public int getmItemAddress() {
        return mItemAddress;
    }

    /**
     * Get the string resource ID for the item's address
     */
    public int getmItemWebpage() {
        return mItemWebpage;
    }

    /**
     * Get the string resource ID for the item's address
     */
    public int getmItemEmail() {
        return mItemEmail;
    }

    /**
     * Get the string resource ID for the item's address
     */
    public int getmItemPhone() {
        return mItemPhone;
    }

    //write the values to parcel for storage
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(mItemListImageId);
        dest.writeInt(mItemNameId);
        dest.writeInt(mItemImageDescription);
        dest.writeInt(mItemAddress);
        dest.writeInt(mItemWebpage);
        dest.writeInt(mItemEmail);
        dest.writeInt(mItemPhone);
    }

    //return hashcode of object
    public int describeContents() {
        return hashCode();
    }

}
