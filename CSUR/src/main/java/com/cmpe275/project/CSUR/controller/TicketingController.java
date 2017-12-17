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


    @GetMapping(value = "/{userId}/booked")
    public ResponseEntity getTickets(@PathVariable("userId") String userId)
    {
        return new ResponseEntity(ticketingService.getTickets(userId), HttpStatus.OK);
    }


    @PostMapping(value = "/{userId}/booked")
    public ResponseEntity <String> bookTcikets(@RequestBody TicketDetails ticketDetails)

    {
        System.out.println("Printing to Console");
        //System.out.println(ticketDetails.getArrivalTime());
        ticketingService.bookTicket(ticketDetails);
        return new ResponseEntity<String>("Booked Successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/{userId}/cancel/{tickeID}")
    public ResponseEntity<String> cancleTickets(@PathVariable("userId") long tickeID)

    {
        System.out.println("Printing to Console");
        System.out.println(tickeID);

        if(ticketingService.cancelTicket(tickeID))
            return new ResponseEntity<String>("Cancelled Successfully", HttpStatus.OK);
        else
            return new ResponseEntity<String>("Ticket Cannot be Cancelled", HttpStatus.BAD_REQUEST);

    }
}
