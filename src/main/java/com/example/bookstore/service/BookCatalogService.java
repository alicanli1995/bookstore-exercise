package com.example.bookstore.service;

import java.util.Collection;

import com.example.bookstore.dto.BookRequest;
import com.example.bookstore.dto.BookResponse;

public interface BookCatalogService {

	BookResponse findBookByIsbn(String isbn);

	BookResponse deleteBook(String isbn);

	Collection<BookResponse> findAll(int pageNo,int pageSize);

	BookResponse addBook(BookRequest book);

	BookResponse updateBook(BookRequest book);

}
