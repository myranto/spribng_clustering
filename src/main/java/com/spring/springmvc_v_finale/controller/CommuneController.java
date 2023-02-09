package com.spring.springmvc_v_finale.controller;

import com.spring.springmvc_v_finale.hib.AccessHibernate;
import com.spring.springmvc_v_finale.model.Commune;
import com.spring.springmvc_v_finale.model.District;
import org.aina.HibernateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommuneController {

    @Autowired
    AccessHibernate dao;

    @GetMapping("/form-commune")
    public String formulaireCommune(Model model) throws Exception {
        model.addAttribute("districts", dao.findAll(District.class));
        return "commune";
    }

    @PostMapping("/commune")
    public String createCommune(@ModelAttribute Commune commune, Model model) throws Exception {
        dao.save(commune);
        model.addAttribute("communes", dao.findAll(Commune.class));
        return "liste-commune";
    }

    public AccessHibernate getDao() {
        return dao;
    }

    public void setDao(AccessHibernate dao) {
        this.dao = dao;
    }
}
