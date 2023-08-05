package com.system.shreejanEcommerce.pojo;

import com.system.shreejanEcommerce.entity.Products;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductsPojo {
    private Integer id;
    private MultipartFile photo;
    @NotEmpty(message = "Product Name can't be empty")
    private String name;
    @NotEmpty(message = "Product Quantity can't be empty")
    private String quantity;
    @NotEmpty(message = "Product Price can't be empty")
    private String price;

    public ProductsPojo(Products products) {
        this.id = products.getId();
        this.name = products.getName();
        this.quantity = products.getQuantity();
        this.price = products.getPrice();
    }
}
