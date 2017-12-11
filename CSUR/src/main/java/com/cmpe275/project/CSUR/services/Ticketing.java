package com.cmpe275.project.CSUR.services;

import com.cmpe275.project.CSUR.model.TicketDetails;

public interface Ticketing {

    void bookTicket(TicketDetails ticketDetails);

    void cancelTicket(long ticketId) ;

}
