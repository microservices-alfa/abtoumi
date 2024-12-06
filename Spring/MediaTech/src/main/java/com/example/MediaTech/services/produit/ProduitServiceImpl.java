package com.example.MediaTech.services.produit;

import com.example.MediaTech.DAO.ProductDAO;
import com.example.MediaTech.DTO.ProduitDTO;
import com.example.MediaTech.Models.Facture;
import com.example.MediaTech.Models.Prodotto;
import com.example.MediaTech.exceptions.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProduitServiceImpl implements ProduitService {

    private ProductDAO produitDAO;
    private ModelMapper modelMapper;

    @Autowired
    public ProduitServiceImpl(ModelMapper modelMapper, ProductDAO produitDAO) {
        this.modelMapper = modelMapper;
        this.produitDAO = produitDAO;
    }

/***************************************************************************************************/

    /***************************************************************************************************/
    @Override
    public ProduitDTO save(ProduitDTO produitDTO) {
        if (produitDTO == null || produitDTO.getRef() == null || produitDTO.getLibelle()== null) {
            throw new NotFoundException("Errore: il prodotto ha informazioni mancanti.");
        }

        // Mapper l'objet ClientRequestDTO vers un objet Client
        Prodotto prodotto = modelMapper.map(produitDTO, Prodotto.class);
        Prodotto saved = produitDAO.save(prodotto);

        // Mapper l'objet   sauvegardé vers un prodottoDTO pour la réponse
        return modelMapper.map(saved, ProduitDTO.class);
    }
/***************************************************************************************************/

    /***************************************************************************************************/

    @Override
    public ProduitDTO findById(Integer id) {

        Prodotto prodotto=produitDAO.findById(id).orElseThrow(() -> new NotFoundException("prodotto with ID " + id + " not found"));

        return modelMapper.map(prodotto, ProduitDTO.class);
    }
/***************************************************************************************************/

    /***************************************************************************************************/
    @Override
    public ProduitDTO findByRef(String ref) {
        Prodotto prodotto=produitDAO.findByRef(ref);
        if (prodotto == null) {
            throw new NotFoundException("prodotto with ref " + ref + " not found.");
        }
        return modelMapper.map(prodotto, ProduitDTO.class) ;
    }
/***************************************************************************************************/

    /***************************************************************************************************/
    @Override
    public ProduitDTO findByLibelle(String libelle) {
        Prodotto prodotto=produitDAO.findByLibelle(libelle);
        if (prodotto == null) {
            throw new NotFoundException("prodotto with ref " + libelle + " not found.");
        }
        return modelMapper.map(prodotto, ProduitDTO.class) ;
    }
/***************************************************************************************************/

    /***************************************************************************************************/
    @Override
    public void delete(Integer id) {
        Prodotto prodotto = produitDAO.findById(id)
                .orElseThrow(() -> new NotFoundException("prodotto with id " + id + " not found"));
            produitDAO.delete(prodotto);
    }
/***************************************************************************************************/

    /***************************************************************************************************/
    @Override
    public ProduitDTO update(ProduitDTO produitDTO, String ref) {
        Prodotto prodotto =produitDAO.findByRef(ref);
        if (prodotto == null) {
            throw new NotFoundException("prodotto with ref " + ref + " not found.");
        }
        modelMapper.map(produitDTO, prodotto);
        produitDAO.save(prodotto);

        return modelMapper.map(prodotto, ProduitDTO.class);
    }
/***************************************************************************************************/

    /***************************************************************************************************/
    @Override
    public List<ProduitDTO> findAll() {
        return produitDAO.findAll().stream().
                map(prodotto -> modelMapper.map(prodotto, ProduitDTO.class)).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public void deleteByRef(String ref) {
        Prodotto prodotto = produitDAO.findByRef(ref);
        if (prodotto == null) {
            throw new NotFoundException("prodotto with tel " + ref + " not found.");
        }
        produitDAO.delete(prodotto);

    }


}
