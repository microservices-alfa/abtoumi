package com.example.MediaTech.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FactureDTO {

    @NotNull(message = "La référence de la facture ne peut pas être nulle")
    @NotBlank(message = "La référence de la facture ne peut pas être vide")
    private String ref;

    @NotNull(message = "La date de la facture ne peut pas être nulle")
    private Date date;

    @NotNull(message = "Le client est obligatoire")
    private Integer clientId;  // Utilisation d'un clientId pour simplifier
    private List<LigneFactureDTO> ligneFactureEntities; // Liste des lignes de la facture

    // Vous pouvez ajouter d'autres champs ou des méthodes supplémentaires si nécessaire
}
