package com.system.shreejanEcommerce.controller;

import com.system.shreejanEcommerce.entity.Products;
import com.system.shreejanEcommerce.pojo.ProductCartPojo;
import com.system.shreejanEcommerce.service.ProductCartService;
import com.system.shreejanEcommerce.service.ProductsService;
import com.system.shreejanEcommerce.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.Principal;
import java.util.Base64;
import java.util.Collection;
import java.util.List;

@Controller
@RequiredArgsConstructor //creates the constructor with all required arguments
@RequestMapping("/dashboard")
public class DashboardController {
    private final ProductsService productsService;
    private final ProductCartService productCartService;
    private final UserService userService;
    public String getImageBase64(String fileName) {
        String filePath = System.getProperty("user.dir") + "/tintinproducts/";
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
    @GetMapping("")
    public String getDashboard(Model model, Principal principal, Authentication authentication){
        if (authentication!=null){
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority grantedAuthority : authorities) {
                if (grantedAuthority.getAuthority().equals("Admin")) {
                    return "redirect:/admin/order-list";
                }
            }
        }
        List<Products> product = productsService.fetchAll();
        if (principal!=null) {
            model.addAttribute("info", userService.findByEmail(principal.getName()));
        }
//        model.addAttribute("products", product.stream().map(products ->
//                        Products.builder()
//                                .id(products.getId())
//                                .imageBase64(getImageBase64(products.getPhoto()))
//                                .name(products.getName())
//                                .quantity(products.getQuantity())
//                                .price(products.getPrice())
//                                .build()
//                )
//        );
        model.addAttribute("product", product);
        model.addAttribute("savecarts", new ProductCartPojo());
        return "index";
    }

    @PostMapping("/save")
    public String savecart(@Valid ProductCartPojo productCartPojo) {
        productCartService.save(productCartPojo);
        return "redirect:/login";
    }
    @GetMapping("/aboutus")
    public String getAboutUsPage() {
        return "aboutus";
    }
}
