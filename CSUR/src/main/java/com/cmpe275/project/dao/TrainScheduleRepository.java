package com.cmpe275.project.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cmpe275.project.model.TrainSchedule;

public interface TrainScheduleRepository extends CrudRepository<TrainSchedule, Long> {

	@Query(value = "SELECT * FROM train_schedule WHERE train_id = ?1 and station_id = ?2", nativeQuery = true)
	TrainSchedule findScheduleTimeAtFirstStationById(@Param("train_id") long id,
			@Param("station_id") int stationId);

}
