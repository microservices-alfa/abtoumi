package com.example.MediaTech.services.Facture;

import com.example.MediaTech.DAO.ClientDAO;
import com.example.MediaTech.DAO.FactureDAO;
import com.example.MediaTech.DTO.FactureDTO;
import com.example.MediaTech.Models.Client;
import com.example.MediaTech.Models.Facture;
import com.example.MediaTech.Models.LigneFactureEntity;
import com.example.MediaTech.exceptions.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class FactureServiceImpl implements FactureService{

    private FactureDAO factureDAO;
    private ClientDAO clientDAO;
    private ModelMapper modelMapper;

    @Autowired
    public FactureServiceImpl(ModelMapper modelMapper, FactureDAO factureDAO, ClientDAO clientDAO) {
        this.modelMapper = modelMapper;
        this.factureDAO = factureDAO;
        this.clientDAO = clientDAO;
    }



    @Override
    public FactureDTO findById(int id) {
        Facture facture=factureDAO.findById(id).orElseThrow(() -> new NotFoundException("fattura with ID " + id + " not found"));

        return modelMapper.map(facture, FactureDTO.class);
    }

    @Override
    public FactureDTO save(FactureDTO factureDTO) {
        // 1. Récupérer le client à partir du clientId dans le DTO.

        Client client = clientDAO.findById(factureDTO.getClientId())
                .orElseThrow(() -> new NotFoundException("Client with ID " + factureDTO.getClientId() + " not found"));

        // 2. Mapper le FactureDTO en entité Facture
        Facture facture = modelMapper.map(factureDTO, Facture.class);

        // 3. Associer le client à la facture
        facture.setClient(client);

        // 4. Si la facture a des lignes (LigneFactureEntity), les associer également
        if (factureDTO.getLigneFactureEntities() != null ) {
            // Mapper les lignes de la facture

            List<LigneFactureEntity> ligneFactures = factureDTO.getLigneFactureEntities().stream()
                    .map(ligneDTO -> modelMapper.map(ligneDTO, LigneFactureEntity.class))
                    .collect(Collectors.toList());

            // Associer les lignes à la facture
            facture.setFactureEntites(ligneFactures);
        }
// Gestione del versionamento: reload entity
        if (facture.getId() != null) {
            Facture existingFacture = factureDAO.findById(facture.getId())
                    .orElseThrow(() -> new NotFoundException("fattura  not found"));
            modelMapper.map(factureDTO, existingFacture);
            facture = existingFacture;
        }

        Facture savedFacture = factureDAO.save(facture);

        // 6. Mapper l'entité sauvegardée en FactureDTO pour la réponse
        return modelMapper.map(savedFacture, FactureDTO.class);
    }




    @Override
    public void delete(int id) {
        Facture facture = factureDAO.findById(id)
                .orElseThrow(() -> new NotFoundException("fattura with id " + id + " not found"));
        factureDAO.delete(facture);

    }

    @Override
    public void deletebyref(String ref) {
        Facture facture = factureDAO.findByRef(ref);
        if (facture == null) {
            throw new NotFoundException("Facture with tel " + ref + " not found.");
        }
        factureDAO.delete(facture);
    }

    @Override
    public List<FactureDTO> findAll() {
        return factureDAO.findAll().stream()
                .map(facture -> modelMapper.map(facture, FactureDTO.class))
                .collect(java.util.stream.Collectors.toList());

    }

    @Override
    public FactureDTO findByRef(String ref) {

        Facture facture=factureDAO.findByRef(ref);

        if(facture==null) {
            throw new NotFoundException("Fattura with ref " + ref + " not found.");
        }
        return modelMapper.map(facture, FactureDTO.class) ;
    }

    @Override
    public List<FactureDTO> findByClientId(Integer clientId) {
        List<Facture> factures = factureDAO.findByClientId(clientId);

        // Mapper les entités en DTOs et les retourner
        return factures.stream()
                .map(facture -> modelMapper.map(facture, FactureDTO.class))
                .collect(Collectors.toList());
    }


    @Override
    public FactureDTO update(FactureDTO factureDTO  ,  Integer id) {

        Facture facture =factureDAO.findById(id).orElseThrow(() -> new NotFoundException("fattura with id " + id + " not found"));

        modelMapper.map(factureDTO, facture);
        factureDAO.save(facture);
        return modelMapper.map(facture, FactureDTO.class);

    }
}


