package com.linkedin.javacd.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import com.flagsmith.FlagsmithClient;
import com.flagsmith.FlagsmithConfig;
import com.flagsmith.FlagsmithLoggerLevel;

@Configuration
public class ApplicationConfig{

	@Value("${service.room.port}")
	private String roomServicePort;
	
	@Value("${service.guest.port}")
	private String guestServicePort;
	
	@Value("${service.booking.port}")
	private String bookingServicePort;
	
	@Value("${service.room.domain}")
	private String roomServiceDomain;
	
	@Value("${service.guest.domain}")
	private String guestServiceDomain;
	
	@Value("${service.booking.domain}")
	private String bookingServiceDomain;
	
	@Value("${flagsmith.uri}")
	private String flagsmithUri;
	
	@Bean("roomServiceClient")
	public WebClient roomServiceClient() {
		String port = this.roomServicePort.isEmpty() ? "":":" + this.roomServicePort; 
		return WebClient.create(String.format("%s%s", this.roomServiceDomain, port));
	}
	
	@Bean("guestServiceClient")
	public WebClient guestServiceClient() {
		String port = this.guestServicePort.isEmpty() ? "":":" + this.guestServicePort; 
		return WebClient.create(String.format("%s%s", this.guestServiceDomain, port));
	}
	
	@Bean("bookingServiceClient")
	public WebClient bookingServiceClient() {
		String port = this.bookingServicePort.isEmpty() ? "":":" + this.bookingServicePort; 
		return WebClient.create(String.format("%s%s", this.bookingServiceDomain, port));
	}
	
	@Bean
	public FlagsmithClient flagSmith() {
		return FlagsmithClient.newBuilder()
			    .enableLogging(FlagsmithLoggerLevel.INFO)
			    .setApiKey("jYGxfcJLP9pQWtWK5JAUqs")
			    .withConfiguration(FlagsmithConfig.newBuilder().baseURI(flagsmithUri).build())
			    .build();
	}
	

}
