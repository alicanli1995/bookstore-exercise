package com.example.bookstore.service;

import com.example.bookstore.events.BusinessEvent;

public interface EventPublisher {
	void publishEvent(BusinessEvent event);
}
