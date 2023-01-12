package com.covid.data.retrieve.impl;

import com.covid.data.retrieve.DataRetrieve;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * This class retrieves data from local files, Dataset.csv,
 * which provides data for calculations of ConfirmedCasesForWomenStrategy.
 * @author Tianci Du
 * @version 1.0
 */
public class ConfirmedCasesForWomenReader implements DataRetrieve {

    /**
     * Read data from Dataset.csv file. According to the country name, retrieve the confirmed cases for woman of the country.
     * @param country the name of country which request the data for confirmed cases for woman
     * @return data of the confirmed cases for woman
     */
    @Override
    public double retrieveData(String country) {
        try {
            InputStream in = ConfirmedCasesForWomenReader.class.getClassLoader().getResourceAsStream("Dataset.csv");
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String str;
            String[] inFile;
            int i;
            int case_index = 4;
            int femal_index = 6;
            double femalPercent, femalCase;
            int totalCase;
            while ((str=bufferedReader.readLine())!=null){
                inFile = str.split(",");
                if (inFile[1].toLowerCase().equals(country.toLowerCase())){
                    if (inFile[2].equals("No")){
                        System.out.println("no data for female confirmed cases for country "+country);
                        return -1.0;
                    }
                    for (i=0; i<case_index; i++){
                        if (inFile[i].equals("")){
                            case_index++;
                            femal_index++;
                        }
                    }
                    for (; i<femal_index; i++){
                        if (inFile[i] == ""){
                            femal_index++;
                        }
                    }
//                    System.out.println("total case="+inFile[case_index]);
//                    System.out.println("female cases = "+inFile[femal_index]);
                    NumberFormat numberFormat = NumberFormat.getPercentInstance();
                    Number num = numberFormat.parse(inFile[femal_index]);
                    femalPercent = num.doubleValue();
                    //System.out.println("female percent="+femalPercent);
                    numberFormat = NumberFormat.getIntegerInstance();
                    num = numberFormat.parse(inFile[case_index]);
                    totalCase = num.intValue();
                    //System.out.println("total case="+totalCase);
                    femalCase = totalCase*femalPercent;
                    //System.out.println("femal case="+femalCase);
                    return femalCase;
                }
//                else {
//                    continue;
//                }
            }
            System.out.println("no sex-disaggregated data for "+country);
//            while ((str = bufferedReader.readLine())!=null){
//                inFile = str.split(",");
//                System.out.println("total case="+inFile[4]);
//                System.out.println("female cases = "+inFile[6]);
//
//                if (i == 1){
//                    double temp;
//                    NumberFormat numberFormat = NumberFormat.getPercentInstance();
//                    Number number = numberFormat.parse(inFile[6]);
//                    temp = number.doubleValue();
//                    System.out.println("temp= "+temp);
//                    break;
//                }
//                i++;
//            }
        }catch (IOException | ParseException e){
            e.printStackTrace();
        }
        return 0;
    }
}
