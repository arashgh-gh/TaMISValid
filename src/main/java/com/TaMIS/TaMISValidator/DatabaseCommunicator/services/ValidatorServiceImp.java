package com.TaMIS.TaMISValidator.DatabaseCommunicator.services;


import com.TaMIS.TaMISValidator.DatabaseCommunicator.domain.DifferenceEntity;
import com.TaMIS.TaMISValidator.DatabaseCommunicator.repositories.DifferencesRepository;
import com.TaMIS.TaMISValidator.Validator.DifferenceCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class ValidatorServiceImp implements ValidatorService {

    @Autowired
    private DifferencesRepository differencesRepository;

    @Override
    public List<DifferenceEntity> getAll(){
        List<DifferenceEntity> differenceResults = (List<DifferenceEntity>) differencesRepository.findAll();
        return differenceResults;
    }

    public void writeDifferencesToDatabase() throws Exception{
        DifferenceCalculator differenceCalculator = new DifferenceCalculator();
        List differences = differenceCalculator.calculateDifferences();
        Iterator it = differences.iterator();

        while(it.hasNext()){
            List element = (List) it.next();

            DifferenceEntity diff = new DifferenceEntity();
            ArrayList<DifferenceEntity> diffList = new ArrayList();

            diff.setDifference((Double) element.get(0));
            diff.setTestScenarioID((String) element.get(1));
            diff.setTimestampMillis((Long) element.get(2));
            diffList.add(diff);
            differencesRepository.saveAll(diffList);


        }

    }
}
