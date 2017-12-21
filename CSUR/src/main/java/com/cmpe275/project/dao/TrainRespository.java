package com.cmpe275.project.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cmpe275.project.model.Train;

public interface TrainRespository extends CrudRepository<Train,Long> {
 
	@Query(value = "select rt.availablecount from  ticketdetails td,running_trains rt  where  td.train_id = rt.train_id  and td.train_id = ?1", nativeQuery = true)
	public Long getCapacity(@Param("train_id") long train_id);
			
}
