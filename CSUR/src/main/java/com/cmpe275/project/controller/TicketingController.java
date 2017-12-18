package com.cmpe275.project.controller;

import com.cmpe275.project.model.TicketDetails;
import com.cmpe275.project.services.TicketingService;

<<<<<<< HEAD:CSUR/src/main/java/com/cmpe275/project/CSUR/controller/TicketingController.java

import com.cmpe275.project.CSUR.mapper.TicketMapper;
import com.cmpe275.project.CSUR.model.TicketDetails;
import com.cmpe275.project.CSUR.services.TicketingService;
=======
>>>>>>> bc6bea80a13075d8d8e6b082275692de1cf22887:CSUR/src/main/java/com/cmpe275/project/controller/TicketingController.java
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
    public ResponseEntity getTickets(@PathVariable("userId") Long userId)
    {
        return new ResponseEntity(ticketingService.getTickets(userId), HttpStatus.OK);
    }



    @PostMapping(value = "/{userId}/booked")
    public ResponseEntity <String> bookTcikets(@RequestBody TicketMapper ticketmapper)

    {
        System.out.println("Printing to Console");
        //System.out.println(ticketDetails.getArrivalTime());
        ticketingService.bookTicket(ticketmapper);
        ticketingService.bookTicketDetails(ticketmapper);
        ticketingService.travellerDetails(ticketmapper);
        ticketingService.runningTrain(ticketmapper);
        return new ResponseEntity(ticketingService.getTickets(ticketmapper.getBookedBy()), HttpStatus.OK);
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



    @PostMapping(value = "/reset/{capacity}")
    public ResponseEntity<String> resetSystem(@PathVariable("capacity") long capacity)
    {
         ticketingService.resetSystem(capacity);
        return new ResponseEntity<String>("System is Reset with New Capacity" ,HttpStatus.OK);
    }
}
