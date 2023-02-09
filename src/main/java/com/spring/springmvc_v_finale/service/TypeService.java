package com.spring.springmvc_v_finale.service;

import com.spring.springmvc_v_finale.model.Type;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TypeService extends ServiceModel{
    public List<Type> findAll() throws Exception {
        return getDao().findAll(Type.class);
    }
}
