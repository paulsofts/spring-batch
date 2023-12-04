package com.paulsofts.springbatch.primary;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  entityManagerFactoryRef = "primaryEntityManagerFactory",
  transactionManagerRef = "primaryTransactionManager",
  basePackages = { "com.paulsofts.springbatch.primary" }
)
public class PrimaryDbConfig {
	
	@Bean(name="primaryDataSource")
	@ConfigurationProperties(prefix="spring.primary")
	@Primary
	public DataSource primaryDataSource() {
	    return DataSourceBuilder.create().build();
	}

	@Bean(name = "primaryEntityManagerFactory")
	@Primary
	public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("primaryDataSource") DataSource primaryDataSource) {
		return builder
				.dataSource(primaryDataSource)
				.packages("com.paulsofts.springbatch.primary")
				.build();
	}

	@Bean(name = "primaryTransactionManager")
	public PlatformTransactionManager primaryTransactionManager(
			@Qualifier("primaryEntityManagerFactory") EntityManagerFactory primaryEntityManagerFactory) {
		return new JpaTransactionManager(primaryEntityManagerFactory);
	}
}
