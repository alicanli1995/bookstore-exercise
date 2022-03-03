package com.example.bookstore.service;

import com.example.bookstore.dto.BasketRequest;
import com.example.bookstore.dto.PurchaseResponse;

public interface PurchaseOrderService {

	PurchaseResponse makePurchase(BasketRequest basket);

}
