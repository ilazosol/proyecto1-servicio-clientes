package com.everis.springboot.clients.documents;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "product")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDocument {
	
	@Id
	private String id;
	
	private ProductTypeDocument product_type;
	
	private Double mount;
	

}
