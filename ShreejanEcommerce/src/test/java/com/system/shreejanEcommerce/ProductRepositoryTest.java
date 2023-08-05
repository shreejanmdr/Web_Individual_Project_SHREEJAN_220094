//package com.system.shreejanEcommerce;
//
//import com.system.shreejanEcommerce.entity.Products;
//import com.system.shreejanEcommerce.repo.ProductsRepo;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.annotation.Rollback;
//
//import java.util.List;
//import java.util.Optional;
//
//@DataJpaTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class ProductRepositoryTest {
//    @Autowired
//    private ProductsRepo booksRepo;
//
//    @Test
//    @Order(1)
//    @Rollback(value = false)
//    public void saveBooks() {
//
//        Products products = Products.builder()
//                .name("TestName")
//                .quantity("1")
//                .price("1000")
//                .build();
////
//        booksRepo.save(products);
//        Assertions.assertThat(products.getId()).isGreaterThan(0);
//
//    }
//
//    @Test
//    @Order(2)
//    @Rollback(value = false)
//    public void getBookTest() {
//        Products products = booksRepo.findById(1).get();
//        Assertions.assertThat(products.getId()).isEqualTo(1);
//    }
//
//    //
//    @Test
//    @Order(3)
//    public void fetchAll() {
//        List<Products> products = booksRepo.findAll();
//        Assertions.assertThat(products.size()).isGreaterThan(0);
//    }
//    //
//    @Test
//    @Order(4)
//    @Rollback(value = false)
//    public void Update() {
//        Products products = booksRepo.findById(1).get();
//        products.setName("TestName");
//        Products books1 = booksRepo.save(products);
//        Assertions.assertThat(books1.getName()).isEqualTo("TestName");
//    }
//    //
//    @Test
//    @Order(5)
//    @Rollback(value = false)
//    public void Delete(){
//        Products products =booksRepo.findById(1).get();
//        booksRepo.delete(products);
//        Products books1=null;
//        Optional<Products> bookOptional=booksRepo.findBybook_id(books1.getName());
//        if(bookOptional.isPresent()){
//            books1 = bookOptional.get();
//        }
//        Products books2=booksRepo.save(products);
//        Assertions.assertThat(books2).isNull();
//    }
//}
