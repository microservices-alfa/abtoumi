package com.example.MediaTech.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Prodotti")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prodotto implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String libelle;

    @Column(nullable = false)
    private String ref;

    @Column(nullable = false)
    private BigDecimal prezzo;

    @Column(nullable = false)
    private double quantita;

    @OneToMany(mappedBy = "produit", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<LigneFactureEntity> factureEntites;
}