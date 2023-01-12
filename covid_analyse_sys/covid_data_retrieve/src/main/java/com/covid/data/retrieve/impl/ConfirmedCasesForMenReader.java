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
 * which provides data for calculations of ConfirmedCasesForMenStrategy.
 * @author Tianci Du
 * @version 1.0
 */
public class ConfirmedCasesForMenReader implements DataRetrieve {

    /**
     * Read data from Dataset.csv file. According to the country name, retrieve the confirmed cases for man of the country.
     * @param country the name of country which request the data for confirmed cases for man
     * @return data of the confirmed cases for man
     */
    public double retrieveData(String country) {
        try {
            InputStream in = ConfirmedCasesForMenReader.class.getClassLoader().getResourceAsStream("Dataset.csv");
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String str;
            String[] inFile;
            int i;
            int case_index = 4;
            int men_index = 5;
            double menPercent, menCase;
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
                            men_index++;
                        }
                    }
                    NumberFormat numberFormat = NumberFormat.getPercentInstance();
                    Number num = numberFormat.parse(inFile[men_index]);
                    menPercent = num.doubleValue();
                    numberFormat = NumberFormat.getIntegerInstance();
                    num = numberFormat.parse(inFile[case_index]);
                    totalCase = num.intValue();
                    menCase = totalCase*menPercent;
                    return menCase;
                }
            }
            System.out.println("no sex-disaggregated data for "+country);
        }catch (IOException | ParseException e){
            e.printStackTrace();
        }
        return 0;
    }
}
