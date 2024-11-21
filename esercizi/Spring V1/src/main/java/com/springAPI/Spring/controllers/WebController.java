package com.springAPI.Spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.springAPI.Spring.Models.Product;
import com.springAPI.Spring.Repository.ProductRepo;
import org.springframework.web.bind.annotation.PostMapping;
 

import java.util.List;


@Controller
@RequestMapping("/web")
public class WebController {

    private final ProductRepo productRepo;

    public WebController(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @GetMapping("/products")
    public String getAllProducts(Model model) {
        List<Product> products = productRepo.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/products/add")
    public String showAddProductForm() {
        return "addProduct";  // Affiche le formulaire pour ajouter un produit
    }

      @PostMapping("/products")
    public String addProduct(@ModelAttribute Product product) {
        // Sauvegarder le produit dans la base de données
        productRepo.save(product);
        return "redirect:/web/products"; // Rediriger vers la liste des produits
    }
}
