package com.cmpe273.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MailRequest {

	@JsonProperty("mailId")
	private String mailId;
	@JsonProperty("message")
	private String message;
}
