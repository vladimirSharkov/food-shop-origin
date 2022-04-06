package com.example.foodshop.repository;

import com.example.foodshop.model.entity.CartEntity;
import com.example.foodshop.model.entity.OrderEntity;
import com.example.foodshop.model.view.CartViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity,Long> {

}
