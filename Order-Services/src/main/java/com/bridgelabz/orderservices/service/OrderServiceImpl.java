package com.bridgelabz.orderservices.service;


import com.bridgelabz.orderservices.Model.OrderModel;
import com.bridgelabz.orderservices.dto.OrderDto;
import com.bridgelabz.orderservices.exception.BookStoreExceptions;
import com.bridgelabz.orderservices.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepo orderRepo;
    @Override
    public void placeOrder(Long id) {


    }

    @Override
    public OrderModel addItemsOrder(OrderDto orderDto) {
        OrderModel orderModel=new OrderModel(orderDto);
        return orderRepo.save(orderModel);
    }

    @Override
    public OrderModel getOrder(Long id) {
        return orderRepo.findById(id).orElseThrow(()->new BookStoreExceptions("not found"));
    }
}
