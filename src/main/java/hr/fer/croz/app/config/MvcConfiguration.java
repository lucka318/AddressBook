package hr.fer.croz.app.config;

import hr.fer.croz.app.dao.AddressDAO;
import hr.fer.croz.app.dao.CityDAO;
import hr.fer.croz.app.dao.ContactDAO;
import hr.fer.croz.app.dao.CountryDAO;
import hr.fer.croz.app.dao.GenderDAO;
import hr.fer.croz.app.dao.implementation.AddressDAOImpl;
import hr.fer.croz.app.dao.implementation.CityDAOImpl;
import hr.fer.croz.app.dao.implementation.ContactDAOImpl;
import hr.fer.croz.app.dao.implementation.CountryDAOImpl;
import hr.fer.croz.app.dao.implementation.GenderDAOImpl;
import hr.fer.croz.app.manager.AddressBookManager;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = "hr.fer.croz.app")
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter {

	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/pages/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean
	public JdbcTemplate getJDBCTemplate() {
		return new JdbcTemplate(getDataSource());
	}

	@Bean
	public AddressBookManager getAddressBookManager() {
		return new AddressBookManager();
	}
	
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/address_book");
		dataSource.setUsername("postgres");
		dataSource.setPassword("lucija");

		return dataSource;
	}

	@Bean
	public ContactDAO getContactDAO() {
		return new ContactDAOImpl();
	}

	@Bean
	public AddressDAO getAddressDAO() {
		return new AddressDAOImpl();
	}

	@Bean
	public CityDAO getCityDAO() {
		return new CityDAOImpl();
	}

	@Bean
	public GenderDAO getGenderDAO() {
		return new GenderDAOImpl();
	}

	@Bean
	public CountryDAO getCountryDAO() {
		return new CountryDAOImpl();
	}
}
