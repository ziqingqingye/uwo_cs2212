package com.covid.analysis.strategy.impl;

import com.covid.analysis.strategy.AnalysisStrategy;
import com.covid.data.retrieve.DataRetrieve;
import com.covid.data.retrieve.impl.ConfirmedCasesReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * This class perform the real analysis on the total confirmed cases for a given country or a list of countries
 * @author Tianci Du
 * @version 1.0
 */
public class CovidConfirmCasesStrategy implements AnalysisStrategy {
    private Map<String,Double> confirmedCase = new HashMap<String, Double>();

    /**
     * This method acquire related data needed to perform the specific analysis.
     * And calculate and return the total confirmed case for selected list of countries.
     * @param country list of countries selected to perform the analysis
     * @return a Map object (key: name of country; value: total confirmed case for the country that the key represents.)
     */
    public Map<String,Double> performAnalysis(List<String> country) {
        DataRetrieve retrieve = new ConfirmedCasesReader();
        double data;
        for (String countryName:country){
            data = retrieve.retrieveData(countryName);
            System.out.println(countryName+"=>");
            System.out.println(" COVID: "+data);
            this.confirmedCase.put(countryName,data);
        }
        return this.confirmedCase;
    }

    /**
     * This method is used to set the confirmedCase Map object.
     * (key: name of country; value: total confirmed cases for the country that the key represents.)
     * @param confirmedCase
     */
    public void setMap(Map<String, Double> confirmedCase) {
        this.confirmedCase = confirmedCase;
    }


    /**
     * This method is used to get the confirmedCase Map object.
     * @return the map representing the country and the related total confirmed case of the country
     */
    public Map<String, Double> getMap() {
        return confirmedCase;
    }
}
