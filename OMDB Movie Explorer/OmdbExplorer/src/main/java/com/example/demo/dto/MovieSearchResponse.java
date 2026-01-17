package com.example.demo.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieSearchResponse implements Serializable {

	@JsonProperty("Search")
    private List<Movie> search;
    
    @JsonProperty("totalResults")
    private String totalResults;
    
    @JsonProperty("Response")
    private String response;
    
	public MovieSearchResponse() {
		
	}
	public MovieSearchResponse(List<Movie> search, String totalResults, String response) {
		
		this.search = search;
		this.totalResults = totalResults;
		this.response = response;
	}
	public List<Movie> getSearch() {
		return search;
	}
	public void setSearch(List<Movie> search) {
		this.search = search;
	}
	public String getTotalResults() {
		return totalResults;
	}
	public void setTotalResults(String totalResults) {
		this.totalResults = totalResults;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
}
