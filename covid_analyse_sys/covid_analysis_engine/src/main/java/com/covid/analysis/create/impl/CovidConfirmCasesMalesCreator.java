package com.covid.analysis.create.impl;

import com.covid.analysis.create.AnalysisTypeCreator;
import com.covid.analysis.strategy.AnalysisStrategy;
import com.covid.analysis.strategy.impl.CovidConfirmeCasesForMenStrategy;

/**
 * This class acts as the creator of the CovidConfirmCasesForMenStrategy object.
 * @author Tianci Du
 * @version 1.0
 */
public class CovidConfirmCasesMalesCreator extends AnalysisTypeCreator {
    /**
     * This method constructs and returns the CovidConfirmCasesForMenStrategy object.
     * @return object of CovidConfirmCasesForMenStrategy
     */
    @Override
    public AnalysisStrategy analysisStrategyCreate() {
        return new CovidConfirmeCasesForMenStrategy();
    }
}
