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
@RequestMapping("/room")
public class RoomController {

	private WebClient roomClient;

	@Autowired
	public RoomController(@Qualifier("roomServiceClient") WebClient roomClient) {
		this.roomClient = roomClient;
	}

	@GetMapping
	public Mono<String> rooms() {

		String path = "/api/room-service/room";

		return roomClient.get()
				.uri(path)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(String.class);

	}
	
}
