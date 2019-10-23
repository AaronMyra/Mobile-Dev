package com.example.quizit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button startQuizBtn, createQuizBtn;
    private TextView nameTV, errTV;
    private EditText nameET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        errTV = findViewById(R.id.errTextView);
        startQuizBtn = findViewById(R.id.loadFileBtn);
        nameET = findViewById(R.id.nameEditText);
        nameTV = findViewById(R.id.NameTextView);
        createQuizBtn = findViewById(R.id.createQuizBtn);

        startQuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (nameET.getText().length() != 0) {
                    Intent intent = new Intent(MainActivity.this, Questions.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("name", nameET.getText().toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else{
                    errTV.setVisibility(View.VISIBLE);
                }
            }
        });

        createQuizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateQuiz.class);
                startActivity(intent);
            }
        });
    }

    void displayToast(String message){
        int duration = Toast.LENGTH_LONG;
        Context contex = getApplicationContext();
        Toast toast = Toast.makeText(contex, message, duration);
        toast.show();
    }
}
