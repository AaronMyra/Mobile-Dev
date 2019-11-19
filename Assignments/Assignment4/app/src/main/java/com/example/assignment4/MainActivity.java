package com.example.assignment4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import android.support.v4.app.*;

public class MainActivity extends AppCompatActivity {

    List<Character> characterList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fr = new MyListFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.contentFragment,fr);
        ft.commit();
    }

    private void getPostsData(){
        Character post;
        for (int i = 0; i < 10; i++) {
            post = new Character(getResources().getStringArray(R.array.character_name)[i], "Lorem Ipsm", getDrawable(R.drawable.link));
            characterList.add(post);
        }
    }
}
