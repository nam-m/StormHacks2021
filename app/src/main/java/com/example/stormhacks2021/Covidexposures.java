package com.example.stormhacks2021;

import java.time.LocalDate;

public class Covidexposures {

    private String place;
    private String address;
    private String city;
    private LocalDate date;

    public Covidexposures(String place, String address, String city, LocalDate date) {
        this.place = place;
        this.address = address;
        this.city = city;
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
