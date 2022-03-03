package com.example.bookstore.controller;

import com.example.bookstore.BookstoreSpringDataApplication;
import com.example.bookstore.dto.BookRequest;
import com.example.bookstore.dto.BookResponse;
import com.example.bookstore.entity.Book;
import com.example.bookstore.service.BookCatalogService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(
        classes = BookstoreSpringDataApplication.class,
        webEnvironment = WebEnvironment.MOCK
)
@AutoConfigureMockMvc
class BookCatalogRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BookCatalogService bookCatalogService;

    @Test
    void findByIsbn() throws Exception {
        var bookCatalog = new BookResponse(
                1L,"123456789","Test Author",
                "Test Title", 450,2005,99.99,
                "Test Cover"
        );

        Mockito.when(bookCatalogService.findBookByIsbn("123456789")).thenReturn(bookCatalog);

        mockMvc.perform(get("/books/123456789")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isbn",containsString("123456789")))
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.author",is("Test Author")))
                .andExpect(jsonPath("$.pages",is(450)))
                .andExpect(jsonPath("$.year",is(2005)))
                .andExpect(jsonPath("$.cover",is("Test Cover")))
                .andExpect(jsonPath("$.price",is(99.99)));


    }

    @Test
    void deleteByIsbn() throws Exception {

        var bookCatalogResponse = new BookResponse(
                1L,"123456789","Test Author",
                "Test Title", 450,2005,99.99,
                "Test Cover"
        );

        Mockito.when(bookCatalogService.deleteBook("123456789")
        ).thenReturn(bookCatalogResponse);

        mockMvc.perform(delete("/books/123456789")
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isbn",is("123456789")))
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.author",is("Test Author")))
                .andExpect(jsonPath("$.pages",is(450)))
                .andExpect(jsonPath("$.year",is(2005)))
                .andExpect(jsonPath("$.cover",is("Test Cover")))
                .andExpect(jsonPath("$.price",is(99.99)));
    }

    @Test
    void findAllBooks() throws Exception {

        var bookCatalogResponse = new BookResponse(
                1L,"123456789","Test Author",
                "Test Title", 450,2005,99.99,
                "Test Cover"
        );

        var bookCatalogResponse2 = new BookResponse(
                2L,"987654321","Test Author2",
                "Test Title2", 654,1980,299.99,
                "Test Cover2"
        );


        Mockito.when(bookCatalogService.findAll(0,2))
                .thenReturn(List.of(bookCatalogResponse,bookCatalogResponse2));

        mockMvc.perform(get("/books?pageNo=0&pageSize=2")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()",is(2)))
                .andExpect(jsonPath("$[0].id",is(1)))
                .andExpect(jsonPath("$[1].id",is(2)))
                .andExpect(jsonPath("$[0].isbn",is("123456789")))
                .andExpect(jsonPath("$[0].id",is(1)))
                .andExpect(jsonPath("$[0].author",is("Test Author")))
                .andExpect(jsonPath("$[0].pages",is(450)))
                .andExpect(jsonPath("$[0].year",is(2005)))
                .andExpect(jsonPath("$[0].cover",is("Test Cover")))
                .andExpect(jsonPath("$[0].price",is(99.99)));

    }

    @Test
    void addBook() throws Throwable  {

        var books = new Book();
        books.setIsbn("123456789");
        books.setPages(450);
        books.setYear(2005);
        books.setPrice(99.99);
        books.setAuthor("Test Author");
        books.setTitle("Test Title");
        books.setCover(new byte[]{1,3,56,95});

        BookRequest request = modelMapper.map(books, BookRequest.class);

        Mockito.when(bookCatalogService.addBook(request))
                        .thenReturn(modelMapper.map(books,BookResponse.class));

        mockMvc.perform(
                        post("/books")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isbn",is("123456789")))
                .andExpect(jsonPath("$.author",is("Test Author")))
                .andExpect(jsonPath("$.pages",is(450)))
                .andExpect(jsonPath("$.year",is(2005)))
                .andExpect(jsonPath("$.cover",is(request.getCover())))
                .andExpect(jsonPath("$.price",is(99.99)));
    }

    @Test
    void updateBook() throws Exception {

        var books = new Book();
        books.setIsbn("123456789");
        books.setPages(450);
        books.setYear(2005);
        books.setPrice(99.99);
        books.setAuthor("Test Author");
        books.setTitle("Test Title");


        Mockito.when(bookCatalogService.updateBook(modelMapper.map(books,BookRequest.class)))
                .thenReturn(modelMapper.map(books,BookResponse.class));

        mockMvc.perform(
                        put("/books/"+books.getIsbn())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(books))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isbn",is("123456789")))
                .andExpect(jsonPath("$.author",is("Test Author")))
                .andExpect(jsonPath("$.pages",is(450)))
                .andExpect(jsonPath("$.year",is(2005)))
                .andExpect(jsonPath("$.price",is(99.99)));



    }

}