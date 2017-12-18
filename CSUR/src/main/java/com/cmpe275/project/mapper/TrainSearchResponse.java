package com.cmpe275.project.mapper;

import java.util.List;

public class TrainSearchResponse extends GenericResponse {

	private List<SearchResults> searchResults;

	public List<SearchResults> getSearchResults() {
		return searchResults;
	}

	public void setSearchResults(List<SearchResults> searchResults) {
		this.searchResults = searchResults;
	}
	
	
}
