package com.ustglobal.mailsimulationapp.dto;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "mail_info")
public class MailBean {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "from_id", nullable = false)
	private UserBean bean;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "to_id", nullable = false)
	private UserBean bean1;

	@Column
	private String subject;
	
	@Column
	private String message;
	
	@Column
	private String status;
	
}
