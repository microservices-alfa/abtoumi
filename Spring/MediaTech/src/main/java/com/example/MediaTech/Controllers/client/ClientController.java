package com.example.MediaTech.Controllers.client;


import com.example.MediaTech.DTO.ClientRequestDTO;
import com.example.MediaTech.DTO.ClientRespostDTO;
import com.example.MediaTech.services.client.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")

public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("")
     public ResponseEntity<List<ClientRespostDTO>>  getAllClients() {
        return new ResponseEntity<>(clientService.findAll(), HttpStatus.OK) ;
     }




    @GetMapping("/find/{id}")
    public ResponseEntity<ClientRespostDTO> getClientById(@PathVariable Integer id) {
        // Chercher le client par ID
        Optional<ClientRespostDTO> client = Optional.ofNullable(clientService.findById(id));

        // Vérifier si le client a été trouvé
        if (client.isPresent()) {
            // Si le client est trouvé, retourner un statut 200 OK avec les données du client
            return new ResponseEntity<>(client.get(), HttpStatus.OK);
        } else {
            // Si le client n'est pas trouvé, retourner un statut 404 Not Found avec un message explicite
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/byName/{name}")
    public  ResponseEntity<ClientRespostDTO> getClientByName(@PathVariable String name) {

        return new ResponseEntity<>(clientService.findByNome(name), HttpStatus.OK);
    }
    @GetMapping("/byTel/{tel}")
    public ResponseEntity<ClientRespostDTO> getClientByTel(@PathVariable String tel) {

        return new ResponseEntity<>(clientService.findByTel(tel), HttpStatus.OK);
    }

   /* @DeleteMapping("/delete/{id}")
     public void delete(@PathVariable Integer id){
         clientService.delete(id);
     }*/

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        // Vérifier si le client existe
        ClientRespostDTO client = clientService.findById(id);

        if (client == null) {
            // Si le client n'existe pas, renvoyer une erreur 404
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        }

        // Si le client existe, on procède à la suppression
        clientService.delete(id);

        // Renvoyer une réponse 200 OK ou 204 No Content après suppression réussie
        return new ResponseEntity<>("Client with id  "+id+"  deleted successfully", HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<ClientRespostDTO> save(@Valid @RequestBody ClientRequestDTO clientRequestDTO) {
        return new ResponseEntity<>(clientService.save(clientRequestDTO), HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ClientRespostDTO> update(@Valid @RequestBody ClientRequestDTO clientRequestDTO, @PathVariable Integer id) {
        return new ResponseEntity<>(clientService.update(clientRequestDTO, id), HttpStatus.OK);
    }
}
