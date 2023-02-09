package com.spring.springmvc_v_finale.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Optional;

@Entity
public class Type_event_detail {
    @ManyToOne(optional=false)
    @JoinColumn(name="idtype", nullable=false, updatable=false)
    private Type idtype;
    private Date date_debut;
    private Date date_fin;
    @Id
    private int remise;
    @ManyToOne(optional=false)
    @JoinColumn(name="id_cp",nullable=false, updatable=false)
    private Computer id_cp;

    public Type getIdtype() {
        return idtype;
    }

    public void setIdtype(Type idtype) {
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

    public Computer getId_cp() {
        return id_cp;
    }

    public void setId_cp(Computer id_cp) {
        this.id_cp = id_cp;
    }
}
