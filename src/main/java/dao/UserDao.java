/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author tuliodesouza
 */
public class UserDao {

    protected final SessionFactory  sessionFactory;
    
    public UserDao(){
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public User get(int entityId) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        User entity = (User) session.get(User.class, entityId);

        session.getTransaction().commit();
        session.close();

        return entity;
    }

    public User save(User newEntity) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Integer id = (Integer) session.save(newEntity);
        newEntity.setId(id);

        session.getTransaction().commit();
        session.close();
 
        return newEntity;
    }

    public User update(User updatedEntity) {
        
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.update(updatedEntity);

        session.getTransaction().commit();
        session.close();
 
        return updatedEntity;
    }

    public User getByEmail(String email) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        User user = (User) session.createQuery("SELECT u FROM User u WHERE u.email=:email")
                .setParameter("email", email)
                .uniqueResult();

        session.getTransaction().commit();
        session.close();

        return user;
    }
    
}
