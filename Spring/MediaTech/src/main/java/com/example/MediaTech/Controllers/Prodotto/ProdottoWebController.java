package com.example.MediaTech.Controllers.Prodotto;


import com.example.MediaTech.DAO.ProductDAO;
import com.example.MediaTech.DTO.ClientRequestDTO;
import com.example.MediaTech.DTO.ClientRespostDTO;
import com.example.MediaTech.DTO.ProduitDTO;
import com.example.MediaTech.services.client.ClientService;
import com.example.MediaTech.services.produit.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Produits")
public class ProdottoWebController {

    @Autowired
    private ProduitService produitService;

    // Méthode pour afficher la liste des produits
    @GetMapping("/list")
    public String listProduits(Model model) {
        List<ProduitDTO> produits = produitService.findAll();
        model.addAttribute("produits", produits);
        return "ProduitList"; // Page HTML affichant la liste des clients
    }

    // Méthode pour supprimer un produit
    @GetMapping("/delete/{ref}")
    public String deleteProduits(@PathVariable("ref") String ref) {

        produitService.deleteByRef(ref);
        return "redirect:/Produits/list"; // Rediriger vers la liste après suppression
    }

    // Méthode pour afficher un formulaire d'édition d'un produit (pour la mise à jour)
    @GetMapping("/edit/{ref}")
    public String editProduit(@PathVariable("ref") String ref, Model model) {
        ProduitDTO prodotto = produitService.findByRef(ref);
        model.addAttribute("prodotto", prodotto);
        return "ProduitEdit"; // Page HTML pour modifier un produit
    }

    @PostMapping("/update/{ref}")
    public String updateProduit(@PathVariable("ref") String ref, @ModelAttribute ProduitDTO produitDTO) {
        produitDTO.setRef(ref);  // Assurez-vous que la référence est correctement passée
        produitService.update(produitDTO,ref);  // Sauvegarder les modifications
        return "redirect:/Produits/list";  // Rediriger vers la liste des produits après mise à jour
    }

    // Afficher le formulaire pour ajouter un nouveau produits
    @GetMapping("/new")
    public String showProduitForm(Model model) {
        model.addAttribute("produit", new ProduitDTO());
        return "ProduitForm";  // Retourne le formulaire d'ajout de produits
    }

    // Ajouter un nouveau client
    @PostMapping("/save")
    public String saveProduit(@ModelAttribute ProduitDTO produitDTO) {
        produitService.save(produitDTO);
        return "redirect:/Produits/list";  // Redirige vers la liste des produits
    }
}

