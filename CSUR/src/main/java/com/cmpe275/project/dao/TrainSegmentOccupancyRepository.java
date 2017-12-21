package com.cmpe275.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

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
	
	@Query(value = "SELECT date, tso.trainid "
			+ "FROM train_segment_occupancy tso "
			+ "WHERE tso.trainid = ?1 AND tso.date = ?2 "
			+ "GROUP BY tso.date, tso.trainid "
			+ "ORDER BY tso.date, tso.trainid", nativeQuery=true)
	public List<Object[]> checkIfTrainExists(@Param("trainid") long trainid, @Param("date") long date);
	
	@Query(value = "SELECT ROUND(SUM(segmentoccupancyrate)/25,2) as segmentoccupancyrate  "
			+ "FROM train_segment_occupancy tso "
			+ "WHERE tso.trainid = ?1 AND tso.date = ?2 "
			+ "GROUP BY tso.date, tso.trainid "
			+ "ORDER BY tso.date, tso.trainid", nativeQuery=true)
	public Double fetchTrainReservationRate(@Param("trainid") long trainid, @Param("date") long date);
	
	@Query(value = "SELECT * "
			+ "FROM train_segment_occupancy tso "
			+ "WHERE tso.trainid = ?1 AND tso.date = ?2 AND tso.segmentid = ?3", nativeQuery=true)
	public TrainSegmentOccupancy getIdForUpdate(@Param("trainid") long trainid, @Param("date") long date, @Param("segmentid") long segmentid);
}
