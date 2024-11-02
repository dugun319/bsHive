package com.postGre.bsHive;

import javax.sql.DataSource;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
	private final DataSource dataSource;
	public SpringConfig(DataSource dataSource) {
		 this.dataSource = dataSource;
	}


}
