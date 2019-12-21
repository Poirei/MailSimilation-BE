package com.ustglobal.mailsimulationapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.ustglobal.mailsimulationapp.service.IUserService;

@RestController
public class MailController {

	@Autowired
	private IUserService service;
	
	
	
}
