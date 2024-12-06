package com.example.MediaTech.Controllers.Facture;


import com.example.MediaTech.DTO.ClientRequestDTO;
import com.example.MediaTech.DTO.ClientRespostDTO;
import com.example.MediaTech.DTO.FactureDTO;
import com.example.MediaTech.services.Facture.FactureService;
import com.example.MediaTech.services.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Factures")
public class FactureWebController {

    @Autowired
    private FactureService factureService;

    // Méthode pour afficher la liste des clients
    @GetMapping("/list")
    public String listFactures(Model model) {
        List<FactureDTO> factures = factureService.findAll();
        model.addAttribute("factures", factures);
        return "FactureList"; // Page HTML affichant la liste des facture
    }

    // Méthode pour supprimer une facture
    @GetMapping("/delete/{ref}")
    public String deleteFacture(@PathVariable("ref") String ref) {

        factureService.deletebyref(ref);
        return "redirect:/Factures/list"; // Rediriger vers la liste après suppression
    }

    // Méthode pour afficher un formulaire d'édition d'un client (pour la mise à jour)
    @GetMapping("/edit/{ref}")
    public String editFacture(@PathVariable("ref") String ref, Model model) {
        FactureDTO facture =factureService.findByRef(ref);
        model.addAttribute("facture", facture);
        return "factureEdit"; // Page HTML pour modifier un client
    }

    // Afficher le formulaire pour ajouter une nouvelle facture
    @GetMapping("/new")
    public String showFactureForm(Model model) {
        model.addAttribute("facture", new FactureDTO());
        return "FactureForm";  // Retourne le formulaire d'ajout de facture
    }

    // Ajouter un nouveau client
    @PostMapping("/save")
    public String saveClient(@ModelAttribute FactureDTO factureDTO) {
        factureService.save(factureDTO);
        return "redirect:/Factures/list";  // Redirige vers la liste des clients
    }
}

