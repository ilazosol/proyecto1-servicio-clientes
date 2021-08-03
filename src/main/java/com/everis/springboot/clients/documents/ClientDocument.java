package com.everis.springboot.clients.documents;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "clients")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientDocument {

	@Id
	private String id;
	
	private String name;
	
	private String last_name;
	
	private ClientTypeDocument type;
	
	private List<ProductDocument> products;
	
	
}
