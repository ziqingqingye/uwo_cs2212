package com.covid.country.list;

import com.covid.country.factory.KnownCountryFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to show the country list which has been selected by users,
 * and has been checked for the existence in known country list.
 * @author Tianci Du
 * @version 1.0
 */
public class SelectedCountryList {
    private static List<String> countryList = new ArrayList<String>();

    /**
     * This method get the list of countries that user selected.
     * @return The selected country list.
     */
    public static List<String> getCountryList() {
        return countryList;
    }

    /**
     * This method is used to set the list of selected country.
     * @param countryName The list of countries which is chosen by the user.
     */
    public static void setCountryList(List<String> countryName) {
        SelectedCountryList.countryList = countryName;
    }

    /**
     * Add the country that user input to the list of selected countries
     * @param countryName the name of country that the client chooses to add
     * @return true if the country can be added, false otherwise.
     */
    public boolean add(String countryName){
//        List<String> knownCountryList = KnownCountryList.getKnownCountryList();
        List<String> knownCountryList = KnownCountryFactory.getInstance();
        if (knownCountryList.contains(countryName)){
            SelectedCountryList.getCountryList().add(countryName);
            System.out.println(countryName+" is added successfully");
            return true;

        }
        return false;
    }

    /**
     * Check if the input country can be remove from the selected country list. And remove if the input is valid
     * @param countryName the name of country the client chooses to remove
     * @return true if the country can be removed, false otherwise.
     */
    public String removeCountry(String countryName) {
        int size;
        if (SelectedCountryList.getCountryList().contains(countryName)){
            System.out.println("the input country name "+countryName+" is valid");
            size = SelectedCountryList.getCountryList().size()-1;
            if (SelectedCountryList.getCountryList().indexOf(countryName) == size){
                SelectedCountryList.getCountryList().remove(countryName);
                System.out.println(countryName+" is removed successfully");
                return countryName;
            }else {
                System.out.println("The input country name "+countryName+" is valid, but not in the first place of selected country list. It can not be removed at this time.");
            }
        }
        return null;
    }
}
