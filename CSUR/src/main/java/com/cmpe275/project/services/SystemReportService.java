package com.cmpe275.project.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmpe275.project.dao.SearchStatisticsRepository;
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
}
