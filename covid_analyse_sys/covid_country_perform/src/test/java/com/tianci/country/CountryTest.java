package com.tianci.country;

import com.covid.country.factory.KnownCountryFactory;

import com.covid.country.list.SelectedCountryList;
import com.covid.country.operation.CountryListOperation;
import com.covid.country.operation.ICountryOperation;
import com.covid.country.operation.impl.CountryListOperationImpl;
import com.covid.country.operation.impl.CountryOperationImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CountryTest {

    @Test
    public void testList(){
        List<String> country = new ArrayList<String>();
        country.add("a");
        country.add("b");
        country.add("c");
        System.out.println(country.indexOf("b"));
        System.out.println(country.size());
    }

//    @Test
//    public void testAdd(){
//        SelectedCountryList countryList = new SelectedCountryList();
//
//        KnownCountryList.getKnownCountryList().add("Africa");
//        KnownCountryList.getKnownCountryList().add("Russia");
//        KnownCountryList.getKnownCountryList().add("America");
//        KnownCountryList.getKnownCountryList().add("Australia");
//        KnownCountryList.getKnownCountryList().add("China");
//        KnownCountryList.getKnownCountryList().add("Canada");
//        countryList.add("Canada");
//        countryList.add("Australia");
//        countryList.add("America");
//        for (String country:SelectedCountryList.getCountryList()){
//            System.out.println("country: "+country);
//        }
//        countryList.removeCountry("America");
//        countryList.removeCountry("Australia");
//        for (String country:SelectedCountryList.getCountryList()){
//            System.out.println("country: "+country);
//        }
//    }

//    @Test
//    public void testCountryListRetrieve(){
//        List<String> list = new ArrayList<String>();
//        list.add("China");
//        list.add("Austria");
//        list.add("Canada");
//        KnownCountryList.setKnownCountryList(list);
//        System.out.println(KnownCountryList.getKnownCountryList());
//    }
//
//    @Test
//    public void knownCountryTest(){
//        System.out.println(KnownCountryList.getKnownCountryList());
//    }

    @Test
    public void testCountryOpt(){
        CountryListOperation operation = new CountryListOperationImpl();
        operation.addCountry("China");
        operation.addCountry("Canada");
        System.out.println(operation.getSelectedCountryList());
    }

    @Test
    public void test22(){
        List<String> list=KnownCountryFactory.getInstance();
        for (String str : list){
            System.out.println(str);
        }
    }

    @Test
    public void test33(){
        ICountryOperation operation=new CountryOperationImpl();
        Double data[]=operation.getCountryCoordinate("Italy");
        System.out.println(data[0]+","+data[1]);
    }
}
