package br.com.abevieiramota.casadocodigo.loja.conf;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public class JpaConfiguration {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		factoryBean.setJpaVendorAdapter(vendorAdapter);

		// necessário jdbc.properties em resources
		Properties jdbcProperties = new Properties();
		try (InputStream is = JpaConfiguration.class.getClassLoader().getResourceAsStream("jdbc.properties")) {
			jdbcProperties.load(is);
		} catch (IOException e) {
			throw new IllegalStateException("Macho, joga esse jdbc.properties no build path porra!");
		}
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername(jdbcProperties.getProperty("username"));
		dataSource.setPassword(jdbcProperties.getProperty("password"));
		dataSource.setUrl("jdbc:postgresql://localhost:5432/casadocodigo");
		dataSource.setDriverClassName("org.postgresql.Driver");
		factoryBean.setDataSource(dataSource);

		Properties props = new Properties();
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
		props.setProperty("hibernate.show_sql", "true");
		props.setProperty("hibernate.hbm2ddl.auto", "update");
		factoryBean.setJpaProperties(props);

		factoryBean.setPackagesToScan("br.com.abevieiramota.casadocodigo.loja.models");

		return factoryBean;
	}

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}
}
