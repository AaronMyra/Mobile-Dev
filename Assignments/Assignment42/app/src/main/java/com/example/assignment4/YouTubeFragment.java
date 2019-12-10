package com.example.assignment4;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;

public class YouTubeFragment extends YouTubePlayerSupportFragment implements YouTubePlayer.OnInitializedListener {

    private static YouTubePlayerView youTubePlayerView;
    private static YouTubePlayer.OnInitializedListener onInitializedListener;
    public static final String DEVELOPER_KEY = "AIzaSyDyDJmLCTLRU_tLgLs8CuANvZknIJhWDgM";
    private static final String VIDEO_ID = "3ymwOvzhwHs";
    private static final int RECOVERY_DIALOG_REQUEST = 1;

    public static YouTubeFragment newInstance() {
        YouTubeFragment youTubeFragment = new YouTubeFragment();
        youTubeFragment.initialize(DEVELOPER_KEY, youTubeFragment);
        return youTubeFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_you_tube, container, false);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b) {
            youTubePlayer.cueVideo(VIDEO_ID);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }
}
