package com.spring.springmvc_v_finale.hib;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Component;

import javax.xml.transform.Transformer;
import java.io.Serializable;
import java.util.List;

//@Component("AccessHibernate")
public class AccessHibernate{
    SessionFactory sessionFactory; 


    public <T> T  save(T mod) throws Exception {
        Session session = this.sessionFactory.openSession();
        Transaction trans =null;
        try {
            session = sessionFactory.openSession();
            trans = session.beginTransaction();
            session.save(mod);
            trans.commit();

        }catch (Exception e){
            if (trans.isActive()) {
                trans.rollback();
            }
            e.printStackTrace();
            throw e;
        }finally {
            session.close();
        }
        return mod;
    }
    public <T> T findById(Class<T> clazz, Serializable id) {
        Session session = this.sessionFactory.openSession();
        T entity = session.get(clazz, id);
        session.close();
        return entity;
    }
    public <T>  void update(T mod) throws Exception {
        Session session = this.sessionFactory.openSession();

        Transaction trans =null;
        try {
            session = sessionFactory.openSession();
            trans = session.beginTransaction();
            session.update(mod);
            trans.commit();

        }catch (Exception e){
            if (trans.isActive()) {
                trans.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
    public void deleteById(Class tClass, Serializable id) throws Exception {
        this.delete(this.findById(tClass, id));
    }

    public void delete(Object mod) throws Exception {
        Session session = this.sessionFactory.openSession();

        Transaction trans =null;
        try {
            session = sessionFactory.openSession();
            trans = session.beginTransaction();
            session.delete(mod);
            trans.commit();
        }catch (Exception e){
            if (trans.isActive()) {
                trans.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public <T> long countRow(Class<T> table) throws Exception {
        long count = 0;
        Session session = this.sessionFactory.openSession();
        String sql = "select count(*)  from "+table.getSimpleName();

        try {
            Query query = session.createQuery(sql);
            count = (long) query.uniqueResult();

        }catch (Exception e){
            throw new Exception(e);
        }finally {
        session.close();

        }
        return count;
    }

    public <T> List<T> findAll(Class<T> tClass) throws Exception {
        Session session = this.sessionFactory.openSession();
        List<T> results = null;
        try {
            results = session.createCriteria(tClass).list();
        }catch (Exception e){
            throw new Exception(e);
        }finally {
        session.close();

        }
        return results;
    }
    public <T>List<T> pagination(T mod,int firstPage,int lastPage) throws Exception {
        Session session = sessionFactory.openSession();
        List<T> list = null;
        try {
        String namTable = mod.getClass().getSimpleName();
        String sql = "from "+namTable;
        Query query = session.createQuery(sql);
            System.out.println(firstPage+" and "+lastPage);
            query.setFirstResult(firstPage);
        query.setMaxResults(lastPage);
         list = query.getResultList();

        }catch (Exception e){
            throw new Exception(e);
        }finally {

        session.close();
        }
        return list;
    }
    public <T>List<T> paginateResult(String sql,int firstPage,int lastPage,Class<T> search) throws Exception {
        Session session = sessionFactory.openSession();
        List<T> list = null;
        try {
            Query query = session.createSQLQuery(sql);
//            Query query = session.createQuery(sql,search);
            query.setResultTransformer(Transformers.aliasToBean(search));
            query.setFirstResult(firstPage);
            query.setMaxResults(lastPage);
            list = query.getResultList();


        }catch (Exception e){
            throw new Exception(e);
        }finally {
            session.close();

        }
        return list;
    }
    public <T>List<T> SelectByNativeQuery(String sql) throws Exception {
        Session session = sessionFactory.openSession();
        List<T> list = null;
        try {
            Query query = session.createSQLQuery(sql);
            list = query.getResultList();
        }catch (Exception e){
            throw new Exception(e);
        }finally {
            session.close();
        }
        return list;
    }
    public <T> List<T> findWhere(T entity) throws Exception {
        Session session = this.sessionFactory.openSession();
        List<T> results = null;
        try {
        Example example = Example.create(entity).ignoreCase();
        results = session.createCriteria(entity.getClass()).add(example).list();

        }catch (Exception e){
                throw new Exception(e);
        }finally {

        session.close();
        }
        return results;
    }
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
