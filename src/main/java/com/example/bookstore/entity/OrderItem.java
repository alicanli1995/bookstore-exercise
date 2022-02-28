package com.example.bookstore.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
@DynamicUpdate
@DynamicInsert
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
	private Book book;
	private int quantity;

	public OrderItem() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public static double itemToPrice(OrderItem item) {
		return item.getQuantity() * item.getBook().getPrice();
	}

	@Override
	public String toString() {
		return "OrderItem{" + "id=" + id + ", book=" + book + ", quantity=" + quantity + '}';
	}
}
