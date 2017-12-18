package com.cmpe275.project.CSUR.dao;

import com.cmpe275.project.CSUR.model.TicketDetails;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TicketDetailsRepository extends CrudRepository<TicketDetails , Long> {

    List<TicketDetails> findAllByTicketId(Long TicketId);
}
