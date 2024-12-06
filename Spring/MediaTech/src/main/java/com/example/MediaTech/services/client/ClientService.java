package com.example.MediaTech.services.client;

import com.example.MediaTech.DTO.ClientRequestDTO;
import com.example.MediaTech.DTO.ClientRespostDTO;

import java.util.List;

public interface ClientService  {

    ClientRespostDTO save(ClientRequestDTO clientRequestDTO);

    ClientRespostDTO findById(Integer id);
    ClientRespostDTO findByNome(String nome);
    ClientRespostDTO findByTel(String tel);

    void delete(Integer id);

    ClientRespostDTO update (ClientRequestDTO clientRequestDTO, Integer id);
    ClientRespostDTO updatebyTel (ClientRequestDTO clientRequestDTO, String tel);



    // Méthode pour supprimer un client par téléphone
    void deleteByTel(String tel) ;


    List<ClientRespostDTO> findAll();



}
