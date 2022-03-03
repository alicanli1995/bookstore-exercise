package com.example.bookstore.events;

public class PurchaseEvent extends BusinessEvent {

	private final double sum;

	public PurchaseEvent(double sum) {
		this.sum = sum;
	}

	public double getSum() {
		return sum;
	}

}
