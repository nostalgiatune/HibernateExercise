package com.tuoppi.pysakointi.persistence;

/* Tuomas Toivonen
 * 17.11.2015
*/

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtils {
    
    private static final SessionFactory sessionFactory = new Configuration()
            .configure().buildSessionFactory();
    
    
    public static Session getSession() {
        return sessionFactory.openSession();
    }
    
}