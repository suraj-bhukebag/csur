package com.cmpe275.project.mapper;

import com.cmpe275.project.model.Ticket;

public class TicketResponse extends GenericResponse {

	private Ticket ticket;

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	
	
}
