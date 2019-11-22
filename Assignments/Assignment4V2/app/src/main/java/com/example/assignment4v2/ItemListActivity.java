package com.example.assignment4v2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemListActivity extends AppCompatActivity {

    private boolean mTwoPane;
    private ArrayList<Drawable> images = new ArrayList<>();
    private static int[] visibleChars = {1,1,1,1,1,1,1,1,1,1,1};
    public static int num = 0;
    private static SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        //Get shared Prefs
        visibleChars = getPrefCharacters("visibleChars", this);

        //Reset the recycler view and characters
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < visibleChars.length ; i++) {
                    visibleChars[i] = 1;
                }
                Toast.makeText(getApplicationContext(), "Characters Reset", Toast.LENGTH_LONG).show();
                savePrefCharacters(visibleChars, "visibleChars", getApplicationContext());
                startActivity(getIntent());
            }
        });

        if (findViewById(R.id.item_detail_container) != null) {
            mTwoPane = true;
        }

        System.out.println("HERE");
        for (int i = 0; i < visibleChars.length; i++) {
            System.out.println(visibleChars[i]);
        }
        System.out.println("END");

        // Removes item from list
        images.clear();
        Character.ITEMS.clear();
        Character.ITEM_MAP.clear();

        //Get characters to populates the recycler view
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

                //Sets visibility flag
                visibleChars[Integer.parseInt(item.getId())] = 0;
                savePrefCharacters(visibleChars, "visibleChars", view.getContext());
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
            ViewHolder viewHolder = new ViewHolder(view);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {

            //Set character specific field in the recycler view list item
            holder.nameTextView.setText(mValues.get(position).getName());
            holder.charImageView.setImageDrawable(mValues.get(position).getImageDrawable());
            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setId(Integer.parseInt(mValues.get(position).getId()));
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

        // Adds drawable assets to list
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

    // Save the character visibility array to shared prefs
    public static boolean savePrefCharacters(int[] array, String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("visibleChars", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(arrayName + "_sizeOf", array.length);
        for(int i=0;i<array.length;i++)
            editor.putInt(arrayName + "_" + i, array[i]);
        return editor.commit();
    }

    // Gets the character visibility array to shared prefs
    public static int[] getPrefCharacters(String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("visibleChars", 0);
        int size = prefs.getInt(arrayName + "_sizeOf", 10);
        int array[] = new int[size];
        for(int i=0;i<size;i++)
            array[i] = prefs.getInt(arrayName + "_" + i, 1);
        return array;
    }
}
