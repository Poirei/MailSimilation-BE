package com.ustglobal.mailsimulationapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ustglobal.mailsimulationapp.dto.MailResponseBean;
import com.ustglobal.mailsimulationapp.dto.UserBean;
import com.ustglobal.mailsimulationapp.service.IUserService;

@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
@RestController
public class MailController {

	@Autowired
	private IUserService service;

	@PostMapping(path = "/register")
	public MailResponseBean register(@RequestBody UserBean userBean) {
		MailResponseBean responseBean = new MailResponseBean();
		if (service.register(userBean)) {
			responseBean.setStatusCode(200);
			responseBean.setMessage("Success!");
			responseBean.setDescription("Registration successfull!");
		} else {
			responseBean.setStatusCode(400);
			responseBean.setMessage("Failure!");
			responseBean.setDescription("Email already exists!");
		}
		return responseBean;
	}

	@PostMapping(path = "/login",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public MailResponseBean login(@RequestBody UserBean userBean) {
		MailResponseBean responseBean = new MailResponseBean();
		if (service.login(userBean.getEmail(), userBean.getPassword())) {
			responseBean.setStatusCode(201);
			responseBean.setMessage("Success!");
			responseBean.setDescription("Login successful!");
		} else {
			responseBean.setStatusCode(401);
			responseBean.setMessage("Failure!");
			responseBean.setDescription("Invalid email or password!");
		}
		return responseBean;

	}

}
