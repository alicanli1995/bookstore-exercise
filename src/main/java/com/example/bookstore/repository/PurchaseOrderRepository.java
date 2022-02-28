package com.example.bookstore.repository;

import com.example.bookstore.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder,Long> {
}
