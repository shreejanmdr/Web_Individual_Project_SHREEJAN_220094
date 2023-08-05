package com.system.shreejanEcommerce.repo;

import com.system.shreejanEcommerce.entity.ProductCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductCartRepo extends JpaRepository<ProductCart, Integer> {
    @Query(value = "SELECT * FROM PRODUCTSCARTS WHERE user_id = ?1", nativeQuery = true)
    Optional<List<ProductCart>> fetchAll(Integer userId);
}
