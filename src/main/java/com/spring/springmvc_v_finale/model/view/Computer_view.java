package com.spring.springmvc_v_finale.model.view;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
public class Computer_view {
    private String nom;
    private int idtype;
    private Date date_debut;
    private Date date_fin;
    private int remise;
    @Id
    private int id_cp;
    private String name;
    private double prix;
    private String description;
    private Timestamp date_sortie;
    private String image;
    private String lieu;
    private int idclient;
    private int status=0;
    private String nom_client;
    private Timestamp date_creation;

    public Timestamp getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Timestamp date_creation) {
        this.date_creation = date_creation;
    }

    public String getNom_client() {
        return nom_client;
    }

    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }

    public int getIdclient() {
        return idclient;
    }

    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public Computer_view(int idtype, Date date_debut, Date date_fin, int remise, double prix, Timestamp date_sortie,String keyword) {
        this.idtype = idtype;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.remise = remise;
        this.prix = prix;
        this.date_sortie = date_sortie;
        this.name=keyword;
        this.description=keyword;
    }

    public Computer_view() {
    }

    public Computer_view(String nom, int idtype, Date date_debut, Date date_fin, int remise, int id_cp, String name, double prix, String description, Timestamp date_sortie,int idclient,int status,String nom_client,Timestamp date_creation) {
        this.nom = nom;
        this.idtype = idtype;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.remise = remise;
        this.id_cp = id_cp;
        this.name = name;
        this.prix = prix;
        this.description = description;
        this.date_sortie = date_sortie;
        this.idclient = idclient;
        this.status=status;
        this.nom_client=nom_client;
        this.date_creation = date_creation;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getIdtype() {
        return idtype;
    }

    public void setIdtype(int idtype) {
        this.idtype = idtype;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public int getRemise() {
        return remise;
    }

    public void setRemise(int remise) {
        this.remise = remise;
    }

    public int getId_cp() {
        return id_cp;
    }

    public void setId_cp(int id_cp) {
        this.id_cp = id_cp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDate_sortie() {
        return date_sortie;
    }

    public void setDate_sortie(Timestamp date_sortie) {
        this.date_sortie = date_sortie;
    }
}
