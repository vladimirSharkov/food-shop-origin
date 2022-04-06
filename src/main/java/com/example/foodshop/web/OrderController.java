package com.example.foodshop.web;


import com.example.foodshop.model.entity.CartEntity;
import com.example.foodshop.model.entity.OrderEntity;
import com.example.foodshop.model.entity.UserEntity;
import com.example.foodshop.service.OrderService;
import com.example.foodshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping(value = "order")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/add")
    public String add(Model model, Principal principal) {


        UserEntity user = userService.findByUsername(principal.getName());
        OrderEntity order = user.getOrder();
        BigDecimal total = order.getCarts().stream().map(CartEntity::getTotalPrice).reduce(BigDecimal.valueOf(0), BigDecimal::add);
        orderService.setTotalPrice(total,order);
        order.setTotalPrice(total);
        List<CartEntity> carts = order.getCarts();

        model.addAttribute("carts",carts);
        model.addAttribute("order",order);

        return "cart";
    }


    @GetMapping("/buy")
    public String deleteOrder(Principal principal){
        UserEntity user = userService.findByUsername(principal.getName());
        orderService.deleteOrder(user);
        return "redirect:/";
    }
}
