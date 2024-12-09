package com.example.MediaTech.services.client;

import com.example.MediaTech.DAO.ClientDAO;
import com.example.MediaTech.DTO.ClientRequestDTO;
import com.example.MediaTech.DTO.ClientRespostDTO;
import com.example.MediaTech.Models.Client;
import com.example.MediaTech.exceptions.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ClientServiceImpl implements ClientService {

    private final ClientDAO clientDAO;
    private final ModelMapper modelMapper;

    @Autowired
    public ClientServiceImpl(ModelMapper modelMapper, ClientDAO clientDAO) {
        this.modelMapper = modelMapper;
        this.clientDAO = clientDAO;
    }


    @Override
    public ClientRespostDTO save(ClientRequestDTO clientRequestDTO) {
        // Vérifier que le clientRequestDTO n'est pas nul et contient des données valides
        if (clientRequestDTO == null || clientRequestDTO.getNome() == null || clientRequestDTO.getCognome() == null) {
            throw new NotFoundException("Erreur : le client a des informations manquantes.");
        }

        // Mapper l'objet ClientRequestDTO vers un objet Client
        Client client = modelMapper.map(clientRequestDTO, Client.class);
        Client savedClient = clientDAO.save(client);

        // Mapper l'objet Client sauvegardé vers un ClientRespostDTO pour la réponse
        return modelMapper.map(savedClient, ClientRespostDTO.class);
    }



    @Override
    public ClientRespostDTO findById(Integer id) {
        // Vérifier si le client existe dans la base de données
        Client client = clientDAO.findById(id)
                .orElseThrow(() -> new NotFoundException("Client with ID " + id + " not found"));


        // Mapper l'entité Client vers ClientRespostDTO
        return modelMapper.map(client, ClientRespostDTO.class);
    }



    @Override
    public ClientRespostDTO findByNome(String nome) {
        Client client= clientDAO.findByNome(nome);
        if (client == null) {
            throw new NotFoundException("Client with name " + nome + " not found.");
        }
        return modelMapper.map(client, ClientRespostDTO.class);

    }


    @Override
    public ClientRespostDTO findByTel(String tel) {
        Client client= clientDAO.findByTel(tel);
        if (client == null) {
            throw new NotFoundException("Client with tel " + tel + " not found.");
        }
        return modelMapper.map(client, ClientRespostDTO.class);
    }



        @Override
    public void delete(Integer id) {
        Client client = clientDAO.findById(id)
                .orElseThrow(() -> new NotFoundException("Client with id " + id + " not found"));
            clientDAO.delete(client);
    }




    @Override
    public ClientRespostDTO update(ClientRequestDTO clientRequestDTO, Integer id) {
        // Cherche le client existant dans la base de données
        Client client = clientDAO.findById(id)
                .orElseThrow(() -> new NotFoundException("Client with ID " + id + " not found"));


        // Utiliser modelMapper pour mettre à jour les propriétés du client existant
        modelMapper.map(clientRequestDTO, client);  // Cela mettra à jour les propriétés de 'client' avec celles de 'clientRequestDTO'
        // Mapper le client mis à jour en ClientRespostDTO pour la réponse
        Client updatedClient = clientDAO.save(client);

      //  ClientRespostDTO clientRespost = modelMapper.map(updatedClient, ClientRespostDTO.class);
        ClientRespostDTO clientRespost=new ClientRespostDTO(updatedClient.getNome(),updatedClient.getCognome(),updatedClient.getTel());

        // Vérifier que le clientRequestDTO n'est pas nul et contient des données valides
        if (clientRequestDTO == null || clientRequestDTO.getNome() == null || clientRequestDTO.getCognome() == null) {
            throw new NotFoundException("Erreur : le client a des informations manquantes.");
        }

        return clientRespost;
    }

    @Override
    public ClientRespostDTO updatebyTel(ClientRequestDTO clientRequestDTO, String tel) {
        Client client= clientDAO.findByTel(tel);
        if (client == null) {
            throw new NotFoundException("Client with tel " + tel + " not found.");
        }
        modelMapper.map(clientRequestDTO, client);  // Cela mettra à jour les propriétés de 'client' avec celles de 'clientRequestDTO'

        // Sauvegarder le client mis à jour dans la base de données
        Client updatedClient = clientDAO.save(client);

        // Mapper le client mis à jour en ClientRespostDTO pour la réponse

        return modelMapper.map(updatedClient, ClientRespostDTO.class);
    }

    @Override
    // Méthode pour supprimer un client par téléphone
    public void deleteByTel(String tel) {
        Client client = clientDAO.findByTel(tel);
        if (client == null) {
            throw new NotFoundException("Client with tel " + tel + " not found.");
        }
        clientDAO.delete(client);
    }



    @Override
    public List<ClientRespostDTO> findAll() {
        return clientDAO.findAll().stream()
                .map(client -> modelMapper.map(client, ClientRespostDTO.class))
                .collect(Collectors.toList());
    }

}
