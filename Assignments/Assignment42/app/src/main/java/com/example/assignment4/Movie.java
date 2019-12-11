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
    public float rating;
    public final String  video_code;
    public final String  image_url;

    public Movie(String id, String title, String description, int year, float rating, String video_code, String image_url) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.year = year;
        this.rating = rating;
        this.video_code = video_code;
        this.image_url = image_url;
    }


    public void mapMovie(Movie item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

}
