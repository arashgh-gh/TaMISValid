package com.TaMIS.TaMISValidator.DatabaseCommunicator.repositories;

import com.TaMIS.TaMISValidator.DatabaseCommunicator.domain.ForecastedObservationMetadataEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WpsMetadataRepository extends CrudRepository<ForecastedObservationMetadataEntity, Long> {
}
