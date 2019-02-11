package com.TaMIS.TaMISValidator.SensorWebCommunicator.wpsCommunicator;


import javafx.util.Pair;
import org.apache.http.entity.StringEntity;
import org.hamcrest.core.StringEndsWith;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class WpsPostBodyConstructor {
    final static String PATH = "inputs/adjustableSosBody.json";
    final static String VALUE = "value";
    final static String TIMESPAN = "timespan";
    final static String RERENCE_TIME = "2018-08-09T00:00:00.402Z/2018-09-09T00:00:00.402Z";
    final static String SINGLE_INPUT_TIMESTAMP = "singleInputZeitstempel";
    final static String PREDICTION_TIME = "2018-09-12T00:00:00.402Z";


    public static StringEntity buildPostBody() throws IOException, ParseException {

        TimestampManager timestampManager = new TimestampManager();
        ArrayList<Pair<String, String>> time =  timestampManager.listOfTimestampsForScenarios();

        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(PATH));
        JSONObject jsonObject = (JSONObject) obj;


        JSONArray arr = (JSONArray) jsonObject.get("inputs");

        for (int j = 0; j<time.size(); j++){
            Pair<String, String> timepair = time.get(j);
            for (int i = 0; i < arr.size(); i++) {
                JSONObject jsonObjectInArray = (JSONObject) arr.get(i);
                if (jsonObjectInArray.get("id").equals(TIMESPAN)){
                    jsonObjectInArray.put(VALUE, timepair.getValue());
                }
                else if(jsonObjectInArray.get("id").equals(SINGLE_INPUT_TIMESTAMP)){
                    jsonObjectInArray.put(VALUE, timepair.getKey());
                }

            }
        }
        /*for (int i = 0; i < arr.size(); i++) {
            JSONObject jsonObjectInArray = (JSONObject) arr.get(i);
            if (jsonObjectInArray.get("id").equals(TIMESPAN)){
                jsonObjectInArray.put(VALUE, RERENCE_TIME);
            }
            else if(jsonObjectInArray.get("id").equals(SINGLE_INPUT_TIMESTAMP)){
                jsonObjectInArray.put(VALUE, PREDICTION_TIME);
            }

        }*/


        System.out.println(jsonObject.toString());

        StringEntity stringEntityJSON = new StringEntity(jsonObject.toJSONString());

        return stringEntityJSON;
    }

    public static ArrayList<StringEntity> buildPostBody2() throws IOException, ParseException {

        TimestampManager timestampManager = new TimestampManager();
        ArrayList<Pair<String, String>> time =  timestampManager.listOfTimestampsForScenarios();

        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(new FileReader(PATH));
        JSONObject jsonObject = (JSONObject) obj;


        JSONArray arr = (JSONArray) jsonObject.get("inputs");
        ArrayList<StringEntity> stringEntityJSONList = new ArrayList<>();
        StringEntity stringEntityJSON = null; 
        for (int j = 0; j<time.size(); j++){
            Pair<String, String> timepair = time.get(j);
            for (int i = 0; i < arr.size(); i++) {
                JSONObject jsonObjectInArray = (JSONObject) arr.get(i);
                if (jsonObjectInArray.get("id").equals(TIMESPAN)){
                    jsonObjectInArray.put(VALUE, timepair.getValue());
                }
                else if(jsonObjectInArray.get("id").equals(SINGLE_INPUT_TIMESTAMP)){
                    jsonObjectInArray.put(VALUE, timepair.getKey());
                }
                stringEntityJSON = new StringEntity(jsonObject.toJSONString());
                

            }
            stringEntityJSONList.add(stringEntityJSON);

        }







        return stringEntityJSONList;
    }


    /*public static void main(String[] args) throws Exception {
        ArrayList<StringEntity> bb = WpsPostBodyConstructor.buildPostBody2();
        Iterator it = bb.iterator();
        while(it.hasNext()){
            System.out.println(it.next().toString());
        }


    }*/
}
