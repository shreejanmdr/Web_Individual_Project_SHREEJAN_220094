package com.system.shreejanEcommerce.service;

import com.system.shreejanEcommerce.entity.Products;
import com.system.shreejanEcommerce.pojo.ProductsPojo;

import java.io.IOException;
import java.util.List;

public interface ProductsService {

    String save(ProductsPojo productsPojo) throws IOException;

    List<Products> fetchAll();

    Products fetchById(Integer id);

    void deleteById(Integer id);

}
