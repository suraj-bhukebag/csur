package com.cmpe275.project.services;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmpe275.project.dao.RunningTrainRepository;
import com.cmpe275.project.dao.SearchRepository;
import com.cmpe275.project.dao.TicketDetailsRepository;
import com.cmpe275.project.dao.TicketingRepository;
import com.cmpe275.project.dao.TrainScheduleRepository;
import com.cmpe275.project.dao.TravellerRepository;
import com.cmpe275.project.mapper.Connection;
import com.cmpe275.project.mapper.SearchCriteria;
import com.cmpe275.project.mapper.SearchResults;
import com.cmpe275.project.mapper.TicketDetailMapper;
import com.cmpe275.project.mapper.TicketMapper;
import com.cmpe275.project.mapper.TrainSearchResponse;
import com.cmpe275.project.mapper.TravellerMapper;
import com.cmpe275.project.model.RunningTrains;
import com.cmpe275.project.model.Ticket;
import com.cmpe275.project.model.TrainSchedule;
import com.cmpe275.project.model.Travellers;

@Service
@Transactional
public class TrainCancellationService {

	@Autowired
	private TicketingRepository ticketingRepository;
	@Autowired
	private RunningTrainRepository runningTrainRepository;
	@Autowired
	private TicketDetailsRepository ticketDetailsRepository;
	@Autowired
	private TravellerRepository travellerRepository;
	@Autowired
	TicketingService ticketingService;

	@Autowired
	private SearchRepository searchRepository;
	@Autowired
	SearchService searchService;

	@Autowired
	private TrainScheduleRepository trainSchedularRepository;

	public boolean cancelTrain(long trainId, long cancelDate) {

		/*
		 * Check if date is today's Date if Yes
		 * 
		 * Find train Start Time
		 * 
		 * Find Current Time
		 * 
		 * See if Difference of Current time and Start time of 1st station is
		 * less than three hours if no train can not be cancelled
		 * 
		 * if train can be cancelled change status of train in running train
		 * table
		 * 
		 * Find all Tickets from tickets table for that train_id on that
		 * particular day Change status of Ticket to cancelled // (Status column
		 * needs to add)
		 * 
		 * Now loop all those tickets which has status as cancelled and Train id
		 * and date is same as given date and Train id
		 * 
		 * Call search feature with the same criteria Now search result should
		 * not show the cancelled train)
		 * 
		 * Sort the result on basis of departure time and booked the top one
		 *
		 * 
		 */

		// Check if Cancel Date is f Today Date and Time is more than three
		// hours or not
		Date todayDate = new Date();
		SimpleDateFormat sm = new SimpleDateFormat("MM-dd-yyyy");

		String todaysFormattedDate = sm.format(todayDate);
		try {
			todayDate = sm.parse(todaysFormattedDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long currentDate = todayDate.getTime();

		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		String currentTime = sdf.format(new Date());

		long minDiffInMilliSeconds = 60 * 3 * 1000;

		if (currentDate == cancelDate) {
			// Check of Train Departure time is greater then current time by
			// at-least three hour

			// Find departure time from First Station

			TrainSchedule trainSchedule = trainSchedularRepository.findScheduleTimeAtFirstStationById(trainId, 1);

			String depTime = trainSchedule.getDeparturetime();

			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			Date date1;
			Date date2;
			long difference = 0;
			try {
				date1 = format.parse(currentTime);
				date2 = format.parse(depTime);
				difference = date1.getTime() - date2.getTime();
				System.out.println("Time Difference in Milli Seconds " + difference);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (difference <= minDiffInMilliSeconds) {

				return false;
			} else {
				// Set Status in running train as false

				RunningTrains runningTrain = runningTrainRepository.findRunningTrainByTrainIDAndDate(trainId,
						cancelDate);
				runningTrain.setStatus("c");

				// Find All Ids of ticket this Train in Ticket Table

			}

		} else {

			RunningTrains runningTrain = runningTrainRepository.findRunningTrainByTrainIDAndDate(trainId, cancelDate);
			runningTrain.setStatus("c");
			List<BigInteger> ticketIds = new ArrayList<BigInteger>();
			List<Ticket> ticketsNeedToCancel = new ArrayList<Ticket>();
			ticketIds = ticketingRepository.findAllByTrainId(trainId, cancelDate);
			for (BigInteger ticketId : ticketIds) {

				ticketsNeedToCancel.add(ticketingRepository.findOne(ticketId.longValue()));

			}

			for (Ticket ticket : ticketsNeedToCancel) {
				ticket.setBookingstatus("c");
				SearchCriteria searchCriteria = buildSearchCriteria(ticket);
				TrainSearchResponse searchResponse = searchService.searchTrains(searchCriteria);
				if (searchResponse == null || searchResponse.getSearchResults().isEmpty())
					return false;
				else {
					SearchResults searchresult = searchResponse.getSearchResults().get(0);
					TicketMapper ticketMapper = new TicketMapper();
					ticketMapper.setBookedBy(ticket.getBookedby());
					ticketMapper.setBookingDate(currentDate + "");
					ticketMapper.setBookingStatus(ticket.getBookingstatus());
					ticketMapper.setDestination(ticket.getDestination());
					ticketMapper.setNumberofPassenger((int) ticket.getNumberofpassengers());
					ticketMapper.setPrice((int) Math.round(ticket.getTotalprice()));
					ticketMapper.setSource(ticket.getSource());
					ticketMapper.setTravelingDate(ticket.getTravellingdate() + "");

					List<TicketDetailMapper> list = new ArrayList<TicketDetailMapper>();
					List<Connection> connections = searchresult.getConnections();
					for (Connection connection : connections) {
						TicketDetailMapper details = new TicketDetailMapper();
						details.setTrainId(Long.valueOf(connection.getTrain().getId()));
						details.setArivalTime(connection.getArvTime());
						details.setDeptTime(connection.getDepTime());
						details.setFrom(connection.getFrom());
						details.setTo(connection.getTo());
						list.add(details);
					}
					ticketMapper.setTicketDetailMapper(list);

					List<Travellers> travellers = travellerRepository.findAllByTicketId(ticket.getId());
					List<TravellerMapper> travellerMapper = new ArrayList<>();

					for (Travellers traveller : travellers) {
						TravellerMapper mapper = new TravellerMapper();
						mapper.setName(traveller.getName());
						mapper.setAge(traveller.getAge() + "");
						mapper.setGender(traveller.getGender());
						travellerMapper.add(mapper);
					}
					ticketMapper.setTravellerMapper(travellerMapper);

					ticketingService.bookTicket(ticketMapper);
				}

			}

		}

		return true;

	}

	public SearchCriteria buildSearchCriteria(Ticket ticket) {
		String departureTime = ticketingRepository.findDepTime(ticket.getId());
		SearchCriteria searchCriteria = new SearchCriteria();
		searchCriteria.setDepartureTime(departureTime + "");
		searchCriteria.setFrom(ticket.getSource());
		searchCriteria.setTo(ticket.getDestination());
		searchCriteria.setTrainType(ticket.getTriptype());
		searchCriteria.setNoOfPassengers((int) ticket.getNumberofpassengers());
		return searchCriteria;

	}

}
