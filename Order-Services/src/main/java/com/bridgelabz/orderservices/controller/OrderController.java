package com.bridgelabz.orderservices.controller;


import com.bridgelabz.orderservices.Model.OrderModel;
import com.bridgelabz.orderservices.dto.OrderDto;
import com.bridgelabz.orderservices.dto.UserResponse;
import com.bridgelabz.orderservices.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/addOrder")
    public ResponseEntity<UserResponse>addToOrder(@RequestBody OrderDto orderDto){
        OrderModel orderModel=orderService.addItemsOrder(orderDto);
        UserResponse userResponse=new UserResponse("order added successfully",orderModel);
        return new ResponseEntity<UserResponse >(userResponse, HttpStatus.OK);
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<UserResponse>placeOrder(@RequestParam Long id){
        orderService.placeOrder(id);
        UserResponse userResponse=new UserResponse("order placed successfully","with"+id);
        return new ResponseEntity<UserResponse >(userResponse, HttpStatus.OK);
    }
    @PostMapping("/getOrder")
    public ResponseEntity<UserResponse>getOrder(@RequestParam Long id){
        OrderModel orderModel=orderService.getOrder(id);
        UserResponse userResponse=new UserResponse("order found"+"with"+id,orderModel);
        return new ResponseEntity<UserResponse >(userResponse, HttpStatus.OK);
    }
}
