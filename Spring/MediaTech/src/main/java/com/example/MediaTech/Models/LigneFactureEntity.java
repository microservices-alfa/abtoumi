package com.example.MediaTech.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "ligne_facture")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LigneFactureEntity implements Serializable {

    @EmbeddedId
    private LigneFactureKey id;

    @ManyToOne
    @MapsId("FactureId")
    @JoinColumn(name = "Facture_Id")  // Correspond à la colonne "Facture_Id" dans la base de données
    private Facture facture;

    @ManyToOne
    @MapsId("ProduitId")
    @JoinColumn(name = "Produit_Id")  // Correspond à la colonne "Produit_Id" dans la base de données
    private Prodotto produit;

   @ManyToOne
    @JoinColumn(name = "client_id")  // Ajoutez cette relation pour corriger le problème
    private Client client;  // Nouvelle propriété client

    private double quantita;
}
