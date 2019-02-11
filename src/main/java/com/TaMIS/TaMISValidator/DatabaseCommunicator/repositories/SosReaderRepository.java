package com.TaMIS.TaMISValidator.DatabaseCommunicator.repositories;


import com.TaMIS.TaMISValidator.DatabaseCommunicator.domain.SosReaderEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SosReaderRepository extends CrudRepository<SosReaderEntity, Long> {
}
