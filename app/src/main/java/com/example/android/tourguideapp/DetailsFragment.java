package com.example.android.tourguideapp;

import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends DialogFragment {

    private static final String KEY_IMAGE = "image";
    private static final String KEY_NAME = "name";
    private static final String KEY_DESC = "description";
    private static final String KEY_IMG_DESC = "image_description";

    @BindView(R.id.details_head_text)
    TextView detailsName;
    @BindView(R.id.details_description)
    TextView detailsDescription;
    @BindView(R.id.image_description)
    TextView detailsImageDescription;

    @BindView(R.id.details_image)
    ImageView detailsImage;

    @BindView(R.id.back_list_button)
    ImageButton okButton;

    private int lineCounter;
    private int orientationChange;

    private MediaPlayer mMediaPlayer;

    public DetailsFragment() {
        // Required empty public constructor
    }

    //Styling for dialog fragment window
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setOrientationChange();

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getDialog().setTitle(getResources().getString(R.string.details_dialog));
        getDialog().setCanceledOnTouchOutside(false);

        View rootView = inflater.inflate(R.layout.details_for_lists, container, false);

        ButterKnife.bind(this, rootView);

        // Getting the data passed from lists
        Bundle mBundle = getArguments();

        if (mBundle != null) {

            int mDetailsImage = mBundle.getInt(KEY_IMAGE);
            int mDetailsName = mBundle.getInt(KEY_NAME);
            int mDetailsDescription = mBundle.getInt(KEY_DESC);
            int mDetailsImageDescription = mBundle.getInt(KEY_IMG_DESC);

            detailsName.setText(mDetailsName);
            detailsDescription.setText(mDetailsDescription);
            detailsImage.setImageResource(mDetailsImage);
            detailsImageDescription.setText(mDetailsImageDescription);

            setContent();
            setAnimations();

        }

        return rootView;

    }

    // This method sets the basic content for dialog fragment's window
    private void setContent() {

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();

                mMediaPlayer = MediaPlayer.create(getContext(), R.raw.hint);
                mMediaPlayer.start();

            }
        });

        detailsDescription.post(new Runnable() {
            @Override
            public void run() {

                orientationChange = getResources().getConfiguration().orientation;

                if (orientationChange == Configuration.ORIENTATION_LANDSCAPE) {

                    setLinesDescriptionLandscape();

                } else {

                    setLinesDescriptionPortrait();

                }
            }
        });
    }

    private void setAnimations() {

        final TranslateAnimation ta = new TranslateAnimation(-10, 10, 0, 0);
        ta.setInterpolator(new BounceInterpolator());
        ta.setStartOffset(3000);
        ta.setDuration(400);
        ta.setRepeatMode(TranslateAnimation.RESTART);
        ta.setRepeatCount(TranslateAnimation.INFINITE);
        detailsName.setAnimation(ta);

    }

    //This method sets the styles for different orientation modes
    private void setOrientationChange() {

        orientationChange = getResources().getConfiguration().orientation;

        if (orientationChange == Configuration.ORIENTATION_LANDSCAPE) {

            setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogFragmentLandscape);

        } else {

            setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogFragment);

        }
    }

    //This method sets the proper lines value for portrait orientation
    private void setLinesDescriptionPortrait() {

        lineCounter = detailsDescription.getLineCount();

        if (lineCounter == 9) {

            detailsDescription.setLines(9);

        } else if (lineCounter == 8) {

            detailsDescription.setLines(8);

        } else if (lineCounter == 7) {

            detailsDescription.setLines(7);

        } else if (lineCounter == 6) {

            detailsDescription.setLines(6);

        } else if (lineCounter == 5) {

            detailsDescription.setLines(5);

        } else {

            detailsDescription.setLines(4);

        }
    }

    // This method sets the proper lines value for landscape orientation
    private void setLinesDescriptionLandscape() {

        lineCounter = detailsDescription.getLineCount();

        if (lineCounter >= 9) {

            detailsDescription.setLines(10);

        } else if (lineCounter <= 6) {

            detailsDescription.setLines(7);

        }
    }
}
