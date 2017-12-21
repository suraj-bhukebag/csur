package com.cmpe275.project.controller;


import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cmpe275.project.mapper.SearchCriteria;
import com.cmpe275.project.mapper.TrainSearchResponse;
import com.cmpe275.project.services.SearchService;
import com.cmpe275.project.services.SystemReportService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	@Autowired
	private SystemReportService systemReportService;

	@GetMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> searchTrains(
			@RequestParam(value = "noOfPassengers", required = false) Integer noOfPassengers,
			@RequestParam(value = "departureTime", required = false) String departureTime,
			@RequestParam(value = "depDate", required = false) String depDate,
			@RequestParam(value = "returnDepartureTime", required = false) String returnDepartureTime,
			@RequestParam(value = "returnDepDate", required = false) String returnDepDate,
			@RequestParam(value = "from", required = false) String from,
			@RequestParam(value = "to", required = false) String to,
			@RequestParam(value = "trainType", required = false) String trainType,
			@RequestParam(value = "noOfConnections", required = false) Integer noOfConnections,
			@RequestParam(value = "isRoundTrip", required = false) Boolean isRoundTrip,
			@RequestParam(value = "isExact", required = false) Boolean isExact) {

		long startTimeInMillis = Calendar.getInstance().get(Calendar.MILLISECOND);
		
		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setDepDate(Long.valueOf(depDate));
		if(returnDepDate != null) {
			searchCriteria.setReturnDepDate(Long.valueOf(returnDepDate));
		}
		if(returnDepartureTime != null) {
			searchCriteria.setReturnDepartureTime(returnDepartureTime);
		}
		if(isRoundTrip != null) {
			searchCriteria.setRoundtrip(isRoundTrip);
		}
		searchCriteria.setExact(isExact);
		searchCriteria.setNoOfPassengers(noOfPassengers);
		searchCriteria.setNoOfConnections(noOfConnections);
		searchCriteria.setDepartureTime(departureTime);
		searchCriteria.setFrom(from);
		searchCriteria.setTo(to);
		searchCriteria.setTrainType(trainType);


		TrainSearchResponse results = searchService
				.searchTrains(searchCriteria);
		
		// End of search request processing and sending a response.
		long endTimeInMillis = Calendar.getInstance().get(Calendar.MILLISECOND);
		long latency = endTimeInMillis - startTimeInMillis;
		
		systemReportService.insertNewSearchStatistics(startTimeInMillis, endTimeInMillis, 
				latency, noOfConnections);

		return new ResponseEntity(results, HttpStatus.OK);
	}

}
