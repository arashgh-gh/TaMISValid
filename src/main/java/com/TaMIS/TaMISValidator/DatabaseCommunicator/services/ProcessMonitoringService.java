package com.TaMIS.TaMISValidator.DatabaseCommunicator.services;

import com.TaMIS.TaMISValidator.DatabaseCommunicator.domain.ForecastedObservationMetadataEntity;
import com.TaMIS.TaMISValidator.DatabaseCommunicator.domain.domain.inputs.ForecastedObservationMetadataInput;
import com.TaMIS.TaMISValidator.SensorWebCommunicator.wpsCommunicator.WpsJobObserver;
import com.TaMIS.TaMISValidator.SensorWebCommunicator.wpsCommunicator.WpsOutputsGetter;
import com.TaMIS.TaMISValidator.SensorWebCommunicator.wpsCommunicator.WpsPoster;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProcessMonitoringService {

    // cron job 12h

    public static List<String> executeProcess() throws Exception {

        // read config on startup

        // create ProcessMetadata

        // for each Process metadata -> start job
        WpsPoster wpsPoster = new WpsPoster();
        String jobLocation = wpsPoster.startWpsJob();

        WpsJobObserver wpsJobObserver = new WpsJobObserver();
        wpsJobObserver.observeJobUntilFinish(jobLocation);
        String end = wpsJobObserver.getOutputURL(jobLocation);

        WpsOutputsGetter wpsOutputsGetter = new WpsOutputsGetter();
        List<String> listOfEndpoints = wpsOutputsGetter.listOfEndpoints(end);
        return listOfEndpoints;





        // Array[] jobs (jobLocation)

        // for each job watch (jobs)

        // if job finished -> get results and insert in DB


    }

    public static ArrayList<List> executeProcess2() throws Exception {

        // read config on startup

        // create ProcessMetadata

        // for each Process metadata -> start job
        WpsPoster wpsPoster = new WpsPoster();
        List<String> jobLocations = wpsPoster.startWpsJob2();
        Iterator it = jobLocations.iterator();
        List<String> listOfEndpoints = new ArrayList<>();
        ArrayList<List> listOfAllEndpoints = new ArrayList<>();
        WpsJobObserver wpsJobObserver = new WpsJobObserver();
        while(it.hasNext()){
            String jobLocation = String.valueOf(it.next());

            wpsJobObserver.observeJobUntilFinish(jobLocation);
            String end = wpsJobObserver.getOutputURL(jobLocation);

            WpsOutputsGetter wpsOutputsGetter = new WpsOutputsGetter();
            listOfEndpoints = wpsOutputsGetter.listOfEndpoints(end);
            listOfAllEndpoints.add(listOfEndpoints);
        }



        return listOfAllEndpoints;





        // Array[] jobs (jobLocation)

        // for each job watch (jobs)

        // if job finished -> get results and insert in DB


    }

   /* public static void main(String[] args) throws Exception {
        ArrayList<List> l = ProcessMonitoringService.executeProcess2();

        Iterator it = l.iterator();
        while(it.hasNext()){
            List<String> endpoint = (List<String>) it.next();
            Iterator it2 = endpoint.iterator();
            while (it2.hasNext()){
                System.out.println(it2.next());
            }

        }
    }*/

    //

}
