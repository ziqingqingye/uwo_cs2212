package com.covid.data.retrieve.impl;


import com.covid.data.retrieve.DataRetrieve;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * This class retrieves data from local files, country-by-population.json,
 * which provides population of countries
 * @author Tianci Du
 * @version 1.0
 */
public class PopulationReader implements DataRetrieve {

    /**
     * Read data from country-by-population.json file. According to the country name, retrieve the population of the country.
     * @param country the name of country which request the data of population
     * @return population of country in parameter
     */
    public double retrieveData(String country) {
        try {
            InputStream in = PopulationReader.class.getClassLoader().getResourceAsStream("country-by-population.json");
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String str;
            String inFile="";
            String countryName;
            double population;
            while ((str = bufferedReader.readLine())!=null){
                inFile+=str;
            }
            JsonArray jsonArray = new JsonParser().parse(inFile).getAsJsonArray();
            int size = jsonArray.size();
            for (int i = 0; i<size;i++){
                countryName = jsonArray.get(i).getAsJsonObject().get("country").getAsString();
                if (countryName.toLowerCase().equals(country.toLowerCase())){
                    System.out.println("countryName="+countryName);         //still the original country name with right upper case/lower case
                    System.out.println("country="+country);
                    population = jsonArray.get(i).getAsJsonObject().get("population").getAsDouble();
                    return population;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return 0.0;
    }
}
