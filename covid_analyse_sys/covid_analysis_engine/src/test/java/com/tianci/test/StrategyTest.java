package com.tianci.test;

import com.covid.analysis.strategy.AnalysisStrategy;
import com.covid.analysis.strategy.impl.CovidConfirmCasesPerCapitaStrategy;
import com.covid.analysis.strategy.impl.CovidConfirmCasesStrategy;
import com.covid.analysis.strategy.impl.CovidConfirmeCasesForMenStrategy;
import com.covid.analysis.strategy.impl.CovidConfirmeCasesForWomenStrategy;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StrategyTest {

    @Test
    public void ConfirmCaseStrategyTest(){
        AnalysisStrategy strategy = new CovidConfirmCasesStrategy();
        List<String> country = new ArrayList<String>();
        country.add("China");
        country.add("Australia");
        country.add("Canada");
        strategy.performAnalysis(country);
        System.out.println(strategy.getMap());
    }

    @Test
    public void CasePerCapitaTest(){
        List<String> country = new ArrayList<String>();
        country.add("China");
        country.add("Australia");
        country.add("Canada");
        AnalysisStrategy strategy = new CovidConfirmCasesPerCapitaStrategy();
        strategy.performAnalysis(country);
        System.out.println(strategy.getMap());
    }

    @Test
    public void ConfirmCaseFemaleStrategyTest(){
        AnalysisStrategy strategy = new CovidConfirmeCasesForWomenStrategy();
        List<String> country = new ArrayList<String>();
        country.add("China");
        country.add("Australia");
        country.add("Canada");
        strategy.performAnalysis(country);
        System.out.println(strategy.getMap());
    }

    @Test
    public void ConfirmCaseMaleStrategyTest(){
        AnalysisStrategy strategy = new CovidConfirmeCasesForMenStrategy();
        List<String> country = new ArrayList<String>();
        country.add("China");
        country.add("Australia");
        country.add("Canada");
        strategy.performAnalysis(country);
        System.out.println(strategy.getMap());
    }
}
