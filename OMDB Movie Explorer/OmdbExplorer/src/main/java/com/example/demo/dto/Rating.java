package com.example.demo.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rating implements Serializable {
	
	 @JsonProperty("Source")
     private String source;
     
     @JsonProperty("Value")
     private String value;
	
	public Rating() {
		
	}

	public Rating(String source, String value) {
		
		this.source = source;
		this.value = value;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
