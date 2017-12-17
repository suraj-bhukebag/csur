package com.cmpe275.project.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cmpe275.project.model.TrainSchedule;

public interface SearchRepository extends CrudRepository<TrainSchedule, Long> {
	
	@Query(value = "SELECT DISTINCT train_id FROM train_schedule where exists(select * from train_schedule where station_id= ?1) and exists(select * from train_schedule where station_id= ?2) and deptime >= ?3", nativeQuery = true)
	public List<BigInteger> findTrainsByFromToDepTimeGt(@Param("station_id") long from, @Param("station_id") long to, @Param("deptime") long deptime);
	
	
	@Query(value = "SELECT DISTINCT train_id FROM train_schedule where exists(select * from train_schedule where station_id= ?1) and exists(select * from train_schedule where station_id= ?2) and deptime = ?3", nativeQuery = true)
	public List<BigInteger> findTrainsByFromToDepTimeEq(@Param("station_id") long from, @Param("station_id") long to, @Param("deptime") long deptime);
	
	@Query(value = "SELECT * FROM train_schedule where train_id= ?1 and station_id= ?2", nativeQuery = true)
	public TrainSchedule findTimeByTrainAndStation(@Param("train_id") long trainId, @Param("station_id") long stationId);
}
