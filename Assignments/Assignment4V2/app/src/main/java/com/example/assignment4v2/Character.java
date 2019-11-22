package com.example.assignment4v2;

import android.graphics.drawable.Drawable;

import com.example.assignment4v2.dummy.DummyContent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/// Summary

// Class for Character fields and methods

public class Character {

//    private boolean isVisible = true;
    private Drawable imageDrawable;
    private String id, name, game, description;
    public static final List<Character> ITEMS = new ArrayList<Character>();
    public static final Map<String, Character> ITEM_MAP = new HashMap<String, Character>();

    public Character(String id, String name, String game, String description, Drawable image){
        this.id = id;
        this.name = name;
        this.game = game;
        this.description = description;
        this.imageDrawable = image;
    }

    public Drawable getImageDrawable(){ return imageDrawable; }
    public String getName(){ return name; }
    public String getDescription(){ return description; }
    public String getId(){ return id; }
    public String getGame() { return game; }
//    public boolean getVisibility() { return isVisible; }

    public void setImageDrawable(Drawable imageDrawable){ this.imageDrawable = imageDrawable; }
    public void setName(String name){ this.name = name; }
    public void setDescription(String description){ this.description = description; }
    public void setGame(String game) { this.game = game; }
//    public void setVisibility(Boolean isVisible) { this.isVisible = isVisible; }

    public void mapCharacter(Character character){
        ITEM_MAP.put(character.getId(), character);
        ITEMS.add(character);
    }
}
