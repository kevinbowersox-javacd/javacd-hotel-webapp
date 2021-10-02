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
@RequestMapping("/booking")
public class BookingController {

	private WebClient bookingClient;

	@Autowired
	public BookingController(@Qualifier("bookingServiceClient") WebClient bookingClient) {
		this.bookingClient = bookingClient;
	}

	@GetMapping
	public Mono<String> bookings() {

		String path = "/api/booking-service/booking";

		return bookingClient.get()
				.uri(path)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(String.class);

	}

}
