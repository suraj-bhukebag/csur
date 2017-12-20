package com.cmpe275.project.model;

public class ClearData {

	private boolean isRunningDataClean;
	private boolean isTicketDataClean;
	private boolean isUserDataClean;
	private int capacity;
	
	
	public boolean isUserDataClean() {
		return isUserDataClean;
	}
	public void setUserDataClean(boolean isUserDataClean) {
		this.isUserDataClean = isUserDataClean;
	}
	public boolean isRunningDataClean() {
		return isRunningDataClean;
	}
	public void setRunningDataClean(boolean isRunningDataClean) {
		this.isRunningDataClean = isRunningDataClean;
	}
	public boolean isTicketDataClean() {
		return isTicketDataClean;
	}
	public void setTicketDataClean(boolean isTicketDataClean) {
		this.isTicketDataClean = isTicketDataClean;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	
	
}
