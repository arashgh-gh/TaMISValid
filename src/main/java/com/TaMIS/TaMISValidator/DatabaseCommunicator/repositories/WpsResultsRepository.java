package com.TaMIS.TaMISValidator.DatabaseCommunicator.repositories;


import com.TaMIS.TaMISValidator.DatabaseCommunicator.domain.ForecastedObservationEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WpsResultsRepository extends CrudRepository<ForecastedObservationEntity, Long> {

}
