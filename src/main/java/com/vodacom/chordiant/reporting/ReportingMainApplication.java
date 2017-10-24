package com.vodacom.chordiant.reporting;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.jolbox.bonecp.BoneCPDataSource;

/**
 * 
 * Application Entry point - Will load all configurations and all Spring
 * dependencies required
 * 
 * @author Gaurav Soni
 * 
 */
@Configuration
@EnableScheduling
@ComponentScan
@EnableAutoConfiguration
public class ReportingMainApplication extends SpringBootServletInitializer {

	/**
	 * DB connection details
	 */
	@Value("${bonecp.url}")
	private String jdbcUrl;

	@Value("${bonecp.username}")
	private String jdbcUsername;

	@Value("${bonecp.password}")
	private String jdbcPassword;

	@Value("${bonecp.driverClass}")
	private String driverClass;

	@Value("${bonecp.idleMaxAgeInMinutes}")
	private Integer idleMaxAgeInMinutes;

	@Value("${bonecp.idleConnectionTestPeriodInMinutes}")
	private Integer idleConnectionTestPeriodInMinutes;

	@Value("${bonecp.maxConnectionsPerPartition}")
	private Integer maxConnectionsPerPartition;

	@Value("${bonecp.minConnectionsPerPartition}")
	private Integer minConnectionsPerPartition;

	@Value("${bonecp.partitionCount}")
	private Integer partitionCount;

	@Value("${bonecp.acquireIncrement}")
	private Integer acquireIncrement;

	@Value("${bonecp.statementsCacheSize}")
	private Integer statementsCacheSize;

	@Value("${bonecp.standby.url}")
	private String jdbcUrlStandBy;

	@Value("${bonecp.standby.username}")
	private String jdbcUsernameStandBy;

	@Value("${bonecp.standby.password}")
	private String jdbcPasswordStandBy;
	
	@Value("${bonecp.interim.url}")
	private String jdbcUrlInterim;
	
	@Value("${bonecp.interim.username}")
	private String jdbcUsernameInterim;
	
	@Value("${bonecp.interim.password}")
	private String jdbcPasswordInterim;

	@Value("${email.host}")
	private String host;

	@Value("${email.port}")
	private Integer port;

	/**
	 * {@inheritDoc}
	 * 
	 * Start Spring Boot app as a Web application WAR
	 * 
	 */
	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {

		return application.sources(ReportingMainApplication.class);
	}

	/**
	 * Main method that can be used to start up application using an embedded
	 * Tomcat
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(ReportingMainApplication.class, args);
	}

	/**
	 * Setups up the data source connection pooling using BoneCP Defined as
	 * primary if we have 2 data source beans
	 * 
	 * @return
	 */
	@Primary
	@Bean(name = "reportingSchemaDataSource", destroyMethod = "close")
	public DataSource reportingSchemaDataSource() {
		BoneCPDataSource dataSource = new BoneCPDataSource();

		dataSource.setDriverClass(driverClass);
		dataSource.setJdbcUrl(jdbcUrl);
		dataSource.setUsername(jdbcUsername);
		dataSource.setPassword(jdbcPassword);
		dataSource
				.setIdleConnectionTestPeriodInMinutes(idleConnectionTestPeriodInMinutes);
		dataSource.setIdleMaxAgeInMinutes(idleMaxAgeInMinutes);
		dataSource.setMaxConnectionsPerPartition(maxConnectionsPerPartition);
		dataSource.setMinConnectionsPerPartition(minConnectionsPerPartition);
		dataSource.setPartitionCount(partitionCount);
		dataSource.setAcquireIncrement(acquireIncrement);
		dataSource.setStatementsCacheSize(statementsCacheSize);

		return dataSource;
	}

	/**
	 * Setups up the data source connection pooling using BoneCP
	 * 
	 * @return
	 */
	@Bean(name = "standByDatabaseDataSource", destroyMethod = "close")
	public DataSource standByDatabaseDataSource() {
		BoneCPDataSource dataSource = new BoneCPDataSource();

		dataSource.setDriverClass(driverClass);
		dataSource.setJdbcUrl(jdbcUrlStandBy);
		dataSource.setUsername(jdbcUsernameStandBy);
		dataSource.setPassword(jdbcPasswordStandBy);
		dataSource
				.setIdleConnectionTestPeriodInMinutes(idleConnectionTestPeriodInMinutes);
		dataSource.setIdleMaxAgeInMinutes(idleMaxAgeInMinutes);
		dataSource.setMaxConnectionsPerPartition(maxConnectionsPerPartition);
		dataSource.setMinConnectionsPerPartition(minConnectionsPerPartition);
		dataSource.setPartitionCount(partitionCount);
		dataSource.setAcquireIncrement(acquireIncrement);
		dataSource.setStatementsCacheSize(statementsCacheSize);

		return dataSource;
	}
	
	/**
	 * Setups up the data source connection pooling using BoneCP
	 * 
	 * @return
	 */
	@Bean(name = "interimDatabaseDataSource", destroyMethod = "close")
	public DataSource interimDatabaseDataSource() {
		BoneCPDataSource dataSource = new BoneCPDataSource();

		dataSource.setDriverClass(driverClass);
		dataSource.setJdbcUrl(jdbcUrlInterim);
		dataSource.setUsername(jdbcUsernameInterim);
		dataSource.setPassword(jdbcPasswordInterim);
		dataSource
				.setIdleConnectionTestPeriodInMinutes(idleConnectionTestPeriodInMinutes);
		dataSource.setIdleMaxAgeInMinutes(idleMaxAgeInMinutes);
		dataSource.setMaxConnectionsPerPartition(maxConnectionsPerPartition);
		dataSource.setMinConnectionsPerPartition(minConnectionsPerPartition);
		dataSource.setPartitionCount(partitionCount);
		dataSource.setAcquireIncrement(acquireIncrement);
		dataSource.setStatementsCacheSize(statementsCacheSize);

		return dataSource;
	}
	
	/**
	 * Configures the SMTP mail relay service for which to send emails through .
	 * 
	 * @author Gaurav Soni
	 * @return
	 */
	@Bean
	public JavaMailSender javaMailService() {

		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

		javaMailSender.setHost(host);
		javaMailSender.setPort(port);
		javaMailSender.setJavaMailProperties(getMailProperties());

		return javaMailSender;
	}

	/**
	 * Configures default settings for emails
	 * 
	 * @author Gaurav Soni
	 * @return
	 */
	private Properties getMailProperties() {

		Properties properties = new Properties();

		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.auth", "false");
		properties.setProperty("mail.smtp.starttls.enable", "false");
		properties.setProperty("mail.debug", "false");

		return properties;
	}

	/**
	 * Email template engine that will be used for thymeleaf templating in
	 * emails
	 * 
	 * @return
	 */
	/*
	 * @Bean public SpringTemplateEngine emailTemplateResolver() {
	 * 
	 * SpringTemplateEngine templateEngine = new SpringTemplateEngine();
	 * 
	 * 
	 * ClassLoaderTemplateResolver emailTemplateResolver = new
	 * ClassLoaderTemplateResolver();
	 * emailTemplateResolver.setPrefix("templates/");
	 * emailTemplateResolver.setSuffix(".html");
	 * emailTemplateResolver.setTemplateMode("HTML5");
	 * emailTemplateResolver.setCharacterEncoding("UTF-8");
	 * emailTemplateResolver.setOrder(1);
	 * 
	 * templateEngine.setTemplateResolver(emailTemplateResolver);
	 * 
	 * return templateEngine; }
	 */

}
