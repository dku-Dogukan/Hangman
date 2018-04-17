package com.dku.hangman.v1.resources;

import java.io.Serializable;

public class ResponseResource implements Serializable {


	private static final long serialVersionUID = 1L;

	private String description;
	private Object transferData;
	


	public String getDescription() {
		return description;
	}
	

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setTransferData(Object transferData) {
		this.transferData = transferData;
	}
	
	public Object getTransferData() {
		return transferData;
	}

}
