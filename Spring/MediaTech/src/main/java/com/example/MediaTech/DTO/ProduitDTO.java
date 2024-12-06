package com.example.MediaTech.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProduitDTO {

    @NotNull(message ="ref non deve essere NUll")
    @NotBlank(message ="ref non deve essere vuoto")
    private String ref;

    @Size(min = 3, max = 20, message = "libelle deve essere compreso tra 3 e 20 caratteri")
    @NotBlank(message ="libelle non deve essere vuoto")
    @NotNull(message ="libelle non deve essere NUll")
    private String libelle;

    @NonNull
    private BigDecimal prezzo;

    @NonNull
    private double quantita;

}
