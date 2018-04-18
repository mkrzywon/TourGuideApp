package com.example.android.tourguideapp;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tour_guide_text)
    TextView cityName;
    @BindView(R.id.title_main)
    TextView mainTitle;

    @BindView(R.id.stamp)
    ImageView stamp;
    @BindView(R.id.stamp_maker)
    ImageView stampMaker;

    @BindView(R.id.categoryButton)
    ImageButton categoryButton;

    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;

    /**
     * This listener gets triggered whenever the audio focus changes
     * (i.e., we gain or lose audio focus because of another app or device).
     */
    private final AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {

                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);

            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {

                mMediaPlayer.start();

            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {

                releaseMediaPlayer();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create and setup the {@link AudioManager} to request audio focus
        mAudioManager = (AudioManager) getApplicationContext().getSystemService(Context.AUDIO_SERVICE);

        ButterKnife.bind(this);

        setContent();
        setAnimations();

    }

    //This method sets components for the main view
    private void setContent() {

        // Category button
        categoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, CategoriesActivity.class);
                MainActivity.this.startActivity(intent);
                mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.unlocking_door);
                mMediaPlayer.start();

            }
        });
    }

    //This method sets animations for components int he view
    private void setAnimations() {

        // Animation for city name text
        ScaleAnimation cityNameAnimation = new ScaleAnimation(0f, 1f, 0f, 1f,
                Animation.RELATIVE_TO_SELF, 0.2f, Animation.RELATIVE_TO_SELF, 0.2f);
        cityNameAnimation.setInterpolator(new LinearInterpolator());
        cityNameAnimation.setStartOffset(1000);
        cityNameAnimation.setDuration(200);
        cityNameAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

                releaseMediaPlayer();

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.explosion);
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {

                        mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.tumble_knocking);
                        mMediaPlayer.start();
                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

                // Do nothing
            }
        });
        cityName.setAnimation(cityNameAnimation);

        // Animation for tour guide text
        ScaleAnimation mainTitleAnimation = new ScaleAnimation(0f, 1f, 0f, 1f,
                Animation.RELATIVE_TO_PARENT, 0.2f, Animation.RELATIVE_TO_PARENT, 0.2f);
        mainTitleAnimation.setInterpolator(new BounceInterpolator());
        mainTitleAnimation.setStartOffset(3000);
        mainTitleAnimation.setDuration(400);
        mainTitleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

                releaseMediaPlayer();

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                mainTitle.animate().translationY(190).setStartDelay(1800).setDuration(500).setInterpolator(new BounceInterpolator());

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

                //Do nothing
            }
        });

        mainTitle.setAnimation(mainTitleAnimation);

        // Animation for stamp maker with certified stamp sign
        TranslateAnimation certifiedAnimation = new TranslateAnimation(500, 0, 0, 0);
        certifiedAnimation.setStartOffset(3600);
        certifiedAnimation.setDuration(100);
        certifiedAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

                stamp.setVisibility(View.INVISIBLE);
                releaseMediaPlayer();

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                stamp.setVisibility(View.VISIBLE);
                stampMaker.animate().translationX(500).setDuration(100);
                mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.stamp);
                mMediaPlayer.start();
                mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {

                        mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.crash);
                        mMediaPlayer.start();

                    }
                });

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

                //Do nothing
            }
        });

        stampMaker.setAnimation(certifiedAnimation);

    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;

            // Regardless of whether or not we were granted audio focus, abandon it. This also
            // unregisters the AudioFocusChangeListener so we don't get anymore callbacks.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
