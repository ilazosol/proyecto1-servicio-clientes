package com.everis.springboot.clients.service.Impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.everis.springboot.clients.dao.ClientDao;
import com.everis.springboot.clients.documents.ClientDocument;
import com.everis.springboot.clients.documents.ProductDocument;
import com.everis.springboot.clients.service.ClientService;

import reactor.core.publisher.Mono;

@Service
public class ClientServiceImpl implements ClientService {
	
	@Autowired
	private ClientDao clientDao;

	@Override
	public Mono<ResponseEntity<?>> saveClient(ClientDocument client) {
		Map<String, Object> response = new HashMap<>();
		Integer cAhorro = 0;
		Integer cCorriente = 0;
		
		if(client.getClient_type().equals("Personal")) {
			for (ProductDocument prod : client.getProducts()) {
				if(prod.getProduct_type().equals("Cuenta de Ahorro")) {
					cAhorro++;
				}
				if(prod.getProduct_type().equals("Cuenta Corriente")) {
					cCorriente++;
				}
				if(cAhorro>1) {
					response.put("mensaje", "No puede guardar un cliente con mas de una cuenta de ahorro");
					return Mono.just(new ResponseEntity<>(response,HttpStatus.BAD_REQUEST));
				}
				if(cCorriente>1) {
					response.put("mensaje", "No puede guardar un cliente con mas de una cuenta corriente");
					return Mono.just(new ResponseEntity<>(response,HttpStatus.BAD_REQUEST));
				}
				
			}
			
			return clientDao.save(client).map( c -> {
				return new ResponseEntity<>(c,HttpStatus.OK);
			});
			
		}else if(client.getClient_type().equals("Empresarial")) {
			for (ProductDocument prod : client.getProducts()) {
				if(prod.getProduct_type().equals("Cuenta de Ahorro")) {
					response.put("mensaje", "Un usuario empresarial no puede tener cuenta de ahorro");
					return Mono.just(new ResponseEntity<>(response,HttpStatus.BAD_REQUEST));
				}
				if(prod.getProduct_type().equals("Cuenta Plazo Fijo")) {
					response.put("mensaje", "Un usuario empresarial no puede tener cuenta a plazo fijo");
					return Mono.just(new ResponseEntity<>(response,HttpStatus.BAD_REQUEST));
				}

			}
			
	
			return clientDao.save(client).map( c -> {
				return new ResponseEntity<>(c,HttpStatus.OK);
			});
		}else {
			response.put("mensaje", "Tipo de usuario indefinido");
			return Mono.just(new ResponseEntity<>(response,HttpStatus.BAD_REQUEST));
		}
		
	}

}
