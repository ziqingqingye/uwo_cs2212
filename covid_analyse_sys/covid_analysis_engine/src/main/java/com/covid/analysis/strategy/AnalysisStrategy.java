package com.covid.analysis.strategy;

import java.util.List;
import java.util.Map;

/**
 * This class perform the real analysis on different analysis strategy type.
 * @author Tianci Du
 * @version 1.0
 */
public interface AnalysisStrategy {
    public Map<String,Double> performAnalysis(List<String> country);
    public void setMap(Map<String, Double> casePerCapita);


    public Map<String, Double> getMap();
}
