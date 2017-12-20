package com.cmpe275.project.mapper;

import java.util.List;

public class DailySearchLatencyResponse extends GenericResponse {

	private List<DailySearchLatency> dailySearchLatencies;

	public List<DailySearchLatency> getDailySearchLatencies() {
		return dailySearchLatencies;
	}

	public void setDailySearchLatencies(List<DailySearchLatency> dailySearchLatencies) {
		this.dailySearchLatencies = dailySearchLatencies;
	}
	
	
}
