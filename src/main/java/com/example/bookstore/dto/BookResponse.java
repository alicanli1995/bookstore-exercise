package com.example.bookstore.dto;

import lombok.EqualsAndHashCode;


public record BookResponse (
	Long id,
	 String isbn,
	 String author,
	 String title,
	 int pages,
	 int year,
	 double price,
	 String cover){

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof BookResponse)) return false;

		BookResponse that = (BookResponse) o;

		return id.equals(that.id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
}