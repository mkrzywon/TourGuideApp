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
public class ListFoodFragment extends Fragment {

    public static final String KEY_IMAGE = "image";
    public static final String KEY_NAME = "name";
    public static final String KEY_IMG_DESC = "image_description";
    public static final String KEY_ADDRESS = "address";
    public static final String KEY_WEBPAGE = "www";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PHONE = "phone";

    private MediaPlayer mMediaPlayer;

    public ListFoodFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_listview, container, false);

        // Create a list of itemListAddresses
        final ArrayList<ItemListAddresses> itemListAddresses = new ArrayList<>();

        itemListAddresses.add(new ItemListAddresses(R.drawable.restaurant, R.string.f1, R.string.f1_img_desc, R.string.f1_address, R.string.f1_www, R.string.f1_email, R.string.f1_phone));
        itemListAddresses.add(new ItemListAddresses(R.drawable.restaurant, R.string.f2, R.string.f2_img_desc, R.string.f2_address, R.string.f2_www, R.string.f2_email, R.string.f2_phone));
        itemListAddresses.add(new ItemListAddresses(R.drawable.restaurant, R.string.f3, R.string.f3_img_desc, R.string.f3_address, R.string.f3_www, R.string.f3_email, R.string.f3_phone));
        itemListAddresses.add(new ItemListAddresses(R.drawable.restaurant, R.string.f4, R.string.f4_img_desc, R.string.f4_address, R.string.f4_www, R.string.f4_email, R.string.f4_phone));
        itemListAddresses.add(new ItemListAddresses(R.drawable.restaurant, R.string.f5, R.string.f5_img_desc, R.string.f5_address, R.string.f5_www, R.string.f5_email, R.string.f5_phone));
        itemListAddresses.add(new ItemListAddresses(R.drawable.restaurant, R.string.f6, R.string.f6_img_desc, R.string.f6_address, R.string.f6_www, R.string.f6_email, R.string.f6_phone));
        itemListAddresses.add(new ItemListAddresses(R.drawable.restaurant, R.string.f7, R.string.f7_img_desc, R.string.f7_address, R.string.f7_www, R.string.f7_email, R.string.f7_phone));
        itemListAddresses.add(new ItemListAddresses(R.drawable.restaurant, R.string.f8, R.string.f8_img_desc, R.string.f8_address, R.string.f8_www, R.string.f8_email, R.string.f8_phone));
        itemListAddresses.add(new ItemListAddresses(R.drawable.restaurant, R.string.f9, R.string.f9_img_desc, R.string.f9_address, R.string.f9_www, R.string.f9_email, R.string.f9_phone));
        itemListAddresses.add(new ItemListAddresses(R.drawable.restaurant, R.string.f10, R.string.f10_img_desc, R.string.f10_address, R.string.f10_www, R.string.f10_email, R.string.f10_phone));

        // Create an {@link ItemListAddressAdapter}, whose data source is a list of {@link ItemListAddresses}. The
        // adapter knows how to create list items for each item in the list.
        ItemListAddressAdapter adapter = new ItemListAddressAdapter(getActivity(), itemListAddresses);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called item_list, which is declared in the
        // item_listview.xml layout file.
        ListView listView = view.findViewById(R.id.item_list);

        // Make the {@link ListView} use the {@link ItemListAddressAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link ItemListAddresses} in the list.
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

                ItemListAddresses itemList1 = itemListAddresses.get(position);

                int mItemListImageId = itemList1.getmItemListImageId();
                int mItemNameId = itemList1.getmItemNameId();
                int mItemImageDescription = itemList1.getmItemImageDescription();
                int mItemAddress = itemList1.getmItemAddress();
                int mItemWww = itemList1.getmItemWebpage();
                int mItemEmail = itemList1.getmItemEmail();
                int mItemPhone = itemList1.getmItemPhone();

                Bundle bundle = new Bundle();

                bundle.putInt(KEY_IMAGE, mItemListImageId);
                bundle.putInt(KEY_NAME, mItemNameId);
                bundle.putInt(KEY_IMG_DESC, mItemImageDescription);
                bundle.putInt(KEY_ADDRESS, mItemAddress);
                bundle.putInt(KEY_WEBPAGE, mItemWww);
                bundle.putInt(KEY_EMAIL, mItemEmail);
                bundle.putInt(KEY_PHONE, mItemPhone);

                FragmentManager fm = getFragmentManager();
                DetailsAddressFragment detailsAddressFragment = new DetailsAddressFragment();
                detailsAddressFragment.setArguments(bundle);

                if (fm != null) {

                    detailsAddressFragment.show(fm, getResources().getString(R.string.details_dialog));

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
