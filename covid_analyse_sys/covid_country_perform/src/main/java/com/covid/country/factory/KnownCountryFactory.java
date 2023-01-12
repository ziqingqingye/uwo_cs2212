package com.covid.country.factory;


import com.covid.data.analysis.Reader;
import com.covid.data.analysis.impl.ReaderImpl;

import java.util.List;

/**
 * This class is used to acquire the list of known countries, which can be useful when the system needs to check
 * whether the input country name is valid or not.
 * We use Singleton Design Pattern in this class such that the KnownCountryFactory class will only create one knownCountryList.
 * After the first time of acquiring KnownCountryList from the disk, we can always get the KnownCountryList from memory which
 * will increase the speed of operation significantly.
 * @author Tianci Du
 * @version 1.0
 */
public class KnownCountryFactory {

    private static List<String> knownCountryList;


    /**
     * This method get the list of known(valid) countries using Reader class.
     * @return the list of countries that can be verified by the system
     */
    public static List<String> getInstance() {
        if (knownCountryList==null){
            Reader reader = new ReaderImpl();
            List<String> countryList = reader.getKnownCountry();
            knownCountryList = countryList;
            return knownCountryList;
        }
        return knownCountryList;
    }
}
