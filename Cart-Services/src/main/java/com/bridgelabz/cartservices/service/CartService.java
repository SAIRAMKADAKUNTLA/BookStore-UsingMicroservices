package com.bridgelabz.cartservices.service;


import com.bridgelabz.cartservices.dto.CartDto;
import com.bridgelabz.cartservices.model.CartModel;

import java.util.List;

public interface CartService {
    CartModel addToCart(CartDto cartDto);

    List<CartModel> getAll();

    CartModel getById(Long id);

    CartModel updateQty(Long id, Long quantity);

   void deleteById(Long id);
}
