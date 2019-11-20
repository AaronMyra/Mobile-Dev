package com.example.quizit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
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
    private Button nxtQuesEntBtn, createQuizBtn;
    private ArrayList<String> questions;
    File file;

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
        createQuizBtn = findViewById(R.id.createQuizBtn);
        questions = new ArrayList<>();
        //File file;

        for (int i = 1; i < 5; i++) {
            this.questions.add("Question " + Integer.toString(i));
        }

        questEntTV.setText(questions.get(0));

        //Initial Changes
        defEntTV.setText("Enter quiz Name");
        warnMsg1.setText("Must enter quiz name");
        questEntTV.setText("Quiz Creator");
        defEntET.setHint("Quiz Name");
        termEntET.setVisibility(View.INVISIBLE);
        termEntTV.setVisibility(View.INVISIBLE);
        nxtQuesEntBtn.setVisibility(View.INVISIBLE);
        nxtQuesEntBtn.setEnabled(false);

        createQuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (defEntET.getText().toString() != "") {

                    defEntTV.setText("Definition");
                    warnMsg1.setText("Must enter definition");
                    termEntET.setVisibility(View.VISIBLE);
                    termEntTV.setVisibility(View.VISIBLE);
                    defEntET.setHint("Definition");
                    nxtQuesEntBtn.setVisibility(View.VISIBLE);
                    createQuizBtn.setVisibility(View.INVISIBLE);
                    nxtQuesEntBtn.setEnabled(true);
                    questEntTV.setText(questions.get(0));
                    defEntET.setText("");

                    file = new File(getApplicationContext().getFilesDir().getPath().toString() + "/" + defEntET.getText().toString());

                    if (!file.exists()){
                        try{
                            file.createNewFile();
                        }
                        catch (IOException e){
                            Log.e("File", "error creating file: " + e.getMessage());
                        }
                        catch (Exception e){
                            Log.e("File", "error creating file: " + e.getMessage());
                        }
                    }
                    else{
                        ClearFile(file);
                    }
                }
            }
        });

        nxtQuesEntBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (defEntET.getText().toString() != "") {

                    if (questions.size() == 1) {
                        if (!termEntET.getText().toString().isEmpty() && !defEntET.getText().toString().isEmpty()) {
                            warnMsg1.setVisibility(View.INVISIBLE);
                            warnMsg2.setVisibility(View.INVISIBLE);
                            WriteToFile(defEntET, termEntET, file);
                            nxtQuesEntBtn.setText("Save Quiz");
                            questions.remove(0);
                            questEntTV.setText("Complete");
                            defEntET.setText("");
                            termEntET.setText("");
                            defEntET.setEnabled(false);
                            termEntET.setEnabled(false);
                        } else {
                            warnMsg1.setVisibility(View.VISIBLE);
                            warnMsg2.setVisibility(View.VISIBLE);
                        }
                    } else if (questions.size() > 1) {
                        if (!termEntET.getText().toString().isEmpty() && !defEntET.getText().toString().isEmpty()) {
                            warnMsg1.setVisibility(View.INVISIBLE);
                            warnMsg2.setVisibility(View.INVISIBLE);
                            WriteToFile(defEntET, termEntET, file);
                            questions.remove(0);
                            questEntTV.setText(questions.get(0));
                            defEntET.setText("");
                            termEntET.setText("");
                        } else {
                            warnMsg1.setVisibility(View.VISIBLE);
                            warnMsg2.setVisibility(View.VISIBLE);
                        }
                    } else {
                        if (termEntET.getText().toString() != "" && defEntET.getText().toString() != "") {
                            Intent intent = new Intent(CreateQuiz.this, MainActivity.class);
                            intent.putExtra("filePath", file.getAbsolutePath());
                            startActivity(intent);
                        } else {
                            warnMsg1.setVisibility(View.VISIBLE);
                            warnMsg2.setVisibility(View.VISIBLE);
                        }
                    }
                }
                else{
                    warnMsg1.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    void ClearFile(File file){
        String output = "";
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(output.getBytes());
            fileOutputStream.close();
        }
        catch(IOException e){
            Log.e("File IO", "Unable to clear file: " + e.getMessage());
        }
        catch (Exception e){
            Log.e("File IO", "Unable to clear file: " + e.getMessage());
        }
    }

    void WriteToFile(EditText defEntET, EditText termEntET, File file){
        String output = "";
        try{
            FileOutputStream outputStream = new FileOutputStream(file, true);
            output += defEntET.getText().toString();
            output += ",";
            output += termEntET.getText().toString();
            output += "\n";
            outputStream.write(output.getBytes());
            outputStream.close();
        }
        catch (IOException ioe){
            Log.e("File output", "Error writing to file");
        }
        catch (Exception e){
            Log.e("File Output", "Error writing to file");
        }
    }

    void displayToast(String message){
        int duration = Toast.LENGTH_SHORT;
        Context contex = getApplicationContext();
        Toast toast = Toast.makeText(contex, message, duration);
        toast.show();
    }
}