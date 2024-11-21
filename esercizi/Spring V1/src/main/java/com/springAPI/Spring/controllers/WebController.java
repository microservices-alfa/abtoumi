package com.springAPI.Spring.controllers;

import com.springAPI.Spring.Models.Product;
import com.springAPI.Spring.Repository.ProductRepo;

 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



 

import java.util.List;

@Controller
@RequestMapping("/web")
public class WebController {

    private final ProductRepo productRepo;

    public WebController(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    // Afficher la liste des produits
    @GetMapping("/products")
    public String getAllProducts(Model model) {
        List<Product> products = productRepo.findAll();
        model.addAttribute("products", products);
        return "products"; // Retourne la vue 'products.html'
    }

    // Afficher le formulaire d'ajout de produit
    @GetMapping("/products/add")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new Product()); // Ajouter un objet Product vide au modèle
        return "addProduct"; // Retourne la vue 'addProduct.html'
    }

    // Soumettre le formulaire d'ajout de produit
    @PostMapping("/products")
    public String addProduct(@ModelAttribute Product product) {
        productRepo.save(product);
        return "redirect:/web/products"; // Redirige vers la liste des produits
    }

    // Supprimer un produit
    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id) {
        productRepo.deleteById(id); // Supprimer le produit par son ID
        return "redirect:/web/products"; // Redirige vers la liste des produits
    }

    // Afficher le formulaire de mise à jour de produit
    @GetMapping("/products/update/{id}")
    public String showUpdateProductForm(@PathVariable("id") long id, Model model) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + id));
        model.addAttribute("product", product);
        return "updateProduct"; // Retourne la vue 'updateProduct.html'
    }

    // Soumettre le formulaire de mise à jour de produit (utilisation de POST mais comme PUT)
    @PostMapping("/products/update/{id}")
    public String updateProduct(@PathVariable("id") long id, @ModelAttribute Product product, 
                                @RequestParam("_method") String method) {
        if ("PUT".equals(method)) {
            // Vérification si la méthode est PUT
            product.setId(id);  // Lier l'ID du produit existant
            productRepo.save(product);  // Sauvegarder les modifications
        }
        return "redirect:/web/products";  // Rediriger vers la liste des produits
    }

    

    
}
