package com.example.assignment4v2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ItemDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";
    private Character mItem;
    private TextView nameTV, gameTV, desTV;
    private ImageView characterImage;
    private FloatingActionButton fab;
    private Animation imageAnim;

    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = Character.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.getName());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        nameTV = rootView.findViewById(R.id.nameTextView);
        desTV = rootView.findViewById(R.id.descriptionTextView);
        gameTV = rootView.findViewById(R.id.gameTextView);
        characterImage = rootView.findViewById(R.id.characterImageView);

        nameTV.setText(mItem.getName());
        gameTV.setText(mItem.getGame());
        desTV.setText(mItem.getDescription());
        characterImage.setImageDrawable(mItem.getImageDrawable());

        if (Integer.parseInt(mItem.getId()) > 4){
            imageAnim = AnimationUtils.loadAnimation(getContext(), R.anim.image_animation);
        }
        else {
            imageAnim = AnimationUtils.loadAnimation(getContext(), R.anim.image_animation_2);
        }
        characterImage.startAnimation(imageAnim);

        return rootView;
    }
}
