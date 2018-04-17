package com.example.android.tourguideapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class CategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);

        // Find the view pager that will allow the user to swipe between fragments
        ViewPager viewPager = findViewById(R.id.viewpager);

        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());

        // Create an adapter that knows which fragment should be shown on each page
        CategoryAdapter adapter = new CategoryAdapter(this, getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // Find the tab layout that shows the tabs
        TabLayout tabLayout = findViewById(R.id.tabs);

        // Make the tabs scrollable with center gravity
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);

        // Connect the tab layout with the view pager. This will
        //   1. Update the tab layout when the view pager is swiped
        //   2. Update the view pager when a tab is selected
        //   3. Set the tab layout's tab names with the view pager's adapter's titles
        //      by calling onPageTitle()
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons(tabLayout);
    }

    // This method defines icons for tabs
    private void setupTabIcons(TabLayout tabs) {

        int[] tabIcons = {R.drawable.history, R.drawable.city, R.drawable.accommodation, R.drawable.food, R.drawable.nature, R.drawable.trips};
        TabLayout.Tab tab;

        for (int i = 0; i < 6; i++) {
            tab = tabs.getTabAt(i);
            if (tab != null) {
                tab.setIcon(tabIcons[i]);

            }
        }
    }
}
