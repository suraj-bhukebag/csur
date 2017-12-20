package com.cmpe275.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmpe275.project.mapper.TicketMapper;
import com.cmpe275.project.mapper.TicketResponse;
import com.cmpe275.project.mapper.UserTicketsResponse;
import com.cmpe275.project.model.Ticket;
import com.cmpe275.project.services.TicketingService;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
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
    public ResponseEntity getTickets(@PathVariable("userId") Long userId)
    {
    	UserTicketsResponse userTicketsResponse = new UserTicketsResponse();
    	userTicketsResponse.setTickets(ticketingService.getTickets(userId));
    	userTicketsResponse.setCode(200);
    	userTicketsResponse.setMsg("User Tickets");
        return new ResponseEntity(userTicketsResponse, HttpStatus.OK);
    }



    @PostMapping(value = "/{userId}/booked")
    public ResponseEntity <String> bookTcikets(@RequestBody TicketMapper ticketmapper)

    {
        System.out.println("Printing to Console");
        //System.out.println(ticketDetails.getArrivalTime());
        Ticket ticket = ticketingService.bookTicket(ticketmapper);
        ticketingService.bookTicketDetails(ticketmapper);
        ticketingService.travellerDetails(ticketmapper);
        ticketingService.runningTrain(ticketmapper);
        TicketResponse ticketResponse = new TicketResponse();
        ticketResponse.setTicket(ticket);
        ticketResponse.setCode(200);
        ticketResponse.setMsg("Booked Ticket Successfully");
        return new ResponseEntity(ticketResponse, HttpStatus.OK);
    }

    @PostMapping(value = "cancel/{ticketID}/{today}")
    public ResponseEntity<String> cancleTickets(@PathVariable("ticketID") long tickeID,@PathVariable("today") long today)

    {
        System.out.println("Printing to Console");
        System.out.println(tickeID);
        
        if(ticketingService.cancelTicket(tickeID,today))
            return new ResponseEntity<String>("Cancelled Successfully", HttpStatus.OK);
        else
            return new ResponseEntity<String>("Ticket Cannot be Cancelled", HttpStatus.BAD_REQUEST);

    }



    /*@PostMapping(value = "/reset/{capacity}")
    public ResponseEntity<String> resetSystem(@PathVariable("capacity") long capacity)
    {
         ticketingService.resetSystem(capacity);
        return new ResponseEntity<String>("System is Reset with New Capacity" ,HttpStatus.OK);
    }*/
}
