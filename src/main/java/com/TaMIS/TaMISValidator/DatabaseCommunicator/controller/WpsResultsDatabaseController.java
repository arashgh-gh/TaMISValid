/*package com.TaMIS.TaMISValidator.DatabaseCommunicator.controller;

import com.TaMIS.TaMISValidator.DatabaseCommunicator.domain.ForecastedObservationEntity;
import com.TaMIS.TaMISValidator.DatabaseCommunicator.domain.ForecastedObservationMetadataEntity;
import com.TaMIS.TaMISValidator.DatabaseCommunicator.domain.domain.inputs.ForecastedObservationInput;
import com.TaMIS.TaMISValidator.DatabaseCommunicator.services.WpsMetadataServiceImp;
import com.TaMIS.TaMISValidator.DatabaseCommunicator.services.WpsResultsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class WpsResultsDatabaseController {
    @Autowired
    WpsResultsServiceImp wpsResultsService;
    @RequestMapping(value = "/value", method = RequestMethod.GET)
    public ForecastedObservationEntity findSomething() throws Exception {

        ForecastedObservationEntity some =  wpsResultsService.writeWpsResultsToDatabase();
        return some;
    }

    @RestController
    public class WpsMetadataDatabaseController {
        @Autowired
        WpsMetadataServiceImp wpsMetadataService;
        @RequestMapping(value = "/meta", method = RequestMethod.GET)
        public ForecastedObservationMetadataEntity findSomething() throws Exception {

            ForecastedObservationMetadataEntity some =  wpsMetadataService.writeMetadataToDatabase();
            return some;
        }

    }

}*/
