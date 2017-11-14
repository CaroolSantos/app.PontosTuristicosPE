/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Place;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

/**
 *
 * @author tuliodesouza
 */
public class PlaceDao {

    protected final SessionFactory  sessionFactory;

    public PlaceDao(){
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public Place get(int entityId) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Place entity = (Place) session.get(Place.class, entityId);

        session.getTransaction().commit();
        session.close();

        return entity;
    }

    public Place save(Place newEntity) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Integer id = (Integer) session.save(newEntity);
        newEntity.setId(id);

        session.getTransaction().commit();
        session.close();

        return newEntity;
    }

    public Place update(Place updatedEntity) {
        
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.update(updatedEntity);

        session.getTransaction().commit();
        session.close();
 
        return updatedEntity;
    }

    public List<Place> listByCategory(int categoryID) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Place> places = session.createQuery("SELECT p FROM Place p WHERE p.category.id=:category_id")
                .setParameter("category_id", categoryID)
                .list();

        session.getTransaction().commit();
        session.close();

        return places;
    }

    public List<Place> listByCategoryAndCity(int categoryID, int cityID) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Place> places = session.createQuery("SELECT p FROM Place p WHERE p.category.id=:category_id AND p.city.id=:city_id")
            .setParameter("category_id", categoryID)
            .setParameter("city_id", cityID)
            .list();

        session.getTransaction().commit();
        session.close();

        return places;
    }

    public List<Place> listAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Place> places = session.createQuery("SELECT p FROM Place p").list();

        session.getTransaction().commit();
        session.close();

        return places;
    }
    
}
