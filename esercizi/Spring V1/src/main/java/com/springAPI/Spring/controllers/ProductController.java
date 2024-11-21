package com.springAPI.Spring.controllers;
 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springAPI.Spring.Models.Product;
import com.springAPI.Spring.Exceptions.ResourceNotFoundException;
import java.util.List;

import com.springAPI.Spring.Repository.ProductRepo;


@CrossOrigin
@RestController
@RequestMapping("/api/Toumi")
public class ProductController {

    private final ProductRepo productRepo;


    public ProductController(ProductRepo productRepo){
        this.productRepo=productRepo;

    }


        @GetMapping("/Products")
        public List<Product> getallProducts(){
            return productRepo.findAll();
    }


    @GetMapping("/Products/{id}")
        public Product getProductByID(@PathVariable(value = "id") long ProdID) throws ResourceNotFoundException {
            // Use .orElseThrow() to handle the case when the product is not found
            return productRepo.findById(ProdID)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + ProdID));
    }

    @DeleteMapping("/Products/{id}")
    public ResponseEntity<Void> deleteProductByID(@PathVariable(value = "id") long ProdID) throws ResourceNotFoundException {
        Product product = productRepo.findById(ProdID)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + ProdID));

        // Delete the product
        productRepo.delete(product);

        // Return a response indicating successful deletion
        return ResponseEntity.noContent().build();
    }

 


    @PutMapping("/Products/{id}")
public Product updateProduct(@PathVariable(value = "id") long ProdID,
                             @Validated @RequestBody Product prodDetails) throws ResourceNotFoundException {

    // Fetch the product from the repository or throw exception if not found
    Product prod = productRepo.findById(ProdID)
            .orElseThrow(() -> new ResourceNotFoundException("Product with ID: " + ProdID + " not found"));

    // Update product fields
    prod.setProductName(prodDetails.getProductName());
    prod.setPrice(prodDetails.getPrice());

    // Save the updated product back to the database
    return productRepo.save(prod);
}


    @PostMapping("/Products")
    public ResponseEntity<Product> addProduct(@Validated @RequestBody Product prodDetails) {
        // Save the new product to the database
        Product newProduct = productRepo.save(prodDetails);
        
        // Return the created product with a 201 status
        return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
    }



}