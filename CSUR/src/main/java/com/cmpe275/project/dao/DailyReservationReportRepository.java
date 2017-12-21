package com.cmpe275.project.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cmpe275.project.mapper.TrainReservationRate;
import com.cmpe275.project.model.TrainReservationReport;
import com.cmpe275.project.model.TrainSegmentOccupancy;

public interface DailyReservationReportRepository extends CrudRepository<TrainReservationReport, Long>{

	@Query(value = "SELECT date, avg(trainreservationrate) "
			+ "FROM train_reservation_report "
			+ "GROUP BY date "
			+ "ORDER BY date", nativeQuery=true)
	public List<Object[]> findDailyReservationRate();
	
}
