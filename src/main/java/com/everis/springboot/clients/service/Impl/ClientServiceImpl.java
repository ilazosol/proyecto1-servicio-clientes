package com.everis.springboot.clients.service.Impl;

import java.util.HashMap;
import java.util.List;
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
	public ResponseEntity<?> saveClient(ClientDocument client) {
		Map<String, Object> response = new HashMap<>();
		Integer cAhorro = 0;
		Integer cCorriente = 0;
		
		if(client.getType().getDescription().equals("Personal")) {
			for (ProductDocument prod : client.getProducts()) {
				if(prod.getProduct_type().getDescription().equals("Cuenta de Ahorro")) {
					cAhorro++;
				}
				if(prod.getProduct_type().getDescription().equals("Cuenta Corriente")) {
					cCorriente++;
				}
				if(cAhorro>1) {
					response.put("mensaje", "No puede guardar un cliente con mas de una cuenta de ahorro");
					return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
				}
				if(cCorriente>1) {
					response.put("mensaje", "No puede guardar un cliente con mas de una cuenta corriente");
					return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
				}
				
			}
			
			Mono<ClientDocument> clientSaved = clientDao.save(client);
			response.put("mensaje", "Se registro el usuario correctamente");
			response.put("client", clientSaved);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}else if(client.getType().getDescription().equals("Empresarial")) {
			for (ProductDocument prod : client.getProducts()) {
				if(prod.getProduct_type().getDescription().equals("Cuenta de Ahorro")) {
					response.put("mensaje", "Un usuario empresarial no puede tener cuenta de ahorro");
					return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
				}
				if(prod.getProduct_type().getDescription().equals("Cuenta Plazo Fijo")) {
					response.put("mensaje", "Un usuario empresarial no puede tener cuenta a plazo fijo");
					return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
				}

			}
			
			Mono<ClientDocument> clientSaved = clientDao.save(client);
			response.put("mensaje", "Se registro el usuario correctamente");
			response.put("client", clientSaved);
			return new ResponseEntity<>(response,HttpStatus.OK);
		}else {
			response.put("mensaje", "Tipo de usuario indefinido");
			return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
		}
		
	}

}
