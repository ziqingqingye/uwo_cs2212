package com.covid.country.operation.impl;

import com.covid.country.operation.ICountryOperation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * This class implements the interface of providing the coordination of country passed by parameter.
 * @author Tianci Du
 * @version 1.0
 */
public class CountryOperationImpl implements ICountryOperation {

    /**
     * According to the country name passed by parameter, read from the coordinates.csv file to get the coordinate of the country.
     * @param countryName country which request the data of coordination
     * @return the coordination of country
     */
    @Override
    public Double[] getCountryCoordinate(String countryName){
        //read from csv file to find the longitude and latitude
        Double[] coord = {0.0, 0.0};
        try {
            InputStream in = this.getClass().getClassLoader().getResourceAsStream("coordinates.csv");
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String str;
            String[] inFile;
            int i;

            while ((str=bufferedReader.readLine())!=null){
                inFile = str.split(",");
                if (inFile[3].toLowerCase().equals(countryName.toLowerCase())){
                    Double longitude = Double.parseDouble(inFile[2]);
                    Double latitude = Double.parseDouble(inFile[1]);
                    coord[0] = longitude;
                    coord[1] = latitude;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return coord;
    }
}
