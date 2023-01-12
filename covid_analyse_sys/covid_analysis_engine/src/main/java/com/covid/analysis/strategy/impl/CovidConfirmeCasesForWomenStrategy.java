package com.covid.analysis.strategy.impl;

import com.covid.analysis.strategy.AnalysisStrategy;
import com.covid.data.retrieve.DataRetrieve;
import com.covid.data.retrieve.impl.ConfirmedCasesForWomenReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class perform the real analysis on the the total confirmed Covid-19 cases of females
 * for a given country or a list of countries.
 * @author Tianci Du
 * @version 1.0
 */
public class CovidConfirmeCasesForWomenStrategy implements AnalysisStrategy {
    private Map<String,Double> caseFemale = new HashMap<String, Double>();

    /**
     * This method acquire related data needed to perform the specific analysis.
     * And calculate and return the total confirmed Covid-19 cases of females for selected list of countries.
     * @param country list of countries selected to perform the analysis
     * @return a Map object (key: name of country; value: total confirmed Covid-19 cases of females for the country that the key represents.)
     */
    @Override
    public Map<String,Double> performAnalysis(List<String> country) {
        DataRetrieve retrieve = new ConfirmedCasesForWomenReader();
        double femaleCase;
        for (String countryName:country){
            femaleCase=retrieve.retrieveData(countryName);
            System.out.println(countryName+"=>");
            System.out.println(" COVID Females: "+femaleCase);
            this.caseFemale.put(countryName,femaleCase);
        }
        return this.caseFemale;
    }

    /**
     * This method is used to get the caseFemale Map object
     * @return the map representing the country and the related total confirmed Covid-19 cases of females of the country (key: country name; value: total confirmed Covid-19 cases of females of the country)
     */
    public Map<String, Double> getMap() {
        return caseFemale;
    }

    /**
     * This method is used to set the caseFemale Map object
     * (key: country name; value: total confirmed Covid-19 cases of females of the country)
     * @param caseFemale
     */
    public void setMap(Map<String, Double> caseFemale) {
        this.caseFemale = caseFemale;
    }
}
