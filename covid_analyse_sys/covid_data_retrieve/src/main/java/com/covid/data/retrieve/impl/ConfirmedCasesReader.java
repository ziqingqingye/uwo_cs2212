package com.covid.data.retrieve.impl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.covid.data.retrieve.DataRetrieve;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

/**
 * This class retrieves data from website
 * which provides data for calculations of ConfirmedCasesStrategy.
 * @author Tianci Du
 * @version 1.0
 */
public class ConfirmedCasesReader implements DataRetrieve {

    /**
     * Read data from website. Set the country name passed by parameter as part of the url, retrieve the total confirmed cases of the country.
     * @param country the name of country which request the data for total confirmed cases
     * @return data of the total confirmed cases
     */
    public double retrieveData(String country) {
        String urlString = String.format("https://api.covid19api.com/total/dayone/country/%s/status/confirmed", country);
        System.out.println(urlString);
        int cases = 0;
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();       //represents a connection to the remote object referred to by the URL.
            conn.setRequestMethod("GET");
            conn.connect();
            int responsecode = conn.getResponseCode();
            if (responsecode == 200) {
                String inline = "";
                Scanner sc = new Scanner(url.openStream());
                while (sc.hasNext()) {
                    inline += sc.nextLine();
                }
                sc.close();
                JsonArray jsonArray = new JsonParser().parse(inline).getAsJsonArray();
                int size = jsonArray.size();
                int n = size - 1;
                cases = jsonArray.get(n).getAsJsonObject().get("Cases").getAsInt();
                System.out.println("Cases: " + cases);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cases;
    }
}
