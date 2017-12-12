package com.cmpe275.project.CSUR.services;

import com.cmpe275.project.CSUR.model.TicketDetails;

import java.util.List;

public interface Ticketing {

    void bookTicket(TicketDetails ticketDetails);

    boolean cancelTicket(long ticketId) ;

    List<TicketDetails> getTickets(String userId);

}
