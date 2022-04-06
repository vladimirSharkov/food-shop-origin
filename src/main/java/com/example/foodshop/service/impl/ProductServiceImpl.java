package com.example.foodshop.service.impl;

import com.example.foodshop.model.entity.ProductEntity;
import com.example.foodshop.model.enumeration.CategoryNameEnum;
import com.example.foodshop.model.service.ProductServiceModel;
import com.example.foodshop.model.view.ProductsViewModel;
import com.example.foodshop.repository.ProductRepository;
import com.example.foodshop.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    public ProductServiceImpl(ModelMapper modelMapper, ProductRepository productRepository) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
    }

    @Override
    public void addProduct(ProductServiceModel productServiceModel) {
        ProductEntity product = modelMapper.map(productServiceModel, ProductEntity.class);

        productRepository.save(product);
    }

    @Override
    public List<ProductsViewModel> findAll() {

        return productRepository
                .findAll()
                .stream()
                .map(productEntity -> modelMapper.map(productEntity, ProductsViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductsViewModel findById(Long id) {

        return productRepository
                .findById(id)
                .map(productEntity -> modelMapper.map(productEntity,ProductsViewModel.class))
                .orElse(null);
    }

    @Override
    public ProductEntity findByIdProduct(Long productId) {

        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public void setQuantity(ProductEntity products) {
        productRepository.saveAndFlush(products);
    }

    @Override
    public List<ProductsViewModel> findAllBioProduct() {

        return productRepository
                .findAllByCategory(CategoryNameEnum.BIO)
                .stream()
                .map(productEntity -> modelMapper.map(productEntity,ProductsViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductsViewModel> findAllMeatProduct() {
        return productRepository
                .findAllByCategory(CategoryNameEnum.MEAT_AND_FISH)
                .stream()
                .map(productEntity -> modelMapper.map(productEntity,ProductsViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductsViewModel> findAllDrinksProduct() {

        return productRepository
                .findAllByCategory(CategoryNameEnum.DRINKS)
                .stream()
                .map(productEntity -> modelMapper.map(productEntity,ProductsViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductsViewModel> findAllBreadProduct() {
        return productRepository
                .findAllByCategory(CategoryNameEnum.BREAD_AND_PASTA)
                .stream()
                .map(productEntity -> modelMapper.map(productEntity, ProductsViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductsViewModel> findAllDairyProduct() {
        return productRepository
                .findAllByCategory(CategoryNameEnum.DAIRY_AND_EGGS)
                .stream()
                .map(productEntity -> modelMapper.map(productEntity,ProductsViewModel.class))
                .collect(Collectors.toList());
    }


}
