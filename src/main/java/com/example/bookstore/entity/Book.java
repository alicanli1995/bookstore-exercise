package com.example.bookstore.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.apache.tomcat.util.codec.binary.Base64;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String isbn;
    private String author;
    private String title;
    private int pages;
    @Column(name="publication_year")
    private int year;
    private double price;
    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] cover;

    public Book() {
    }

    public Book(String isbn, String author, String title, int pages, int year, double price, byte[] cover) {
		this.isbn = isbn;
		this.author = author;
		this.title = title;
		this.pages = pages;
		this.year = year;
		this.price = price;
		this.cover = cover;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public byte[] getCover() {
        return cover;
    }
    
    public String getCoverBase64() {
    	if (Objects.isNull(cover))
    		return null;
    	return Base64.encodeBase64String(cover);
    }

    public void setCover(byte[] cover) {
        this.cover = cover;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", pages=" + pages +
                ", year=" + year +
                ", price=" + price +
                '}';
    }
}
