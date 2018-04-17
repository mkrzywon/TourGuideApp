package com.example.android.tourguideapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * {@link ItemListAdapter} is an {@link ArrayAdapter} that can provide the layout for each list item
 * based on a data source, which is a list of {@link ItemList} objects.
 */
public class ItemListAdapter extends ArrayAdapter<ItemList> {

    @BindView(R.id.title_item)
    TextView itemTitle;

    @BindView(R.id.item_image)
    ImageView itemImage;

    /**
     * Create a new {@link ItemListAdapter} object.
     *
     * @param context    is the current context (i.e. Activity) that the adapter is being created in.
     * @param categories is the list of {@link ItemList}s to be displayed.
     */
    public ItemListAdapter(Context context, List<ItemList> categories) {
        super(context, 0, categories);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        ButterKnife.bind(this, listItemView);

        // Get the {@link ItemList} object located at this position in the list
        ItemList currentItem = getItem(position);

        if (currentItem != null) {

            itemImage.setImageResource(currentItem.getmItemListImageId());

            itemTitle.setText(currentItem.getmItemNameId());

        }

        return listItemView;

    }
}