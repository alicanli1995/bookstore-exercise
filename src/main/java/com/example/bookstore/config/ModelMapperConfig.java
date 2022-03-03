package com.example.bookstore.config;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.bookstore.dto.BookRequest;
import com.example.bookstore.dto.BookResponse;
import com.example.bookstore.entity.Book;

@Configuration
public class ModelMapperConfig {
	private static final Converter<Book, BookResponse> BOOK_TO_BOOK_RESPONSE_CONVERTER =
			context -> new BookResponse(
					context.getSource().getId(), 
					context.getSource().getIsbn(), 
					context.getSource().getAuthor(), 
					context.getSource().getTitle(), 
					context.getSource().getPages(), 
					context.getSource().getYear(),
					context.getSource().getPrice(), 
					context.getSource().getCoverBase64());
			
	private static final Converter<BookRequest, Book> BOOK_REQUEST_TO_BOOK_CONVERTER =
			context -> new Book(
					context.getSource().getIsbn(), 
					context.getSource().getAuthor(), 
					context.getSource().getTitle(), 
					context.getSource().getPages(), 
					context.getSource().getYear(),
					context.getSource().getPrice(), 
					context.getSource().getBase64Cover());
					
	@Bean("standardModelMapper")
	ModelMapper createModelMapper() {
		var modelMapper = new ModelMapper();
		modelMapper.addConverter(BOOK_TO_BOOK_RESPONSE_CONVERTER,Book.class,BookResponse.class);
		modelMapper.addConverter(BOOK_REQUEST_TO_BOOK_CONVERTER,BookRequest.class,Book.class);
		return modelMapper;
	}
}
