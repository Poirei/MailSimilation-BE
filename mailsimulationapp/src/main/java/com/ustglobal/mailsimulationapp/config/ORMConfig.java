package com.ustglobal.mailsimulationapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

@Configuration
public class ORMConfig {

	@Bean
	public LocalEntityManagerFactoryBean getLocalEntityManagerFactoryBean() {
		LocalEntityManagerFactoryBean localEntityManagerFactoryBean =
				new LocalEntityManagerFactoryBean();
		localEntityManagerFactoryBean.setPersistenceUnitName("mail-unit");
		return localEntityManagerFactoryBean;
	}
	
}
