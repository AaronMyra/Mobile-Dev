package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";
    private Character mItem;
    private TextView nameTV, gameTV, desTV;
    private ImageView characterImage;

    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Activity activity = this.getActivity();
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = Character.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
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

        nameTV = rootView.findViewById(R.id.nameDetailTextView);
        desTV = rootView.findViewById(R.id.descriptionTextView);
        gameTV = rootView.findViewById(R.id.gameTextView);
        characterImage = rootView.findViewById(R.id.characterImageView);

        if (mItem != null) {
            nameTV.setText(mItem.getName());
            gameTV.setText(mItem.getGame());
            desTV.setText(mItem.getDescription());
            characterImage.setImageDrawable(mItem.getImageDrawable());
        }

        return rootView;
    }
}
