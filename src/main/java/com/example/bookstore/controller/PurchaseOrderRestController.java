package com.example.bookstore.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.bookstore.dto.BasketRequest;
import com.example.bookstore.dto.PurchaseResponse;
import com.example.bookstore.service.PurchaseOrderService;

@RestController
@RequestScope
@RequestMapping("/orders")
@CrossOrigin
@Validated
public class PurchaseOrderRestController {

	private PurchaseOrderService purchaseOrderService;
	
    public PurchaseOrderRestController(PurchaseOrderService purchaseOrderService) {
		this.purchaseOrderService = purchaseOrderService;
	}

	@PostMapping
    public PurchaseResponse purchase(@RequestBody BasketRequest basket){
        return purchaseOrderService.makePurchase(basket);
    }

}
