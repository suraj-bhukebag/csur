package com.cmpe275.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cmpe275.project.mapper.DailyReservationReportResponse;
import com.cmpe275.project.mapper.DailySearchCountResponse;
import com.cmpe275.project.mapper.DailySearchLatencyResponse;
import com.cmpe275.project.mapper.DailySearchPercentageResponse;
import com.cmpe275.project.mapper.TrainReservationReportResponse;
import com.cmpe275.project.services.SystemReportService;

@Controller
public class SystemReportController {

	@Autowired
	private SystemReportService systemReportService;
	
	@GetMapping(path = "/trainReservationReport", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> trainReservationReport(){
		
		TrainReservationReportResponse results = systemReportService.getTrainReservationReport();

		return new ResponseEntity(results, HttpStatus.OK);
	}
	
	@GetMapping(path = "/dailyReservationReport", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> dailyReservationReport(){
		
		DailyReservationReportResponse results = systemReportService.getDailyReservationReport();

		return new ResponseEntity(results, HttpStatus.OK);
	}
	
	@GetMapping(path = "/dailySearchCountReport", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> dailySearchCountReport(
			@RequestParam(value = "date", required = false) Long date){
		
		DailySearchCountResponse results = systemReportService.getDailySearchCountReport(date);

		return new ResponseEntity(results, HttpStatus.OK);
	}
	
	@GetMapping(path = "/dailySearchPercentageReport", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> dailySearchPercentageReport(
			@RequestParam(value = "date", required = false) Long date){
		
		DailySearchPercentageResponse results = systemReportService.getDailySearchPercentageReport(date);

		return new ResponseEntity(results, HttpStatus.OK);
	}
	
	@GetMapping(path = "/dailySearchLatencyReport", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> dailySearchLatencyReport(
			@RequestParam(value = "date", required = false) Long date){
		
		DailySearchLatencyResponse results = systemReportService.getDailySearchLatencyReport(date);
		return new ResponseEntity(results, HttpStatus.OK);
	}
}
