package com.cmpe275.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cmpe275.project.mapper.TrainCancelRequest;
import com.cmpe275.project.services.TrainCancellationService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TrainCancellationController {
	
	
	@Autowired
	 TrainCancellationService trainCancellationService;
	
	@RequestMapping(path = "/traincancel",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<String> cancleTickets(@RequestBody TrainCancelRequest trainCancelRequest)

	    {
	        System.out.println("Printing to Console");
	        System.out.println(trainCancelRequest.getCancelDate());

	        if(trainCancellationService.cancelTrain(trainCancelRequest.getTrain_id(), trainCancelRequest.getCancelDate()))
	            return new ResponseEntity<String>("Cancelled Successfully", HttpStatus.OK);
	        else
	            return new ResponseEntity<String>("Ticket Cannot be Cancelled", HttpStatus.BAD_REQUEST);

	    }

}
