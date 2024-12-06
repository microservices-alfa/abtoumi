package com.example.MediaTech.DAO;

import com.example.MediaTech.Models.LigneFactureEntity;
import com.example.MediaTech.Models.LigneFactureKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository//L'annotation @Repository dans Spring sert à marquer une interface comme
// un composant de type repository, ce qui indique à Spring que cette interface est responsable
// de l'accès aux données dans la couche persistence (base de données).
public interface LigneFactureDAO  extends JpaRepository<LigneFactureEntity, LigneFactureKey> {

}
