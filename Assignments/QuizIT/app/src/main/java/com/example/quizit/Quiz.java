package com.example.quizit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// Class for Quiz
public class Quiz {

    private ArrayList<String> definitions;
    private ArrayList<String> terms;
    private Map<String, String> hashMap;

    public Quiz(){
        this.hashMap = new HashMap<String, String>();
        this.definitions = new ArrayList<>();
        this.terms = new ArrayList<>();
    };

    // Getters & Setters
    public Map<String, String> getHashMap() {
        return hashMap;
    }

    public void setHashMap(Map<String, String> hashMap) {
        this.hashMap = hashMap;
    }

    public ArrayList<String> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(ArrayList<String> definitions) {
        this.definitions = definitions;
    }

    public ArrayList<String> getTerms() {
        return terms;
    }

    public void setTerms(ArrayList<String> terms) {
        this.terms = terms;
    }

}
