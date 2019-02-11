package com.TaMIS.TaMISValidator.Validator;

import com.TaMIS.TaMISValidator.DatabaseCommunicator.domain.DifferenceEntity;
import com.TaMIS.TaMISValidator.DatabaseCommunicator.domain.ForecastedObservationEntity;
import com.TaMIS.TaMISValidator.DatabaseCommunicator.domain.SosReaderEntity;
import com.TaMIS.TaMISValidator.DatabaseCommunicator.services.SosReaderServiceImp;
import com.TaMIS.TaMISValidator.DatabaseCommunicator.services.WpsResultsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DifferenceCalculator {

    @Autowired
    private WpsResultsServiceImp wpsResultsServiceImp;

    @Autowired
    private SosReaderServiceImp sosReaderServiceImp;

    public List<DifferenceEntity> calculateDifferences() throws Exception {

        List wps = wpsResultsServiceImp.getAll();
        Iterator itWps = wps.iterator();
        List sos = sosReaderServiceImp.getAll();
        Iterator itSos = sos.iterator();
        List allDiff = new ArrayList();

        while(itWps.hasNext()) {
            ForecastedObservationEntity f = (ForecastedObservationEntity) itWps.next();

            while (itSos.hasNext()) {
                List diffAndMore = new ArrayList();
                SosReaderEntity s = (SosReaderEntity) itSos.next();

                if (f.getTimestamp() == s.getTimestamp()) {
                    diffAndMore.add(f.getPredictedSeepageValue()-s.getObservedSeepageValue());
                    diffAndMore.add(f.getTestScenarioID());
                    diffAndMore.add(f.getTimestamp());
                }
                allDiff.add(diffAndMore);
            }
        }
        return allDiff;
    }

}
