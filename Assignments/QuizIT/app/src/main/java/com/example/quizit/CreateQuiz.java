package com.example.quizit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class CreateQuiz extends AppCompatActivity {

    private TextView questEntTV, defEntTV, termEntTV, warnMsg1, warnMsg2;
    private EditText defEntET, termEntET;
    private Button nxtQuesEntBtn;
    private ArrayList<String> questions;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);

        questEntTV = findViewById(R.id.quesTitleTextView);
        defEntTV = findViewById(R.id.defEnterTextView);
        termEntTV = findViewById(R.id.termEnterTextView);
        defEntET = findViewById(R.id.defEnterEditText);
        termEntET = findViewById(R.id.termEnterEditText);
        nxtQuesEntBtn = findViewById(R.id.nxtQuestEntButton);
        warnMsg1 = findViewById(R.id.warnMsgTextView);
        warnMsg2 = findViewById(R.id.warnMsgTextView2);
        questions = new ArrayList<>();
        File file = new File("Answers.txt");

        for (int i = 1; i < 11; i++) {
            this.questions.add("Question " + Integer.toString(i));
        }

        questEntTV.setText(questions.get(0));



        nxtQuesEntBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questions.size() == 1){
                    if (!termEntET.getText().toString().isEmpty()  && !defEntET.getText().toString().isEmpty()){
                        warnMsg1.setVisibility(View.INVISIBLE);
                        warnMsg2.setVisibility(View.INVISIBLE);
                        WriteToFile(defEntET, termEntET);
                        nxtQuesEntBtn.setText("Save Quiz");
                        questions.remove(0);
                        questEntTV.setText("Complete");
                        defEntET.setText("");
                        termEntET.setText("");
                        defEntET.setEnabled(false);
                        termEntET.setEnabled(false);
                    }
                    else{
                        warnMsg1.setVisibility(View.VISIBLE);
                        warnMsg2.setVisibility(View.VISIBLE);
                    }
                }
                else if (questions.size() > 1){
                    if (!termEntET.getText().toString().isEmpty()  && !defEntET.getText().toString().isEmpty()){
                        warnMsg1.setVisibility(View.INVISIBLE);
                        warnMsg2.setVisibility(View.INVISIBLE);
                        WriteToFile(defEntET, termEntET);
                        questions.remove(0);
                        questEntTV.setText(questions.get(0));
                        defEntET.setText("");
                        termEntET.setText("");
                    }
                    else{
                        warnMsg1.setVisibility(View.VISIBLE);
                        warnMsg2.setVisibility(View.VISIBLE);
                    }
                }
                else {
                    if (termEntET.getText().toString() != "" && defEntET.getText().toString() != ""){
                        WriteToFile(defEntET, termEntET);
                        Intent intent = new Intent(CreateQuiz.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else{
                        warnMsg1.setVisibility(View.VISIBLE);
                        warnMsg2.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    void WriteToFile(EditText defEntET, EditText termEntET){
        String output = "";

        try{
            FileOutputStream outputStream = openFileOutput("Answers.txt", Context.MODE_PRIVATE);
            output += defEntET.getText().toString();
            output += ",";
            output += termEntET.getText().toString();
            output += "\n";
            outputStream.write(output.getBytes());
            outputStream.flush();
            outputStream.close();
        }
        catch (IOException ioe){
            displayToast(ioe.getMessage());
        }
        catch (Exception e){
            displayToast(e.getMessage());
        }
    }

    void displayToast(String message){
        int duration = Toast.LENGTH_SHORT;
        Context contex = getApplicationContext();
        Toast toast = Toast.makeText(contex, message, duration);
        toast.show();
    }
}