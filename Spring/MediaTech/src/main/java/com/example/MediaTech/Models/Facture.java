package com.example.MediaTech.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import jakarta.persistence.Version;

@Entity
@Table(name="factures")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Facture implements Serializable {

    @Version
    private int version;

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String ref;

    @Column(nullable = false)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToMany(mappedBy = "facture", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<LigneFactureEntity> factureEntites;


}