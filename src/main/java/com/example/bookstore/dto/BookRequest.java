package com.example.bookstore.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.apache.tomcat.util.codec.binary.Base64;

public class BookRequest {
	@NotBlank
	private String isbn;
	@NotBlank
	private String author;
	@NotBlank
	private String title;
	@Min(20)
	private int pages;
	private int year;
	private double price;
	private String cover;

	public BookRequest() {
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getCover() {
		return cover;
	}
	
	public byte[] getBase64Cover() {
		return Base64.decodeBase64(cover);
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	@Override
	public String toString() {
		return "BookRequest [isbn=" + isbn + ", author=" + author + ", title=" + title + ", pages=" + pages + ", year="
				+ year + ", price=" + price + "]";
	}

}
