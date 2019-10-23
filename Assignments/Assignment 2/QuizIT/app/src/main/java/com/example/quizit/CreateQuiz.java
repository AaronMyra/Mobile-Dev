package com.example.quizit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizit.ui.main.SectionsPagerAdapter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class CreateQuiz extends AppCompatActivity {

    private TextView questEntTV, defEntTV, termEntTV, warnMsg1, warnMsg2;
    private EditText defEntET, termEntET;
    private Button nxtQuesEntBtn;
    private ArrayList<String> questions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_create_quiz);

        questEntTV = findViewById(R.id.quesTitleTextView);
        defEntTV = findViewById(R.id.defEnterTextView);
        termEntTV = findViewById(R.id.termEnterTextView);
        defEntET = findViewById(R.id.defEnterEditText);
        termEntET = findViewById(R.id.termEnterEditText);
        nxtQuesEntBtn = findViewById(R.id.nxtQuestEntButton);
        warnMsg1 = findViewById(R.id.warnMsgTextView);
        warnMsg2 = findViewById(R.id.warnMsgTextView2);
        for (int i = 1; i < 11; i++) {
            questions.add("Question " + i);
        }

        questEntTV.setText(questions.get(0));



        nxtQuesEntBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questions.size() == 1){
                    if (termEntET.getText().toString() != "" && defEntET.getText().toString() != ""){
                        warnMsg1.setVisibility(View.INVISIBLE);
                        warnMsg2.setVisibility(View.INVISIBLE);
                        WriteToFile(defEntET, termEntET);
                        nxtQuesEntBtn.setText("Save Quiz");
                    }
                    else{
                        warnMsg1.setVisibility(View.VISIBLE);
                        warnMsg2.setVisibility(View.VISIBLE);
                    }
                }
                else if (questions.size() > 1){
                    if (termEntET.getText().toString() != "" && defEntET.getText().toString() != ""){
                        warnMsg1.setVisibility(View.INVISIBLE);
                        warnMsg2.setVisibility(View.INVISIBLE);
                        WriteToFile(defEntET, termEntET);

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

            void WriteToFile(EditText defEntET, EditText termEntET){
                String output = "";
                try{
                    output += defEntET.getText().toString();
                    output += ",";
                    output += termEntET.getText().toString();
                    FileOutputStream outputStream = new FileOutputStream("Answers.txt");
                    byte[] strToBytes = output.getBytes();
                    outputStream.write(strToBytes);
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
        });



    }
}


activity_create_quiz