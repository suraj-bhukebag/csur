package com.cmpe275.project.dao;



import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cmpe275.project.model.TicketDetails;

public interface TicketDetailsRepository extends CrudRepository<TicketDetails, Long> {

    List<TicketDetails> findAllByTicketId(Long TicketId);
    
    @Query(value = "select td.depttime from ticketdetails td, ticket t where t.id = td.ticket_id and td.fromstation = t.source and t.id =?1", nativeQuery = true)
	public String findDepTime(@Param("id") long id);
    
    @Query(value = "select td.train_id from ticketdetails td, ticket t where t.id = td.ticket_id and td.fromstation = t.source and t.id =?1", nativeQuery = true)
   	public long findTrainId(@Param("id") long id);
    
}



