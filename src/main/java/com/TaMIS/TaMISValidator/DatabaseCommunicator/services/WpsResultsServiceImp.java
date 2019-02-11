package com.TaMIS.TaMISValidator.DatabaseCommunicator.services;



import com.TaMIS.TaMISValidator.DatabaseCommunicator.domain.ForecastedObservationEntity;
import com.TaMIS.TaMISValidator.DatabaseCommunicator.domain.domain.inputs.ForecastedObservationInput;
import com.TaMIS.TaMISValidator.DatabaseCommunicator.repositories.WpsResultsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class WpsResultsServiceImp implements WpsResultsService{

    @Autowired
    private WpsResultsRepository wpsResultsRepository;

    @Override
    public List<ForecastedObservationEntity> getAll(){
        List<ForecastedObservationEntity> wpsResults = (List<ForecastedObservationEntity>) wpsResultsRepository.findAll();
        return wpsResults;
    }


   public void writeWpsResultsToDatabase() throws Exception {

        ForecastedObservationInput forecastedObservationInput = new ForecastedObservationInput();
        List<ForecastedObservationEntity> obs = forecastedObservationInput.getDayTimeAndValueOfWpsForecast();

        for (ForecastedObservationEntity o: obs){
            wpsResultsRepository.save(o);
        }
   }

    public void writeWpsResultsToDatabase2() throws Exception {


        ArrayList<List> allData = ForecastedObservationInput.getDayTimeAndValueOfWpsForecast3();

        Iterator it = allData.iterator();


        while(it.hasNext()){




            List data = (List) it.next();
            int dataLength = data.size();




            if(dataLength == 3){
                ForecastedObservationEntity obs = new ForecastedObservationEntity();
                ArrayList<ForecastedObservationEntity> obsList = new ArrayList();
                obs.setPredictedSeepageValue((Double) data.get(0));
                obs.setTimestamp((Long) data.get(1));
                obs.setTestScenarioID((String) data.get(dataLength-1));
                obsList.add(obs);
                wpsResultsRepository.saveAll(obsList);

            }

            if(dataLength == 5){

                for(int i= 0; i<2; i++) {
                    ForecastedObservationEntity obs = new ForecastedObservationEntity();
                    ArrayList<ForecastedObservationEntity> obsList = new ArrayList();
                    obs.setPredictedSeepageValue((Double) data.get(2 * i));
                    obs.setTimestamp((Long) data.get(2 * i + 1));
                    obs.setTestScenarioID((String) data.get(dataLength - 1));
                    obsList.add(obs);
                    wpsResultsRepository.saveAll(obsList);
                }



            }

            if(dataLength == 7){


                for (int i = 0; i<3; i++) {
                    ForecastedObservationEntity obs = new ForecastedObservationEntity();
                    ArrayList<ForecastedObservationEntity> obsList = new ArrayList();
                    obs.setPredictedSeepageValue((Double) data.get(2 * i));
                    obs.setTimestamp((Long) data.get(2 * i + 1));
                    obs.setTestScenarioID((String) data.get(dataLength - 1));
                    obsList.add(obs);
                    wpsResultsRepository.saveAll(obsList);
                }
            }
        }


    }



}
