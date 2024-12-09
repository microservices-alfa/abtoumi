package com.example.MediaTech.DTO;


import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientRespostDTO implements Serializable {

    private String nome;
    private String cognome;
    private String tel;
}
