package com.example.assignment4v2;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemListActivity extends AppCompatActivity {

    private boolean mTwoPane;
    private ArrayList<Drawable> images = new ArrayList<>();
    private static int[] visibleChars = {1,1,1,1,1,1,1,1,1,1,1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if (findViewById(R.id.item_detail_container) != null) {
            mTwoPane = true;
        }

        getItems();

        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, Character.ITEMS, mTwoPane));
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final ItemListActivity mParentActivity;
        private final List<Character> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Character item = (Character) view.getTag();
                visibleChars[Integer.parseInt(item.getId())] = 0;
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(ItemDetailFragment.ARG_ITEM_ID, item.getId());
                    ItemDetailFragment fragment = new ItemDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ItemDetailActivity.class);
                    intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, item.getId());

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(ItemListActivity parent,
                                      List<Character> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {

            Drawable drawable;
            holder.nameTextView.setText(mValues.get(position).getName());
            holder.charImageView.setImageDrawable(mValues.get(position).getImageDrawable());
            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView nameTextView;
            final ImageView charImageView;

            ViewHolder(View view) {
                super(view);
                nameTextView = (TextView) view.findViewById(R.id.nameTextView);
                charImageView = (ImageView) view.findViewById(R.id.imageIV3);
            }
        }
    }

    public void getItems(){
        Character character;
        images.add(getDrawable(R.drawable.yoshi));
        images.add(getDrawable(R.drawable.link));
        images.add(getDrawable(R.drawable.agent_47));
        images.add(getDrawable(R.drawable.luigi));
        images.add(getDrawable(R.drawable.doomguy));
        images.add(getDrawable(R.drawable.handsome_jack));
        images.add(getDrawable(R.drawable.geralt_of_rivia));
        images.add(getDrawable(R.drawable.bigdaddy));
        images.add(getDrawable(R.drawable.commander_shepard));
        images.add(getDrawable(R.drawable.ganondorf));

        for (int i = 0; i < images.size(); i++) {
            if (visibleChars[i] != 0) {
                character = new Character(
                        Integer.toString(i),
                        getResources().getStringArray(R.array.character_names)[i],
                        getResources().getStringArray(R.array.character_games)[i],
                        getResources().getStringArray(R.array.character_descriptions)[i],
                        images.get(i));
                character.mapCharacter(character);
            }
        }
    }
}
