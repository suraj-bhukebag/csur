package com.cmpe275.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cmpe275.project.model.SearchStatistics;

public interface SearchStatisticsRepository extends CrudRepository<SearchStatistics, Long>{
	
	@Query(value = "SELECT date, SUM(noofsearchrequests) as noofsearchrequests "
			+ "FROM search_statistics "
			+ "WHERE date = ?1 "
			+ "GROUP BY date "
			+ "ORDER BY date", nativeQuery=true)
	public List<Object[]> findNoOfSearches(@Param("date") long date);
	
	@Query(value = "SELECT ss.typeofsearch, ROUND((SUM(ss.noofsearchrequests)/t.cnt) * 100,2) as Percentage "
			+ "FROM search_statistics ss "
			+ "INNER JOIN "
			+ "(SELECT date, SUM(noofsearchrequests) AS cnt FROM search_statistics GROUP BY date) t "
			+ "ON ss.date = t.date "
			+ "WHERE ss.date = ?1 "
			+ "GROUP BY ss.date,ss.typeofsearch "
			+ "ORDER BY ss.date,ss.typeofsearch ", nativeQuery=true)
	public List<Object[]> findConnectionPercentage(@Param("date") long date);
	
	@Query(value = "SELECT ss.typeofsearch, ROUND(avg(timeofresponse-timeofrequest),2) as Latency "
			+ "FROM search_statistics ss "
			+ "WHERE date = ?1 "
			+ "GROUP BY ss.date,ss.typeofsearch "
			+ "ORDER BY ss.date,ss.typeofsearch ", nativeQuery=true)
	public List<Object[]> findConnectionLatency(@Param("date") long date);

	
}
