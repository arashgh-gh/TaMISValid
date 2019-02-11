package com.TaMIS.TaMISValidator.DatabaseCommunicator.services;

import com.TaMIS.TaMISValidator.DatabaseCommunicator.domain.ForecastedObservationEntity;

import java.util.List;

public interface WpsResultsService {

    List<ForecastedObservationEntity> getAll();
}
