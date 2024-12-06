package com.example.MediaTech.Controllers.client;


import com.example.MediaTech.DTO.ClientRequestDTO;
import com.example.MediaTech.DTO.ClientRespostDTO;
import com.example.MediaTech.DTO.ProduitDTO;
import com.example.MediaTech.services.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clients")
public class ClientWebController {

    @Autowired
    private ClientService clientService;

    // Méthode pour afficher la liste des clients
    @GetMapping("/list")
    public String listClients(Model model) {
        List<ClientRespostDTO> clients = clientService.findAll();
        model.addAttribute("clients", clients);
        return "clientList"; // Page HTML affichant la liste des clients
    }

    // Méthode pour supprimer un client
    @GetMapping("/delete/{tel}")
    public String deleteClient(@PathVariable("tel") String tel) {
        System.out.println("tel da eliminare = "  +  tel);
        clientService.deleteByTel(tel);
        return "redirect:/clients/list"; // Rediriger vers la liste après suppression
    }

    // Méthode pour afficher un formulaire d'édition d'un client (pour la mise à jour)
    @GetMapping("/edit/{tel}")
    public String editClient(@PathVariable("tel") String tel, Model model) {
        ClientRespostDTO client = clientService.findByTel(tel);
        model.addAttribute("client", client);
        return "clientEdit"; // Page HTML pour modifier un client
    }


    @PostMapping("/update/{tel}")
    public String updateClient(@PathVariable("tel") String tel, @ModelAttribute ClientRequestDTO clientRequestDTO) {

        clientService.updatebyTel(clientRequestDTO,tel);  // Sauvegarder les modifications
        return "redirect:/clients/list";  // Rediriger vers la liste des produits après mise à jour
    }

    // Afficher le formulaire pour ajouter un nouveau client
    @GetMapping("/new")
    public String showClientForm(Model model) {
        model.addAttribute("client", new ClientRequestDTO());
        return "ClientForm";  // Retourne le formulaire d'ajout de client
    }

    // Ajouter un nouveau client
    @PostMapping("/save")
    public String saveClient(@ModelAttribute ClientRequestDTO clientRequestDTO) {
        clientService.save(clientRequestDTO);
        return "redirect:/clients/list";  // Redirige vers la liste des clients
    }
}

