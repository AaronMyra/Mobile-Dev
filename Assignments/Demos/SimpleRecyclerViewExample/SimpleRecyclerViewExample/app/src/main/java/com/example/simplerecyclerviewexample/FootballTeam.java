package com.example.simplerecyclerviewexample;


public class FootballTeam {

    private String name;
    private String league;
    private int yearEstablished;

    public FootballTeam() {

    }

    public FootballTeam(String name, String league, int yearEstablished) {
        this.name = name;
        this.league = league;
        this.yearEstablished = yearEstablished;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public int getYearEstablished() {
        return yearEstablished;
    }

    public void setYearEstablished(int yearEstablished) {
        this.yearEstablished = yearEstablished;
    }
}
