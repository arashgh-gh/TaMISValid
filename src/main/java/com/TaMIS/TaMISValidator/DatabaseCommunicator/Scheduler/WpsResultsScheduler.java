package com.TaMIS.TaMISValidator.DatabaseCommunicator.Scheduler;

import com.TaMIS.TaMISValidator.DatabaseCommunicator.services.SosReaderServiceImp;



import com.TaMIS.TaMISValidator.DatabaseCommunicator.services.WpsMetadataServiceImp;
import com.TaMIS.TaMISValidator.DatabaseCommunicator.services.WpsResultsServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WpsResultsScheduler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SosReaderServiceImp sosReaderServiceImp;
    @Autowired
    private WpsResultsServiceImp wpsResultsServiceImp;
    @Autowired
    private WpsMetadataServiceImp wpsMetadataServiceImp;

    @Scheduled(cron="0 0 12 * * *") // Everyday at 12 o'clock
    public void croneJob() throws Exception {

        //sosReaderServiceImp.writeObservationsToSosDatabase();
        wpsResultsServiceImp.writeWpsResultsToDatabase2();
        //wpsMetadataServiceImp.writeMetadataToDatabase();
    }

}
