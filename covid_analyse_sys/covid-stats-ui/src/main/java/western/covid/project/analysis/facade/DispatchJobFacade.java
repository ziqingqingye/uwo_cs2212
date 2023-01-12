package western.covid.project.analysis.facade;

import com.covid.analysis.calculate.Calculator;
import com.covid.analysis.create.AnalysisTypeCreator;
import com.covid.analysis.create.impl.CovidConfirmCasesCreator;
import com.covid.analysis.create.impl.CovidConfirmCasesFemalesCreator;
import com.covid.analysis.create.impl.CovidConfirmCasesMalesCreator;
import com.covid.analysis.create.impl.CovidConfirmCasesPerCapitaCreator;
import com.covid.analysis.strategy.AnalysisStrategy;
import com.covid.analysis.strategy.impl.CovidConfirmCasesPerCapitaStrategy;
import com.covid.analysis.strategy.impl.CovidConfirmCasesStrategy;
import com.covid.analysis.strategy.impl.CovidConfirmeCasesForMenStrategy;
import com.covid.analysis.strategy.impl.CovidConfirmeCasesForWomenStrategy;
import western.covid.project.dispatch.observe.MessageObservable;
import western.covid.project.dispatch.observe.Observable;
import western.covid.project.ui.MapPanel;

import java.util.List;
import java.util.Map;

/**
 * A class is responsible for dispatch task based on facade design pattern which is for
 * coordinating activities between the UI and the backend services
 * @author Tianci Du
 * @version 1.0
 */
public class DispatchJobFacade {

    private Observable messageObserable;

    public DispatchJobFacade(Observable observable){
        this.messageObserable=observable;
    }

    /**
     * This method is called whenever it need to perform multitask including data retrieve,render image.
     * @param analysisMethod
     * @param countryList
     * @param mapPanel
     * @return the map representing the country and the related case of the country
     */
    public Map<String,Double> perform(String analysisMethod, List<String> countryList, MapPanel mapPanel){

        MessageObservable msgDispatcher=(MessageObservable)messageObserable;
        msgDispatcher.setMessage("refreshAll");
        msgDispatcher.notifyUpdate();
//        AnalysisTypeCreator creator = new AnalysisTypeCreator();
        Calculator cal = new Calculator(new CovidConfirmCasesStrategy());
        AnalysisTypeCreator[] creator = new AnalysisTypeCreator[4];
        creator[0] = new CovidConfirmCasesCreator();
        creator[1] = new CovidConfirmCasesPerCapitaCreator();
        creator[2] = new CovidConfirmCasesFemalesCreator();
        creator[3] = new CovidConfirmCasesMalesCreator();
        if (analysisMethod.trim().toLowerCase().equals("ConfirmCase".toLowerCase())){
//            AnalysisStrategy strategy = new CovidConfirmCasesStrategy();
            cal.setStrategy(creator[0].analysisStrategyCreate());
            cal.setCountry(countryList);
            cal.execute();
//            strategy.performAnalysis(countryList);
            mapPanel.refresh(countryList,cal.getStrategy().getMap());
            this.updateOutput(cal.getStrategy().getMap());
            return cal.getStrategy().getMap();
        }
        else if (analysisMethod.trim().toLowerCase().equals("CasePerCapita".toLowerCase())){
//            AnalysisStrategy strategy = new CovidConfirmCasesPerCapitaStrategy();
//            strategy.performAnalysis(countryList);
//            mapPanel.refresh(countryList,strategy.getMap());
//            this.updateOutput(strategy.getMap());
//            return strategy.getMap();
            cal.setStrategy(creator[1].analysisStrategyCreate());
            cal.setCountry(countryList);
            cal.execute();
            mapPanel.refresh(countryList,cal.getStrategy().getMap());
            this.updateOutput(cal.getStrategy().getMap());
            return cal.getStrategy().getMap();
        }
        else if (analysisMethod.trim().toLowerCase().equals("ConfirmCaseFemale".toLowerCase())){
//            AnalysisStrategy strategy = new CovidConfirmeCasesForWomenStrategy();
//            strategy.performAnalysis(countryList);
//            mapPanel.refresh(countryList,strategy.getMap());
//            this.updateOutput(strategy.getMap());
//            return strategy.getMap();
            cal.setStrategy(creator[2].analysisStrategyCreate());
            cal.setCountry(countryList);
            cal.execute();
            mapPanel.refresh(countryList,cal.getStrategy().getMap());
            this.updateOutput(cal.getStrategy().getMap());
            return cal.getStrategy().getMap();
        }
        else if (analysisMethod.trim().toLowerCase().equals("ConfirmCaseMale".toLowerCase())){
//            AnalysisStrategy strategy = new CovidConfirmeCasesForMenStrategy();
//            strategy.performAnalysis(countryList);
//            mapPanel.refresh(countryList,strategy.getMap());
//            this.updateOutput(strategy.getMap());
//            return strategy.getMap();
            cal.setStrategy(creator[3].analysisStrategyCreate());
            cal.setCountry(countryList);
            cal.execute();
            mapPanel.refresh(countryList,cal.getStrategy().getMap());
            this.updateOutput(cal.getStrategy().getMap());
            return cal.getStrategy().getMap();
        }

        return null;

    }

//    private void drawCircleByCoordinate(Double[] coordinate,MapPanel mapPanel,Map<String,Double> casesMap
//            ,List<String> countryList,String country){
//        if (coordinate!=null&&coordinate.length==2&&country!=null&&!country.equals("")){
//            mapPanel.refresh(coordinate[0],coordinate[1],casesMap,countryList,country);
//            messageObserable.notifyUpdate();
//        }
//
//    }


    /**
     * Notify the change when the content of output result modified.
     * It is based on Observable Design Pattern.
     * @param data
     */
    private void updateOutput(Map<String,Double> data){
        StringBuffer buffer=new StringBuffer();
        for (Map.Entry<String, Double> entry: data.entrySet()){
            String content=String.format("%s --> %f",entry.getKey(),entry.getValue());
            content+="\n";
            buffer.append(content);
        }
        MessageObservable msgDispatcher=(MessageObservable)messageObserable;
        msgDispatcher.setMessage(buffer);
        messageObserable.notifyUpdate();
    }
}
