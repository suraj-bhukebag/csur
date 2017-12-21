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

import com.cmpe275.project.dao.StationDao;
import com.cmpe275.project.dao.TrainRespository;
import com.cmpe275.project.mapper.GenericResponse;
import com.cmpe275.project.mapper.TicketMapper;
import com.cmpe275.project.mapper.TicketResponse;
import com.cmpe275.project.mapper.UserTicketsResponse;
import com.cmpe275.project.model.Ticket;
import com.cmpe275.project.model.Train;
import com.cmpe275.project.services.EmailService;
import com.cmpe275.project.services.TicketingService;
import com.cmpe275.project.services.UserService;

@Controller
//@CrossOrigin(origins = "http://localhost:3000")
public class TicketingController {

    @Autowired
    private TicketingService ticketingService ;
    @Autowired
	private EmailService emailService;
	
	@Autowired
	private UserService userService;
	@Autowired
	StationDao stationRepository;
	
	@Autowired
	TrainRespository trainRepository;
	
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
        
        long trainid = ticketmapper.getTicketDetailMapper().get(0).getTrainId();
        Ticket ticket = ticketingService.bookTicket(ticketmapper);
       long capacity= trainRepository.getCapacity(trainid);
       System.out.println("Capacity"+ capacity);
        long noofPassenger =  ticket.getNumberofpassengers();
        TicketResponse ticketResponse = new TicketResponse();
        if(noofPassenger >capacity){
            ticketResponse.setCode(200);
            ticketResponse.setMsg("Ticket Can Not Be Booked, Train is already full");
        	
        }else{
        ticketingService.bookTicketDetails(ticketmapper);
        ticketingService.travellerDetails(ticketmapper);
        ticketingService.runningTrain(ticketmapper);
        ticketResponse = new TicketResponse();
        ticketResponse.setTicket(ticket);
        ticketResponse.setCode(200);
        ticketResponse.setMsg("Booked Ticket Successfully");
        String Subject = "Your Ticket "+ ticket.getId()+" from "+ stationRepository.findStationIdByName(ticket.getSource())+ " to " 
				+ stationRepository.findStationIdByName(ticket.getSource())+ " of " + ticket.getNumberofpassengers() + " is Booked";
				String emailId = userService.getUser(ticket.getBookedBy()+"").getEmail();
				
		emailService.sendMail(emailId, "Ticket Booking Notice", Subject);
        }
        return new ResponseEntity(ticketResponse, HttpStatus.OK);
    }


    @PostMapping(value = "cancel/{ticketID}/{today}")
    public ResponseEntity<String> cancleTickets(@PathVariable("ticketID") long tickeID,@PathVariable("today") long today)
    {


        GenericResponse gr = new GenericResponse();
        
        if(ticketingService.cancelTicket(tickeID, today)){
        	gr.setCode(200);
        	gr.setMsg("Cancelled Successfully");
            return new ResponseEntity(gr, HttpStatus.OK);            
        }
        else {
          	gr.setCode(500);
        	gr.setMsg("Ticket Cannot be Cancelled");
            return new ResponseEntity(gr, HttpStatus.OK);   
        }

    }



    /*@PostMapping(value = "/reset/{capacity}")
    public ResponseEntity<String> resetSystem(@PathVariable("capacity") long capacity)
    {
         ticketingService.resetSystem(capacity);
        return new ResponseEntity<String>("System is Reset with New Capacity" ,HttpStatus.OK);
    }*/
}
