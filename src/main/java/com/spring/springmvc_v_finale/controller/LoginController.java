package com.spring.springmvc_v_finale.controller;

import com.spring.springmvc_v_finale.model.Admin;
import com.spring.springmvc_v_finale.model.Client;
import com.spring.springmvc_v_finale.model.Type;
import com.spring.springmvc_v_finale.service.AdminService;
import com.spring.springmvc_v_finale.service.ClientService;
import com.spring.springmvc_v_finale.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private ClientService service;
    @Autowired
    private AdminService service_admin;
    @Autowired
    private  ComputerController cmp;
    @Autowired
    private TypeService t_serve;
    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView m = new ModelAndView();
        m.setViewName("computer/login/logincli");
        return m;
    }
    @GetMapping("/logadmin")
    public ModelAndView log_admin(){
        ModelAndView m = new ModelAndView();
        m.setViewName("computer/login/loginadmin");
        return m;
    }
    @GetMapping("/logout")
    public ModelAndView logOut(HttpServletRequest req){
        HttpSession session = req.getSession();
        session.removeAttribute("action");
        return index();
    }
    @PostMapping("/validate_cli")
    public ModelAndView validate_cli(@ModelAttribute Client client, HttpServletRequest req) throws Exception {
        try {
            Client c =service.Login(client);
            if (c==null)
                throw new Exception("email or mot de passe incorrect");
            HttpSession session = req.getSession();
            session.setAttribute("idcli",c.getId());
            session.setAttribute("action","cli");

        }catch (Exception e){
            e.printStackTrace();
            ModelAndView m = new ModelAndView("computer/login/logincli");
            m.addObject("error",e.getMessage());
            return m;
        }
        return cmp.getList(0,6);
    }
    @PostMapping("/validate_admin")
    public ModelAndView validate_admin(@ModelAttribute Admin admin, HttpServletRequest req) throws Exception {
        ModelAndView m = new ModelAndView();
        HttpSession session = req.getSession();
        session.setAttribute("action","admin");
        try {
            Admin ad =service_admin.login(admin);
            if (ad==null)
                throw new Exception("email or mot de passe incorrect");
        }catch (Exception e){
            m.addObject("error",e.getMessage());
            m.setViewName("computer/login/loginadmin");
            return m;
        }

        return cmp.selectAllCreated(0);
    }
}
