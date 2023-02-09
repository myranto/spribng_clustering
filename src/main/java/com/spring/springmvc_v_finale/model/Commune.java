package com.spring.springmvc_v_finale.model;

import javax.persistence.*;

@Entity
public class Commune{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private
    Integer id;

    @Column(name = "id_district")
    private Integer idDistrict;

    private String nom;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdDistrict() {
        return idDistrict;
    }

    public void setIdDistrict(Integer idDistrict) {
        this.idDistrict = idDistrict;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
