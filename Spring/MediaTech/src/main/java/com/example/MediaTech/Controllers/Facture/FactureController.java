package com.example.MediaTech.Controllers.Facture;

import com.example.MediaTech.DTO.FactureDTO;
import com.example.MediaTech.services.Facture.FactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/factures")
public class FactureController {

    private final FactureService factureService;

    @Autowired
    public FactureController(FactureService factureService) {
        this.factureService = factureService;
    }


    @GetMapping("")
    public ResponseEntity<List<FactureDTO>>  getAllProdotti() {
        return new ResponseEntity<>(factureService.findAll(), HttpStatus.OK) ;
    }
    // Créer une nouvelle facture
    @PostMapping("")
    public ResponseEntity<FactureDTO> createFacture(@Valid @RequestBody FactureDTO factureDTO) {
        FactureDTO createdFacture = factureService.save(factureDTO);
        return new ResponseEntity<>(createdFacture, HttpStatus.CREATED);
    }

    // Récupérer une facture par ID
    @GetMapping("/{id}")
    public ResponseEntity<FactureDTO> getFactureById(@PathVariable Integer id) {
        FactureDTO factureDTO = factureService.findById(id);
        return new ResponseEntity<>(factureDTO, HttpStatus.OK);
    }

    // Récupérer toutes les factures d'un client par clientId
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<FactureDTO>> getFacturesByClientId(@PathVariable Integer clientId) {
        List<FactureDTO> factures = factureService.findByClientId(clientId);
        return new ResponseEntity<>(factures, HttpStatus.OK);
    }

    // Mettre à jour une facture existante
    @PutMapping("/{id}")
    public ResponseEntity<FactureDTO> updateFacture(@Valid @RequestBody FactureDTO factureDTO, @PathVariable Integer id) {
        FactureDTO updatedFacture = factureService.update(factureDTO, id);
        return new ResponseEntity<>(updatedFacture, HttpStatus.OK);
    }

    // Supprimer une facture par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFacture(@PathVariable Integer id) {
        factureService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Retourner une réponse 204 No Content
    }
}
