package com.three.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("hibernateDao")
public class HibernateDao<T> implements IBaseDao<T> {

    private static Logger logger = LogManager.getLogger(HibernateDao.class);


    @Resource
    private SessionFactory sessionFactory ;



//    @Transactional
    public int insert(T o) {
        int result = 0;
        Session session = null;
        Transaction transaction = null;

        try{
//            session = sessionFactory.getCurrentSession();
              session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(o);
            transaction.commit();
            result = 1;
        }catch (Exception e){
            logger.info("insert error");
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            logger.info("insert finally");
        }
        session.close();
        return result;
    }


//    @Transactional
    public int insert(List<T> list) {
        int result = 0;
        for(T t:list)
            insert(t);
        return list.size();
    }

//    @Transactional
    public int delete(T o) {
        Session session = null;
        Transaction transaction = null;
        int result = 0;
        try{
//            session = sessionFactory.getCurrentSession();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.delete(o);
            transaction.commit();
            result = 1;
        }catch (Exception e){
            if(transaction != null)
                transaction.rollback();
        }
        session.close();
        return result;

    }

//    @Transactional
    public int delete(Class c, int id) {
        Session session = null;
        Transaction transaction = null;
        int result = 0;
        try{
//            session = sessionFactory.getCurrentSession();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            logger.info("session.load()   "+session.load(c,id));
            session.delete(session.load(c,id));                             //
            transaction.commit();
            result = 1;
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
        }
        session.close();
        return result;
    }

//    @Transactional
    public int deleteList(Class c, int... ids) {
        for(int id:ids)
            delete(c,id);
        return ids.length;
    }


//    @Transactional
    public T findById(Class c, int id) {
        Session session = null;
        T t = null;
//        session = sessionFactory.getCurrentSession();
        session = sessionFactory.openSession();
        t = (T)session.get(c,id);
        session.close();

        return t;
    }

//    @Transactional
    public T findOne(String hql, String[] param) {
        Session session = null;
        T t = null;

//        session = sessionFactory.getCurrentSession();
        session = sessionFactory.openSession();
        Query query = session.createQuery(hql);
        if(param != null){
            for(int i=0; i<param.length; i++)
            {
                query.setParameter(i,param[i]);
            }
        }
        t = (T)query.uniqueResult();
        session.close();
        return t;
    }


    //    @Transactional
    public List<T> find(String hql, String[] param) {
        List<T> list = null;
        Session session = null;


//        session = sessionFactory.getCurrentSession();
        session = sessionFactory.openSession();
        Query query = session.createQuery(hql);
        if(query !=null){
            if(param != null){
                for(int i=0; i<param.length; i++)
                {
                    query.setParameter(i,param[i]);
                }
            }
        }
        list = query.list();

        session.close();
        return list;
    }

//    @Transactional
    public List findPage(String hql, String[] param, int page, int size) {
        List<T> list = null;
        Session session = null;

//        session = sessionFactory.getCurrentSession();
        session = sessionFactory.openSession();
        Query query = session.createQuery(hql);
        if(query !=null){
            for(int i=0; i<param.length; i++)
            {
                query.setParameter(i,param[i]);
            }
        }
        query.setFirstResult((page - 1)*size);
        query.setMaxResults(size);
        list = query.list();


        session.close();
        return list;
    }


    //更改

//    @Transactional
    public int update(Object o) {

        Session session = null;
        int result = 0;
        Transaction transaction = null;

        try{
//            session = sessionFactory.getCurrentSession();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(o);
            transaction.commit();
            result = 1;
        }catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }

        }
        session.close();
        return result;
    }

//    @Transactional
    public int getCount(String hql, String[] param) {
        int result = 0;
        Session session = null;

//        session = sessionFactory.getCurrentSession();
        session = sessionFactory.openSession();
        Query query = session.createQuery(hql);
        if(param != null){
            for(int i=0; i<param.length; i++){
                query.setParameter(i,param[i]);
            }
        }
        result = Integer.valueOf(query.iterate().next().toString());

        session.close();
        return result;
    }




//    @Transactional
    public List findByFields(String hql, String[] fields, String []condition) {       //模糊字段查找
        Session session = null;
        String findhql = hql;
        int size = fields.length;
        if(fields != null && condition != null && fields.length > 0 && !condition.equals("")){
            findhql = findhql + " where ";
            if(size == 1){
                findhql += fields[0] + " like '%" + condition[0] + "%' ";
            }else{
                for(int i=0; i<fields.length-1; i++){
                    findhql += fields[i] + " like '%" + condition[i] + "%' or ";
                }
                findhql += fields[fields.length-1] + " like '%" + condition[fields.length-1] + "%' ";
            }

        }


//        session = sessionFactory.getCurrentSession();
        logger.info(findhql);
        session = sessionFactory.openSession();
        Query query = session.createQuery(findhql);
        List<T> list = query.list();
        session.close();
        return list;


    }


}
