package com.example.foodshop.service;

import com.example.foodshop.model.entity.OrderEntity;
import com.example.foodshop.model.entity.ProductEntity;
import com.example.foodshop.model.entity.UserEntity;

import java.math.BigDecimal;

public interface OrderService {

    OrderEntity createOrder(UserEntity user);

    void addProduct(UserEntity user, Integer quantity, ProductEntity product);

    void deleteOrder(UserEntity user);

    void setTotalPrice(BigDecimal total, OrderEntity order);
}
