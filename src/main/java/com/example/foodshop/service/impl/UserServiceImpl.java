package com.example.foodshop.service.impl;

import com.example.foodshop.model.binding.UserRegisterBindingModel;
import com.example.foodshop.model.entity.OrderEntity;
import com.example.foodshop.model.entity.RoleEntity;
import com.example.foodshop.model.entity.UserEntity;
import com.example.foodshop.model.enumeration.RoleNameEnum;
import com.example.foodshop.model.service.UserRegisterServiceModel;
import com.example.foodshop.model.service.UserServiceModel;
import com.example.foodshop.repository.RoleRepository;
import com.example.foodshop.repository.UserRepository;
import com.example.foodshop.service.CartService;
import com.example.foodshop.service.OrderService;
import com.example.foodshop.service.RoleService;
import com.example.foodshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final CartService cartService;
    private final RoleService roleService;
    private final OrderService orderService;
    private final FoodShoppUserService foodShoppUserService;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, RoleRepository roleRepository, CartService cartService, RoleService roleService, OrderService orderService, FoodShoppUserService foodShoppUserService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.cartService = cartService;
        this.roleService = roleService;
        this.orderService = orderService;
        this.foodShoppUserService = foodShoppUserService;
    }

    @Override
    public void registerUser(UserRegisterServiceModel userRegisterServiceModel) {
        RoleEntity userRole = roleRepository.findByRole(RoleNameEnum.USER);

        UserEntity user = new UserEntity();

        user
                .setUsername(userRegisterServiceModel.getUsername())
                .setFullName(userRegisterServiceModel.getFullName())
                .setAddress(userRegisterServiceModel.getAddress())
                .setPassword(passwordEncoder.encode(userRegisterServiceModel.getPassword()))
                .setEmail(userRegisterServiceModel.getEmail())
                .setRoles(Set.of(userRole));

        OrderEntity order = orderService.createOrder(user);
        user.setOrder(order);
        userRepository.save(user);

        UserDetails principal = foodShoppUserService.loadUserByUsername(user.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                user.getPassword(),
                principal.getAuthorities());

        SecurityContextHolder
                .getContext()
                .setAuthentication(authentication);
    }

    @Override
    public void initializeRoles() {
        if (roleRepository.count() == 0) {
            RoleEntity admin = new RoleEntity();
            admin.setRole(RoleNameEnum.ADMIN);

            RoleEntity user = new RoleEntity();
            user.setRole(RoleNameEnum.USER);

            roleRepository.saveAll(List.of(admin, user));

        }
    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password)
                .map(userEntity -> modelMapper.map(userEntity, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public UserEntity findByUsername(String name) {

        return userRepository.findByUsername(name).orElse(null);
    }

    @Override
    public Long getCartId(Principal principal) {
        UserEntity user = userRepository.findByUsername(principal.getName()).orElse(null);
        assert user != null;
        return user.getOrder().getId();
    }

    @Override
    public BigDecimal totalPriceForAllOrders(UserEntity user) {

        return user.getOrder().getTotalPrice();
    }

    @Override
    public void initializeAdmin() {
        if (userRepository.count() == 0) {
            RoleEntity adminRole = roleRepository.findByRole(RoleNameEnum.ADMIN);

            UserEntity admin = new UserEntity();
            admin
                    .setUsername("admin")
                    .setEmail("admin@.com")
                    .setFullName("Admin Admin")
                    .setAddress("Admin123")
                    .setRoles(Set.of(adminRole))
                    .setPassword(passwordEncoder.encode("1"));

            admin = userRepository.save(admin);
        }
    }
}
