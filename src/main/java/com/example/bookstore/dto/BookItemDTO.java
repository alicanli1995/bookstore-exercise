package com.example.bookstore.dto;

public class BookItemDTO {
	private String isbn;
	private int quantity;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof BookItemDTO)) return false;

		BookItemDTO that = (BookItemDTO) o;

		if (quantity != that.quantity) return false;
		return isbn.equals(that.isbn);
	}

	@Override
	public int hashCode() {
		int result = isbn.hashCode();
		result = 31 * result + quantity;
		return result;
	}

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
