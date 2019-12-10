package com.example.assignment4;

import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

public class YoutubeHelper extends YouTubeBaseActivity
    implements YouTubePlayer.OnInitializedListener{

    public static final String DEVELOPER_KEY = "AIzaSyDyDJmLCTLRU_tLgLs8CuANvZknIJhWDgM";
    private static final String VIDEO_ID = "3ymwOvzhwHs";
    private static final int RECOVERY_DIALOG_REQUEST = 1;


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (b){
            youTubePlayer.cueVideo(VIDEO_ID);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        }
        else{
            String errorMessage = String.format("An error occurred initializing the YouTube Player",
                    youTubeInitializationResult.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }
}
