package com.cmpe275.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cmpe275.project.mapper.SearchCriteria;
import com.cmpe275.project.mapper.SearchResults;
import com.cmpe275.project.services.SearchService;

@RestController
public class SearchController {

	@Autowired
	private SearchService searchService;

	@GetMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> searchTrains(
			@RequestParam(value = "noOfPassengers", required = false) Integer noOfPassengers,
			@RequestParam(value = "departureTime", required = false) String departureTime,
			@RequestParam(value = "depDate", required = false) String depDate,
			@RequestParam(value = "from", required = false) String from,
			@RequestParam(value = "to", required = false) String to,
			@RequestParam(value = "trainType", required = false) String trainType,
			@RequestParam(value = "noOfConnections", required = false) Integer noOfConnections,
			@RequestParam(value = "roundTrip", required = false) String roundTrip,
			@RequestParam(value = "isExact", required = false) boolean isExact) {

		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setDepDate(Long.valueOf(depDate));
		searchCriteria.setExact(isExact);
		searchCriteria.setNoOfPassengers(noOfPassengers);
		searchCriteria.setNoOfConnections(noOfConnections);
		searchCriteria.setDepartureTime(Long.valueOf(departureTime));
		searchCriteria.setFrom(from);
		searchCriteria.setTo(to);
		searchCriteria.setTrainType(trainType);
		searchCriteria.setRoundTrip(roundTrip);

		List<SearchResults> results = searchService
				.searchTrains(searchCriteria);

		return new ResponseEntity(results, HttpStatus.OK);
	}

}
