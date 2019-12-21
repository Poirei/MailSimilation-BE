package com.ustglobal.mailsimulationapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ustglobal.mailsimulationapp.dao.IUserDAO;
import com.ustglobal.mailsimulationapp.dto.MailBean;
import com.ustglobal.mailsimulationapp.dto.UserBean;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserDAO dao;
	
	@Override
	public boolean register(UserBean userBean) {
		return dao.register(userBean);
	}

	@Override
	public boolean login(String email, String password) {
		return dao.login(email, password);
	}

	@Override
	public boolean changePassword(UserBean userBean) {
		return dao.changePassword(userBean);
	}

	@Override
	public MailBean sendEmail(MailBean bean, UserBean to, UserBean from) {
		return dao.sendEmail(bean, to, from);
	}

	@Override
	public boolean deleteEmail(int id) {
		return dao.deleteEmail(id);
	}

	@Override
	public boolean draftEmail(int id) {
		return dao.draftEmail(id);
	}

	@Override
	public List<MailBean> viewInbox(int id) {
		return dao.viewInbox(id);
	}

	@Override
	public List<MailBean> sentItems(int id) {
		return dao.sentItems(id);
	}

	@Override
	public List<MailBean> deleteItems(int id) {
		return dao.deleteItems(id);
	}

	@Override
	public List<MailBean> draftItems(int id) {
		return dao.draftItems(id);
	}

}
