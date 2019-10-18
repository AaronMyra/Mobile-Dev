package com.example.quizit;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class CSV {

    private InputStream inStream;
    private String[][] fileContents;

    public CSV(InputStream inStream){
        this.inStream = inStream;
    }

    public String[][] readFile(){
        InputStreamReader inputStreamReader = new InputStreamReader(this.inStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String result;
        String[] row;
        int index1 = 0;
        int index2 = 0;
        try{
            while ((result = bufferedReader.readLine()) != null){
                row = result.split("'");
                this.fileContents[index1][index2] = row[index1];
                index2++;
                this.fileContents[index1][index2] = row[index2];
                index1++;
                index2--;

            }
        inStream.close();
        }catch(Exception e){
            this.fileContents = null;
            return this.fileContents;
        }
        return this.fileContents;
    }
}
