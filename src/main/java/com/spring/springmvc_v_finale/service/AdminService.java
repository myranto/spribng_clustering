package com.spring.springmvc_v_finale.service;

import com.spring.springmvc_v_finale.inter.AdminInterface;
import com.spring.springmvc_v_finale.model.Admin;
import com.spring.springmvc_v_finale.model.Client;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService extends ServiceModel implements AdminInterface {
    @Override
    public Admin login(Admin admin) throws Exception {
        List<Admin> root = getDao().findWhere(admin);
        return ((root!=null)&&(root.size()>0))?root.get(0):null;
    }
}
