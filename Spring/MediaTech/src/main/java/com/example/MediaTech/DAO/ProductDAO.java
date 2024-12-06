package com.example.MediaTech.DAO;

import com.example.MediaTech.Models.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDAO extends JpaRepository <Prodotto,Integer>{


    Prodotto findByRef(String ref);

    Prodotto findByLibelle(String libelle);



}
