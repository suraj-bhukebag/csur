package com.cmpe275.project.services;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmpe275.project.dao.RunningTrainsDao;
import com.cmpe275.project.dao.SearchRepository;
import com.cmpe275.project.dao.StationDao;
import com.cmpe275.project.dao.TrainDao;
import com.cmpe275.project.mapper.SearchCriteria;
import com.cmpe275.project.mapper.SearchResults;
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

	public List<SearchResults> searchTrains(SearchCriteria searchCriteria) {

		List<SearchResults> searchResults = new ArrayList<SearchResults>();
		if (!searchCriteria.getTrainType().equalsIgnoreCase("A")) {
			List<BigInteger> trainIds = null;
			List<Train> expressTrains = new ArrayList<Train>();
			List<Train> regularTrains = new ArrayList<Train>();
			if (searchCriteria.isExact()) {
				trainIds = searchRepository.findTrainsByFromToDepTimeEq(
						Long.valueOf(searchCriteria.getFrom()),
						Long.valueOf(searchCriteria.getTo()),
						searchCriteria.getDepartureTime());
			} else {
				trainIds = searchRepository.findTrainsByFromToDepTimeGt(
						Long.valueOf(searchCriteria.getFrom()),
						Long.valueOf(searchCriteria.getTo()),
						searchCriteria.getDepartureTime());
			}

			for (BigInteger trainId : trainIds) {
				Train train = trainDao.findOne(trainId.longValue());
				if (train.getType().equalsIgnoreCase("E")
						&& hasSeats(train, searchCriteria.getNoOfPassengers(),
								searchCriteria.getDepDate())) {
					expressTrains.add(train);
				} else {
					if (hasSeats(train, searchCriteria.getNoOfPassengers(),
							searchCriteria.getDepDate())) {
						regularTrains.add(train);
					}
				}
			}
			int i = 0;
			if (searchCriteria.getTrainType().equalsIgnoreCase("E")) {

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
			else {

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

		} else {

		}

		return searchResults;
	}

	private SearchResults buildSearchResult(Train train,
			SearchCriteria searchCriteria, boolean isExpress) {
		SearchResults result = new SearchResults();
		TrainSchedule trainSchedule = searchRepository
				.findTimeByTrainAndStation(train.getId(),
						Long.valueOf(searchCriteria.getFrom()));
		result.setTrain(train);
		result.setArvTime(trainSchedule.getArrivaltime());
		result.setDepTime(trainSchedule.getDeparturetime());
		String from = stationDao
				.findOne(Long.valueOf(searchCriteria.getFrom())).getName();
		String to = stationDao.findOne(Long.valueOf(searchCriteria.getTo()))
				.getName();
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
}
