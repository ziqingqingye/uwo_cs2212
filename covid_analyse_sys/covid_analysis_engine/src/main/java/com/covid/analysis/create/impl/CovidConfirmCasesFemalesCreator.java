package com.covid.analysis.create.impl;

import com.covid.analysis.create.AnalysisTypeCreator;
import com.covid.analysis.strategy.AnalysisStrategy;
import com.covid.analysis.strategy.impl.CovidConfirmeCasesForWomenStrategy;

/**
 * This class acts as the creator of the CovidConfirmCasesForWomenStrategy object.
 * @author Tianci Du
 * @version 1.0
 */
public class CovidConfirmCasesFemalesCreator extends AnalysisTypeCreator {
    /**
     * This method constructs and returns the CovidConfirmCasesForWomenStrategy object.
     * @return object of CovidConfirmCasesForWomenStrategy
     */
    @Override
    public AnalysisStrategy analysisStrategyCreate() {
        return new CovidConfirmeCasesForWomenStrategy();
    }
}
