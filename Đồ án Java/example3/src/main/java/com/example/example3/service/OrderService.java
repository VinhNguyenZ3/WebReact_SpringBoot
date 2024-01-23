package com.example.example3.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.example3.entity.Order;

public interface OrderService {
    Order createOrder(Order order);

    Order getOrderById(Long orderId);

    Page<Order> getAllOrders(Pageable pageable);

    Order updateOrder(Order order);

    void deleteOrder(Long orderId);
    // public void saveImage(MultipartFile file) throws IOException;
}
