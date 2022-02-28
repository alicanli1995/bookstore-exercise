package com.example.bookstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookstore.entity.Book;

public interface BookCatalogRepository extends JpaRepository<Book,Long>{
    Optional<Book> findByIsbn(String isbn);
}
