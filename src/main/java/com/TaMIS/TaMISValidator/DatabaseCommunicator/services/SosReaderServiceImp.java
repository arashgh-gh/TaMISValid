package com.TaMIS.TaMISValidator.DatabaseCommunicator.services;


import com.TaMIS.TaMISValidator.DatabaseCommunicator.domain.SosReaderEntity;
import com.TaMIS.TaMISValidator.DatabaseCommunicator.domain.domain.inputs.SosReaderInput;
import com.TaMIS.TaMISValidator.DatabaseCommunicator.repositories.SosReaderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Service
public class SosReaderServiceImp implements SosReaderService{
    @Autowired
    private SosReaderRepository sosReaderRepository;

    @Override
    public List<SosReaderEntity> getAll(){
        List<SosReaderEntity> sosReaderResults = (List<SosReaderEntity>) sosReaderRepository.findAll();
        return sosReaderResults;
    }

    public void writeObservationsToSosDatabase() throws Exception {


        List<SosReaderEntity> obs2 = SosReaderInput.extractSosObservation();

        for (SosReaderEntity o: obs2){
            sosReaderRepository.save(o);
        }
    }




}
