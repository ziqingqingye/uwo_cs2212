package com.covid.data.retrieve;

/**
 * This class retrieves data from local files or websites, which can be used to proceed the further calculations of analysis.
 * @author Tianci Du
 * @version 1.0
 */
public interface DataRetrieve {
    public double retrieveData(String country);
}
