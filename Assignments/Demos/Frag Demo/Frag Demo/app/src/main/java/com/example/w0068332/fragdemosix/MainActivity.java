package com.example.w0068332.fragdemosix;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    Button btnFragOne;
    Button btnFragTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnFragOne = findViewById(R.id.btnFragOne);
        btnFragOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFrag(v);
            }
        });

        btnFragTwo = findViewById(R.id.btnFragTwo);
        btnFragTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFrag(v);
            }
        });

    }//onCreate




    public void selectFrag(View view){
        Fragment fr;

        if(view == findViewById(R.id.btnFragTwo)){
            fr = new FragmentTwo();
        }else {
            fr = new FragmentOne();
        }

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frag_place,fr);
        ft.commit();

    }//end selectFrag

}//class
