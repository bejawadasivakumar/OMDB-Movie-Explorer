package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.client.OmdbClient;
import com.example.demo.config.RedisConfig;
import com.example.demo.dto.MovieDetails;
import com.example.demo.dto.MovieSearchResponse;

@Service
public class MovieService {
	
	@Autowired
	private OmdbClient omdbClient;
	
	// Logger
    private static final Logger logger = LoggerFactory.getLogger(MovieService.class);
	
	@Cacheable(value = "movieSearch", key = "#title.toLowerCase()", unless = "#result == null || #result.response == 'False'")
	public MovieSearchResponse search(String title) {
		logger.info("Fetching movieSearch from DB");
		return omdbClient.searchMovie(title);
	}
	
	 @Cacheable(value = "movieDetails", key = "#imdbId", unless = "#result == null || #result.response == 'False'")
	public MovieDetails getMovieDetails(String imdbId) {
		 logger.info("Fetching movieDetails from DB");
		return omdbClient.getMovieDetails(imdbId);
	}

}
