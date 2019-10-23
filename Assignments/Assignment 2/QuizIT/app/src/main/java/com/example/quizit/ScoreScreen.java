package com.example.quizit;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class ScoreScreen extends AppCompatActivity {

    private TextView nameTVS, scoreTVS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_screen);

        nameTVS = findViewById(R.id.nameTextViewScr);
        scoreTVS = findViewById(R.id.currScoreTextViewScr);
        Bundle bundle = getIntent().getExtras();
        nameTVS.setText(bundle.getSerializable("name").toString());
        scoreTVS.setText(bundle.getSerializable("score").toString());
    }

}
