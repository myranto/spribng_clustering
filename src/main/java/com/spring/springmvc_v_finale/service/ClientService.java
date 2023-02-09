package com.spring.springmvc_v_finale.service;

import com.spring.springmvc_v_finale.inter.ClientInterface;
import com.spring.springmvc_v_finale.model.Client;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService extends ServiceModel implements ClientInterface {
    @Override
    public Client Login(Client cli)throws Exception {
        List<Client> root = getDao().findWhere(cli);

        return ((root!=null)&&(root.size()>0))?root.get(0):null;
    }
}
