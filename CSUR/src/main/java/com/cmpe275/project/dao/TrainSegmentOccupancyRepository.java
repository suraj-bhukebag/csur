package com.cmpe275.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cmpe275.project.model.TrainSegmentOccupancy;

public interface TrainSegmentOccupancyRepository extends CrudRepository<TrainSegmentOccupancy, Long> {

	@Query(value = "SELECT date, t.trainnumber  as train, "
			+ "ROUND(SUM(segmentoccupancyrate)/25,2) as segmentoccupancyrate "
			+ "FROM train_segment_occupancy tso "
			+ "INNER JOIN train t "
			+ "ON tso.trainid = t.id "
			+ "GROUP BY tso.date, tso.trainid "
			+ "ORDER BY tso.date, tso.trainid", nativeQuery=true)
	public List<Object[]> findTrainReservationRate();
}
