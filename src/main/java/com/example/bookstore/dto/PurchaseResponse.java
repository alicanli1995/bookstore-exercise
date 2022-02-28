package com.example.bookstore.dto;

public class PurchaseResponse {

	private final String status;

	public PurchaseResponse(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
