package com.example.bookstore.service.business;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.bookstore.dto.BasketRequest;
import com.example.bookstore.dto.PurchaseResponse;
import com.example.bookstore.entity.OrderItem;
import com.example.bookstore.entity.PurchaseOrder;
import com.example.bookstore.events.PurchaseEvent;
import com.example.bookstore.repository.BookCatalogRepository;
import com.example.bookstore.repository.PurchaseOrderRepository;
import com.example.bookstore.service.EventPublisher;
import com.example.bookstore.service.PurchaseOrderService;

@Service
public class StandardPurchaseOrderService implements PurchaseOrderService {
    private PurchaseOrderRepository purchaseOrderRepository;
    private BookCatalogRepository bookCatalogRepository;
    private EventPublisher eventPublisher;

    public StandardPurchaseOrderService(PurchaseOrderRepository purchaseOrderRepository,
			BookCatalogRepository bookCatalogRepository, EventPublisher eventPublisher) {
		this.purchaseOrderRepository = purchaseOrderRepository;
		this.bookCatalogRepository = bookCatalogRepository;
		this.eventPublisher = eventPublisher;
	}

	@Override
    public PurchaseResponse makePurchase(BasketRequest basket){
        var po= new PurchaseOrder();
        var items= basket.getItems().stream().map(itemDto -> {
               OrderItem item= new OrderItem();
               item.setQuantity(itemDto.getQuantity());
               String isbn= itemDto.getIsbn();
               var book = bookCatalogRepository.findByIsbn(isbn).orElseThrow();
               item.setBook(book);
               return item;
            }).collect(Collectors.toSet());
        po.setItems(items);
        purchaseOrderRepository.save(po);
        var sum= items.stream()
                       .mapToDouble(OrderItem::itemToPrice)
                       .sum();
        eventPublisher.publishEvent(new PurchaseEvent(sum));
        return new PurchaseResponse("success");
    }
}
