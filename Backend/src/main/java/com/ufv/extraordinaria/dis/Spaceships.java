package com.ufv.extraordinaria.dis;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Spaceships {
    @JsonProperty
    ArrayList<Spaceship> results;

    public Spaceships() {
    }

    public Spaceships(ArrayList<Spaceship> results) {
        this.results = results;
    }

    public ArrayList<Spaceship> getResults() {
        return results;
    }

    public void setResults(ArrayList<Spaceship> results) {
        this.results = results;
    }
}
