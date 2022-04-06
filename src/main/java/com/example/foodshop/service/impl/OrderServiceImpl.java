package com.example.foodshop.service.impl;

import com.example.foodshop.model.entity.CartEntity;
import com.example.foodshop.model.entity.OrderEntity;
import com.example.foodshop.model.entity.ProductEntity;
import com.example.foodshop.model.entity.UserEntity;
import com.example.foodshop.repository.OrderRepository;
import com.example.foodshop.service.CartService;
import com.example.foodshop.service.OrderService;
import com.example.foodshop.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final ProductService productService;

    public OrderServiceImpl(OrderRepository orderRepository, CartService cartService, ProductService productService) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.productService = productService;
    }

    @Override
    public OrderEntity createOrder(UserEntity user) {
        OrderEntity order =new OrderEntity();
        order.setTotalPrice(BigDecimal.valueOf(0));
        order.setUser(user);
        order.setCarts(List.of());
        orderRepository.save(order);
        return order;
    }

    @Override
    public void addProduct(UserEntity user, Integer quantity, ProductEntity product) {
        OrderEntity order = orderRepository.findById(user.getOrder().getId()).orElse(null);
        CartEntity cart = cartService.createCart(product,quantity,order);
        assert order != null;
        BigDecimal total = order.getCarts().stream().map(CartEntity::getTotalPrice).reduce(BigDecimal.valueOf(0), BigDecimal::add);
        setTotalPrice(total,order);
    }

    @Override
    public void deleteOrder(UserEntity user) {
        OrderEntity order = user.getOrder();
        order.setTotalPrice(BigDecimal.valueOf(0));
        for (CartEntity cart : order.getCarts()) {
            ProductEntity products = cart.getProducts();
            products.setQuantity(products.getQuantity()-cart.getCount());
            productService.setQuantity(products);
            cartService.deleteCart(cart);
        }
        order.setCarts(new ArrayList<>());

        orderRepository.saveAndFlush(order);
    }

    @Override
    public void setTotalPrice(BigDecimal total, OrderEntity order) {
        order.setTotalPrice(total);
        orderRepository.saveAndFlush(order);
    }


}
