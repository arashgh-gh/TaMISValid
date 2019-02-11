package com.TaMIS.TaMISValidator.SensorWebCommunicator.wpsCommunicator;

import org.apache.http.entity.StringEntity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WpsPoster {

    private static final String TARGETPOSTREQUEST = "http://tamis.dev.52north.org/tamis-rest/api/v1/services/1/processes/org.n52.wps.server.r.tamis-rest-prediction-single";


    public static String startWpsJob() throws Exception {

        WpsPostBodyConstructor wpsPostBodyConstructor = new WpsPostBodyConstructor();
        StringEntity body = wpsPostBodyConstructor.buildPostBody();

        WpsStarter wpsStarter = new WpsStarter();
        String jobLocation = wpsStarter.sendPostRequest(TARGETPOSTREQUEST, body);


        return jobLocation;


    }

    public static List<String> startWpsJob2() throws Exception {


        ArrayList<StringEntity> listOfBodies = WpsPostBodyConstructor.buildPostBody2();
        List<String> listOfJobLocations = new ArrayList<>();

        Iterator it = listOfBodies.iterator();
        while(it.hasNext()){
            StringEntity body = (StringEntity) it.next();
            WpsStarter wpsStarter = new WpsStarter();
            String jobLocation = wpsStarter.sendPostRequest(TARGETPOSTREQUEST, body);
            listOfJobLocations.add(jobLocation);
        }




        return listOfJobLocations;


    }

    public static void main(String[] args) throws Exception {
        List<String> l = WpsPoster.startWpsJob2();

        Iterator it = l.iterator();
        while(it.hasNext()){
            System.out.println(it.next());

        }
    }
}
