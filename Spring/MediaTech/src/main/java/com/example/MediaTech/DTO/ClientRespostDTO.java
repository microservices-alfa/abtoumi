package com.example.MediaTech.DTO;


import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRespostDTO implements Serializable {

    private String nome;
    private String cognome;
    private String tel;
}
