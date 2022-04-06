package com.example.foodshop.service;

import com.example.foodshop.model.entity.CartEntity;
import com.example.foodshop.model.entity.OrderEntity;
import com.example.foodshop.model.entity.ProductEntity;

public interface CartService {


    CartEntity createCart(ProductEntity product, Integer quantity, OrderEntity order);

    void deleteCart(CartEntity cart);
}
