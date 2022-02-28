package com.example.bookstore.dto;

public class BookItemDTO {
	private String isbn;
	private int quantity;

	public BookItemDTO() {
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "BookItemDTO{" + "isbn='" + isbn + '\'' + ", quantity=" + quantity + '}';
	}
}
