package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MovieDetails;
import com.example.demo.dto.MovieSearchResponse;
import com.example.demo.service.MovieService;

@RestController
@RequestMapping("/api")
public class MovieContoller {
	
	@Autowired
	private MovieService movieService;
	
	@GetMapping("/search")
	public ResponseEntity<?> search(@RequestParam String title){
		MovieSearchResponse movies = movieService.search(title);
		if(!"false".equalsIgnoreCase(movies.getResponse())) {
		return new ResponseEntity<>(movies,HttpStatus.OK);
		}
		return new ResponseEntity<>("Not found with the given title", HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/movieDetails/{imdbId}")
	public ResponseEntity<?> getMovie(@PathVariable String imdbId){
		MovieDetails details = movieService.getMovieDetails(imdbId);
		if(details.getImdbID() != null) {
			return new ResponseEntity<>(details, HttpStatus.OK);
		}
		return new ResponseEntity<>("not found with the given imdbId",HttpStatus.NOT_FOUND);
	}

}
