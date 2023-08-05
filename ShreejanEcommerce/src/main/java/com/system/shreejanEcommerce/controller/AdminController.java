package com.system.shreejanEcommerce.controller;

import com.system.shreejanEcommerce.entity.ProductCart;
import com.system.shreejanEcommerce.entity.Products;
import com.system.shreejanEcommerce.entity.Queries;
import com.system.shreejanEcommerce.entity.User;
import com.system.shreejanEcommerce.pojo.ProductsPojo;
import com.system.shreejanEcommerce.service.ProductCartService;
import com.system.shreejanEcommerce.service.ProductsService;
import com.system.shreejanEcommerce.service.QueryService;
import com.system.shreejanEcommerce.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Principal;
import java.util.Base64;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final ProductsService productsService;
    private final ProductCartService productCartService;
    private final QueryService queryService;
    @GetMapping("/order-list")
    public String getOrderListPage(Model model, Principal principal) {
        if (principal!=null) {
            model.addAttribute("info", userService.findByEmail(principal.getName()));
        }
        assert principal != null;
        Integer id = userService.findByEmail(principal.getName()).getId();
        List<ProductCart> list = productCartService.fetchAll(id);
        model.addAttribute("cartItems", list);
        return "order_list";
    }
    @GetMapping("/user-list")
    public String getUserListPage(Model model, Principal principal) {
        if (principal!=null) {
            model.addAttribute("info", userService.findByEmail(principal.getName()));
        }
        List<User> users = userService.fetchAll();
        model.addAttribute("userlist", users.stream().map(user ->
                User.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .number(user.getNumber())
                        .address(user.getAddress())
                        .build()
        ));
        return "userlist";
    }
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model, Principal principal) {
        userService.deleteById(id);
        return "redirect:/admin/user-list";
    }
    @GetMapping("/add-products")
    public String getAddproductPage(Model model, Principal principal) {
        if (principal!=null) {
            model.addAttribute("info", userService.findByEmail(principal.getName()));
        }
        model.addAttribute("products", new ProductsPojo());
        return "add_products";
    }
    @GetMapping("/products-list")
    public String getProductList(Model model, Principal principal) {
        if (principal!=null) {
            model.addAttribute("info", userService.findByEmail(principal.getName()));
        }
        List<Products> product = productsService.fetchAll();
        model.addAttribute("product", product);
        return "productslist";
    }
    @GetMapping("/editproducts/{id}")
    public String editProducts(@PathVariable("id") Integer id, Model model, Principal principal) {
        if (principal!=null) {
            model.addAttribute("info", userService.findByEmail(principal.getName()));
        }
        Products products = productsService.fetchById(id);
        model.addAttribute("products", new ProductsPojo(products));
        return "add_products";
    }
    @GetMapping("/deleteproducts/{id}")
    public String deleteProducts(@PathVariable("id") Integer id, Model model, Principal principal) {
        if (principal!=null) {
            model.addAttribute("info", userService.findByEmail(principal.getName()));
        }
        productsService.deleteById(id);
        return "redirect:/admin/products-list";
    }
    @PostMapping("/save/products")
    public String saveproducts(@Valid ProductsPojo productsPojo) throws IOException {
        productsService.save(productsPojo);
        return "redirect:/dashboard";
    }
    @GetMapping("/queries")
    public String getQueryPage(Model model, Principal principal) {
        if (principal!=null) {
            model.addAttribute("info", userService.findByEmail(principal.getName()));
        }
        List<Queries> queries = queryService.fetchAll();
        model.addAttribute("queries", queries);
        return "query_section";
    }

    @GetMapping("/settings")
    public String getAdminSettingsPage(Model model, Principal principal) {
        if (principal!=null) {
            model.addAttribute("info", userService.findByEmail(principal.getName()));
        }
        return "admin_settings";
    }
    public String getImageBase64(String fileName) {
        String filePath = System.getProperty("user.dir") + "/EazyMart/";
        File file = new File(filePath + fileName);
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        String base64 = Base64.getEncoder().encodeToString(bytes);
        return base64;
    }
}
