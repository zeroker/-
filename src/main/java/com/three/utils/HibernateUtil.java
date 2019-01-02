package com.three.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

public class HibernateUtil {
//    private  static Logger logger = LogManager.getLogger(HibernateUtil.class);
//    private static SessionFactory sessionFactory;
//    private static ThreadLocal threadLocal = new ThreadLocal();
////    private LocalSessionFactoryBean sessionFactory;
//
//    private HibernateUtil(){}
//    static {
//        Configuration config = new Configuration().configure();
//
//
//        StandardServiceRegistryBuilder serviceRegistryBuilder = new
//                StandardServiceRegistryBuilder().applySettings(config.getProperties());
//
//        ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
//
//        sessionFactory = config.buildSessionFactory(serviceRegistry);
//
//    }
//
//    public static Session getThreadLocalSession(){
//        Session session = (Session)threadLocal.get();
//        if(session == null){
//            session = sessionFactory.openSession();
//            threadLocal.set(session);
//        }
//        return session;
//    }
//    public static void closeSession(){
//        Session session = (Session)threadLocal.get();
////        logger.info("close session");
//        if(session!=null){
//            session.close();
//            threadLocal.set(null);
////            logger.info("close!!!");
//        }
//
//    }
//
//
//
}
//

