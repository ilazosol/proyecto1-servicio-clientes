package com.everis.springboot.clients.documents;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "clients")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientDocument {

	@Id
	private String id;
	
	private String first_name;
	
	private String last_name;
	
	private String client_type;
	
	private List<ProductDocument> products;

	public ClientDocument(String first_name, String last_name, String client_type, List<ProductDocument> products) {
		super();
		this.first_name = first_name;
		this.last_name = last_name;
		this.client_type = client_type;
		this.products = products;
	}

}
