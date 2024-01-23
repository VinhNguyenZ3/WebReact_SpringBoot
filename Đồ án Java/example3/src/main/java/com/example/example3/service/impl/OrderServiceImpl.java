package com.example.example3.service.impl;

import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.example3.entity.Order;
import com.example.example3.service.OrderService;
import com.example.example3.repository.OrderRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        return optionalOrder.get();
    }

    @Override
    public Page<Order> getAllOrders(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Override
    public Order updateOrder(Order order) {
        Order existingOrder = orderRepository.findById(order.getId()).get();
        existingOrder.setTitle(order.getTitle());        
        existingOrder.setDescription(order.getDescription());
        existingOrder.setPhoto(order.getPhoto());
        existingOrder.setTotal(order.getTotal());        
        existingOrder.setUser(order.getUser());
        Order updatedOrder = orderRepository.save(existingOrder);
        return updatedOrder;
    }
    // CategoryRepository categoryRepository;
    // @Override
    // public void saveImage(MultipartFile file) throws IOException {
    //     Order image = new Order();
    //     image.setPhotoData(file.getBytes());
    //     Optional<Category> categoryOptional = categoryRepository.findById(1L);
    //     Category category = categoryOptional.get();
    //     image.setCategory(category);
    //     image.setDescription("AA");
    //     image.setPhoto("aaa");
    //     image.setPrice(0);
    //     image.setTitle("ANV");
    //     orderRepository.save(image);
    // }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
