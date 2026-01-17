package com.example.demo.client;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.dto.MovieDetails;
import com.example.demo.dto.MovieSearchResponse;

@Component
public class OmdbClient {
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	@Value("${omdb.base.url}")
	private String baseUrl;
	
	@Value("${omdb.api.key}")
	private String apiKey;
	
	

	public MovieSearchResponse searchMovie(String title) {
		URI uri = UriComponentsBuilder
                .fromUriString(baseUrl)
                .queryParam("apikey", apiKey)
                .queryParam("s", title)
                .build()
                .toUri();
		/* String url = baseUrl + "apikey=" + apiKey + "&s=" + title;*/
		return restTemplate.getForObject(uri,MovieSearchResponse.class);
	}
	
	public MovieDetails getMovieDetails(String imdbId) {
		URI uri = UriComponentsBuilder
				  .fromUriString(baseUrl)
				  .queryParam("apikey", apiKey)
				  .queryParam("i",imdbId)
				  .queryParam("plot", "full")
				  .build()
				  .toUri();
		/*String url = baseUrl + "apikey=" + apiKey + "&i=" + imdbId;*/
		return restTemplate.getForObject(uri, MovieDetails.class);
	}
}
