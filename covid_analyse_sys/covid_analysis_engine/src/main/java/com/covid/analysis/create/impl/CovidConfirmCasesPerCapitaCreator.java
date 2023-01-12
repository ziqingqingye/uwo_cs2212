package com.covid.analysis.create.impl;

import com.covid.analysis.create.AnalysisTypeCreator;
import com.covid.analysis.strategy.AnalysisStrategy;
import com.covid.analysis.strategy.impl.CovidConfirmCasesPerCapitaStrategy;

/**
 * This class acts as the creator of the CovidConfirmCasesPerCapitaStrategy object.
 * @author Tianci Du
 * @version 1.0
 */
public class CovidConfirmCasesPerCapitaCreator extends AnalysisTypeCreator {
    /**
     * This method constructs and returns the CovidConfirmCasesForWomenStrategy object.
     * @return object of CovidConfirmCasesPerCapitaStrategy
     */
    @Override
    public AnalysisStrategy analysisStrategyCreate() {
        return new CovidConfirmCasesPerCapitaStrategy();
    }
}
