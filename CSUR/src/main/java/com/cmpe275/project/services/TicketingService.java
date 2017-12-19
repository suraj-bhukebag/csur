package com.cmpe275.project.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmpe275.project.dao.RunningTrainRepository;
import com.cmpe275.project.dao.TicketDetailsRepository;
import com.cmpe275.project.dao.TicketingRepository;
import com.cmpe275.project.dao.TravellerRepository;
import com.cmpe275.project.mapper.TicketDetailMapper;
import com.cmpe275.project.mapper.TicketMapper;
import com.cmpe275.project.mapper.TravellerMapper;
import com.cmpe275.project.model.RunningTrains;
import com.cmpe275.project.model.Ticket;
import com.cmpe275.project.model.TicketDetails;
import com.cmpe275.project.model.Travellers;



@Service
public class TicketingService implements Ticketing {



    @Autowired
    private TicketingRepository ticketingRepository;
    @Autowired
    private RunningTrainRepository runningTrainRepository;
    @Autowired
    private TicketDetailsRepository ticketDetailsRepository;
    @Autowired
    private TravellerRepository travellerRepository ;

    long ticketId ;
    long capacity  = 1000 ;







   
    public Ticket bookTicket(TicketMapper ticketMapper) {

        Ticket ticket = new Ticket();
        ticket.setNumberofpassengers(ticketMapper.getNumberofPassenger());
        ticket.setSource(ticketMapper.getSource());
        ticket.setDestination(ticketMapper.getDestination());
        ticket.setTotalprice((double) ticketMapper.getPrice() );
        ticket.setBookedby(ticketMapper.getBookedBy());
        ticket.setBookingDate(Long.parseLong(ticketMapper.getBookingDate()));
        ticket.setTriptype(ticketMapper.getTripType());
        ticket.setTravellingdate(Long.parseLong(ticketMapper.getTravelingDate()));
        // Added by manish to track Booking Status
        ticket.setBookingstatus("a");


        //Saving Ticket Object in DB

        Ticket bookedTicket = ticketingRepository.save(ticket);
        ticketId = bookedTicket.getId();
        return bookedTicket;

    }


    public void bookTicketDetails(TicketMapper ticketMapper) {
        for (TicketDetailMapper ticketDetailMapper : ticketMapper.getTicketDetailMapper()) {
            TicketDetails ticketDetails = new TicketDetails();
            ticketDetails.setFromstation(ticketDetailMapper.getFrom());
            ticketDetails.setTostation(ticketDetailMapper.getTo());
            ticketDetails.setArrivaltime(ticketDetailMapper.getArivalTime());
            ticketDetails.setTrainId(ticketDetailMapper.getTrainId());
            ticketDetails.setDepttime(ticketDetailMapper.getDeptTime());
            ticketDetails.setSequencenumber(ticketDetailMapper.getSequence());
            ticketDetails.setTicketId(ticketId);

            ticketDetailsRepository.save(ticketDetails);
        }

    }


    public void runningTrain(TicketMapper ticketMapper) {
        for (TicketDetailMapper ticketDetailMapper : ticketMapper.getTicketDetailMapper()) {

            long runningId = ticketDetailMapper.getTrainId() + Long.parseLong(ticketMapper.getBookingDate());
            RunningTrains runningTrain = runningTrainRepository.findById(runningId);
            if (runningTrain == null) {

                RunningTrains runningTrains = new RunningTrains();
                runningTrains.setId(ticketDetailMapper.getTrainId() + Long.parseLong(ticketMapper.getBookingDate()));
                runningTrains.setTrainId(ticketDetailMapper.getTrainId());
                runningTrains.setDate(Long.parseLong(ticketMapper.getBookingDate()));
                runningTrains.setStatus("Running");
                runningTrains.setAvailablecount(capacity - ticketMapper.getNumberofPassenger());
                runningTrains.setTicketsbooked(ticketMapper.getNumberofPassenger());

                runningTrainRepository.save(runningTrains);
            } else {
                runningTrain.setAvailablecount(runningTrain.getAvailablecount() - ticketMapper.getNumberofPassenger());
                runningTrain.setTicketsbooked(runningTrain.getTicketsbooked() + ticketMapper.getNumberofPassenger());
                runningTrainRepository.save(runningTrain);
            }
        }

    }


    public void travellerDetails(TicketMapper ticketMapper) {
        for(TravellerMapper passenger : ticketMapper.getTravellerMapper()){

            Travellers travellers = new Travellers();
            travellers.setName(passenger.getName());
            travellers.setAge(Long.parseLong(passenger.getAge()));
            travellers.setGender(passenger.getGender());
            travellers.setTicketId(ticketId);

            travellerRepository.save(travellers);
        }

    }


    public List<TicketMapper> getTickets(long userId) {

        List<TicketMapper> response = new ArrayList<TicketMapper>();
        List<Ticket> bookedTickets = ticketingRepository.findAllByBookedBy(userId);
        for(Ticket bookedTicket : bookedTickets)
        {
            TicketMapper ticket = new TicketMapper();
            ticket.setNumberofPassenger((int)bookedTicket.getNumberofpassengers());
            ticket.setBookedBy(bookedTicket.getBookedby());
            ticket.setBookingDate(Long.toString(bookedTicket.getBookingDate()));
            ticket.setSource(bookedTicket.getSource());
            ticket.setDestination(bookedTicket.getDestination());
            ticket.setPrice(bookedTicket.getTotalprice().intValue());
            ticket.setTripType(bookedTicket.getTriptype());
            ticket.setTravelingDate(Long.toString(bookedTicket.getTravellingdate()));
            ticket.setBookingStatus(bookedTicket.getBookingstatus());

           List<TicketDetails> bookedTicketsDetails = ticketDetailsRepository.findAllByTicketId(bookedTicket.getId());
           List<TicketDetailMapper> resTicketDetail = new ArrayList<TicketDetailMapper>();
            for(TicketDetails bookedTicketDetail : bookedTicketsDetails)
            {
                TicketDetailMapper ticketDetailMapper = new TicketDetailMapper();
                ticketDetailMapper.setTrainId(bookedTicketDetail.getTrainId());
                ticketDetailMapper.setArivalTime(bookedTicketDetail.getArrivaltime());
                ticketDetailMapper.setDeptTime(bookedTicketDetail.getDepttime());
                ticketDetailMapper.setFrom(bookedTicketDetail.getFromstation());
                ticketDetailMapper.setTo(bookedTicketDetail.getTostation());
                ticketDetailMapper.setSequence(bookedTicketDetail.getSequencenumber());

                resTicketDetail.add(ticketDetailMapper);
            }

            ticket.setTicketDetailMapper(resTicketDetail);


           //Traveller Details
           List<Travellers> bookedTravellers = travellerRepository.findAllByTicketId(bookedTicket.getId());
           List<TravellerMapper> resTravellers = new ArrayList<TravellerMapper>();
           for(Travellers bookedTraveller : bookedTravellers)
           {
               TravellerMapper travellerMapper = new TravellerMapper();
               travellerMapper.setName(bookedTraveller.getName());
               travellerMapper.setAge(Long.toString(bookedTraveller.getAge()));
               travellerMapper.setGender(bookedTraveller.getGender());
               resTravellers.add(travellerMapper);
           }
           ticket.setTravellerMapper(resTravellers);

           response.add(ticket);

        }
        return response;

    }

    public void resetSystem(long seatCount)
    {
        capacity = seatCount ;
        ticketingRepository.deleteAll();
        ticketDetailsRepository.deleteAll();
        travellerRepository.deleteAll();
        runningTrainRepository.deleteAll();
    }


    @Override
	public boolean cancelTicket(long ticketId) {
		Ticket ticket = ticketingRepository.findOne(ticketId);
		TicketDetails ticketDetails = ticketDetailsRepository.findOne(ticketId);
		if(ticket==null || ticketDetails==null)
			return false;

		String sourceStation = ticket.getSource();

		// Check if Ticket is of Same Date
		Date todayDate = new Date();
		System.out.println(todayDate);
		SimpleDateFormat sm = new SimpleDateFormat("MM-dd-yyyy");
		String todaysFormattedDate = sm.format(todayDate);
		try {
			todayDate = sm.parse(todaysFormattedDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long currentDate = todayDate.getTime();
//ToDo Travel Date is still Not coming correctly
		long travelDate = ticket.getTravellingdate();

		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		String currentTime = sdf.format(new Date());

		long minDiffInMilliSeconds = 60 * 1 * 1000;

		if (currentDate == travelDate) {
			// Check of Train Departure time is greater then current time by
			// at-least three hour
			String DepTime = ticketDetails.getDepttime();
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			Date date1;
			Date date2;
			long difference =0;
			try {
				date1 = format.parse(currentTime);
				date2 = format.parse(DepTime);
				difference = date1.getTime() - date2.getTime();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(difference <= minDiffInMilliSeconds){	
				
				return false;
			}
		}
		
		ticket.setBookingstatus("c");
		ticketingRepository.save(ticket);
		return true;

	}
    
    public void clearTicketingTable() {
		ticketingRepository.deleteAll();
	}

	public void clearTicketDetailsTable() {
		ticketingRepository.deleteAll();
	}

	public void clearTravellersTable() {
		travellerRepository.deleteAll();
	}
    

//    public void bookTicket(TicketMapper ticketMapper) {
//
//
//
//        //TicketDetails ticket = ticketingRepository.save(ticketDetails);
//    }


//    public boolean validateTicket(long ticketId)
//    {
//
//        TicketDetails ticket = ticketingRepository.findOne(ticketId);
//        DateTime ticketTime = DateTime.parse(ticket.getDeptTime(),
//                DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss"));
//
//        DateTime currentTime = new DateTime();
//        Period p = new Period(currentTime, ticketTime);
//        int minutes = p.getMinutes();
//        if( minutes > 60)
//            return true;
//        return false ;
//         return true;
//    }

//    public boolean cancelTicket(long ticketId) {
//        if(validateTicket(ticketId)) {
//            ticketingRepository.delete(ticketId);
//            return true;
//        }
//        return false ;
//
//
//    }

//    public List<TicketDetails> getTickets(String userId) {
//        System.out.println(ticketingRepository.findAllByUserId(userId));
//        return ticketingRepository.findAllByUserId(userId);
//    }

}
