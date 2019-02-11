package com.TaMIS.TaMISValidator.DatabaseCommunicator.services;

import com.TaMIS.TaMISValidator.DatabaseCommunicator.domain.ForecastedObservationMetadataEntity;
import com.TaMIS.TaMISValidator.DatabaseCommunicator.domain.domain.inputs.ForecastedObservationMetadataInput;
import com.TaMIS.TaMISValidator.DatabaseCommunicator.repositories.WpsMetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WpsMetadataServiceImp implements WpsMetadataService{
    @Autowired
    private WpsMetadataRepository wpsMetadataRepository;

    @Override
    public List<ForecastedObservationMetadataEntity> getAll(){
        List<ForecastedObservationMetadataEntity> wpsMetadata = (List<ForecastedObservationMetadataEntity>) wpsMetadataRepository.findAll();
        return wpsMetadata;
    }

    public void writeMetadataToDatabase() throws Exception{

        List<ForecastedObservationMetadataEntity> meta = ForecastedObservationMetadataInput.extractMetadata();
        for (ForecastedObservationMetadataEntity o: meta){
            wpsMetadataRepository.save(o);
        };
    }
}
