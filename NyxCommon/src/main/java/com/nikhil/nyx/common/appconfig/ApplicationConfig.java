package com.nikhil.nyx.common.appconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * @purpose Bean contains all properties form Spring application.properties.Auto populated during application startup.
 */ 
@Component
@ConfigurationProperties("nyx")
@Getter @Setter
public class ApplicationConfig {

	private String mongoUserName;
	private String mongoPassword;
	private String mongoHost;
	private String mongoPort;
	private String mongoUri;
	private String dbName;
	private String mongoRequestCollection;
	private String mongoResponseCollection;
	private String mongoContentType;
	private String monggoGridFsChunkSize;
	private String mongoUriPrefix;
	
}
