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
	/*
	MovieSearchResponse response = webClient
        .get()
        .uri(uriBuilder -> uriBuilder
                .queryParam("s", "Avengers")
                .build())
        .header("X-API-KEY", apiKey) // we can use APIKey as Header not in query parameter as above
        .retrieve()
        .bodyToMono(MovieSearchResponse.class)
        .block();
		Disadvantages of using Query parameter:
		Problems:
		API key appears in browser history.
		API key may be written to server logs.
		API key may appear in monitoring tools and analytics.
		*/
	
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
