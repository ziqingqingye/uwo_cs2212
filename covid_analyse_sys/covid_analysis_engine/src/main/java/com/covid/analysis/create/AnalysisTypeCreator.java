package com.covid.analysis.create;

import com.covid.analysis.strategy.AnalysisStrategy;

/**
 * This class acts as the Creator on Factory Method Design Pattern.
 * It defines the a interface, which is used to construct and return analysis strategy of different types
 * without letting clients know what kind of objects will be build.
 * @author Tianci Du
 * @version 1.0
 */
public abstract class AnalysisTypeCreator {

    public abstract AnalysisStrategy analysisStrategyCreate();
}
