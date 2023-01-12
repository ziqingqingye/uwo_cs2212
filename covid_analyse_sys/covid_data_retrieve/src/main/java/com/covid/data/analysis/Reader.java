package com.covid.data.analysis;

import java.util.List;

/**
 * This class gets the data from DataRetrieve class, such that clients can directly calls this Reader class to get
 * whatever data they need for the analysis calculation.
 * @author Tianci Du
 * @version 1.0
 */
public interface Reader {
    public double getConfirmedCases(String countryName);
    public double getPopulation(String countryName);
    public double getWomenNum(String countryName);
    public double getMenNum(String countryName);
    public void setConfirmedCases(double confirmedCases);

    public void setPopulation(double population);

    public void setWomenNum(double womenNum);

    public void setMenNum(double menNum);

    public List<String> getKnownCountry();

    public void setKnownCountry(List<String> knownCountry);
}
