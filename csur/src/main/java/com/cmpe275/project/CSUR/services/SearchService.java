package com.cmpe275.project.CSUR.services;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmpe275.project.CSUR.dao.SearchRepository;
import com.cmpe275.project.CSUR.dao.TrainDao;
import com.cmpe275.project.CSUR.mapper.SearchCriteria;
import com.cmpe275.project.CSUR.mapper.SearchResults;
import com.cmpe275.project.CSUR.model.TrainSchedule;

@Service
public class SearchService {

	@Autowired
	private SearchRepository searchRepository;

	@Autowired
	private TrainDao trainDao;

	public List<SearchResults> searchTrains(SearchCriteria searchCriteria) {

		List<BigInteger> trainIds = searchRepository.findTrainsByFromTo(
				Long.valueOf(searchCriteria.getFrom()),
				Long.valueOf(searchCriteria.getTo()));
		System.out.println(trainIds.toString());
		List<SearchResults> searchResults = new ArrayList<SearchResults>();

		/*for (BigInteger trainId : trainIds) {
			SearchResults sResults = new SearchResults();
			sResults.setTrain(trainDao.findOne(trainId.longValue()));
			sResults.setFrom(searchCriteria.getFrom());
			sResults.setTo(searchCriteria.getTo());
			TrainSchedule trainSchedule = searchRepository
					.findTimeByTrainAndStation(trainId.longValue(),
							Long.valueOf(searchCriteria.getFrom()));
			sResults.setArvTime(trainSchedule.getArrivalTime());
			sResults.setDepTime(trainSchedule.getDepartureTime());
			searchResults.add(sResults);
		}
*/
		return searchResults;
	}
}
