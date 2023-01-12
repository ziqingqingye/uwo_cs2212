package com.covid.analysis.strategy.impl;

import com.covid.analysis.strategy.AnalysisStrategy;
import com.covid.data.retrieve.DataRetrieve;
import com.covid.data.retrieve.impl.ConfirmedCasesForMenReader;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * This class perform the real analysis on the the total confirmed Covid-19 cases of males
 * for a given country or a list of countries.
 * @author Tianci Du
 * @version 1.0
 */
public class CovidConfirmeCasesForMenStrategy implements AnalysisStrategy {
    private Map<String,Double> caseMale = new HashMap<String, Double>();

    /**
     * This method acquire related data needed to perform the specific analysis.
     * And calculate and return the total confirmed Covid-19 cases of males for selected list of countries.
     * @param country list of countries selected to perform the analysis
     * @return a Map object (key: name of country; value: total confirmed Covid-19 cases of males for the country that the key represents.)
     */
    @Override
    public Map<String,Double> performAnalysis(List<String> country) {
        DataRetrieve retrieve = new ConfirmedCasesForMenReader();
        double maleCase;
        for (String countryName:country){
            maleCase=retrieve.retrieveData(countryName);
            System.out.println(countryName+"=>");
            System.out.println(" COVID Males: "+maleCase);
            this.caseMale.put(countryName,maleCase);
        }
        return this.caseMale;
    }

    /**
     * This method is used to get the caseMale Map object
     * @return the map representing the country and the related total confirmed Covid-19 cases of males of the country (key: country name; value: total confirmed Covid-19 cases of males of the country)
     */
    public Map<String, Double> getMap() {
        return caseMale;
    }

    /**
     * This method is used to set the caseMale Map object
     * (key: country name; value: total confirmed Covid-19 cases of males of the country)
     * @param caseMale
     */
    public void setMap(Map<String, Double> caseMale) {
        this.caseMale = caseMale;
    }
}
