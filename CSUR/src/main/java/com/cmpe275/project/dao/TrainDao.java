package com.cmpe275.project.dao;

import org.springframework.data.repository.CrudRepository;

import com.cmpe275.project.model.Train;

public interface TrainDao extends CrudRepository<Train, Long> {

}
