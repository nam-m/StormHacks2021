package com.example.stormhacks2021;

import java.util.ArrayList;
import java.util.List;

public class ExposureManager {
    private List<Exposure> exposures;
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

    public List<Exposure> getList() {return  exposures;}
    public void setExposures(List<Exposure> exposures) {
        this.exposures = exposures;
    }
}
