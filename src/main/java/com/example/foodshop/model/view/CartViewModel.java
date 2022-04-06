package com.example.foodshop.model.view;

import com.example.foodshop.model.entity.OrderEntity;
import com.example.foodshop.model.entity.ProductEntity;

import java.math.BigDecimal;

public class CartViewModel {

    private BigDecimal price;
    private Integer quantity;
    private OrderEntity cart;
    private ProductEntity products;
    private BigDecimal totalPrice;

    public CartViewModel() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CartViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public CartViewModel setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public OrderEntity getCart() {
        return cart;
    }

    public CartViewModel setCart(OrderEntity cart) {
        this.cart = cart;
        return this;
    }

    public ProductEntity getProducts() {
        return products;
    }

    public CartViewModel setProducts(ProductEntity products) {
        this.products = products;
        return this;
    }
    public BigDecimal totalPrice() {

        return BigDecimal.valueOf(this.quantity).multiply(this.price);
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public CartViewModel setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }
}
