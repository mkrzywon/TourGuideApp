package com.example.android.tourguideapp;

import android.content.Context;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * {@link CategoryListAdapter} is an {@link ArrayAdapter} that can provide the layout for each list item
 * based on a data source, which is a list of {@link CategoryList} objects.
 */
public class CategoryListAdapter extends ArrayAdapter<CategoryList> {

    @BindView(R.id.head_text)
    TextView headText;
    @BindView(R.id.category_description)
    TextView categoryDescription;
    @BindView(R.id.category_enter)
    TextView categoryEnter;

    @BindView(R.id.category_image)
    ImageView imageView;

    @BindView(R.id.list_button)
    ImageButton listButton;

    private MediaPlayer mMediaPlayer;

    // Setting the customButtonListener interface to pass the onClick method into category fragments
    private CustomButtonListener customListner;

    /**
     * Create a new {@link CategoryListAdapter} object.
     *
     * @param context    is the current context (i.e. Activity) that the adapter is being created in.
     * @param categories is the list of {@link CategoryList}s to be displayed.
     */
    public CategoryListAdapter(Context context, List<CategoryList> categories) {
        super(context, 0, categories);
    }

    public void setCustomButtonListener(CustomButtonListener listener) {
        this.customListner = listener;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_category, parent, false);

        }

        ButterKnife.bind(this, listItemView);

        // Get the {@link CategoryList} object located at this position in the list
        CategoryList currentCategoryList = getItem(position);

        if (currentCategoryList != null) {

            headText.setText(currentCategoryList.getHeadTextId());

            imageView.setImageResource(currentCategoryList.getCategoryImageId());

            categoryDescription.setText(currentCategoryList.getCategoryDescriptionId());

            int orientationChange = listItemView.getResources().getConfiguration().orientation;

            if (orientationChange == Configuration.ORIENTATION_LANDSCAPE) {

                categoryDescription.setLines(8);

            } else {

                categoryDescription.setLines(6);
            }

            categoryEnter.setText(currentCategoryList.getmCategoryEnter());
            final TranslateAnimation ta = new TranslateAnimation(-10, 10, 0, 0);
            ta.setInterpolator(new BounceInterpolator());
            ta.setStartOffset(3000);
            ta.setDuration(400);
            ta.setRepeatMode(TranslateAnimation.RESTART);
            ta.setRepeatCount(TranslateAnimation.INFINITE);
            categoryEnter.setAnimation(ta);

            listButton.setImageResource(currentCategoryList.getmCategoryButton());

            //Listener for the playlist button
            listButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (customListner != null) {
                        customListner.onButtonClickListener();
                    }

                    mMediaPlayer = MediaPlayer.create(getContext(), R.raw.ticket);
                    mMediaPlayer.start();
                }
            });

        }

        return listItemView;

    }

    // Custom listener for category enter button
    public interface CustomButtonListener {

        void onButtonClickListener();

    }
}

