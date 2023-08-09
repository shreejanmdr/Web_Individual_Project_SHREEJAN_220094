package com.system.shreejanEcommerce;

import com.system.shreejanEcommerce.entity.Products;
import com.system.shreejanEcommerce.repo.ProductsRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductRepositoryTest {
    @Autowired
    private ProductsRepo productsRepo;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveProducts() {

        Products products = Products.builder()
                .name("TestName")
                .quantity("1")
                .price("1000")
                .build();
//
        productsRepo.save(products);
        Assertions.assertThat(products.getId()).isGreaterThan(0);

    }

    @Test
    @Order(2)
    @Rollback(value = false)
    public void getProductTest() {
        Products products = productsRepo.findById(1).get();
        Assertions.assertThat(products.getId()).isEqualTo(1);
    }

    //
    @Test
    @Order(3)
    public void fetchAll() {
        List<Products> products = productsRepo.findAll();
        Assertions.assertThat(products.size()).isGreaterThan(0);
    }
    //
    @Test
    @Order(4)
    @Rollback(value = false)
    public void Update() {
        Products products = productsRepo.findById(1).get();
        products.setName("TestName");
        Products products1 = productsRepo.save(products);
        Assertions.assertThat(products1.getName()).isEqualTo("TestName");
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void Delete(){
        Products products =productsRepo.findById(1).get();
        productsRepo.delete(products);
        Products products1=null;
        Optional<Products> productOptional=productsRepo.findByproduct_id(products1.getName());
        if(productOptional.isPresent()){
            products1 = productOptional.get();
        }
        Products products2= productsRepo.save(products);
        Assertions.assertThat(products2).isNull();

    }

}
