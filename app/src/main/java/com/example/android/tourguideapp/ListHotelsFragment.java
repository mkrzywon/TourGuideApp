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
public class ListHotelsFragment extends Fragment {

    private static final String KEY_IMAGE = "image";
    private static final String KEY_NAME = "name";
    private static final String KEY_IMG_DESC = "image_description";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_WEBPAGE = "www";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";

    private MediaPlayer mMediaPlayer;

    public ListHotelsFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_listview, container, false);

        // Create a list of itemListAddresses
        final ArrayList<ItemListAddresses> itemListAddresses = new ArrayList<>();

        itemListAddresses.add(new ItemListAddresses(R.drawable.legnica_qubus_hotel, R.string.a1, R.string.a1_img_desc, R.string.a1_address, R.string.a1_www, R.string.a1_email, R.string.a1_phone));
        itemListAddresses.add(new ItemListAddresses(R.drawable.legnica_palacyk_hotel, R.string.a2, R.string.a2_img_desc, R.string.a2_address, R.string.a2_www, R.string.a2_email, R.string.a2_phone));
        itemListAddresses.add(new ItemListAddresses(R.drawable.legnica_kamieniczka_hotel, R.string.a3, R.string.a3_img_desc, R.string.a3_address, R.string.a3_www, R.string.a3_email, R.string.a3_phone));
        itemListAddresses.add(new ItemListAddresses(R.drawable.legnica_sekowski_hotel, R.string.a4, R.string.a4_img_desc, R.string.a4_address, R.string.a4_www, R.string.a4_email, R.string.a4_phone));
        itemListAddresses.add(new ItemListAddresses(R.drawable.legnica_arkadia_hotel, R.string.a5, R.string.a5_img_desc, R.string.a5_address, R.string.a5_www, R.string.a5_email, R.string.a5_phone));
        itemListAddresses.add(new ItemListAddresses(R.drawable.legnica_gwarna_hotel, R.string.a6, R.string.a6_img_desc, R.string.a6_address, R.string.a6_www, R.string.a6_email, R.string.a6_phone));
        itemListAddresses.add(new ItemListAddresses(R.drawable.legnica_moj_hotel, R.string.a7, R.string.a7_img_desc, R.string.a7_address, R.string.a7_www, R.string.a7_email, R.string.a7_phone));
        itemListAddresses.add(new ItemListAddresses(R.drawable.legnica_rezydencja_hotel, R.string.a8, R.string.a8_img_desc, R.string.a8_address, R.string.a8_www, R.string.a8_email, R.string.a8_phone));
        itemListAddresses.add(new ItemListAddresses(R.drawable.legnica_youth_hostel, R.string.a9, R.string.a9_img_desc, R.string.a9_address, R.string.a9_www, R.string.a9_email, R.string.a9_phone));

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
    private void onBackPressed() {

        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();

        }

        mMediaPlayer = MediaPlayer.create(getContext(), R.raw.door_closing);
        mMediaPlayer.start();

    }
}
