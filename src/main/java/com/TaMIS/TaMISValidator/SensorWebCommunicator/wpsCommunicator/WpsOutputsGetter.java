package com.TaMIS.TaMISValidator.SensorWebCommunicator.wpsCommunicator;

import com.TaMIS.TaMISValidator.ApplicationUtils;

import com.TaMIS.TaMISValidator.DatabaseCommunicator.services.ProcessMonitoringService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WpsOutputsGetter {
    private static final String REQUESTMETHOD = "GET";
    private static final String RESULT = "result";
    private static final String VALUE = "value";


    public List<String> listOfEndpoints(String jobLocation) throws Exception {


        URL link = new URL(jobLocation);
        HttpURLConnection con = (HttpURLConnection) link.openConnection();
        con.setRequestMethod(REQUESTMETHOD);

        JSONObject jsonObject = ApplicationUtils.parseInputStreamToJsonObject(con.getInputStream());

        JSONArray resultArray = (JSONArray) jsonObject.get(RESULT);
        String valueOfMetaJson = null;
        String valueOfDataJson = null;
        List<String> endPoints = new ArrayList<>();
        for (int i = 0; i < resultArray.size(); i++) {
            JSONObject jsonObject1 = (JSONObject) resultArray.get(i);
            if (((String) jsonObject1.get("ID")).equalsIgnoreCase("metaJson")) {
                valueOfMetaJson = (String) jsonObject1.get(VALUE);
            }
            if (((String) jsonObject1.get("ID")).equalsIgnoreCase("dataJson")) {
                valueOfDataJson = (String) jsonObject1.get(VALUE);
            }

        }
        endPoints.add(valueOfMetaJson);
        endPoints.add(valueOfDataJson);


        return endPoints;

    }
    public static void main(String[] args) throws Exception {
        String jobLocation = WpsPoster.startWpsJob();

        WpsJobObserver wpsJobObserver = new WpsJobObserver();
        wpsJobObserver.observeJobUntilFinish(jobLocation);
        WpsOutputsGetter wpsOutputsGetter = new WpsOutputsGetter();
        List<String> end = wpsOutputsGetter.listOfEndpoints(jobLocation);
        Iterator it = end.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
}
