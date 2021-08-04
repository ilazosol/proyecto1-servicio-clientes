package com.everis.springboot.clients.documents;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDocument {
	
	private String id;
	
	private String product_type;
	
	private Double mount;

}
