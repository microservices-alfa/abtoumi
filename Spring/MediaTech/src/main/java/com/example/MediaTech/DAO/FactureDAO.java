package com.example.MediaTech.DAO;

import com.example.MediaTech.Models.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FactureDAO extends JpaRepository<Facture,Integer> {

    Facture findByRef(String ref);
    // Trouver toutes les factures associées à un client spécifique
    List<Facture> findByClientId(Integer clientId);



}
