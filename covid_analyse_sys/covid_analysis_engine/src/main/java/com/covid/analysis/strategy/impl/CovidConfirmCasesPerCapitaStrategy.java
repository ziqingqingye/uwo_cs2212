package com.covid.analysis.strategy.impl;

import com.covid.analysis.strategy.AnalysisStrategy;
import com.covid.data.retrieve.DataRetrieve;
import com.covid.data.retrieve.impl.ConfirmedCasesReader;
import com.covid.data.retrieve.impl.PopulationReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class perform the real analysis on the total confirmed Covid-19 cases per capita
 * for a given country or a list of countries
 * @author Tianci Du
 * @version 1.0
 */
public class CovidConfirmCasesPerCapitaStrategy implements AnalysisStrategy {
    private Map<String,Double> casePerCapita = new HashMap<String, Double>();

    /**
     * This method acquire related data needed to perform the specific analysis.
     * And calculate and return the number of cases per capita for selected list of countries.
     * @param country list of countries selected to perform the analysis
     * @return a Map object (key: name of country; value: cases per capita for the country that the key represents.)
     */
    public Map<String,Double> performAnalysis(List<String> country) {
        DataRetrieve caseRetrieve = new ConfirmedCasesReader();
        DataRetrieve populationRetrieve = new PopulationReader();
        double confirmedCase,population;
        double casePerCapita;
        for (String countryName: country){
            confirmedCase = caseRetrieve.retrieveData(countryName);
            population = populationRetrieve.retrieveData(countryName);
            System.out.println("population: "+population);
            casePerCapita = confirmedCase/population;
            System.out.println(countryName+"=>");
            System.out.println(" COVID: "+casePerCapita);
            this.casePerCapita.put(countryName,casePerCapita);
        }
        return this.casePerCapita;
    }

    /**
     * This method is used to set the casePerCapita Map object.
     * (key: name of country; value: cases per capita for the country that the key represents.)
     * @param casePerCapita
     */
    public void setMap(Map<String, Double> casePerCapita) {
        this.casePerCapita = casePerCapita;
    }


    /**
     * This method is used to get the casePerCapita Map object
     * @return the map representing the country and the related case per capita of the country (key: country name; value: cases of the country)
     */
    public Map<String, Double> getMap() {
        return casePerCapita;
    }
}
