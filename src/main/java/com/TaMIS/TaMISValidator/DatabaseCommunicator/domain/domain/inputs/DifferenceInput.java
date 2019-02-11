package com.TaMIS.TaMISValidator.DatabaseCommunicator.domain.domain.inputs;

import com.TaMIS.TaMISValidator.DatabaseCommunicator.domain.DifferenceEntity;
import com.TaMIS.TaMISValidator.DatabaseCommunicator.domain.ForecastedObservationEntity;
import com.TaMIS.TaMISValidator.DatabaseCommunicator.repositories.DifferencesRepository;
import com.TaMIS.TaMISValidator.Validator.DifferenceCalculator;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DifferenceInput {

    private String scenarioID;
    private Double difference;
    private Long timestampMillis;

    @Autowired
    public DifferenceEntity toDifferenceEntity() {

        DifferenceEntity diff = new DifferenceEntity();
        diff.setDifference(this.difference);
        diff.setTestScenarioID(this.scenarioID);
        diff.setTimestampMillis(this.timestampMillis);

        return diff;

    }

    public static ArrayList<DifferenceEntity> getDifferences() throws Exception {
        DifferenceCalculator differenceCalculator = new DifferenceCalculator();
        List differences = differenceCalculator.calculateDifferences();
        Iterator it = differences.iterator();

        while(it.hasNext()){
            DifferenceEntity diff = new DifferenceEntity();
            List element = (List) it.next();

            diff.setDifference((Double) element.get(0));
            diff.setTestScenarioID((String) element.get(1));
            diff.setTimestampMillis((Long) element.get(2));




        }
        return null;
    }


}
