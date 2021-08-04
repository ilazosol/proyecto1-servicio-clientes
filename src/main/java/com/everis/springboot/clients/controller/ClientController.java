package com.everis.springboot.clients.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.everis.springboot.clients.documents.ClientDocument;
import com.everis.springboot.clients.service.ClientService;

import reactor.core.publisher.Mono;

@RestController
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@PostMapping("/saveClient")
	public Mono<ResponseEntity<?>> saveClient(@Valid @RequestBody ClientDocument client){
		System.out.println("Entro al metodo guardar");
		return clientService.saveClient(client);
	}

}
