package com.example.MediaTech.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LigneFactureDTO {
    private Integer id;
    private Integer produitId; // ID du produit lié à la ligne de facture
    private double quantita; // Quantité du produit dans la ligne
}
