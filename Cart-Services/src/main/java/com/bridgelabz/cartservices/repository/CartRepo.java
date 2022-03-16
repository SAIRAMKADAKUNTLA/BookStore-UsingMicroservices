package com.bridgelabz.cartservices.repository;


import com.bridgelabz.cartservices.model.CartModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartRepo extends JpaRepository<CartModel,Long> {

}
