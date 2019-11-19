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

    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        nameTV = getActivity().findViewById(R.id.nameTextView);
        desTV = getActivity().findViewById(R.id.descriptionTextView);
        gameTV = getActivity().findViewById(R.id.gameTextView);
        characterImage = getActivity().findViewById(R.id.characterImageView);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = Character.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            nameTV.setText(mItem.getName());
            gameTV.setText(mItem.getGame());
            desTV.setText(mItem.getDescription());
            characterImage.setImageDrawable(mItem.getImageDrawable());
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        return rootView;
    }
}
