package com.cmpe275.project.dao;

import com.cmpe275.project.model.TicketDetails;

<<<<<<< HEAD:CSUR/src/main/java/com/cmpe275/project/CSUR/dao/TicketingRepository.java
import com.cmpe275.project.CSUR.model.Ticket;
=======
>>>>>>> bc6bea80a13075d8d8e6b082275692de1cf22887:CSUR/src/main/java/com/cmpe275/project/dao/TicketingRepository.java
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TicketingRepository extends CrudRepository<Ticket,Long> {


    List<Ticket> findAllByBookedBy(Long bookedBy);

}
