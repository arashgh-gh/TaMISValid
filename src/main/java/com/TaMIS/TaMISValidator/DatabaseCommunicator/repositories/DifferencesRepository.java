package com.TaMIS.TaMISValidator.DatabaseCommunicator.repositories;

import com.TaMIS.TaMISValidator.DatabaseCommunicator.domain.DifferenceEntity;
import org.springframework.data.repository.CrudRepository;

public interface DifferencesRepository extends CrudRepository<DifferenceEntity, Long> {
}
