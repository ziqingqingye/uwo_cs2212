package com.tianci.dataTest;

import com.covid.data.analysis.Reader;
import com.covid.data.analysis.impl.ReaderImpl;
import com.covid.data.retrieve.DataRetrieve;
import com.covid.data.retrieve.impl.ConfirmedCasesForMenReader;
import com.covid.data.retrieve.impl.ConfirmedCasesForWomenReader;
import com.covid.data.retrieve.impl.ConfirmedCasesReader;
import com.covid.data.retrieve.impl.PopulationReader;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;


public class DataTest {

    @Test
    public void confirmedCasesRetrieveTest(){
        DataRetrieve retrieve = new ConfirmedCasesReader();
        retrieve.retrieveData("ITALY");         //does not distinguish upper case/lower case
    }

    @Test
    public void populationTest(){
        DataRetrieve retrieve = new PopulationReader();
        double population = retrieve.retrieveData("christmas Island");      //distinguish with upper case/ lower case
        System.out.println("population = "+population);
    }

    @Test
    public void readLineTest() throws IOException {
        InputStream in = DataTest.class.getClassLoader().getResourceAsStream("test.txt");
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String str;
        String inFile;
        str = bufferedReader.readLine();
        System.out.println("str: "+str);
        inFile = bufferedReader.readLine();     //change line
        System.out.println("inFile: "+inFile);
    }

    @Test
    public void FemaleCaseTest() throws ParseException {
        DataRetrieve retrieve = new ConfirmedCasesForWomenReader();
        double femaleCase = retrieve.retrieveData("china");
        System.out.println("FemaleCaseTest "+femaleCase);
        /*
        String str = "230";
        Integer temp = Integer.getInteger(str);
        System.out.println(temp);       //doesn't work

        NumberFormat numberFormat = NumberFormat.getIntegerInstance();
        Number num=numberFormat.parse(str);
        Integer temp1 = num.intValue();
        System.out.println(temp1);      //work
        */

    }

    @Test
    public void MaleCaseTest(){
        DataRetrieve retrieve = new ConfirmedCasesForMenReader();
        double menCase = retrieve.retrieveData("China");
        System.out.println("menCaseTest "+menCase);
    }

    @Test
    public void countryTest(){
        Reader reader = new ReaderImpl();
        reader.getKnownCountry();
    }
}
