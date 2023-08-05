package com.system.shreejanEcommerce.repo;

import com.system.shreejanEcommerce.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductsRepo extends JpaRepository<Products, Integer> {
    @Query(value = "select * from products where name=?1", nativeQuery = true)
    Optional<Products> findByproduct_id(String name);
}
