package com.TaMIS.TaMISValidator.DatabaseCommunicator.controller;

import com.TaMIS.TaMISValidator.DatabaseCommunicator.domain.ForecastedObservationEntity;
import com.TaMIS.TaMISValidator.DatabaseCommunicator.domain.SosReaderEntity;
import com.TaMIS.TaMISValidator.DatabaseCommunicator.services.SosReaderServiceImp;
import com.TaMIS.TaMISValidator.DatabaseCommunicator.services.WpsResultsServiceImp;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;

@RestController
public class SosDatabseController {
    @Autowired
    SosReaderServiceImp sosReaderServiceImp = new SosReaderServiceImp();
    @Autowired
    WpsResultsServiceImp wpsResultsServiceImp = new WpsResultsServiceImp();

    @RequestMapping(value = "/sos", method=RequestMethod.GET)
    public List findSomething() throws Exception {

        List<SosReaderEntity> some =  sosReaderServiceImp.getAll();



        return some;
    }
    @RequestMapping(value="/wps", method=RequestMethod.GET)
    public List findWps() throws Exception {
        List<ForecastedObservationEntity> w = wpsResultsServiceImp.getAll();
        return w;
    }
}
