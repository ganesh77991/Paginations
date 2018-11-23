package com.app.config;

import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.app.model.Employee;

@Configuration // java config files
@EnableWebMvc // makes this as web application
@EnableTransactionManagement // enables tx commit or rollback
@ComponentScan(basePackages = "com.app.*") // all files are create under this folder only
@PropertySource("classpath:app.properties") // loads properties file from src/main/resources folder
public class AppConfig {

	// loads properties into config
	@Autowired
	private Environment env;

	// 1.DataSource Bean
	@Bean // @Bean creating Object
	public BasicDataSource dsObj() {

		BasicDataSource ds = new BasicDataSource();

		// set properties ds
		ds.setDriverClassName(env.getProperty("dc"));
		ds.setUrl(env.getProperty("url"));
		ds.setUsername(env.getProperty("un"));
		ds.setPassword(env.getProperty("pwd"));

		// set connection pooling properties
		ds.setInitialSize(5);
		ds.setMaxIdle(3);
		ds.setMinIdle(3);
		ds.setMaxIdle(5);
				
		return ds;
	}

	// 2. SessionFactory Bean
	@Bean
	public LocalSessionFactoryBean sfObj() {

		LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
		sf.setDataSource(dsObj());
		sf.setAnnotatedClasses(Employee.class);
		sf.setHibernateProperties(props());

		return sf;
	}

	private Properties props() {

		Properties props = new Properties();
		props.put("hibernate.dialect", env.getProperty("dialect"));
		props.put("hibernate.show_sql", env.getProperty("showsql"));
		props.put("hibernate.format_sql", env.getProperty("fmtsql"));
		props.put("hibernate.hbm2ddl.auto", env.getProperty("ddlauto"));

		return props;
	}
	
	// 3. Hibernate Template Bean
	@Bean
	public HibernateTemplate htObj() {
		
		HibernateTemplate ht=new HibernateTemplate();
		ht.setSessionFactory(sfObj().getObject());
		
		return ht;
	}
	
	// 4.hibernate TX manager bean
	@Bean
	public HibernateTransactionManager txmObj() {
		HibernateTransactionManager htxm=new HibernateTransactionManager();
		htxm.setSessionFactory(sfObj().getObject());
		return htxm;
	}
	
	// 5. viewResolver
	@Bean
	public InternalResourceViewResolver vrObj() {
		
		InternalResourceViewResolver vr=new InternalResourceViewResolver();
		
		vr.setPrefix(env.getProperty("mvc.prefix"));
		vr.setSuffix(env.getProperty("mvc.suffix"));
		return vr;
		
	}

}
