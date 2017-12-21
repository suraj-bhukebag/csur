package com.cmpe275.project.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cmpe275.project.model.Train;

public interface TrainDao extends CrudRepository<Train, Long> {

	@Query(value = "SELECT capacity FROM train WHERE id = ?1", nativeQuery = true)
	public long findTrainCapacityById(@Param("id") long id);

}
