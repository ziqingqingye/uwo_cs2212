package com.covid.country.operation;

import java.util.List;

/**
 * This class defines an interface for operations on country list (including adding, removing and acquiring list of
 * selected countries).
 * @author Tianci Du
 * @version 1.0
 */
public interface CountryListOperation {
    public void addCountry(String countryName);
    public String removeCountry(String countryName);
    public List<String> getSelectedCountryList();
}
