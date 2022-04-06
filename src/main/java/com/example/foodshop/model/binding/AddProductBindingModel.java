package com.example.foodshop.model.binding;

import com.example.foodshop.model.enumeration.CategoryNameEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AddProductBindingModel {

    private String name;
    private BigDecimal price;
    private String imageUrl;
    private CategoryNameEnum category;
    private String description;
    private LocalDate addTime;
    private Integer quantity;

    public AddProductBindingModel() {
    }

    public String getName() {
        return name;
    }

    public AddProductBindingModel setName(String name) {
        this.name = name;
        return this;
    }
    @Positive
    public BigDecimal getPrice() {
        return price;
    }

    public AddProductBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AddProductBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
    @Enumerated(EnumType.STRING)
    public CategoryNameEnum getCategory() {
        return category;
    }

    public AddProductBindingModel setCategory(CategoryNameEnum category) {
        this.category = category;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddProductBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDate getAddTime() {
        return addTime;
    }

    public AddProductBindingModel setAddTime(LocalDate addTime) {
        this.addTime = addTime;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public AddProductBindingModel setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }
}
