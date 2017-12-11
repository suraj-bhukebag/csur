package com.cmpe275.project.CSUR.controller;

import com.cmpe275.project.CSUR.model.TicketDetails;
import com.cmpe275.project.CSUR.services.TicketingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TicketingController {

    @Autowired
    private TicketingService ticketingService ;

    @GetMapping(value ="/")
    @ResponseBody
    public
    String main(){
        return "275 final Project";
    }

    @PostMapping(value = "/booked")
    public ResponseEntity<TicketDetails> bookTcikets(@RequestBody TicketDetails ticketDetails)

    {
        System.out.println("Printing to Console");
        System.out.println(ticketDetails.getArrivalTime());
        ticketingService.bookTicket(ticketDetails);
        return new ResponseEntity<TicketDetails>(ticketDetails, HttpStatus.OK);
    }

    @PostMapping(value = "/cancel")
    public ResponseEntity<Long> cancleTickets(@RequestBody Long tickeID)

    {
        System.out.println("Printing to Console");
        System.out.println(tickeID);
        ticketingService.cancelTicket(tickeID);
        return new ResponseEntity<Long>(tickeID, HttpStatus.OK);
    }
}
