package com.pvthuan.book.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.google.common.base.Preconditions;
import com.pvthuan.book.utils.ConstanModel;
import com.pvthuan.book.utils.EncryptDecyptUtils;


@Configuration
@EnableTransactionManagement
@PropertySource({ ConstanModel.MYSQL_PROPERTIES })
@ComponentScan({ ConstanModel.PACKAGE_SCAN })
@EnableJpaRepositories(basePackages = ConstanModel.PACKAGE_ENABLE)
@PersistenceContext(type = PersistenceContextType.EXTENDED)
public class PersistenceJPAMySqlConfig {
	@Autowired
	private Environment env;
	
	private EncryptDecyptUtils encryDecry;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { ConstanModel.PACKAGE_DATA });

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());

		return em;
	}

	@Bean
	public DataSource dataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(Preconditions.checkNotNull(env.getProperty(ConstanModel.DRIVER)));
		dataSource.setUrl(Preconditions.checkNotNull(env.getProperty(ConstanModel.URL)));
		dataSource.setUsername(env.getProperty(ConstanModel.USER));
		dataSource.setPassword(env.getProperty(ConstanModel.PASS));

		return dataSource;
	}

//	@Bean
//	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
//		JpaTransactionManager transactionManager = new JpaTransactionManager();
//		transactionManager.setEntityManagerFactory(emf);
//
//		return transactionManager;
//	}
	@Bean
    @Autowired
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        JpaDialect jpaDialect = new HibernateJpaDialect();
        txManager.setEntityManagerFactory(entityManagerFactory);
        txManager.setJpaDialect(jpaDialect);
        return txManager;
    }

	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}

	Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty(ConstanModel.HIBERNATE_HBM2DDL_AUTO, ConstanModel.CREATE_DROP);
		properties.setProperty(ConstanModel.HIBERNATE_DIALECT, ConstanModel.HIBERNATE_MYSQL);
		return properties;
	}
	public String useDB() {
		encryDecry = new EncryptDecyptUtils();
		String result = "";
		try {
			result = encryDecry.decrypt(Preconditions.checkNotNull(env.getProperty(ConstanModel.USER)));
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return result;
	}
	public String passDB() {
		encryDecry = new EncryptDecyptUtils();
		String result = "";
		try {
			result = encryDecry.decrypt(Preconditions.checkNotNull(env.getProperty(ConstanModel.PASS)));
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
}