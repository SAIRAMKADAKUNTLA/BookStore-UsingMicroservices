package com.bridgelabz.orderservices.repository;


import com.bridgelabz.orderservices.Model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<OrderModel,Long> {
}
