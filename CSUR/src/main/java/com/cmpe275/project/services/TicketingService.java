package com.cmpe275.project.services;

import com.cmpe275.project.CSUR.dao.RunningTrainRepository;
import com.cmpe275.project.CSUR.dao.TicketDetailsRepository;
import com.cmpe275.project.CSUR.dao.TravellerRepository;
import com.cmpe275.project.CSUR.mapper.TicketDetailMapper;
import com.cmpe275.project.CSUR.mapper.TicketMapper;
import com.cmpe275.project.CSUR.mapper.TravellerMapper;

import com.cmpe275.project.model.RunningTrains;
import com.cmpe275.project.model.Ticket;
import com.cmpe275.project.model.Travellers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmpe275.project.dao.TicketingRepository;
import com.cmpe275.project.model.TicketDetails;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;



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







    @Override
    public void bookTicket(TicketMapper ticketMapper) {

        Ticket ticket = new Ticket();
        ticket.setNumberofpassengers(ticketMapper.getNumberofPassenger());
        ticket.setSource(ticketMapper.getSource());
        ticket.setDestination(ticketMapper.getDestination());
        ticket.setTotalprice((double) ticketMapper.getPrice() );
        ticket.setBookedby(ticketMapper.getBookedBy());
        ticket.setBookingDate(Long.parseLong(ticketMapper.getBookingDate()));
        ticket.setTriptype(ticketMapper.getTripType());
        ticket.setTravellingdate(Long.parseLong(ticketMapper.getTravelingDate()));


        //Saving Ticket Object in DB

        Ticket bookedTicket = ticketingRepository.save(ticket);
        ticketId = bookedTicket.getId();


    }

    @Override
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

    @Override
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

    @Override
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

    @Override
    public List<TicketMapper> getTickets(long userId) {

        List<TicketMapper> response = new ArrayList<>();
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

           List<TicketDetails> bookedTicketsDetails = ticketDetailsRepository.findAllByTicketId(bookedTicket.getId());
           List<TicketDetailMapper> resTicketDetail = new ArrayList<>();
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
           List<TravellerMapper> resTravellers = new ArrayList<>();
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
        return false;
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
