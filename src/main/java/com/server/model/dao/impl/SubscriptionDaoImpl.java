package com.server.model.dao.impl;

import com.server.model.HibernateUtil;
import com.server.model.dao.SubscriptionDao;
import com.server.model.entity.Subscription;
import com.server.model.exception.InvalidIdException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Properties;

public class SubscriptionDaoImpl implements SubscriptionDao {

    private Properties properties;
    private SessionFactory sessionFactory;

    public SubscriptionDaoImpl(Properties properties) {
        this.properties = properties;
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public List<Subscription> findByClient(Integer clientId) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Subscription> subscriptions = (List<Subscription>) session
                .createQuery(properties.getProperty("findByClient")).setParameter("clientId", clientId).list();
        return subscriptions;
    }

    @Override
    public void create(Subscription subscription) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(subscription);
    }

    @Override
    public Subscription findById(Integer entityId) throws InvalidIdException {
        Session session = this.sessionFactory.getCurrentSession();
        Subscription subscription = session.get(Subscription.class, entityId);
        return subscription;
    }

    @Override
    public void update(Subscription subscription) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(subscription);
    }

    @Override
    public void delete(Subscription subscription) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(subscription);
    }

    @Override
    public List<Subscription> findAll() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Subscription> subscriptions = (List<Subscription>) session
                .createQuery(properties.getProperty("findAll")).list();
        return subscriptions;
    }
}
