package com.cmpe275.project.CSUR.services;

import com.cmpe275.project.CSUR.mapper.TicketMapper;
import com.cmpe275.project.CSUR.model.TicketDetails;

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
