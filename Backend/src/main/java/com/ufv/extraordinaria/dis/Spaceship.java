package com.ufv.extraordinaria.dis;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Spaceship {

    @JsonProperty
    private String name;
    @JsonProperty
    private String model;
    @JsonProperty
    private String manufacturer;
    @JsonProperty
    private String length;
    @JsonProperty
    private String crew;

    public Spaceship() {
    }
    public Spaceship(String name, String model, String manufacturer, String length, String crew) {
        this.name = name;
        this.model = model;
        this.manufacturer = manufacturer;
        this.length = length;
        this.crew = crew;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getCrew() {
        return crew;
    }

    public void setCrew(String crew) {
        this.crew = crew;
    }

    @Override
    public String toString() {
        return "Spaceship{" +
                "name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", length=" + length +
                ", crew=" + crew +
                '}';
    }
}
