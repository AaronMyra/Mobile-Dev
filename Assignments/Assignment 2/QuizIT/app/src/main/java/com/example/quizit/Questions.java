package com.example.quizit;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Questions extends AppCompatActivity {

    private Button btnA, btnB, btnC, btnD;
    private TextView defView;
    List<String> definitions,terms;
    int score, maxScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        btnA = (Button) findViewById(R.id.btnA);
        btnB = (Button) findViewById(R.id.btnB);
        btnC = (Button) findViewById(R.id.btnC);
        btnD = (Button) findViewById(R.id.btnD);
        defView = (TextView) findViewById(R.id.defView);

        Bundle bundle = getIntent().getExtras();
        String[][] fileContents = (String[][])(bundle.getSerializable("Array"));

        String[] row;
        for (int j = 0; j < fileContents.length; j++) {
            row = fileContents[j];
            for(int i=0; i < row.length; i++ ){
                definitions.add(row[0]);
                terms.add(row[1]);
            }
        }

        Map<String, String> hashMap = new HashMap<String, String>();
        for (int i = 0; i < definitions.size(); i++) {
            hashMap.put(definitions.get(i),terms.get(i));
        }

        Collections.shuffle(definitions);
        btnA.setText(hashMap.get(definitions.get(0)));

//        //Loop while there are values in the definitions list
//        while (definitions.size() != 0){
//            //Set the def text
//            defView.setText(definitions.get(0));
//            // Set random button as right answer
//            int randNum = new Random().nextInt(5) + 1;
//            switch (randNum){
//                case 1:
//                    btnA.setText(hashMap.get(definitions.get(0)));
//            }
//            // Set other buttons as random answers !!NO DUPLICATES!!
//
//        }

    }

}
