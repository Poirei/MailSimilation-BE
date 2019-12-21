package com.ustglobal.mailsimulationapp.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import net.bytebuddy.build.ToStringPlugin.Exclude;

@Data
@Entity
@Table(name = "user_info")
public class UserBean {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column
	private String user_name;
	
	@Column(unique = true)
	private String email;
	
	@Column
	private String password;
	
	@Transient
	private String pet_name;
	
	@Transient
	private String birth_place;
	
	@Transient
	private String fav_dish;
	
	@JsonIgnore
	@Exclude
	@OneToMany(mappedBy = "bean1")
	private List<MailBean> mails;
	
	@JsonIgnore
	@Exclude
	@OneToMany(mappedBy = "bean")
	private List<MailBean> mails1;
	
}
