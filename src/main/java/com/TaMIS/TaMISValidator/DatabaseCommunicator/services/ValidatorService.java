package com.TaMIS.TaMISValidator.DatabaseCommunicator.services;

import com.TaMIS.TaMISValidator.DatabaseCommunicator.domain.DifferenceEntity;

import java.util.List;

public interface ValidatorService{
    List<DifferenceEntity> getAll();
}
