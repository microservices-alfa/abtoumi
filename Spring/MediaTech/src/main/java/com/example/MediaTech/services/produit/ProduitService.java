package com.example.MediaTech.services.produit;

import com.example.MediaTech.DTO.ProduitDTO;

import java.util.List;

public interface ProduitService {

    ProduitDTO save(ProduitDTO produitDTO);

    ProduitDTO findById(Integer id);
    ProduitDTO findByRef(String ref);
    ProduitDTO findByLibelle(String libelle);

    void  delete(Integer ref);


    ProduitDTO update (ProduitDTO produitDTO, String ref);



    List<ProduitDTO> findAll();


    void deleteByRef(String ref);
}
