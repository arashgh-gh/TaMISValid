package com.TaMIS.TaMISValidator.DatabaseCommunicator.domain.domain.inputs;


import com.TaMIS.TaMISValidator.ApplicationUtils;
import com.TaMIS.TaMISValidator.DatabaseCommunicator.services.ProcessMonitoringService;
import com.TaMIS.TaMISValidator.DatabaseCommunicator.domain.ForecastedObservationEntity;
import org.apache.commons.math3.util.Precision;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;


public class ForecastedObservationInput {


    private double PredictedSeepageValue;
    private long timestampMillis;
    private String jobLocation;

    @Autowired
    public ForecastedObservationEntity toForecastedEntity() {

        ForecastedObservationEntity obs = new ForecastedObservationEntity();
        obs.setTimestamp(this.timestampMillis);
        obs.setPredictedSeepageValue(this.PredictedSeepageValue);

        return obs;
    }


    public static ArrayList<ForecastedObservationEntity> getDayTimeAndValueOfWpsForecast() throws Exception {

        List<String> endpoints = ProcessMonitoringService.executeProcess();
        String link = endpoints.get(1);
        URL url = new URL(link);
        InputStream is = url.openStream();


        JSONObject jsonObject = ApplicationUtils.parseInputStreamToJsonObject(is);
        JSONArray values = (JSONArray) jsonObject.get("values");
        Iterator<JSONObject> iter = values.iterator();
        List<Double> listOfValues = new ArrayList<>();
        List<Long> listOfTimestamps = new ArrayList<>();
        Double value;
        Long timestamp;
        while (iter.hasNext()) {
            //System.out.println(iter.next());
            JSONObject jsonObject1 = iter.next();

            value = (Double) jsonObject1.get("value");
            timestamp = (Long) jsonObject1.get("timestamp");

            listOfValues.add(value);
            listOfTimestamps.add(timestamp);

        }
        Double sum = 0.0;
        for(int i = 48; i<listOfValues.size(); i++){
            value = listOfValues.get(i);
            sum = sum + value;


        }
        Double mean = Precision.round(sum/24, 4);



        Long timestampFeatureOfInterest = listOfTimestamps.get(listOfTimestamps.size()-15);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        String formattedDate = sdf.format(timestampFeatureOfInterest);

        ForecastedObservationEntity obs = new ForecastedObservationEntity();
        obs.setPredictedSeepageValue(mean);
        obs.setTimestamp(timestampFeatureOfInterest);

        ArrayList<ForecastedObservationEntity> obsList = new ArrayList();

        obsList.add(obs);

        return obsList;

    } //not used



    public static ArrayList<ForecastedObservationEntity> getDayTimeAndValueOfWpsForecast2() throws Exception {

        ArrayList<List> endpoints = ProcessMonitoringService.executeProcess2();
        Iterator it = endpoints.iterator();
        ForecastedObservationEntity obs = new ForecastedObservationEntity();
        int j = 1;
        while(it.hasNext()){

            List<String> links = (List<String>) it.next();
            String link = links.get(1);
            URL url = new URL(link);
            InputStream is = url.openStream();


            JSONObject jsonObject = ApplicationUtils.parseInputStreamToJsonObject(is);
            JSONArray values = (JSONArray) jsonObject.get("values");
            Iterator<JSONObject> iter = values.iterator();
            List<Double> listOfValues = new ArrayList<>();
            List<Long> listOfTimestamps = new ArrayList<>();
            Double value;
            Long timestamp;
            while (iter.hasNext()) {
                //System.out.println(iter.next());
                JSONObject jsonObject1 = iter.next();

                value = (Double) jsonObject1.get("value");
                timestamp = (Long) jsonObject1.get("timestamp");

                listOfValues.add(value);
                listOfTimestamps.add(timestamp);

            }


            if (j>= 1 && j<=3) {

                Double sum = 0.0;
                for (int i = 48; i < listOfValues.size(); i++) {
                    value = listOfValues.get(i);
                    sum = sum + value;


                }
                Double mean = Precision.round(sum / 24, 4);
                obs.setPredictedSeepageValue(mean);
            }

            if (j>=4 && j<=6) {
                /*while (iter.hasNext()) {
                    //System.out.println(iter.next());
                    JSONObject jsonObject1 = iter.next();

                    value = (Double) jsonObject1.get("value");
                    timestamp = (Long) jsonObject1.get("timestamp");

                    listOfValues.add(value);
                    listOfTimestamps.add(timestamp);

                }*/
                Double sum = 0.0;
                Double sum2 = 0.0;
                for (int i = 48; i < 72; i++) {
                    value = listOfValues.get(i);
                    sum = sum + value;
                }
                for (int i = 72; i < listOfValues.size(); i++) {
                    value = listOfValues.get(i);
                    sum2 = sum2 + value;
                }

                Double mean = Precision.round(sum / 24, 4);
                Double mean2 = Precision.round(sum2/24, 4);
                obs.setPredictedSeepageValue(mean);
                obs.setPredictedSeepageValue(mean2);
            }

            if (j>=7 && j<=9) {
                /*while (iter.hasNext()) {
                    //System.out.println(iter.next());
                    JSONObject jsonObject1 = iter.next();

                    value = (Double) jsonObject1.get("value");
                    timestamp = (Long) jsonObject1.get("timestamp");

                    listOfValues.add(value);
                    listOfTimestamps.add(timestamp);

                }*/
                Double sum = 0.0;
                Double sum2 = 0.0;
                Double sum3 = 0.0;
                for (int i = 48; i < 72; i++) {
                    value = listOfValues.get(i);
                    sum = sum + value;
                }
                for (int i = 72; i < 96; i++) {
                    value = listOfValues.get(i);
                    sum2 = sum2 + value;
                }
                for (int i = 96; i < listOfValues.size(); i++) {
                    value = listOfValues.get(i);
                    sum3 = sum3 + value;
                }

                Double mean = Precision.round(sum / 24, 4);
                Double mean2 = Precision.round(sum2/24, 4);
                Double mean3 = Precision.round(sum2/24, 4);
                obs.setPredictedSeepageValue(mean);
                obs.setPredictedSeepageValue(mean2);
                obs.setPredictedSeepageValue(mean3);
            }



            Long timestampFeatureOfInterest = listOfTimestamps.get(listOfTimestamps.size()-15);
            SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
            String formattedDate = sdf.format(timestampFeatureOfInterest);



            obs.setTimestamp(timestampFeatureOfInterest);



        }
        ArrayList<ForecastedObservationEntity> obsList = new ArrayList();
        obsList.add(obs);


        return obsList;
    } // not used

    public static ArrayList<List> getDayTimeAndValueOfWpsForecast3() throws Exception {

        ArrayList<List> endpoints = ProcessMonitoringService.executeProcess2();
        Iterator it = endpoints.iterator();

        ArrayList<List> a = new ArrayList();
        int j = 1;
        while(it.hasNext()){
            ArrayList<Object> obs = new ArrayList();
            String testScenarioID = "";

            List<String> links = (List<String>) it.next();
            String link = links.get(1);
            URL url = new URL(link);
            InputStream is = url.openStream();

            JSONObject jsonObject = ApplicationUtils.parseInputStreamToJsonObject(is);
            JSONArray values = (JSONArray) jsonObject.get("values");
            Iterator<JSONObject> iter = values.iterator();
            List<Double> listOfValues = new ArrayList<>();
            List<Long> listOfTimestamps = new ArrayList<>();
            Double value;
            Long timestamp;

            while (iter.hasNext()) {
                    //System.out.println(iter.next());
                    JSONObject jsonObject1 = iter.next();

                    value = (Double) jsonObject1.get("value");
                    timestamp = (Long) jsonObject1.get("timestamp");

                    listOfValues.add(value);
                    listOfTimestamps.add(timestamp);

            }

            if (j>= 1 && j<=3) {
                Double mean = 0.0;
                Double sum = 0.0;

                //System.out.println(j);
                for (int i = 48; i < 72; i++) {
                    value = listOfValues.get(i);
                    sum = sum + value;
                }

                mean = Precision.round(sum / 24, 4);
                Long timestampFeatureOfInterest = listOfTimestamps.get(57);
                testScenarioID = "TS" + String.valueOf(j);
                obs.addAll(Arrays.asList(mean, timestampFeatureOfInterest, testScenarioID));
            }


           if (j>=4 && j<=6) {
                Double sum = 0.0;
                Double sum2 = 0.0;
                //System.out.println(listOfValues.size());
                for (int i = 48; i < 72; i++) {
                    value = listOfValues.get(i);
                    sum = sum + value;
                }
                for (int i = 72; i < 96; i++) {
                    value = listOfValues.get(i);
                    sum2 = sum2 + value;
                }

                Double mean = Precision.round(sum / 24, 4);
                Double mean2 = Precision.round(sum2/24, 4);
                Long timestampFeatureOfInterest = listOfTimestamps.get(57);
                Long timestampFeatureOfInterest2 = listOfTimestamps.get(81);
                testScenarioID = "TS" + String.valueOf(j);
                obs.addAll(Arrays.asList(mean, timestampFeatureOfInterest, mean2, timestampFeatureOfInterest2, testScenarioID));
            }

            if (j>=7 && j<=9) {
                Double sum = 0.0;
                Double sum2 = 0.0;
                Double sum3 = 0.0;
                for (int i = 48; i < 72; i++) {
                    value = listOfValues.get(i);
                    sum = sum + value;
                }
                for (int i = 72; i < 96; i++) {
                    value = listOfValues.get(i);
                    sum2 = sum2 + value;
                }
                for (int i = 96; i < 120; i++) {
                    value = listOfValues.get(i);
                    sum3 = sum3 + value;
                }

                Double mean = Precision.round(sum / 24, 4);
                Long timestampFeatureOfInterest = listOfTimestamps.get(58);
                Double mean2 = Precision.round(sum2/24, 4);
                Long timestampFeatureOfInterest2 = listOfTimestamps.get(82);
                Double mean3 = Precision.round(sum2/24, 4);
                Long timestampFeatureOfInterest3 = listOfTimestamps.get(106);
                testScenarioID = "TS" + String.valueOf(j);
                obs.addAll(Arrays.asList(mean, timestampFeatureOfInterest, mean2, timestampFeatureOfInterest2, mean3, timestampFeatureOfInterest3, testScenarioID));


            }
            a. add(obs);
            j+=1;
        }


        return a;
    }

    /*public static void main(String[] args) throws Exception {
        List a = ForecastedObservationInput.getDayTimeAndValueOfWpsForecast3();
        Iterator it = a.iterator();
        //System.out.println(a.get(1));
        //System.out.println(a.get(1));
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }*/

}
