package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.client.OmdbClient;
import com.example.demo.dto.MovieDetails;
import com.example.demo.dto.MovieSearchResponse;

@Service
public class MovieService {
	
	@Autowired
	private OmdbClient omdbClient;
	
	@Cacheable(value = "movieSearch", key = "#title.toLowerCase()")
	public MovieSearchResponse search(String title) {
		return omdbClient.searchMovie(title);
	}
	
	 @Cacheable(value = "movieDetails", key = "#imdbId")
	public MovieDetails getMovieDetails(String imdbId) {
		return omdbClient.getMovieDetails(imdbId);
	}

}
