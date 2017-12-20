package com.cmpe275.project.dao;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.cmpe275.project.model.Ticket;

public interface TicketingRepository extends CrudRepository<Ticket,Long> {


    List<Ticket> findAllByBookedBy(Long bookedBy);
    
    
    @Query(value = "SELECT t.id FROM ticket t,ticketdetails td WHERE  td.ticket_id = t.id and td.train_id =?1 and t.travellingdate = ?2  ", nativeQuery = true)
	public  List<BigInteger>  findAllByTrainId(@Param("train_id") long train_id,@Param("travellingdate") long travellingdate);
	
    @Query(value = "select td.depttime from ticket t, ticketdetails td where t.id = td.ticket_id and t.source = td.fromstation and t.id = ?1 ", nativeQuery = true)
 	public  String findDepTime(@Param("id") long id);

}
