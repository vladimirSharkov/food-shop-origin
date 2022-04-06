package com.example.foodshop.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "carts")
public class CartEntity extends BaseEntity {


    private BigDecimal totalPrice;
    private Integer count;
    private OrderEntity order;
    private ProductEntity products;

    public CartEntity() {
    }


    public Integer getCount() {
        return count;
    }

    public CartEntity setCount(Integer quantity) {
        this.count = quantity;
        return this;
    }

    @ManyToOne
    public OrderEntity getOrder() {
        return order;
    }

    public CartEntity setOrder(OrderEntity cart) {
        this.order = cart;
        return this;
    }
    @ManyToOne
    public ProductEntity getProducts() {
        return products;
    }

    public CartEntity setProducts(ProductEntity products) {
        this.products = products;
        return this;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public CartEntity setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }


}
