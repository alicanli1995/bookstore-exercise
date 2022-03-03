package com.example.bookstore.dto;

public class PurchaseResponse {

	private final String status;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof PurchaseResponse)) return false;

		PurchaseResponse that = (PurchaseResponse) o;

		return status.equals(that.status);
	}

	@Override
	public int hashCode() {
		return status.hashCode();
	}

	public PurchaseResponse(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
