package com.ustglobal.mailsimulationapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ustglobal.mailsimulationapp.dto.MailResponseBean;
import com.ustglobal.mailsimulationapp.dto.UserBean;
import com.ustglobal.mailsimulationapp.service.IUserService;

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
	
	public MailResponseBean login()
	
}
