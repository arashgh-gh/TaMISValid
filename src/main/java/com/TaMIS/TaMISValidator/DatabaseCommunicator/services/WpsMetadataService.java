package com.TaMIS.TaMISValidator.DatabaseCommunicator.services;

import com.TaMIS.TaMISValidator.DatabaseCommunicator.domain.ForecastedObservationMetadataEntity;

import java.util.List;

public interface WpsMetadataService {
    List<ForecastedObservationMetadataEntity> getAll();
}
