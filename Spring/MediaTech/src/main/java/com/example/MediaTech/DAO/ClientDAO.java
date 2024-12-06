package com.example.MediaTech.DAO;

import com.example.MediaTech.Models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDAO extends JpaRepository <Client,Integer>{

    Client findByNome(String nome);
    Client findByTel(String tel);
    void deleteByTel(String tel);



}
