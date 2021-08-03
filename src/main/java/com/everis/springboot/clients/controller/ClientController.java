package com.everis.springboot.clients.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.everis.springboot.clients.documents.ClientDocument;
import com.everis.springboot.clients.service.ClientService;

@RestController
@RequestMapping("/api/client")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@PostMapping("/saveClient")
	public ResponseEntity<?> saveClient(@RequestBody ClientDocument client){
		return clientService.saveClient(client);
	}

}
