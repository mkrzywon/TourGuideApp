package com.example.android.tourguideapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryTripsFragment extends Fragment implements CategoryListAdapter.CustomButtonListener {

    public CategoryTripsFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_listview, container, false);

        // Create a list of categories
        final ArrayList<CategoryList> categories = new ArrayList<>();

        categories.add(new CategoryList(R.string.regional_trips, R.drawable.category_trips, R.string.category_trips_description, R.string.category_enter, R.drawable.category_button));

        // Create an {@link CategoryListAdapter}, whose data source is a list of {@link CategoryList}. The
        // adapter knows how to create list items for each item in the list.
        CategoryListAdapter adapter = new CategoryListAdapter(getActivity(), categories);

        // Linking adapter with custom onClickListener interface from CategoryListAdapter
        adapter.setCustomButtonListener(this);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // category_listview.xml layout file.
        ListView listView = view.findViewById(R.id.list);

        // Make the {@link ListView} use the {@link CategoryListAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link CategoryList} in the list.
        listView.setAdapter(adapter);

        return view;

    }

    // This method replace the current fragment with the new one
    public void changeFragment(Fragment fr) {

        getChildFragmentManager().beginTransaction().replace(R.id.main, fr).addToBackStack(null).commit();

    }

    // Custom onClickListener from CategoryListAdapter
    @Override
    public void onButtonClickListener(int position) {

        changeFragment(new ListTripsFragment());

    }
}
