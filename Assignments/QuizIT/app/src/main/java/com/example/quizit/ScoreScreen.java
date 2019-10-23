package com.example.quizit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreScreen extends AppCompatActivity {

    private TextView nameTVS, scoreTVS;
    private Button tryAgainBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_screen);

        nameTVS = findViewById(R.id.nameTextViewScr);
        scoreTVS = findViewById(R.id.currScoreTextViewScr);
        tryAgainBtn = findViewById(R.id.tryAgainBtn);

        Bundle bundle = getIntent().getExtras();
        nameTVS.setText(bundle.getSerializable("name").toString());
        scoreTVS.setText(bundle.getSerializable("score").toString());

        tryAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ScoreScreen.this, Questions.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", nameTVS.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
