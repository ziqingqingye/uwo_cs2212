package com.covid.data.analysis.impl;

import com.covid.data.analysis.Reader;
import com.covid.data.retrieve.DataRetrieve;
import com.covid.data.retrieve.impl.ConfirmedCasesForMenReader;
import com.covid.data.retrieve.impl.ConfirmedCasesForWomenReader;
import com.covid.data.retrieve.impl.ConfirmedCasesReader;
import com.covid.data.retrieve.impl.PopulationReader;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class implements the Reader class, used to set and get the data needed for analysis calculations.
 * @author Tianci Du
 * @version 1.0
 */
public class ReaderImpl implements Reader {
    private double confirmedCases;
    private double population;
    private double womenNum;
    private double menNum;
    private List<String> knownCountry;

    /**
     * Get the total confirmed cases from ConfirmedCasesReader class.
     * @param countryName country which request the data of total confirmed cases
     * @return the total confirmed cases
     */
    @Override
    public double getConfirmedCases(String countryName) {
        DataRetrieve retrieve = new ConfirmedCasesReader();
        return retrieve.retrieveData(countryName);
    }

    /**
     * Get the population of specific country from PopulationReader
     * @param countryName country which request the data of population
     * @return population of the country passed by parameter
     */
    @Override
    public double getPopulation(String countryName) {
        DataRetrieve retrieve = new PopulationReader();
        return retrieve.retrieveData(countryName);
    }


    /**
     * Get the confirmed COVID-19 cases for women of specific country from ConfirmedCasesForWomenReader
     * @param countryName country which request the data of confirmed COVID-19 cases for women
     * @return confirmed COVID-19 cases for women of the country passed by parameter
     */
    @Override
    public double getWomenNum(String countryName) {
        DataRetrieve retrieve = new ConfirmedCasesForWomenReader();
        return retrieve.retrieveData(countryName);
    }


    /**
     * Get the confirmed COVID-19 cases for men of specific country from ConfirmedCasesForMenReader
     * @param countryName country which request the data of confirmed COVID-19 cases for men
     * @return confirmed COVID-19 cases for men of the country passed by parameter
     */
    @Override
    public double getMenNum(String countryName) {
        DataRetrieve retrieve = new ConfirmedCasesForMenReader();
        return retrieve.retrieveData(countryName);
    }

    /**
     * Set the total confirmed cases
     * @param confirmedCases the total confirm cases to be set the the attribute confirmedCases in this class
     */
    @Override
    public void setConfirmedCases(double confirmedCases) {
        this.confirmedCases = confirmedCases;
    }

    /**
     * Set the population
     * @param population population to be set the the attribute population in this class
     */
    @Override
    public void setPopulation(double population) {
        this.population = population;
    }

    /**
     * Set the confirmed cases of women
     * @param womenNum the confirmed cases of women to be set the the attribute womenNum in this class
     */
    @Override
    public void setWomenNum(double womenNum) {
        this.womenNum = womenNum;
    }

    /**
     * Set the confirmed cases of men
     * @param menNum the confirmed cases of men to be set the the attribute menNum in this class
     */
    @Override
    public void setMenNum(double menNum) {
        this.menNum = menNum;
    }

    /**
     * Get the list of name of countries from website: https://api.covid19api.com/countries, which is set to
     * be all the valid country names that user can select/input to perform analysis on.
     * @return
     */
    @Override
    public List<String> getKnownCountry() {
        String urlString = String.format("https://api.covid19api.com/countries");
        System.out.println(urlString);
        List<String> countryList = new ArrayList<String>();
        String country;
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();       //represents a connection to the remote object referred to by the URL.
            conn.setRequestMethod("GET");
            conn.connect();
            int responsecode = conn.getResponseCode();
            int i;
            if (responsecode == 200) {
                String inline = "";
                Scanner sc = new Scanner(url.openStream());
                while (sc.hasNext()) {
                    inline += sc.nextLine();
                }
                sc.close();
                JsonArray jsonArray = new JsonParser().parse(inline).getAsJsonArray();
                int size = jsonArray.size();
                for (i=0;i<size;i++){
                    country = jsonArray.get(i).getAsJsonObject().get("Country").getAsString();
                    countryList.add(country);
//                    System.out.println("Country: " + country);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return countryList;
    }

    /**
     * Set the valid list of countries that user can select/input to perform analysis on.
     * @param knownCountry list of countries to be set as valid country input
     */
    @Override
    public void setKnownCountry(List<String> knownCountry) {
        this.knownCountry = knownCountry;
    }
}
