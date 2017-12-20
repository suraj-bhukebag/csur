package com.cmpe275.project.mapper;

import java.util.List;


public class UserTicketsResponse extends GenericResponse {

	 List<TicketMapper> tickets;

	public List<TicketMapper> getTickets() {
		return tickets;
	}

	public void setTickets(List<TicketMapper> tickets) {
		this.tickets = tickets;
	}
	 
	 
}
