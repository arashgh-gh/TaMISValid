package com.TaMIS.TaMISValidator.DatabaseCommunicator.Scheduler;

import com.TaMIS.TaMISValidator.DatabaseCommunicator.domain.ForecastedObservationEntity;
import com.TaMIS.TaMISValidator.DatabaseCommunicator.domain.SosReaderEntity;
import com.TaMIS.TaMISValidator.DatabaseCommunicator.services.SosReaderServiceImp;
import com.TaMIS.TaMISValidator.DatabaseCommunicator.services.ValidatorServiceImp;
import com.TaMIS.TaMISValidator.DatabaseCommunicator.services.WpsResultsServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component
public class SosReaderScheduler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SosReaderServiceImp sosReaderServiceImp;
    @Autowired
    private ValidatorServiceImp validatorServiceImp;
    @Autowired
    private WpsResultsServiceImp wpsResultsServiceImp;
    @Scheduled(fixedRate = 10000)
    public void croneJob2() throws Exception {

        //sosReaderServiceImp.writeObservationsToSosDatabase();
        validatorServiceImp.writeDifferencesToDatabase();
        /*List sos = wpsResultsServiceImp.getAll();
        Iterator it = sos.iterator();
        while(it.hasNext()){
            ForecastedObservationEntity sosr = (ForecastedObservationEntity) it.next();
            System.out.println(sosr.getTimestamp());
        }*/




    }
}

