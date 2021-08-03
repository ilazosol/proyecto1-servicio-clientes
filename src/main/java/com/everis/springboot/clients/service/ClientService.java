package com.everis.springboot.clients.service;

import org.springframework.http.ResponseEntity;

import com.everis.springboot.clients.documents.ClientDocument;

import reactor.core.publisher.Mono;

public interface ClientService {
	
	ResponseEntity<?> saveClient(ClientDocument client);

}
