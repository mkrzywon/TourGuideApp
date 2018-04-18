package com.example.android.tourguideapp;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * {@link CategoryAdapter} is a {@link FragmentPagerAdapter} that can provide the layout for
 * each list item based on a data source which is a list of {@link } objects.
 */
public class CategoryAdapter extends FragmentPagerAdapter {

    /**
     * Context of the app
     */
    private final Context mContext;

    /**
     * Create a new {@link CategoryAdapter} object.
     *
     * @param context is the context of the app
     * @param fm      is the fragment manager that will keep each fragment's state in the adapter
     *                across swipes.
     */
    public CategoryAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    /**
     * Return the {@link Fragment} that should be displayed for the given page number.
     */
    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return new CategoryHistoryFragment();
        } else if (position == 1) {
            return new CategoryCityFragment();
        } else if (position == 2) {
            return new CategoryHotelsFragment();
        } else if (position == 3) {
            return new CategoryFoodFragment();
        } else if (position == 4) {
            return new CategoryNatureFragment();
        } else {
            return new CategoryTripsFragment();
        }
    }

    /**
     * Return the total number of pages.
     */
    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.category_history);
        } else if (position == 1) {
            return mContext.getString(R.string.category_city);
        } else if (position == 2) {
            return mContext.getString(R.string.category_accommodation);
        } else if (position == 3) {
            return mContext.getString(R.string.category_food);
        } else if (position == 4) {
            return mContext.getString(R.string.category_nature);
        } else {
            return mContext.getString(R.string.category_tours);
        }
    }
}
