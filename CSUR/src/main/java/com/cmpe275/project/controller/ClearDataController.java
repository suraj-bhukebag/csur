package com.cmpe275.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.cmpe275.project.mapper.ClearDataResponse;
import com.cmpe275.project.model.ClearData;
import com.cmpe275.project.services.UserService;
import com.cmpe275.project.services.TicketingService;

@RestController
@RequestMapping("/clear")
public class ClearDataController {

	@Autowired
	UserService userService;
	@Autowired
	TicketingService ticketingService;

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>  clearData(@RequestBody ClearData clearData)
    {
       ClearDataResponse response  = new ClearDataResponse();
       StringBuilder responseString = new StringBuilder();
       ResponseEntity res = null;
       HttpStatus httpStatus = null;
        
    	   userService.clearUserTable();  
    	   responseString.append("User Data is Cleaned ");
    	   
    	   ticketingService.clearTicketDetailsTable();
    	   responseString.append(" Ticket Deails is Cleaned ");
    	   
    	   ticketingService.clearTicketingTable();
    	   responseString.append(" Ticket Data is Cleaned ");
    	   
    	   ticketingService.clearTravellersTable();
    	   responseString.append(" Travellers Details Data is Cleaned ");
    	   
    	   httpStatus = HttpStatus.OK;
     
      response.setMessage(responseString.toString()); 
      return new ResponseEntity(response, httpStatus);
    	
    }


	
	
}
