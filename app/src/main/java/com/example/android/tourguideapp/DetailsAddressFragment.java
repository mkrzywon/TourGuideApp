package com.example.android.tourguideapp;

import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
public class DetailsAddressFragment extends DialogFragment {

    private static final String KEY_IMAGE = "image";
    private static final String KEY_NAME = "name";
    private static final String KEY_IMG_DESC = "image_description";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_WEBPAGE = "www";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";

    @BindView(R.id.details_head_text)
    TextView detailsName;
    @BindView(R.id.image_description)
    TextView detailsImageDescription;
    @BindView(R.id.address)
    TextView detailsAddress;
    @BindView(R.id.webpage)
    TextView detailsWebpage;
    @BindView(R.id.email)
    TextView detailsEmail;
    @BindView(R.id.phone)
    TextView detailsPhone;

    @BindView(R.id.details_image)
    ImageView detailsImage;

    @BindView(R.id.back_list_button)
    ImageButton okButton;

    private MediaPlayer mMediaPlayer;

    public DetailsAddressFragment() {
        // Required empty public constructor
    }

    //Styling for dialog fragment window
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setOrientationChange();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getDialog().setTitle(getResources().getString(R.string.details_dialog));
        getDialog().setCanceledOnTouchOutside(false);

        View rootView = inflater.inflate(R.layout.details_for_lists_addresses, container, false);

        ButterKnife.bind(this, rootView);

        //Getting the data from item lists and using these data to fill the dialog fragment
        Bundle mBundle = getArguments();

        if (mBundle != null) {
            int mDetailsImage = mBundle.getInt(KEY_IMAGE);
            int mDetailsName = mBundle.getInt(KEY_NAME);
            int mDetailsImageDescription = mBundle.getInt(KEY_IMG_DESC);
            int mDetailsAddress = mBundle.getInt(KEY_ADDRESS);
            int mDetailsWebpage = mBundle.getInt(KEY_WEBPAGE);
            int mDetailsEmail = mBundle.getInt(KEY_EMAIL);
            int mDetailsPhone = mBundle.getInt(KEY_PHONE);

            detailsName.setText(mDetailsName);
            detailsImage.setImageResource(mDetailsImage);
            detailsImageDescription.setText(mDetailsImageDescription);
            detailsAddress.setText(mDetailsAddress);
            detailsWebpage.setText(mDetailsWebpage);
            detailsEmail.setText(mDetailsEmail);
            detailsPhone.setText(mDetailsPhone);

            setContent();
            setAnimations();

        }

        return rootView;

    }

    // This method sets the content
    private void setContent() {

        //back to list button
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();

                mMediaPlayer = MediaPlayer.create(getContext(), R.raw.hint);
                mMediaPlayer.start();

            }
        });
    }

    // This method sets animations for the view components
    private void setAnimations() {

        final TranslateAnimation ta = new TranslateAnimation(-10, 10, 0, 0);
        ta.setInterpolator(new BounceInterpolator());
        ta.setStartOffset(3000);
        ta.setDuration(500);
        ta.setRepeatMode(TranslateAnimation.RESTART);
        ta.setRepeatCount(TranslateAnimation.INFINITE);
        detailsName.setAnimation(ta);

    }

    //This method sets the styles for different orientation modes
    private void setOrientationChange() {

        int orientationChange;

        orientationChange = getResources().getConfiguration().orientation;

        if (orientationChange == Configuration.ORIENTATION_LANDSCAPE) {

            setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogFragmentLandscape);

        } else {

            setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogFragment);

        }
    }
}
