package com.TaMIS.TaMISValidator;

import com.TaMIS.TaMISValidator.SensorWebCommunicator.wpsCommunicator.WpsOutputsGetter;
import com.TaMIS.TaMISValidator.SensorWebCommunicator.wpsCommunicator.WpsPoster;
import com.TaMIS.TaMISValidator.SensorWebCommunicator.wpsCommunicator.WpsJobObserver;

public class ApplicationTest {

    public static void main(String[] args) throws Exception {

        String jobLocation = WpsPoster.startWpsJob();

        WpsJobObserver wpsJobObserver = new WpsJobObserver();
        wpsJobObserver.observeJobUntilFinish(jobLocation);

        wpsJobObserver.getOutputURL(jobLocation);
        WpsOutputsGetter wpsOutputsGetter = new WpsOutputsGetter();

        //wpsOutputsGetter.extractWpsOutputsModelPrediction();
        //wpsOutputsGetter.extractOutputsMetaJson(jobLocation);

    }

}
