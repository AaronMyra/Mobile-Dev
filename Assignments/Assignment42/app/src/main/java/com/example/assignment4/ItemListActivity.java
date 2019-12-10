package com.example.assignment4;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//Database
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.assignment4.dummy.DummyContent;

import java.util.ArrayList;
import java.util.List;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class ItemListActivity extends AppCompatActivity {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    static DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        //Initialize Database Helper
        mDatabaseHelper = new DatabaseHelper(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        deleteTableContents();
        populateTable();
        Movie.ITEMS.clear();
        Movie.ITEM_MAP.clear();
        getMovies();

        View recyclerView = findViewById(R.id.item_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, Movie.ITEMS, mTwoPane));
    }

    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final ItemListActivity mParentActivity;
        private final List<Movie> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Movie item = (Movie) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(ItemDetailFragment.ARG_ITEM_ID, item.id);
                    ItemDetailFragment fragment = new ItemDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ItemDetailActivity.class);
                    intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, item.id);

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(ItemListActivity parent,
                                      List<Movie> items,
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
            holder.mListTextView.setText(mValues.get(position).title);

            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mListTextView;

            ViewHolder(View view) {
                super(view);
                mListTextView = (TextView) view.findViewById(R.id.listTextView);

            }
        }
    }

    public void getMovies(){
        try {
            Movie movie;
            Cursor data = mDatabaseHelper.getData();
            while (data.moveToNext()) {
                String id = data.getString(0);
                String title = data.getString(1);
                String description = data.getString(2);
                String year = data.getString(3);
                String rating = data.getString(4);
                movie = new Movie(id, title, description, Integer.parseInt(year), Integer.parseInt(rating));
                movie.mapMovie(movie);
            }
        }catch (Exception e){
            Log.e("Cursor", e.getMessage());
        }

        for (int i = 0; i < Movie.ITEMS.size(); i++) {
            System.out.println(Movie.ITEMS.get(i).id);
            System.out.println(Movie.ITEMS.get(i).description);
            System.out.println(Movie.ITEMS.get(i).title);
            System.out.println(Movie.ITEMS.get(i).description);
            System.out.println(Movie.ITEMS.get(i).year);
            System.out.println(Movie.ITEMS.get(i).rating);
        }
    }

    public void populateTable(){
        String titles[] = {"Back to the Future", "The Empire Strikes Back", "Raiders of the Lost Ark"};
        String descriptions[] = {"Lorem Ipsm", "Lorem Ipsm", "Lorem Ipsm"};
        Integer year[] = {1985, 1980, 1981};
        Integer rating[] = {5, 5, 5};
        for (int i = 0; i < 3; i++) {
            if (!mDatabaseHelper.insertData(titles[i],  descriptions[i], year[i],  rating[i])){
                Log.e("Database INSERT", "ERROR INSERTING DATA");
            }
        }
    }

    public void deleteTableContents(){
        Cursor data = mDatabaseHelper.getData();
        while (data.moveToNext()) {
            mDatabaseHelper.deleteData(data.getString(0));
        }
    }
}
