package com.ustglobal.mailsimulationapp.dao;

import com.ustglobal.mailsimulationapp.dto.UserBean;

public interface IUserDAO {

	public boolean register(UserBean userBean);
	public boolean login(String email, String password);
	public boolean changePassword(UserBean userBean, String password);
	public boolean sendEmail(String toAddress, String subject, String message);
	public boolean viewInbox(int id);
	public boolean sentItems(int id);
	public boolean deleteItems(int id);
	public boolean draft(int id);
	
}
