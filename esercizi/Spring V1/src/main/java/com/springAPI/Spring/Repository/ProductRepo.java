package com.springAPI.Spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springAPI.Spring.Models.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{

    
 
    
}
