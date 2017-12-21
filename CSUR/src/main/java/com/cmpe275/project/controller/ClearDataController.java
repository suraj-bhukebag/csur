package com.cmpe275.project.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cmpe275.project.dao.RunningTrainRepository;
import com.cmpe275.project.dao.SearchStatisticsRepository;
import com.cmpe275.project.dao.TrainDao;
import com.cmpe275.project.dao.TrainSegmentOccupancyRepository;
import com.cmpe275.project.mapper.ClearDataResponse;
import com.cmpe275.project.model.ClearData;
import com.cmpe275.project.model.Train;
import com.cmpe275.project.services.TicketingService;
import com.cmpe275.project.services.UserService;

@RestController
@RequestMapping("/clear")
@CrossOrigin(origins = "http://localhost:3000")
public class ClearDataController {

	@Autowired
	UserService userService;
	@Autowired
	TicketingService ticketingService;
	
	@Autowired
	TrainDao trainDao;
	
	@Autowired
	SearchStatisticsRepository searchStatisticsRepository;
	
	@Autowired
	TrainSegmentOccupancyRepository trainSegmentOccupancyRepository;
	
	@Autowired
	RunningTrainRepository runningTrainRepository;

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>  clearData(@RequestBody ClearData clearData)
    {
       ClearDataResponse response  = new ClearDataResponse();
       StringBuilder responseString = new StringBuilder();
       ResponseEntity res = null;
       HttpStatus httpStatus = null;
        
    	   userService.clearUserTable();  
    	   responseString.append("User Data is Cleaned.\n ");
    	   
    	   ticketingService.clearTicketDetailsTable();
    	   responseString.append(" Ticket Deails is Cleaned.\n ");
    	   
    	   ticketingService.clearTicketingTable();
    	   responseString.append(" Ticket Data is Cleaned.\n ");
    	   
    	   ticketingService.clearTravellersTable();
    	   responseString.append(" Travellers Details Data is Cleaned.\n ");
    	   
    	   searchStatisticsRepository.deleteAll();
    	   responseString.append(" Search Statistic Data is Cleaned.\n ");
    	   
    	   trainSegmentOccupancyRepository.deleteAll();
    	   responseString.append(" Train Segment Occupancy Data is Cleaned.\n ");    
    	   
    	   runningTrainRepository.deleteAll();
    	   responseString.append(" Running Train Data is Cleaned.\n ");
    	   
    	   Iterator<Train> trains = trainDao.findAll().iterator();
    	   while(trains.hasNext()) {
    		   Train t = trains.next();
    		   t.setCapacity(clearData.getCapacity());
    		   trainDao.save(t);
    	   }
    	   
    	   responseString.append(" Train Capacity Updated.\n ");
    	   httpStatus = HttpStatus.OK;
     
      response.setMsg(responseString.toString()); 
      return new ResponseEntity(response, httpStatus);
    	
    }


	
	
}
