package com.cmpe275.project.services;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmpe275.project.dao.RunningTrainsDao;
import com.cmpe275.project.dao.SearchRepository;
import com.cmpe275.project.dao.StationDao;
import com.cmpe275.project.dao.TrainDao;
import com.cmpe275.project.mapper.Connection;
import com.cmpe275.project.mapper.SearchCriteria;
import com.cmpe275.project.mapper.SearchResults;
import com.cmpe275.project.mapper.TrainSearchResponse;
import com.cmpe275.project.model.RunningTrains;
import com.cmpe275.project.model.Train;
import com.cmpe275.project.model.TrainSchedule;

@Service
public class SearchService {

	@Autowired
	private SearchRepository searchRepository;

	@Autowired
	private TrainDao trainDao;

	@Autowired
	private RunningTrainsDao runningTrainsDao;

	@Autowired
	private StationDao stationDao;

	public TrainSearchResponse searchTrains(SearchCriteria searchCriteria) {	
		
		TrainSearchResponse trainSearchResponse = new TrainSearchResponse();

		List<SearchResults> searchResults = searchForTrain(searchCriteria);
		List<SearchResults> returnSearchResults = new ArrayList<SearchResults>();
		if (searchCriteria.isRoundtrip()) {
			String from = searchCriteria.getFrom();
			String to = searchCriteria.getTo();
			String depTime = searchCriteria.getReturnDepartureTime();
			long date = searchCriteria.getReturnDepDate();
			searchCriteria.setFrom(to);
			searchCriteria.setTo(from);
			searchCriteria.setDepartureTime(depTime);
			searchCriteria.setDepDate(date);

			returnSearchResults = searchForTrain(searchCriteria);
		}

		if (searchCriteria.isRoundtrip() && returnSearchResults != null
				&& !returnSearchResults.isEmpty()) {
			trainSearchResponse.setCode(200);
			trainSearchResponse.setMsg("Search Results");
			trainSearchResponse.setSearchResults(searchResults);
			trainSearchResponse.setReturnSearchResults(returnSearchResults);
		} else if (!searchCriteria.isRoundtrip() && !searchResults.isEmpty()) {
			trainSearchResponse.setCode(200);
			trainSearchResponse.setMsg("Search Results");
			trainSearchResponse.setSearchResults(searchResults);
			trainSearchResponse.setReturnSearchResults(returnSearchResults);
		} else {
			trainSearchResponse.setCode(200);
			trainSearchResponse.setMsg("No Trains Found");
			trainSearchResponse.setSearchResults(new ArrayList<SearchResults>());
			trainSearchResponse.setReturnSearchResults(new ArrayList<SearchResults>());
		}

		return trainSearchResponse;
	}

	private List<SearchResults> searchForTrain(SearchCriteria searchCriteria) {

		List<SearchResults> searchResults = new ArrayList<SearchResults>();
		if (!searchCriteria.getTrainType().equalsIgnoreCase("A")) {
			List<BigInteger> trainIds = new ArrayList<BigInteger>();
			List<Train> expressTrains = new ArrayList<Train>();
			List<Train> regularTrains = new ArrayList<Train>();
			if (searchCriteria.isExact()) {
				trainIds = searchRepository.findTrainsByFromToDepTimeEq(
						Long.valueOf(searchCriteria.getFrom()),
						Long.valueOf(searchCriteria.getTo()),
						timToMilliSeconds(searchCriteria.getDepartureTime()));
			} else {
				trainIds = searchRepository.findTrainsByFromToDepTimeGt(
						Long.valueOf(searchCriteria.getFrom()),
						Long.valueOf(searchCriteria.getTo()),
						timToMilliSeconds(searchCriteria.getDepartureTime()));
			}
			System.out.println(trainIds);
			if (trainIds.size() > 0) {

				long trainDir = Long.valueOf(searchCriteria.getFrom())
						- Long.valueOf(searchCriteria.getTo());
				String trainDirStr = null;
				if (trainDir < 0) {
					trainDirStr = "SB";
				} else {
					trainDirStr = "NB";
				}
				for (BigInteger trainId : trainIds) {
					Train train = trainDao.findOne(trainId.longValue());
					if (train.getTrainnumber().startsWith(trainDirStr)) {
						if (train.getType().equalsIgnoreCase("E")
								&& hasSeats(train,
										searchCriteria.getNoOfPassengers(),
										searchCriteria.getDepDate())) {
							expressTrains.add(train);
						} else {
							if (hasSeats(train,
									searchCriteria.getNoOfPassengers(),
									searchCriteria.getDepDate())) {
								regularTrains.add(train);
							}
						}
					}
				}
				int i = 0;
				if (searchCriteria.getTrainType().equalsIgnoreCase("E")) {

					if (expressTrains.size() > 0) {

						for (Train train : expressTrains) {
							if (i < 5) {
								searchResults.add(buildSearchResult(train,
										searchCriteria, true));
								i++;
							} else {
								break;
							}
						}
					}
					

				} else {
					if (regularTrains.size() > 0) {
						for (Train train : regularTrains) {
							if (i < 5) {
								searchResults.add(buildSearchResult(train,
										searchCriteria, false));
								i++;
							} else {
								break;
							}
						}
					}
					
				}

			}

		} else {
			// any type ticket

		}
		
		return searchResults;

	}

	private SearchResults buildSearchResult(Train train,
			SearchCriteria searchCriteria, boolean isExpress) {
		SearchResults result = new SearchResults();
		TrainSchedule trainSchedule = searchRepository
				.findTimeByTrainAndStation(train.getId(),
						Long.valueOf(searchCriteria.getFrom()));
		result.setDateOfTravel(searchCriteria.getDepDate());
		result.setPaxs(searchCriteria.getNoOfPassengers());
		result.setArvTime(trainSchedule.getArrivaltime());
		result.setDepTime(trainSchedule.getDeparturetime());
		String from = stationDao
				.findOne(Long.valueOf(searchCriteria.getFrom())).getName();
		String to = stationDao.findOne(Long.valueOf(searchCriteria.getTo()))
				.getName();
		result.setConnections(buildConnection(train, searchCriteria,
				trainSchedule, from, to));
		result.setFrom(from);
		result.setTo(to);
		if (isExpress) {
			result.setPrice(calculateExpressPrice(
					Long.valueOf(searchCriteria.getFrom()),
					Long.valueOf(searchCriteria.getTo())));
		} else {
			result.setPrice(calculateRegularPrice(
					Long.valueOf(searchCriteria.getFrom()),
					Long.valueOf(searchCriteria.getTo())));
		}
		return result;
	}

	private List<Connection> buildConnection(Train train,
			SearchCriteria searchCriteria, TrainSchedule trainSchedule,
			String from, String to) {
		List<Connection> connections = new ArrayList<Connection>();
		Connection connection = new Connection();
		connection.setTrain(train);
		connection.setArvTime(trainSchedule.getArrivaltime());
		connection.setDepTime(trainSchedule.getDeparturetime());
		connection.setFrom(from);
		connection.setTo(to);
		connection.setSequenceNumber(1);
		connections.add(connection);
		return connections;
	}

	private double calculateExpressPrice(long from, long to) {
		double price = 0;
		price = (((to - from) / 5) * 2) + 1;
		return price;
	}

	private double calculateRegularPrice(long from, long to) {
		double price = 0;
		long d = to - from;

		int b = (int) (d / 5);
		int a = 0;
		if (d % 5 != 0) {
			a = 1;
		}
		price = b + a + 1;
		return price;
	}

	private boolean hasSeats(Train train, int noOfPassengers, long date) {

		boolean seatsAvailable = false;
		RunningTrains runningTrain = runningTrainsDao
				.findRunningTrainsByTrainAndDate(train.getId(), date);
		if (runningTrain == null) {
			seatsAvailable = true;
		} else if (runningTrain.getAvailablecount() >= noOfPassengers) {
			seatsAvailable = true;
		}
		return seatsAvailable;
	}

	private long timToMilliSeconds(String timeString) {
		long time = 0l;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

		// String inputString = "06:00:00";

		Date date;
		try {
			date = sdf.parse("1970-01-01 " + timeString);
			time = date.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("time : " + time);
		return time;
	}
}
