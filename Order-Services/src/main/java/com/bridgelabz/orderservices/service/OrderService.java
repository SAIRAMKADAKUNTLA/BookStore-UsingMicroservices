package com.bridgelabz.orderservices.service;


import com.bridgelabz.orderservices.Model.OrderModel;
import com.bridgelabz.orderservices.dto.OrderDto;

public interface OrderService {
    void placeOrder(Long id);

    OrderModel addItemsOrder(OrderDto orderDto);

    OrderModel getOrder(Long id);
}
