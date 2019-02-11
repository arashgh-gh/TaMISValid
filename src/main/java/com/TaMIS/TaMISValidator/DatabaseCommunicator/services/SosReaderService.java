package com.TaMIS.TaMISValidator.DatabaseCommunicator.services;


import com.TaMIS.TaMISValidator.DatabaseCommunicator.domain.SosReaderEntity;
import java.util.List;

public interface SosReaderService {
    List<SosReaderEntity> getAll();
}
