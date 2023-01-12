package com.covid.analysis.create.impl;

import com.covid.analysis.create.AnalysisTypeCreator;
import com.covid.analysis.strategy.AnalysisStrategy;
import com.covid.analysis.strategy.impl.CovidConfirmCasesStrategy;

/**
 * This class acts as the creator of CovidConfirmCasesStrategy object.
 * @author Tianci Du
 * @version 1.0
 */
public class CovidConfirmCasesCreator extends AnalysisTypeCreator {

    /**
     * This method constructs and returns the CovidConfirmCasesStrategy object.
     * @return object of CovidConfirmCasesStrategy
     */
    @Override
    public AnalysisStrategy analysisStrategyCreate() {
        return new CovidConfirmCasesStrategy();
    }
}
