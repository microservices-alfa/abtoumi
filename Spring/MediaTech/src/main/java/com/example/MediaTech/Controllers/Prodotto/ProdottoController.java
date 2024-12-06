package com.example.MediaTech.Controllers.Prodotto;

import com.example.MediaTech.DTO.ProduitDTO;

import com.example.MediaTech.services.produit.ProduitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Produits")

public class ProdottoController {
    @Autowired
    private ProduitService produitService;

    @GetMapping("")
     public ResponseEntity<List<ProduitDTO>>  getAllProdotti() {
        return new ResponseEntity<>(produitService.findAll(), HttpStatus.OK) ;
     }

    @GetMapping("/find/{id}")
    public ResponseEntity<ProduitDTO> getClientById(@PathVariable Integer id) {
        // Chercher le client par ID
        Optional<ProduitDTO> prodotto = Optional.ofNullable(produitService.findById(id));

        // Vérifier si le prod a été trouvé
        // Si le prod n'est pas trouvé, retourner un statut 404 Not Found avec un message explicite
        return prodotto.map(produitDTO -> new ResponseEntity<>(produitDTO, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping("/byref/{ref}")
    public  ResponseEntity<ProduitDTO> getprodottobyRef(@PathVariable String ref) {

        return new ResponseEntity<>(produitService.findByRef(ref), HttpStatus.OK);
    }
    @GetMapping("/bylibelle/{libelle}")
    public ResponseEntity<ProduitDTO> getprodottobyLabelle(@PathVariable String libelle) {

        return new ResponseEntity<>(produitService.findByLibelle(libelle), HttpStatus.OK);
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        // Vérifier si le produit existe
        ProduitDTO prodotto = produitService.findById(id);

        if (prodotto == null) {
            // Si le  produit n'existe pas, renvoyer une erreur 404
            return new ResponseEntity<>("Prodotto with id  "+ id +" not found", HttpStatus.NOT_FOUND);
        }

        // Si le client existe, on procède à la suppression
        produitService.delete(id);

        // Renvoyer une réponse 200 OK ou 204 No Content après suppression réussie
        return new ResponseEntity<>("prodotto with id  "+id+"  deleted successfully", HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<ProduitDTO> save(@Valid @RequestBody ProduitDTO produitDTO) {
        return new ResponseEntity<>(produitService.save(produitDTO), HttpStatus.CREATED);
    }
    @PutMapping("/update/{ref}")
    public ResponseEntity<ProduitDTO> update(@Valid @RequestBody ProduitDTO produitDTO, @PathVariable String ref) {
        return new ResponseEntity<>(produitService.update(produitDTO, ref), HttpStatus.OK);
    }


}
