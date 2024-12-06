package com.example.MediaTech.services.Facture;

import com.example.MediaTech.DTO.FactureDTO;



import java.util.List;

public interface FactureService {
   FactureDTO findById(int id);
   FactureDTO save(FactureDTO factureDTO);
   FactureDTO update(FactureDTO factureDTO,  Integer id);
   void delete(int id);
   void deletebyref(String ref);
   List<FactureDTO> findAll();
   FactureDTO findByRef(String ref);
    List<FactureDTO> findByClientId(Integer clientId);






}
