package com.example.bookstore.controller;

import java.util.Collection;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.bookstore.dto.BookRequest;
import com.example.bookstore.dto.BookResponse;
import com.example.bookstore.exception.ErrorMessage;
import com.example.bookstore.exception.RestExceptionBase;
import com.example.bookstore.service.BookCatalogService;

@RestController
@RequestScope
@RequestMapping("/books")
@CrossOrigin
@Validated
public class BookCatalogRestController {

	private BookCatalogService bookCatalogService;

	public BookCatalogRestController(BookCatalogService bookCatalogService) {
		this.bookCatalogService = bookCatalogService;
	}

	@GetMapping("{isbn}")
	public BookResponse findByIsbn(@PathVariable String isbn) {
		return bookCatalogService.findBookByIsbn(isbn);
	}

	@DeleteMapping("{isbn}")
	public BookResponse deleteByIsbn(@PathVariable String isbn) {
		return bookCatalogService.deleteBook(isbn);
	}

	@GetMapping
	public Collection<BookResponse> findAllBooks(
			@RequestParam(required = false, defaultValue = "0") @Min(0) int pageNo,
			@RequestParam(required = false, defaultValue = "10") @Max(25) int pageSize) {
		return bookCatalogService.findAll(pageNo, pageSize);
	}

	@PostMapping
	public BookResponse addBook(@RequestBody BookRequest book) {
		return bookCatalogService.addBook(book);
	}

	@PutMapping("{isbn}")
	public BookResponse updateBook(@PathVariable String isbn, @RequestBody BookRequest book) {
		return bookCatalogService.updateBook(book);
	}

	@ExceptionHandler({ RestExceptionBase.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage handleErrors(RestExceptionBase e) {
		return new ErrorMessage(e.getMessageId(), e.getDebugId(), e.getMessage());
	}
}
