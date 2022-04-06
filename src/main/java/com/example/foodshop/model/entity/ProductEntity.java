package com.example.foodshop.model.entity;

import com.example.foodshop.model.enumeration.CategoryNameEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "products")
public class ProductEntity extends BaseEntity {

    private String name;
    private BigDecimal price;
    private String imageUrl;
    private CategoryNameEnum category;
    private String description;
    private LocalDate addTime;
    private Integer quantity;

    public ProductEntity() {
    }


    public String getName() {
        return name;
    }

    public ProductEntity setName(String name) {
        this.name = name;
        return this;
    }

    @Enumerated(EnumType.STRING)
    public CategoryNameEnum getCategory() {
        return category;
    }

    public ProductEntity setCategory(CategoryNameEnum category) {
        this.category = category;
        return this;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public ProductEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }


    public String getImageUrl() {
        return imageUrl;
    }

    public ProductEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }


    public String getDescription() {
        return description;
    }

    public ProductEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDate getAddTime() {
        return addTime;
    }

    public ProductEntity setAddTime(LocalDate addTime) {
        this.addTime = addTime;
        return this;
    }


    public Integer getQuantity() {
        return quantity;
    }

    public ProductEntity setQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

}
