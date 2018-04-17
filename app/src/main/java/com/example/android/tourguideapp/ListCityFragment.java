package com.example.android.tourguideapp;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListCityFragment extends Fragment {

    public static final String KEY_IMAGE = "image";
    public static final String KEY_NAME = "name";
    public static final String KEY_DESC = "description";
    public static final String KEY_IMG_DESC = "image_description";

    private MediaPlayer mMediaPlayer;

    public ListCityFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_listview, container, false);

        // Create a list of itemList
        final ArrayList<ItemList> itemList = new ArrayList<>();

        itemList.add(new ItemList(R.drawable.legnica_cathedral, R.string.c1, R.string.c1_desc, R.string.c1_img_desc));
        itemList.add(new ItemList(R.drawable.legnica_town_hall, R.string.c2, R.string.c2_desc, R.string.c2_img_desc));
        itemList.add(new ItemList(R.drawable.legnica_theatre, R.string.c3, R.string.c3_desc, R.string.c3_img_desc));
        itemList.add(new ItemList(R.drawable.legnica_hospital, R.string.c4, R.string.c4_desc, R.string.c4_img_desc));
        itemList.add(new ItemList(R.drawable.legnica_castle, R.string.c5, R.string.c5_desc, R.string.c5_img_desc));
        itemList.add(new ItemList(R.drawable.legnica_museum, R.string.c6, R.string.c6_desc, R.string.c6_img_desc));
        itemList.add(new ItemList(R.drawable.legnica_marketplace, R.string.c7, R.string.c7_desc, R.string.c7_img_desc));
        itemList.add(new ItemList(R.drawable.legnica_ammonia_tower, R.string.c8, R.string.c8_desc, R.string.c8_img_desc));
        itemList.add(new ItemList(R.drawable.legnica_railway_station, R.string.c9, R.string.c9_desc, R.string.c9_img_desc));
        itemList.add(new ItemList(R.drawable.legnica_stadium, R.string.c10, R.string.c10_desc, R.string.c10_img_desc));

        // Create an {@link ItemListAdapter}, whose data source is a list of {@link ItemList}. The
        // adapter knows how to create list items for each item in the list.
        ItemListAdapter adapter = new ItemListAdapter(getActivity(), itemList);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called item_list, which is declared in the
        // item_listview.xml layout file.
        ListView listView = view.findViewById(R.id.item_list);

        // Make the {@link ListView} use the {@link ItemListAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link ItemList} in the list.
        listView.setAdapter(adapter);

        // Back to category button
        ImageButton backButton = view.findViewById(R.id.back_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();

            }
        });

        //Set a click listener to pass the parcelable values to the details dialog fragment when any of the list items is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ItemList itemList1 = itemList.get(position);

                int mItemListImageId = itemList1.getmItemListImageId();
                int mItemNameId = itemList1.getmItemNameId();
                int mItemDescriptionId = itemList1.getmItemDescriptionId();
                int mItemImageDescription = itemList1.getmItemImageDescription();

                Bundle bundle = new Bundle();

                bundle.putInt(KEY_IMAGE, mItemListImageId);
                bundle.putInt(KEY_NAME, mItemNameId);
                bundle.putInt(KEY_DESC, mItemDescriptionId);
                bundle.putInt(KEY_IMG_DESC, mItemImageDescription);

                FragmentManager fm = getFragmentManager();
                DetailsFragment detailsFragment = new DetailsFragment();
                detailsFragment.setArguments(bundle);

                if (fm != null) {

                    detailsFragment.show(fm, "Details Dialog");

                }

                mMediaPlayer = MediaPlayer.create(getContext(), R.raw.bell);
                mMediaPlayer.start();

            }
        });

        return view;

    }

    //This method returns to the previous fragment
    public void onBackPressed() {

        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();

        }

        mMediaPlayer = MediaPlayer.create(getContext(), R.raw.door_closing);
        mMediaPlayer.start();

    }
}

