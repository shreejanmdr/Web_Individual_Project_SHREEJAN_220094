package com.system.shreejanEcommerce.service.impl;

import com.system.shreejanEcommerce.entity.ProductCart;
import com.system.shreejanEcommerce.pojo.ProductCartPojo;
import com.system.shreejanEcommerce.repo.ProductCartRepo;
import com.system.shreejanEcommerce.repo.ProductsRepo;
import com.system.shreejanEcommerce.repo.UserRepo;
import com.system.shreejanEcommerce.service.ProductCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ProductCartServiceImpl implements ProductCartService {
    private final UserRepo userRepo;
    private final ProductCartRepo productCartRepo;
    private final ProductsRepo productsRepo;

    @Override
    public List<ProductCart> fetchAll() {
        return this.productCartRepo.findAll();
    }

    @Override
    public ProductCartPojo save(ProductCartPojo productCartPojo) {
        ProductCart productCart = new ProductCart();
        if(productCartPojo.getId()!=null){
            productCart.setId(productCartPojo.getId());
        }
        productCart.setProducts(productsRepo.findById(productCartPojo.getProducts_id()).orElseThrow());
        productCart.setUser(userRepo.findById(productCartPojo.getUser_id()).orElseThrow());
        productCartRepo.save(productCart);
        return new ProductCartPojo();
    }

    @Override
    public ProductCart fetchOne(Integer id) {
        return productCartRepo.findById(id).orElseThrow();
    }

    @Override
    public void deleteFromCart(Integer id) {
        productCartRepo.deleteById(id);
    }

    @Override
    public String updateQuantity(ProductCart productCart) {
        productCartRepo.save(productCart);
        return "Updated";
    }

    @Override
    public List<ProductCart> fetchAll(Integer id) {
        return findAllInList(productCartRepo.findAll());
    }
    public List<ProductCart> findAllInList(List<ProductCart> list){
        Stream<ProductCart> allCart=list.stream().map(productCart ->
                ProductCart.builder()
                        .id(productCart.getId())
                        .products(productCart.getProducts())
                        .user(productCart.getUser())
                        .build()
        );

        list = allCart.toList();
        return list;
    }
}
