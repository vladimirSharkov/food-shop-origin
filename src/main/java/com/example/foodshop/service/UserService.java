package com.example.foodshop.service;

import com.example.foodshop.model.binding.UserRegisterBindingModel;
import com.example.foodshop.model.entity.UserEntity;
import com.example.foodshop.model.service.UserRegisterServiceModel;
import com.example.foodshop.model.service.UserServiceModel;

import java.math.BigDecimal;
import java.security.Principal;

public interface UserService {
    void registerUser(UserRegisterServiceModel userRegisterServiceModel);

    void initializeRoles();
    UserServiceModel findByUsernameAndPassword(String username, String password);

    UserEntity findByUsername(String name);


    Long getCartId(Principal principal);

    BigDecimal totalPriceForAllOrders(UserEntity user);

    void initializeAdmin();
}
