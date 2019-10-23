package com.example.quizit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class Questions extends AppCompatActivity {

    private Button btnA, btnB, btnC, btnD, nextQues;
    private TextView defView, nameTVQ, scoreTV;
    private ArrayList<Button> btns = new ArrayList<>();
    private int randNum;
    private Quiz quiz = new Quiz();
    private int score = 0;

    public int getScore(){
        return score;
    }

    public void incrementScore(int score) {
        this.score += score;
    }

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

        InputStream inStream = null;
        try {
            inStream = getAssets().open("Test.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String result;
            String[] row;
            try{
                while ((result = bufferedReader.readLine()) != null){
                    row = result.split(",");
                    quiz.getDefinitions().add(row[0]);
                    quiz.getTerms().add(row[1]);
                }
                inStream.close();
            }
            catch(Exception e){
                displayToast("Unable to read file");
            }
        }
        catch (IOException e) {
            displayToast("Unable to load file");
        }


        //Generate Hash Map
        for (int i = 0; i < quiz.getDefinitions().size(); i++) {
            quiz.getHashMap().put(quiz.getDefinitions().get(i), quiz.getTerms().get(i));
        }

        //Shuffle Defs
        Collections.shuffle(quiz.getDefinitions());

        //Set button click listener
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quiz.getHashMap().get(defView.getText()) == btnA.getText()){
                    RightAnswer(btnA, quiz);
                }
                else{
                    WrongAnswer(btnA, btns.get(randNum), quiz);
                }
            }
        });

        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quiz.getHashMap().get(defView.getText()) == btnB.getText()){
                    RightAnswer(btnB, quiz);
                }
                else{
                    WrongAnswer(btnB, btns.get(randNum), quiz);
                }
            }
        });

        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quiz.getHashMap().get(defView.getText()) == btnC.getText()){
                    RightAnswer(btnC, quiz);
                }
                else{
                    WrongAnswer(btnC, btns.get(randNum), quiz);
                }
            }
        });

        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quiz.getHashMap().get(defView.getText()) == btnD.getText()){
                    RightAnswer(btnD, quiz);

                }
                else{
                    WrongAnswer(btnD, btns.get(randNum), quiz);
                }
            }
        });

        nextQues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nextQues.getText().toString() != "View Score"){
                    for (int i = 0; i <btns.size() ; i++) {
                        btns.get(i).setBackgroundResource(R.drawable.ic_android_btnblack_24dp);
                        setQandA();
                    }
                }
                else{
                    ViewScoreActivity(getScore());
                }
            }
        });

        setQandA();
    }


    void setQandA(){
        nextQues.setVisibility(View.INVISIBLE);
        //Set the def text
        this.randNum = new Random().nextInt(4);
        this.defView.setText((String) quiz.getDefinitions().get(0));
        // Set random button as right answer
        for (int i = 0; i <this.btns.size() ; i++) {
            btns.get(i).setText("");
        }
        switch (randNum) {
            case 0:
                this.btnA.setText((String)quiz.getHashMap().get(quiz.getDefinitions().get(0)));
                break;
            case 1:
                this.btnB.setText((String)quiz.getHashMap().get(quiz.getDefinitions().get(0)));
                break;
            case 2:
                this.btnC.setText((String)quiz.getHashMap().get(quiz.getDefinitions().get(0)));
                break;
            case 3:
                this.btnD.setText((String)quiz.getHashMap().get(quiz.getDefinitions().get(0)));
                break;
            default:
                displayToast("ERROR DISPLAYING ANSWER");
                break;
        }

        // Set other buttons as random answers !!NO DUPLICATES!!
        for (int i = 0; i <= 3; i++) {
            boolean valid = false;
            String term;
            if (randNum != i) {
                do {
                    int random = new Random().nextInt(quiz.getTerms().size());
                    term = quiz.getTerms().get(random);
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

    void RightAnswer(Button btn, Quiz quiz){
        DisableEnableButtons(false);
        btn.setBackgroundResource(R.drawable.ic_android_btngreen_24dp);
        String num = scoreTV.getText().toString();
        scoreTV.setText(String.valueOf(Integer.parseInt(num) + 1));
        incrementScore(1);
        //displayToast("CORRECT");
        quiz.getDefinitions().remove(0);
        if (quiz.getDefinitions().size() != 0){
            nextQues.setVisibility(View.VISIBLE);
        }
        else{
            nextQues.setText("View Score");
            nextQues.setVisibility(View.VISIBLE);
        }
    }

    void WrongAnswer(Button rBtn, Button wBtn, Quiz quiz){
        DisableEnableButtons(false);
        Resources res = getResources();
        rBtn.setBackgroundResource(R.drawable.ic_android_btnred_24dp);
        wBtn.setBackgroundResource(R.drawable.ic_android_btngreen_24dp);
        String num = scoreTV.getText().toString();
        scoreTV.setText(String.valueOf(Integer.parseInt(num) + 1));
        //displayToast("WRONG");
        quiz.getDefinitions().remove(0);
        if (quiz.getDefinitions().size() != 0){
            nextQues.setVisibility(View.VISIBLE);
        }
        else{
            defView.setText("Quiz Complete");
            nextQues.setText("View Score");
            nextQues.setVisibility(View.VISIBLE);
        }
    }

    void DisableEnableButtons(boolean enable){
        for (int i = 0; i < this.btns.size() ; i++) {
            btns.get(i).setClickable(enable);
        }
    }

    void ViewScoreActivity(int score){
        Intent newIntent = new Intent(Questions.this, ScoreScreen.class);
        Bundle bundle = new Bundle();
        bundle.putString("name", nameTVQ.getText().toString());
        bundle.putString("score", Integer.toString(score));
        newIntent.putExtras(bundle);
        startActivity(newIntent);

    }

}

