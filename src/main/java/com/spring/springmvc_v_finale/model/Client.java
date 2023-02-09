package com.spring.springmvc_v_finale.model;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Client {
    @Id
    private int id;
    private String nom;
    private String email;

    public Client() {
    }

    private String mdp;

    public Client(String email, String mdp) {
        this.email = email;
        this.mdp = mdp;
    }

    public Client(String nom, String email, String mdp) {
        this.nom = nom;
        this.email = email;
        this.mdp = mdp;
    }

    public Client(int id, String nom, String email, String mdp) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.mdp = mdp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

}
