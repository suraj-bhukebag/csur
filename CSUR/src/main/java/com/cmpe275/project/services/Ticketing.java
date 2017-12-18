package com.cmpe275.project.services;

<<<<<<< HEAD:CSUR/src/main/java/com/cmpe275/project/CSUR/services/Ticketing.java
import com.cmpe275.project.CSUR.mapper.TicketMapper;
import com.cmpe275.project.CSUR.model.TicketDetails;
=======
import com.cmpe275.project.model.TicketDetails;
>>>>>>> bc6bea80a13075d8d8e6b082275692de1cf22887:CSUR/src/main/java/com/cmpe275/project/services/Ticketing.java

import java.util.List;

public interface Ticketing {

    void bookTicket(TicketMapper ticketMapper);
    void bookTicketDetails(TicketMapper ticketMapper);
    void runningTrain(TicketMapper ticketMapper);
    void travellerDetails(TicketMapper ticketMapper);

    List<TicketMapper> getTickets(long userId);

    boolean cancelTicket(long ticketId) ;

   // List<TicketDetails> getTickets(String userId);

}
