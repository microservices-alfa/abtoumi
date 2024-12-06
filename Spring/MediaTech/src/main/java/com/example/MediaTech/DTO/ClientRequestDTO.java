package com.example.MediaTech.DTO;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequestDTO {

    @NotNull(message ="nome non deve essere NUll")
    @NotBlank(message ="nome non deve essere vuoto")
    @Size(min = 3, max = 20, message = "nome deve essere compreso tra 3 e 20 caratteri")
    private String nome;
    @Size(min = 3, max = 20, message = "Cognome deve essere compreso tra 3 e 20 caratteri")
    @NotBlank(message ="Cognome non deve essere vuoto")
    @NotNull(message ="Cognome non deve essere NUll")
    private String cognome;
    @NonNull
    private String tel;

}
