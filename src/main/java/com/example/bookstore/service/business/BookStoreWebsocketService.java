package com.example.bookstore.service.business;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.example.bookstore.events.BusinessEvent;
import com.example.bookstore.service.EventPublisher;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class BookStoreWebsocketService implements WebSocketHandler, EventPublisher {
	private final Logger logger = LoggerFactory.getLogger(BookStoreWebsocketService.class);

	private Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
	private ObjectMapper objectMapper;

	public BookStoreWebsocketService(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.put(session.getId(), session);
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable e) throws Exception {
		logger.error("Error has occured while transfering data using websocket: {}", e.getMessage());
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		sessions.remove(session.getId());
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

	@Override
	public void publishEvent(BusinessEvent event) {
		sessions.forEach((id, session) -> {
			try {
				var jsonEvent = objectMapper.writeValueAsString(event);
				session.sendMessage(new TextMessage(jsonEvent));
			} catch (Exception e) {
				logger.error("Error has occured while converting event object to json: {}", e.getMessage());
			}
		});
	}

}
