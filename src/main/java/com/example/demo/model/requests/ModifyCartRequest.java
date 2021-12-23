package com.example.demo.model.requests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ModifyCartRequest {

	private static final Logger logger = LoggerFactory.getLogger(ModifyCartRequest.class);
	
	@JsonProperty
	private String username;
	
	@JsonProperty
	private long itemId;
	
	@JsonProperty
	private int quantity;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}
