package com.example.assignment4;

import android.graphics.drawable.Drawable;
import android.media.Image;

public class Character {

    private Drawable imageDrawable;
    private String name;
    private String description;

    public Character(String name, String description, Drawable image){
        this.name = name;
        this.description = description;
        this.imageDrawable = image;
    }

    public Drawable getImageDrawable(){ return imageDrawable; }
    public String getName(){ return name; }
    public String getDescription(){ return description; }

    public void setImageDrawable(Drawable imageDrawable){ this.imageDrawable = imageDrawable; }
    public void setName(String name){ this.name = name; }
    public void setDescription(String description){ this.description = description; }
}
