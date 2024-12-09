package com.example.MediaTech.Controllers.client;

import com.example.MediaTech.DAO.ClientDAO;
import com.example.MediaTech.DTO.ClientRequestDTO;
import com.example.MediaTech.DTO.ClientRespostDTO;
import com.example.MediaTech.Models.Client;
import com.example.MediaTech.exceptions.NotFoundException;
import com.example.MediaTech.services.client.ClientServiceImpl;
import lombok.ToString;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientServiceImplTest {

    @Mock
    private ClientDAO clientDAO; // Mock du DAO

    @Mock
    private ModelMapper modelMapper; // Mock du ModelMapper

    private ClientServiceImpl clientService;


    private ClientRequestDTO clientRequestDTO;
    private Client client;


    @BeforeEach
    void setUp() {
        // Initialisation de la classe de service avec les mocks
        MockitoAnnotations.openMocks(this);
        clientService = new ClientServiceImpl(modelMapper, clientDAO);
        client = new Client(1, "Abdessalem", "Toumi", "123456789", null, null);

        clientRequestDTO = new ClientRequestDTO("Abdessalem", "Toumi", "123456789");
    }

    @Test
    void findAll() {
        // Création de données de test
        Client client1 = new Client(1, "Abdessalem", "Toumi", "123456789", null, null);
        Client client2 = new Client(2, "Luca", "Salsone", "987654321", null, null);

        // Création de la liste des clients
        List<Client> clients = Arrays.asList(client1, client2);

        // Mock de la méthode findAll du DAO
        when(clientDAO.findAll()).thenReturn(clients);

        // Mock de la conversion avec ModelMapper
        ClientRespostDTO clientDTO1 = new ClientRespostDTO("Abdessalem", "Toumi", "123456789");
        ClientRespostDTO clientDTO2 = new ClientRespostDTO("Luca", "Salsone", "987654321");
        when(modelMapper.map(client1, ClientRespostDTO.class)).thenReturn(clientDTO1);
        when(modelMapper.map(client2, ClientRespostDTO.class)).thenReturn(clientDTO2);

        // Appel de la méthode findAll
        List<ClientRespostDTO> clientRespostDTOS = clientService.findAll();

        // Vérification des résultats
        assertNotNull(clientRespostDTOS); // La liste ne doit pas être nulle
        assertEquals(2, clientRespostDTOS.size()); // La liste doit contenir 2 éléments
        assertEquals("Abdessalem", clientRespostDTOS.get(0).getNome()); // Vérification des données du premier client
        assertEquals("Luca", clientRespostDTOS.get(1).getNome()); // Vérification des données du deuxième client

        // Vérification des interactions avec les mocks
        verify(clientDAO, times(1)).findAll();
        verify(modelMapper, times(2)).map(any(Client.class), eq(ClientRespostDTO.class)); // Vérification du mappage
    }


    @Test
    void deleteClient_Success() {

        // Créer un client exemple

        // Simuler la recherche d'un client dans le DAO
        when(clientDAO.findById(1)).thenReturn(java.util.Optional.of(client));

        // Exécuter la méthode delete
        clientService.delete(1);

        // Vérifier que la méthode delete a été appelée sur le DAO
        verify(clientDAO, times(1)).delete(client);  // Vérifie que delete a été appelé une fois
    }

    @Test
    void deleteClient_NotFound() {
        // Simuler le cas où le client n'existe pas dans le DAO
        when(clientDAO.findById(1)).thenReturn(java.util.Optional.empty());

        // Vérifier que la méthode delete lance une exception NotFoundException
        assertThrows(NotFoundException.class, () -> clientService.delete(1));

        // Vérifier que la méthode delete n'a pas été appelée
        verify(clientDAO, never()).delete(any(Client.class));
    }



    @Test
    void saveClient_Success() {


        ClientRequestDTO clientRequestDTO = new ClientRequestDTO("Abdessalem", "Toumi", "123456789");
        // Simuler le mapping de ClientRequestDTO vers Client
        when(modelMapper.map(clientRequestDTO, Client.class)).thenReturn(client);

        // Simuler la sauvegarde du client dans le DAO
        when(clientDAO.save(client)).thenReturn(client);

        // Simuler le mapping de Client vers ClientRespostDTO
        ClientRespostDTO clientRespostDTO = new ClientRespostDTO();
        clientRespostDTO.setNome("Abdessalem");
        clientRespostDTO.setCognome("Toumi");
        clientRespostDTO.setTel("123456789");
        when(modelMapper.map(client, ClientRespostDTO.class)).thenReturn(clientRespostDTO);

        // Exécuter la méthode save
        ClientRespostDTO savedClient = clientService.save(clientRequestDTO);

        // Vérifier que la méthode save a été appelée sur le DAO
        verify(clientDAO, times(1)).save(client);

        // Vérifier les résultats
        assertNotNull(savedClient);
        assertEquals("Abdessalem", savedClient.getNome());
        assertEquals("Toumi", savedClient.getCognome());
        assertEquals("123456789", savedClient.getTel());
    }

    @Test
    void saveClient_InvalidData() {


        // Tester le cas où les données sont invalides (par exemple, nom ou prénom manquants)
        ClientRequestDTO invalidClientRequestDTO = new ClientRequestDTO();
        invalidClientRequestDTO.setNome(null);  // Nom manquant

        // Vérifier que la méthode save lance une exception NotFoundException
        assertThrows(NotFoundException.class, () -> clientService.save(invalidClientRequestDTO));

        // Vérifier que la méthode save n'a pas été appelée sur le DAO
        verify(clientDAO, never()).save(any(Client.class));
    }

    @Test
    void findById_Success() {
        ClientRespostDTO clientRespostDTO = new ClientRespostDTO("Abdessalem", "Toumi", "123456789");
        // Simuler la réponse du DAO pour findById
        when(clientDAO.findById(1)).thenReturn(java.util.Optional.of(client));

        // Simuler le mappage du Client vers ClientRespostDTO
        when(modelMapper.map(client, ClientRespostDTO.class)).thenReturn(clientRespostDTO);

        // Exécuter la méthode findById
        ClientRespostDTO foundClient = clientService.findById(1);

        // Vérifier que la méthode findById a été appelée sur le DAO
        verify(clientDAO, times(1)).findById(1);

        // Vérifier les résultats
        assertNotNull(foundClient);
        assertEquals("Abdessalem", foundClient.getNome());
        assertEquals("Toumi", foundClient.getCognome());
        assertEquals("123456789", foundClient.getTel());
    }




    @Test
    void findById_ClientNotFound() {
        // Simuler que le client n'existe pas
        when(clientDAO.findById(1)).thenReturn(java.util.Optional.empty());

        // Vérifier que la méthode lance une exception NotFoundException
        assertThrows(NotFoundException.class, () -> clientService.findById(1));

        // Vérifier que la méthode findById n'a pas été appelée sur le DAO
        verify(clientDAO, times(1)).findById(1);
    }


    @Test
    void findByTel_ClientNotFound() {
        // Simuler que le client n'existe pas
        when(clientDAO.findByTel("123456789")).thenReturn(null);

        // Vérifier que la méthode lance une exception NotFoundException
        assertThrows(NotFoundException.class, () -> clientService.findByTel("123456789"));

        // Vérifier que la méthode findByTel n'a pas été appelée plus d'une fois
        verify(clientDAO, times(1)).findByTel("123456789");
    }


    @Test
    void  findByTel_Success(){
        ClientRespostDTO clientRespostDTO = new ClientRespostDTO("Abdessalem", "Toumi", "123456789");
        when(clientDAO.findByTel("123456789")).thenReturn(client);
        when(modelMapper.map(client, ClientRespostDTO.class)).thenReturn(clientRespostDTO);
        ClientRespostDTO foundClient = clientService.findByTel("123456789");
        verify(clientDAO, times(1)).findByTel("123456789");
        assertNotNull(foundClient);
        assertEquals("Abdessalem", foundClient.getNome());
        assertEquals("Toumi", foundClient.getCognome());
        assertEquals("123456789", foundClient.getTel());

    }


    @Test
    void testUpdate() {

        // Simuler le lient trouvé dans la base de données
        when(clientDAO.findById(1)).thenReturn(Optional.of(client));

        // Simuler le mappage du DTO vers le client
        when(modelMapper.map(clientRequestDTO, Client.class)).thenReturn(client);

        // Simuler la sauvegarde du client mis à jour
        when(clientDAO.save(client)).thenReturn(client);

        // Appeler la méthode update
        ClientRespostDTO updatedClient = clientService.update(clientRequestDTO, 1);

        // Vérifier que le client a été mis à jour et retourné
        assertNotNull(updatedClient);
        assertEquals("Abdessalem", updatedClient.getNome());
        assertEquals("Toumi", updatedClient.getCognome());
        assertEquals("123456789", updatedClient.getTel());

        // Vérifier que la méthode findById a été appelée une fois avec l'ID 1
        verify(clientDAO, times(1)).findById(1);

        // Vérifier que la méthode save a été appelée une fois pour sauvegarder le client mis à jour
        verify(clientDAO, times(1)).save(client);
    }

    @Test
    void testUpdate_ClientNotFound() {
        // Simuler le cas où le client n'est pas trouvé
        when(clientDAO.findById(1)).thenReturn(Optional.empty());

        // Appeler la méthode update et vérifier qu'une exception est lancée
        assertThrows(NotFoundException.class, () -> clientService.update(clientRequestDTO, 1));
    }




}
