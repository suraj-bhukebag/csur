package com.cmpe275.project.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cmpe275.project.model.RunningTrains;

public interface RunningTrainRepository extends CrudRepository<RunningTrains,Long> {

    public RunningTrains findById(Long Id);
    
    @Query(value = "SELECT * FROM running_trains WHERE train_id = ?1 and date = ?2", nativeQuery = true)
	RunningTrains findRunningTrainByTrainIDAndDate(@Param("train_id") long id,
			@Param("date") long date);
    
}
