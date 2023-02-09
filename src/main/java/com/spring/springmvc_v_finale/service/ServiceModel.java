package com.spring.springmvc_v_finale.service;

import com.spring.springmvc_v_finale.hib.AccessHibernate;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceModel {
    @Autowired
    AccessHibernate dao;

    public AccessHibernate getDao() {
        return dao;
    }

    public void setDao(AccessHibernate dao) {
        this.dao = dao;
    }
}
