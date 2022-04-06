package com.example.foodshop.web;

import com.example.foodshop.model.binding.AddProductBindingModel;
import com.example.foodshop.model.binding.BuyProductBindingModel;
import com.example.foodshop.model.entity.ProductEntity;
import com.example.foodshop.model.entity.UserEntity;
import com.example.foodshop.model.service.ProductServiceModel;
import com.example.foodshop.model.view.ProductsViewModel;
import com.example.foodshop.service.CartService;
import com.example.foodshop.service.OrderService;
import com.example.foodshop.service.ProductService;
import com.example.foodshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CartService cartService;
    private final OrderService orderService;

    public ProductController(ProductService productService, ModelMapper modelMapper, UserService userService, CartService cartService, OrderService orderService) {
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.cartService = cartService;
        this.orderService = orderService;
    }


    @ModelAttribute
    AddProductBindingModel addProductBindingModel() {
        return new AddProductBindingModel();
    }

    @ModelAttribute
    BuyProductBindingModel buyProductBindingModel() {
        return new BuyProductBindingModel();
    }

    @GetMapping("/all")
    public String products(Model model) {

        List<ProductsViewModel> productsView = productService.findAll();
        model.addAttribute("productsView", productsView);
        return "product";
    }

    @GetMapping("/bio")
    public String bioProduct(Model model) {

        List<ProductsViewModel> allBioProduct = productService.findAllBioProduct();
        model.addAttribute("bioProduct", allBioProduct);
        return "bio-product";
    }

    @GetMapping("meat")
    public String meatProduct(Model model) {

        List<ProductsViewModel> allMeaProduct = productService.findAllMeatProduct();
        model.addAttribute("meatProduct", allMeaProduct);
        return "meat-product";
    }

    @GetMapping("drinks")
    public String drinksProduct(Model model) {

        List<ProductsViewModel> allDrinksProduct = productService.findAllDrinksProduct();
        model.addAttribute("drinksProduct", allDrinksProduct);
        return "drinks-product";
    }

    @GetMapping("bread")
    public String breadProduct(Model model) {

        List<ProductsViewModel> allBreadProduct = productService.findAllBreadProduct();
        model.addAttribute("breadProduct", allBreadProduct);
        return "bread-product";
    }

    @GetMapping("dairy")
    public String dairyProduct(Model model) {

        List<ProductsViewModel> allDairyProduct = productService.findAllDairyProduct();
        model.addAttribute("dairyProduct", allDairyProduct);
        return "dairy-product";
    }


    @GetMapping("add")
    public String add() {
        return "add-product";
    }

    @PostMapping("add")
    public String adminAddProductPost(@Valid AddProductBindingModel addProductBindingModel, BindingResult bindingResult,
                                      RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("addProductBindingModel", addProductBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.addProductBindingModel"
                            , bindingResult);
            return "redirect:add";
        }
        productService.addProduct(modelMapper.map(addProductBindingModel, ProductServiceModel.class));

        return "redirect:all";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        model.addAttribute("productDetails", productService.findById(id));
        return "details";
    }

    @GetMapping("/cartInfo")
    public String cartInfo() {
        return "cart";
    }

    @PostMapping("/addProduct/{id}")
    public String addProductToCart(@Valid BuyProductBindingModel buyProductBindingModel, BindingResult bindingResult,
                                   RedirectAttributes redirectAttributes, @PathVariable Long id,
                                   Principal principal, HttpServletRequest request) {
        String referer = request.getHeader("Referer");

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("buyProductBindingModel", buyProductBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.buyProductBindingModel"
                            , bindingResult);
            return "redirect:all";
        }

        UserEntity user = userService.findByUsername(principal.getName());
        Integer quantity = buyProductBindingModel.getQuantity();
        ProductEntity product = productService.findByIdProduct(id);
        orderService.addProduct(user, quantity, product);
        return "redirect:/order/add" ;

    }
}
