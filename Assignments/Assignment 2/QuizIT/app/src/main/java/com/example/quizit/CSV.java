package com.example.quizit;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class CSV {

    private InputStream inStream;
    private ArrayList<String[]> fileContents = new ArrayList<>();

    public CSV(InputStream inStream){
        this.inStream = inStream;
    }

    public ArrayList<String[]> readFile(){
        InputStreamReader inputStreamReader = new InputStreamReader(this.inStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String result;
        String[] row;
        int index1 = 0;
        int index2 = 0;
        try{
            while ((result = bufferedReader.readLine()) != null){
                row = result.split(",");
                this.fileContents.add(row);
            }
            inStream.close();
        }catch(Exception e){
            this.fileContents = null;
            return this.fileContents;
        }
        return this.fileContents;
    }
}
