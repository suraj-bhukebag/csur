package com.cmpe275.project.CSUR.dao;

import com.cmpe275.project.CSUR.model.TicketDetails;
import org.springframework.data.repository.CrudRepository;

public interface TicketingRepository extends CrudRepository<TicketDetails ,Long> {


}
