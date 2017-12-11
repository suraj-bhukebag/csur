package com.cmpe275.project.CSUR.services;

import com.cmpe275.project.CSUR.dao.TicketingRepository;
import com.cmpe275.project.CSUR.model.TicketDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TicketingService implements Ticketing {
    @Autowired
    private TicketingRepository ticketingRepository ;


    @Override
    public void bookTicket(TicketDetails ticketDetails) {

        TicketDetails ticket = ticketingRepository.save(ticketDetails);
    }

    @Override
    public void cancelTicket(long ticketId) {

        TicketDetails ticket = ticketingRepository.findOne(ticketId);

        ticketingRepository.delete(ticketId);

    }
}
