package com.example.assignment4;

import android.database.Cursor;

import com.example.assignment4.dummy.DummyContent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Movie {

    static DatabaseHelper databaseHelper;
    public static final List<Movie> ITEMS = new ArrayList<Movie>();
    public static final Map<String,Movie> ITEM_MAP = new HashMap<String, Movie>();
    final String id;
    public final String title;
    public final String description;
    public final int year;
    public final int rating;

    public Movie(String id, String title, String description, int year, int rating) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.year = year;
        this.rating = rating;
    }


    public void mapMovie(Movie item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

}
