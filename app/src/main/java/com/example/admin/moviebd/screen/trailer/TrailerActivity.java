package com.example.admin.moviebd.screen.trailer;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.admin.moviebd.BuildConfig;
import com.example.admin.moviebd.R;
import com.example.admin.moviebd.utils.Constant;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class TrailerActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private static final int REQUESTCODE = 911;
    private YouTubePlayerView mPlayerView;
    private String mIdYoutube;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailer);

        init();
        mIdYoutube = getIntent().getStringExtra(Constant.BaseApiUrl.ID_YOUTUBE);
        mPlayerView.initialize(BuildConfig.YOUTUBE_API_KEY, this);
    }

    private void init() {
        mPlayerView = (YouTubePlayerView) findViewById(R.id.view_youtubePlayer);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.loadVideo(mIdYoutube);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(TrailerActivity.this, REQUESTCODE);
        } else {
            Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUESTCODE) {
            mPlayerView.initialize(BuildConfig.YOUTUBE_API_KEY, TrailerActivity.this);
        }
    }
}
