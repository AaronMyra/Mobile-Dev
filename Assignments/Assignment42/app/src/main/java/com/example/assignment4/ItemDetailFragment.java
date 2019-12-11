package com.example.assignment4;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import android.util.Config;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.assignment4.dummy.DummyContent;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.squareup.picasso.Picasso;

import java.security.Provider;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */


public class ItemDetailFragment extends Fragment{

    public static final String ARG_ITEM_ID = "item_id";
    private TextView ratingTV, descTV;
    private Button deleteBtn;
    private ImageButton playBtn;
    private ImageView moviePosterIV;
    RatingBar ratingBar;
    private Movie mItem;

    public ItemDetailFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = Movie.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            //Adds character name to upper app bar
            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.title);
            }
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        moviePosterIV = rootView.findViewById(R.id.movieImageView);
        ratingBar = rootView.findViewById(R.id.ratingBar);
        descTV = rootView.findViewById(R.id.descTextView);
        deleteBtn = rootView.findViewById(R.id.deleteBtn);
        playBtn = rootView.findViewById(R.id.playBtn);

//        //Image
//        String imageName = mItem.title.toLowerCase();
//        imageName = imageName.replaceAll(" ", "_").toLowerCase();
//        moviePosterIV.setImageDrawable(GetImage(rootView.getContext(), imageName));

        Picasso.with(rootView.getContext()).load(mItem.image_url).placeholder(R.drawable.the_empire_strikes_back).into(moviePosterIV);

        //Description
        descTV.setText(mItem.description);

        //Update Rating Bar
        ratingBar.setRating(mItem.rating);

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), MovieTrailer_Activity.class);
                Bundle bundle = new Bundle();
                bundle.putString("video_code", mItem.video_code);
                //Starts Video intent
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View view = v;
                new AlertDialog.Builder(v.getContext())
                        .setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this entry?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                ItemListActivity.mDatabaseHelper.deleteData(mItem.id);

                                Context context = view.getContext();
                                Intent intent = new Intent(context, ItemListActivity.class);
                                intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, mItem.id);
                                context.startActivity(intent);
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ItemListActivity.mDatabaseHelper.updateRecord(mItem.id, ratingBar.getRating());
                mItem.rating = ratingBar.getRating();
            }
        });

        return rootView;
    }

    public static Drawable GetImage(Context c, String ImageName) {
        return c.getResources().getDrawable(c.getResources().getIdentifier(ImageName, "drawable", c.getPackageName()));
    }

}
