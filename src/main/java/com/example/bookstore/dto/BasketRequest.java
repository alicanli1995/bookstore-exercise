package com.example.bookstore.dto;

import java.util.Collection;

public class BasketRequest {
    private Collection<BookItemDTO> items;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BasketRequest)) return false;

        BasketRequest that = (BasketRequest) o;

        return items.equals(that.items);
    }

    @Override
    public int hashCode() {
        return items.hashCode();
    }

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
