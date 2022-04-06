package com.example.foodshop.service.impl;

import com.example.foodshop.model.entity.OrderEntity;
import com.example.foodshop.model.entity.CartEntity;
import com.example.foodshop.model.entity.ProductEntity;
import com.example.foodshop.repository.CartRepository;
import com.example.foodshop.service.CartService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public CartEntity createCart(ProductEntity product, Integer quantity, OrderEntity order) {
        CartEntity cart = new CartEntity();
        cart.setCount(quantity);
        cart.setTotalPrice(product.getPrice().multiply(BigDecimal.valueOf(quantity)));
        cart.setOrder(order);
        cart.setProducts(product);

        cartRepository.save(cart);

        return cart;
    }

    @Override
    public void deleteCart(CartEntity cart) {
        cartRepository.deleteById(cart.getId());
    }
}
