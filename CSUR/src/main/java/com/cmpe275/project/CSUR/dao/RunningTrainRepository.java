package com.cmpe275.project.CSUR.dao;


import com.cmpe275.project.model.RunningTrains;
import org.springframework.data.repository.CrudRepository;

public interface RunningTrainRepository extends CrudRepository<RunningTrains,Long> {

    public RunningTrains findById(Long Id);
}
