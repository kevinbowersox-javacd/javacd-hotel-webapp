package com.linkedin.javacd.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/guest")
public class GuestController {

	private WebClient guestClient;

	@Autowired
	public GuestController(@Qualifier("guestServiceClient") WebClient guestClient) {
		this.guestClient = guestClient;
	}
	
	@GetMapping
	public Mono<String> guests() {

		String path = "/api/guest-service/guest";

		return guestClient.get()
				.uri(path)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(String.class);

	}
	
	
}
