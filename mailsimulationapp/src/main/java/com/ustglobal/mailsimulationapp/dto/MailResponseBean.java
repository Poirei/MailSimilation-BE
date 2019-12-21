package com.ustglobal.mailsimulationapp.dto;

import lombok.Data;

@Data
public class MailResponseBean {

	private int statusCode;
	private String message;
	private String description;
	
}
