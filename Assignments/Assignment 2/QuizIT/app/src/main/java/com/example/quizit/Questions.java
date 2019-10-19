package com.example.quizit;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Questions extends AppCompatActivity {

    private Button btnA, btnB, btnC, btnD, nextQues;
    private TextView defView, nameTVQ, scoreTV;
    private ArrayList<Button> btns = new ArrayList<Button>();
    private ArrayList<String> definitions = new ArrayList<>();
    private ArrayList<String> terms = new ArrayList<>();
    private ArrayList<String[]> fileContents;
    private int score, maxScore;
    private boolean clicked = false;
    private Map<String, String> hashMap;
    int randNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        //Buttons
        nextQues = findViewById(R.id.nextQuesBtn);
        btnA = findViewById(R.id.btnA);
        btnB = findViewById(R.id.btnB);
        btnC = findViewById(R.id.btnC);
        btnD = findViewById(R.id.btnD);
        btns.add(btnA);
        btns.add(btnB);
        btns.add(btnC);
        btns.add(btnD);

        //Text Views
        defView = (TextView) findViewById(R.id.defView);
        scoreTV = findViewById(R.id.currScoreTextView);
        nameTVQ = findViewById(R.id.nameTextViewQues);

        //Set text view - initial
        try {
            Bundle bundle = getIntent().getExtras();
            scoreTV.setText("0");
            nameTVQ.setText(bundle.getSerializable("name").toString());
        }
        catch(Exception e){
            displayToast(e.getMessage());
        }

        //Seperate Defs and terms
        try {
            Bundle bundle = getIntent().getExtras();
            this.fileContents = ((ArrayList<String[]>) bundle.getSerializable("File"));
        }
        catch (Exception e){
            displayToast(e.getMessage());
        }

        String[] row;
        for (int j = 0; j < this.fileContents.size(); j++) {
            row = this.fileContents.get(j);
                definitions.add(row[0]);
                terms.add(row[1]);
        }

        //Generate Hash Map
        this.hashMap = new HashMap<String, String>();
        for (int i = 0; i < definitions.size(); i++) {
            hashMap.put(definitions.get(i),terms.get(i));
        }

        //Shuffle Defs
        Collections.shuffle(definitions);

        //Set button click listener
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hashMap.get(defView.getText()) == btnA.getText()){
                    RightAnswer(btnA);
                }
                else{
                    WrongAnswer(btnA, btns.get(randNum));
                }
                clicked=true;
            }
        });

        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hashMap.get(defView.getText()) == btnB.getText()){
                    RightAnswer(btnB);
                }
                else{
                    WrongAnswer(btnB, btns.get(randNum));
                }
                clicked=true;
            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hashMap.get(defView.getText()) == btnC.getText()){
                    RightAnswer(btnC);
                }
                else{
                    WrongAnswer(btnC, btns.get(randNum));
                }
                clicked=true;
            }
        });

        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hashMap.get(defView.getText()) == btnD.getText()){
                    RightAnswer(btnD);
                }
                else{
                    WrongAnswer(btnD, btns.get(randNum));
                }
                clicked=true;
            }
        });

        nextQues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i <btns.size() ; i++) {
                    btns.get(i).setBackgroundResource(R.drawable.ic_android_btnblack_24dp);
                    setQandA();
                }
            }
        });

        setQandA();
    }


    void setQandA(){
        nextQues.setVisibility(View.INVISIBLE);
        //Set the def text
        this.randNum = new Random().nextInt(4);
        this.defView.setText((String) definitions.get(0));
        // Set random button as right answer
        for (int i = 0; i <this.btns.size() ; i++) {
            btns.get(i).setText("");
        }
        switch (randNum) {
            case 0:
                this.btnA.setText((String)hashMap.get(definitions.get(0)));
                break;
            case 1:
                this.btnB.setText((String)hashMap.get(definitions.get(0)));
                break;
            case 2:
                this.btnC.setText((String)hashMap.get(definitions.get(0)));
                break;
            case 3:
                this.btnD.setText((String)hashMap.get(definitions.get(0)));
                break;
            default:
                displayToast("ERROR DISPLATING ANSWER");
                break;
        }

        // Set other buttons as random answers !!NO DUPLICATES!!
        for (int i = 0; i <= 3; i++) {
            boolean valid = false;
            String term;
            if (randNum != i) {
                do {
                    int random = new Random().nextInt(terms.size());
                    term = terms.get(random);
                    if (btnA.getText() != term && btnB.getText() != term && btnC.getText() != term && btnD.getText() != term) {
                        btns.get(i).setText(term);
                        valid = true;
                    }
                } while (!valid);
            }
        }

        DisableEnableButtons(true);
    }


    void displayToast(String message){
        int duration = Toast.LENGTH_SHORT;
        Context contex = getApplicationContext();
        Toast toast = Toast.makeText(contex, message, duration);
        toast.show();
    }

    boolean CheckAnswer(Button btn, TextView defView, HashMap hashMap){
        if (defView.getText() == hashMap.get(btn.getText())){
            return true;
        }
        else {
            return false;
        }
    }

    void RightAnswer(Button btn){
        DisableEnableButtons(false);
        btn.setBackgroundResource(R.drawable.ic_android_btngreen_24dp);
        String num = scoreTV.getText().toString();
        scoreTV.setText(String.valueOf(Integer.parseInt(num) + 1));
        displayToast("CORRECT");
        try {
            Thread.sleep(250);
        }
        catch (Exception e) {
            displayToast(e.getMessage());
        }
        if (this.definitions.size() != 0){
            nextQues.setVisibility(View.VISIBLE);
        }
        else{
            nextQues.setText("View Score");
            nextQues.setVisibility(View.VISIBLE);
        }
        definitions.remove(0);

    }

    void WrongAnswer(Button rBtn, Button wBtn){
        DisableEnableButtons(false);
        Resources res = getResources();
        rBtn.setBackgroundResource(R.drawable.ic_android_btngreen_24dp);
        wBtn.setBackgroundResource(R.drawable.ic_android_btnred_24dp);
        displayToast("WRONG");
        if (this.definitions.size() != 0){
            nextQues.setVisibility(View.VISIBLE);
        }
        else{
            nextQues.setText("View Score");
            nextQues.setVisibility(View.VISIBLE);
        }
        definitions.remove(0);
    }

    void DisableEnableButtons(boolean enable){
        for (int i = 0; i < this.btns.size() ; i++) {
            btns.get(i).setClickable(enable);
        }
    }

}
