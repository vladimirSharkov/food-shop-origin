package com.example.foodshop.web;

import com.example.foodshop.model.entity.OrderEntity;
import com.example.foodshop.model.entity.UserEntity;
import com.example.foodshop.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;
import java.net.Authenticator;
import java.security.Principal;

@Controller
public class HomeController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(Model model, Principal principal , Authentication authentication) {
        if (authentication == null) {
            return "index";
        }

        UserEntity user = userService.findByUsername(principal.getName());
        OrderEntity order = user.getOrder();
        long count = order.getCarts().size();
        BigDecimal totalPrice = order.getTotalPrice();
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("count", count);
        return "index";
    }
}
