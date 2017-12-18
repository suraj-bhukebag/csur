package com.cmpe275.project.dao;

import com.cmpe275.project.model.Ticket;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TicketingRepository extends CrudRepository<Ticket,Long> {


    List<Ticket> findAllByBookedBy(Long bookedBy);

}
