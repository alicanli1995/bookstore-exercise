package com.example.bookstore.controller;

import com.example.bookstore.BookstoreSpringDataApplication;
import com.example.bookstore.dto.BasketRequest;
import com.example.bookstore.dto.BookItemDTO;
import com.example.bookstore.dto.PurchaseResponse;
import com.example.bookstore.service.BookCatalogService;
import com.example.bookstore.service.PurchaseOrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(
        classes = BookstoreSpringDataApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
@AutoConfigureMockMvc
class PurchaseOrderRestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    PurchaseOrderService purchaseOrderService;


    @Test
    void purchase() throws Exception {
        var bookItemDto = new BookItemDTO();
        bookItemDto.setIsbn("123456789");
        bookItemDto.setQuantity(5);

        var basketRequest = new BasketRequest();
        basketRequest.setItems(List.of(bookItemDto));

        var purchaseResponse = new PurchaseResponse("Accept");

        Mockito.when(purchaseOrderService.makePurchase(basketRequest))
                .thenReturn(purchaseResponse);

        mockMvc.perform(
                        post("/orders")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(basketRequest))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status",is("Accept")));

    }
}