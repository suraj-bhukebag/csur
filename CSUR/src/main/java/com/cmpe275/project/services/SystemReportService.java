package com.cmpe275.project.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmpe275.project.dao.SearchStatisticsRepository;
import com.cmpe275.project.dao.StationDao;
import com.cmpe275.project.dao.DailyReservationReportRepository;
import com.cmpe275.project.dao.TrainSegmentOccupancyRepository;
import com.cmpe275.project.mapper.DailyReservationRate;
import com.cmpe275.project.mapper.DailyReservationReportResponse;
import com.cmpe275.project.mapper.DailySearchCount;
import com.cmpe275.project.mapper.DailySearchCountResponse;
import com.cmpe275.project.mapper.DailySearchLatency;
import com.cmpe275.project.mapper.DailySearchLatencyResponse;
import com.cmpe275.project.mapper.DailySearchPercentage;
import com.cmpe275.project.mapper.DailySearchPercentageResponse;
import com.cmpe275.project.mapper.TrainReservationRate;
import com.cmpe275.project.mapper.TrainReservationReportResponse;
import com.cmpe275.project.model.SearchStatistics;
import com.cmpe275.project.model.TrainReservationReport;
import com.cmpe275.project.model.TrainSegmentOccupancy;

@Service
public class SystemReportService {
	
	@Autowired
	private SearchStatisticsRepository searchStatisticsRepository;
	
	@Autowired
	private DailyReservationReportRepository dailyReservationReportRepository;
	
	@Autowired
	private TrainSegmentOccupancyRepository trainSegmentOccupancyRepository;
	
	@Autowired
	private StationDao stationDao;
	
	public TrainReservationReportResponse getTrainReservationReport(){
		
		TrainReservationReportResponse trainReservationReportResponse = new TrainReservationReportResponse();
		List<TrainReservationRate> trainReservationRate = new ArrayList<TrainReservationRate>();
		
		List<Object[]> objects = new ArrayList<Object[]>();
		objects = trainSegmentOccupancyRepository.findTrainReservationRate();
		
		SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");

		if (objects.size()==0) {
			for(Object[] r : objects) {
				TrainReservationRate t = new TrainReservationRate();
				t.setDate(df.format(Long.valueOf(r[0].toString())));
				t.setTrain(String.valueOf(r[1]));
				t.setReservationRate(Double.valueOf(r[2].toString()));
				trainReservationRate.add(t);
				
				// Set the HTTP status code and msg
				trainReservationReportResponse.setCode(200);
				trainReservationReportResponse.setMsg("Per Train Reservation Rates:");
			}
		}else{
			// Set the HTTP status code and msg
			trainReservationReportResponse.setCode(200);
			trainReservationReportResponse.setMsg("Per Train Reservation Rates:");
		}
		
		
		trainReservationReportResponse.setTrainReservationRate(trainReservationRate);
		return trainReservationReportResponse;
		
	}

	public DailyReservationReportResponse getDailyReservationReport(){
		
		DailyReservationReportResponse dailyReservationReportResponse = new DailyReservationReportResponse();
		
		List<DailyReservationRate> dailyReservationRates = new ArrayList<DailyReservationRate>();
		
		List<Object[]> objects = new ArrayList<Object[]>();
		objects = dailyReservationReportRepository.findDailyReservationRate();
		
		SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
		for(Object[] r : objects) {
			DailyReservationRate t = new DailyReservationRate();
			t.setDate(df.format(Long.valueOf(r[0].toString())));
			t.setReservationRate(Double.valueOf(r[1].toString()));
			dailyReservationRates.add(t);
//			System.out.println(r[0]);
//			System.out.println(r[1]);
		}
		
		dailyReservationReportResponse.setDailyReservationRates(dailyReservationRates);
		return dailyReservationReportResponse;
		
	}
	
	public DailySearchCountResponse getDailySearchCountReport(Long date){
		
		DailySearchCountResponse dailySearchCountResponse = new DailySearchCountResponse();
		
		List<DailySearchCount> dailySearchCounts = new ArrayList<DailySearchCount>();
		
		List<Object[]> objects = new ArrayList<Object[]>();
		objects = searchStatisticsRepository.findNoOfSearches(date);
		
		SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
		for(Object[] r : objects) {
			DailySearchCount t = new DailySearchCount();
			t.setDate(df.format(Long.valueOf(r[0].toString())));
			t.setNoOfSearchRequests(Integer.valueOf(r[1].toString()));
			dailySearchCounts.add(t);
		}
		
		dailySearchCountResponse.setDailySearchCounts(dailySearchCounts);
		
		return dailySearchCountResponse;
		
	}
	
	public DailySearchPercentageResponse getDailySearchPercentageReport(Long date){
		
		DailySearchPercentageResponse dailySearchPercentageResponse = new DailySearchPercentageResponse();
		
		List<DailySearchPercentage> dailySearchPercentages = new ArrayList<DailySearchPercentage>();
		
		List<Object[]> objects = new ArrayList<Object[]>();
		objects = searchStatisticsRepository.findConnectionPercentage(date);
		
		SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
		for(Object[] r : objects) {
			DailySearchPercentage t = new DailySearchPercentage();
			t.setTypeOfSearch(r[0].toString());
			t.setConnectionPercentage(Double.valueOf(r[1].toString()));
			dailySearchPercentages.add(t);
		}
		
		dailySearchPercentageResponse.setDailySearchPercentages(dailySearchPercentages);
		
		return dailySearchPercentageResponse;
		
	}
	
	public DailySearchLatencyResponse getDailySearchLatencyReport(Long date){
		
		DailySearchLatencyResponse dailySearchLatencyResponse = new DailySearchLatencyResponse();
		
		List<DailySearchLatency> dailySearchLatencies = new ArrayList<DailySearchLatency>();
		
		List<Object[]> objects = new ArrayList<Object[]>();
		objects = searchStatisticsRepository.findConnectionLatency(date);
		
		SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
		for(Object[] r : objects) {
			DailySearchLatency t = new DailySearchLatency();
			t.setTypeOfSearch(r[0].toString());
			t.setLatency(Double.valueOf(r[1].toString()));
			dailySearchLatencies.add(t);
		}
		
		dailySearchLatencyResponse.setDailySearchLatencies(dailySearchLatencies);
		
		return dailySearchLatencyResponse;
		
	}
	
	// Method to insert rows into the Search_Statistics table
	public void insertNewSearchStatistics(long st, long et, long latency,long noOfConnections ){
		
		SearchStatistics searchStatistics = new SearchStatistics();
		Date date = new Date();
		long todaysDate = date.getTime();
		searchStatistics.setDate(todaysDate);
		searchStatistics.setTimeofrequest(st);
		searchStatistics.setTimeofresponse(et);
		searchStatistics.setLatency(latency);
		searchStatistics.setNoofsearchrequests(1);
		searchStatistics.setTypeofsearch(String.valueOf(noOfConnections));
		searchStatisticsRepository.save(searchStatistics);
	}
	
	public void insertTrainSegmentOccupancyRate(long date, long noOfPassengers, long trainid, 
			long srcid, long destid, long trainCapacity ){
		
		List<Object[]> objects = new ArrayList<Object[]>();
		objects = stationDao.findAllStations();
		
		List<Object[]> trainObject = new ArrayList<Object[]>(); 
		trainObject = trainSegmentOccupancyRepository.checkIfTrainExists(trainid, date);
		
		if (trainObject.isEmpty()) {
			for(Object[] obj: objects){
				// Looping through all stations from A to Z
				TrainSegmentOccupancy trainSegmentOccupancy = new TrainSegmentOccupancy();
				trainSegmentOccupancy.setDate(date);
				trainSegmentOccupancy.setNoofticketsbooked(0);
				trainSegmentOccupancy.setTrainid(trainid);
				trainSegmentOccupancy.setSegmentid(Long.parseLong(obj[0].toString()));
				trainSegmentOccupancy.setSegmentoccupancyrate(0.00);
				trainSegmentOccupancyRepository.save(trainSegmentOccupancy);
			}
		}
		
		// Check if it is northbound or southbound train
			
		if (srcid - destid < 0) {
			// Southbound trains from A to Z
			for(long i = srcid + 1; i <= destid; i++){
				// For each destination id i.e. each station in the journey update a row.
//				TrainSegmentOccupancy sb = new TrainSegmentOccupancy();
//				sb.setDate(date);
//				sb.setTrainid(trainid);
//				sb.setNoofticketsbooked(noOfPassengers);
//				sb.setSegmentid(i);
//				sb.setSegmentoccupancyrate(Double.valueOf(String.valueOf(noOfPassengers))/trainCapacity);
				
				TrainSegmentOccupancy t = trainSegmentOccupancyRepository.getIdForUpdate(trainid, date,i);
				t.setNoofticketsbooked(noOfPassengers);
				t.setSegmentoccupancyrate(Double.valueOf(String.valueOf(noOfPassengers))/trainCapacity);
				trainSegmentOccupancyRepository.save(t);
			}
		}
		else{
			// Northbound trains from Z to A
			for(long i = srcid - 1; i >= destid; i--){
				// For each destination id i.e. each station in the journey update a row.
				TrainSegmentOccupancy sb = new TrainSegmentOccupancy();
				sb.setDate(date);
				sb.setTrainid(trainid);
				sb.setNoofticketsbooked(noOfPassengers);
				sb.setSegmentid(i);
				sb.setSegmentoccupancyrate(Double.valueOf(String.valueOf(noOfPassengers))/trainCapacity);
				trainSegmentOccupancyRepository.save(sb);
			}
		
		}
		
		// Insert or Update record in train_reservation_rate
		
		Double newTrainReservationRate = trainSegmentOccupancyRepository.fetchTrainReservationRate(trainid,date);
		TrainReservationReport trainReservationReport = new TrainReservationReport();
		trainReservationReport.setDate(date);
		trainReservationReport.setTrainid(trainid);
		trainReservationReport.setTrainreservationrate(newTrainReservationRate);
		dailyReservationReportRepository.save(trainReservationReport);
		
	}
}
