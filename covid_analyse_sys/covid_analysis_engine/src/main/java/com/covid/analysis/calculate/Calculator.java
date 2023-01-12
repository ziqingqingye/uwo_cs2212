package com.covid.analysis.calculate;

import com.covid.analysis.strategy.AnalysisStrategy;

import java.util.List;

/**
 * This class acts as the Context in the Strategy Design Pattern.
 * It is a common interface that clients can call to execute different algorithm.
 * @author Tianci Du
 * @version 1.0
 */
public class Calculator {
    private AnalysisStrategy strategy;
    private List<String> country;

    /**
     * This method is the constructor. It is called whenever a calculator object is created.
     * @param strategy The type of analysis strategy that is initialized to the Calculator object
     */
    public Calculator(AnalysisStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * This method gets the type of analysis strategy assigned to the Calculator object
     * @return the type of analysis strategy of Calculator object
     */
    public AnalysisStrategy getStrategy() {
        return strategy;
    }

    /**
     * This method sets the type of analysis strategy to Calculator object.
     * @param strategy the type of analysis strategy to be set to the Calculator object
     */
    public void setStrategy(AnalysisStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * This method gets the list of countries to be performed the specific analysis strategy type on
     * @return the list of countries
     */
    public List<String> getCountry() {
        return country;
    }

    /**
     * This method sets the list of countries to be performed the specific analysis strategy type on
     * @param country the list of countries
     */
    public void setCountry(List<String> country) {
        this.country = country;
    }

    /**
     * This method calls the real analysis strategy method of different analysis strategy types
     * depending on the value of the attribute, AnalysisStrategy strategy, in this class.
     */
    public void execute(){
        strategy.performAnalysis(country);
    }
}
