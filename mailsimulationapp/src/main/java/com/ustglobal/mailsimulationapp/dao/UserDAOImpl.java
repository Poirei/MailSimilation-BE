package com.ustglobal.mailsimulationapp.dao;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.ustglobal.mailsimulationapp.dto.MailBean;
import com.ustglobal.mailsimulationapp.dto.UserBean;

@Repository
public class UserDAOImpl implements IUserDAO {

	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	
	public boolean register(UserBean userBean) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		String jpql = "from UserBean where user_name NOT IN :user_name";
		TypedQuery<UserBean> typedQuery = entityManager.createQuery(jpql, UserBean.class);
		typedQuery.setParameter("user_name", userBean.getUser_name());
		List<UserBean> beans = typedQuery.getResultList();
		List<String> emails = new LinkedList<String>();
		for (UserBean bean: beans) {
			emails.add(bean.getEmail());
		}
		if (!emails.contains(userBean.getEmail())) {
			try {
				entityTransaction.begin();
				entityManager.persist(userBean);
				entityTransaction.commit();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} else
			return false;
	}
	
	public boolean login(String email, String password) {
		String jpql = "from UserBean where email=:email and password=:password";
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		TypedQuery<UserBean> query = entityManager.createQuery(jpql, UserBean.class);
		query.setParameter("email", email);
		query.setParameter("password", password);
		try {
			query.getSingleResult();	
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean changePassword(UserBean userBean) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		UserBean bean = entityManager.find(UserBean.class, userBean.getId());
		try {
			entityTransaction.begin();
			bean.setPassword(userBean.getPassword());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public MailBean sendEmail(MailBean bean, UserBean to, UserBean from) {
		EntityManager manager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		bean.setBean(from);
		bean.setBean1(to);
		bean.setMessage("Mail Sent");
		bean.setStatus("sent");
		try {
			transaction.begin();
			manager.persist(bean);
			transaction.commit();
			return bean;
			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<MailBean> viewInbox(int id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String jpql = "from MailBean where from_id = :id";
		TypedQuery<MailBean> typedQuery = entityManager.createQuery(jpql, MailBean.class);
		typedQuery.setParameter("id", id);
		List<MailBean> mailBeans = typedQuery.getResultList();
		return mailBeans;
	}

	@Override
	public List<MailBean> sentItems(int id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String jpql = "from MailBean where id = :id and status = 'sent'";
		TypedQuery<MailBean> typedQuery = entityManager.createQuery(jpql, MailBean.class);
		typedQuery.setParameter("id", id);
		List<MailBean> mailBeans = typedQuery.getResultList();
		return mailBeans;
	}

	@Override
	public List<MailBean> deleteItems(int id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String jpql = "from MailBean where id = :id and status = 'deleted'";
		TypedQuery<MailBean> typedQuery = entityManager.createQuery(jpql, MailBean.class);
		typedQuery.setParameter("id", id);
		List<MailBean> mailBeans = typedQuery.getResultList();
		return mailBeans;
	}

	@Override
	public List<MailBean> draftItems(int id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String jpql = "from MailBean where id = :id and status = 'drafted'";
		TypedQuery<MailBean> typedQuery = entityManager.createQuery(jpql, MailBean.class);
		typedQuery.setParameter("id", id);
		List<MailBean> mailBeans = typedQuery.getResultList();
		return mailBeans;
	}

	@Override
	public boolean deleteEmail(int id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		MailBean mailBean = entityManager.find(MailBean.class, id);
		try {
			entityTransaction.begin();
			mailBean.setStatus("deleted");
			entityTransaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean draftEmail(int id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		MailBean mailBean = entityManager.find(MailBean.class, id);
		try {
			entityTransaction.begin();
			mailBean.setStatus("drafted");
			entityTransaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
