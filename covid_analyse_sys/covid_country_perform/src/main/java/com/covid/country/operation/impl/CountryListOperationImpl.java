package com.covid.country.operation.impl;


import com.covid.country.list.SelectedCountryList;
import com.covid.country.operation.CountryListOperation;

import java.util.List;

/**
 * This class implements the interface of handling the operations on country list.
 * @author Tianci Du
 * @version 1.0
 */
public class CountryListOperationImpl implements CountryListOperation {

    /**
     * Add country to selected country list.
     * @param countryName country to be added to the selected country list
     */
    public void addCountry(String countryName) {
        SelectedCountryList selectedCountryList = new SelectedCountryList();
        selectedCountryList.add(countryName);
    }

    /**
     * Remove country from the selected country list.
     * @param countryName country to be removed from the selected country list.
     * @return the country name that has been removed
     */
    public String removeCountry(String countryName) {
        SelectedCountryList selectedCountryList = new SelectedCountryList();
        selectedCountryList.removeCountry(countryName);
        return countryName;
    }

    /**
     * Get the list of selected country.
     * @return the list of selected country
     */
    public List<String> getSelectedCountryList(){
        return SelectedCountryList.getCountryList();
    }
}
