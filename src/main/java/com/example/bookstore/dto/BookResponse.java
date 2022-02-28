package com.example.bookstore.dto;

public record BookResponse (
	Long id,
	 String isbn,
	 String author,
	 String title,
	 int pages,
	 int year,
	 double price,
	 String cover){
}