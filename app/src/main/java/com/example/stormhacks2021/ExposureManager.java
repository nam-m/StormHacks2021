package com.example.stormhacks2021;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ExposureManager {
    private List<Covidexposures> exposures;
    private static ExposureManager instance;

    private ExposureManager(){
        exposures = new ArrayList<>();
    }

    public static ExposureManager getInstance(){
        if(instance == null){
            instance = new ExposureManager();
        }
        return instance;
    }

    public List<Covidexposures> getList() {return  exposures;}
}
