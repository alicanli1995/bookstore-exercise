package com.example.bookstore.dto;

import java.util.Collection;

public class BasketRequest {
    private Collection<BookItemDTO> items;

    public BasketRequest() {
    }

    public Collection<BookItemDTO> getItems() {
        return items;
    }

    public void setItems(Collection<BookItemDTO> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "BasketDTO{" +
                "items=" + items +
                '}';
    }
}
