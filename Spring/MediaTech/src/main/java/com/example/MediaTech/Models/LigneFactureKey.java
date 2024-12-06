package com.example.MediaTech.Models;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;

import java.io.Serializable;

@Embeddable
public class LigneFactureKey implements Serializable {

    @Column(name = "Facture_Id")
    @GeneratedValue
    private Integer FactureId;

    @Column(name = "Produit_Id")
    private Integer ProduitId;
}