package com.example.assignment4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.assignment4.ItemListActivity.mDatabaseHelper;

public class CreateMovieEntry extends AppCompatActivity {

    private EditText titleET, descET, imageET, videoET, yearET;
    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_movie_entry);

        titleET = findViewById(R.id.titleEntryEditText);
        descET  = findViewById(R.id.descEntryEditText);
        imageET  = findViewById(R.id.imageEntryEditText);
        videoET  = findViewById(R.id.videoEntryEditText);
        yearET = findViewById(R.id.yearEntryEditText);
        submitBtn  = findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ValidateData(titleET.getText().toString(), descET.getText().toString(), yearET.getText().toString(), imageET.getText().toString(), videoET.getText().toString())){
                    if (!mDatabaseHelper.insertData(titleET.getText().toString(),
                            descET.getText().toString(),
                            Integer.parseInt(yearET.getText().toString()),
                            0,
                            videoET.getText().toString(),
                            imageET.getText().toString())){
                        Log.e("Database INSERT", "ERROR INSERTING DATA");
                    }
                    Intent intent = new Intent(getApplicationContext(), ItemListActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Unable to add record to database", Toast.LENGTH_LONG).show();
                }


            }
        });

    }

    public boolean ValidateData(String title, String desc, String year, String imageURL, String videoCode){
        try {

            if (title.length() < 3) {
                return false;
            } else if (desc.length() < 3) {
                return false;
            } else if (imageURL.length() < 3) {
                return false;
            } else if (videoCode.length() < 3) {
                return false;
            } else if (Integer.parseInt(year) < 0 || Integer.parseInt(year) > 2019){
                return false;
            }
        else{
                return true;
            }
        }
        catch (Exception e){
            Log.e("Record Validation", e.getMessage());
            return false;
        }
    }
}
