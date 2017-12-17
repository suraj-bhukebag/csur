package com.cmpe275.project.CSUR.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmpe275.project.CSUR.dao.TicketingRepository;
import com.cmpe275.project.CSUR.model.TicketDetails;


@Service
public class TicketingService implements Ticketing {
    @Autowired
    private TicketingRepository ticketingRepository ;


    public void bookTicket(TicketDetails ticketDetails) {

        TicketDetails ticket = ticketingRepository.save(ticketDetails);
    }


    public boolean validateTicket(long ticketId)
    {

//        TicketDetails ticket = ticketingRepository.findOne(ticketId);
//        DateTime ticketTime = DateTime.parse(ticket.getDeptTime(),
//                DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss"));
//
//        DateTime currentTime = new DateTime();
//        Period p = new Period(currentTime, ticketTime);
//        int minutes = p.getMinutes();
//        if( minutes > 60)
//            return true;
//        return false ;
         return true;
    }

    public boolean cancelTicket(long ticketId) {
        if(validateTicket(ticketId)) {
            ticketingRepository.delete(ticketId);
            return true;
        }
        return false ;


    }

//    public List<TicketDetails> getTickets(String userId) {
//        System.out.println(ticketingRepository.findAllByUserId(userId));
//        return ticketingRepository.findAllByUserId(userId);
//    }

}
