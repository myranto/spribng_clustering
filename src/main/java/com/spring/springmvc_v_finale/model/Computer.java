package com.spring.springmvc_v_finale.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double prix;
    private String description;
    private Timestamp date_sortie;
    private String image;
    private String lieu;
    private int idclient;
    private int status=0;

    private Timestamp date_creation=new Timestamp(System.currentTimeMillis());


    public int getIdclient() {
        return idclient;
    }

    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }

    public int getStatus() {
        return status;
    }

    public Timestamp getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Timestamp date_creation) {
        this.date_creation = date_creation;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Computer(String name, double prix, String description, Timestamp date_sortie, String image,int idclient,int status,Timestamp date_creation) {
        this.name = name;
        this.prix = prix;
        this.description = description;
        this.date_sortie = date_sortie;
        this.image = image;
        this.idclient = idclient;
        this.status=status;
        this.date_creation = date_creation;
    }

    public Computer(String name, double prix, String description, Timestamp date_sortie) {
        this.name = name;
        this.prix = prix;
        this.description = description;
        this.date_sortie = date_sortie;
    }

    public Computer() {
    }

    public Computer(String name, double prix, String description) {
        this.name = name;
        this.prix = prix;
        this.description = description;
    }

    public Computer(int id, String name, double prix, String description, Timestamp date_sortie) {
        this.id = id;
        this.name = name;
        this.prix = prix;
        this.description = description;
        this.date_sortie = date_sortie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getImage() {
        return image;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
