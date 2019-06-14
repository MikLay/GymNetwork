package com.server.model.dao.impl;

import com.server.model.HibernateUtil;
import com.server.model.dao.CoachDao;
import com.server.model.entity.Coach;
import com.server.model.exception.InvalidIdException;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Properties;

@Log4j
public class CoachDaoImpl implements CoachDao {

    private Properties properties;
    private SessionFactory sessionFactory;

    public CoachDaoImpl(Properties properties) {
        this.properties = properties;
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public List<Coach> findByGym(Integer gymId) {
        log.info("findByGym start with parameters: gymId: " + gymId);
        Session session = this.sessionFactory.getCurrentSession();
        List<Coach> coaches = (List<Coach>) session.createSQLQuery(properties.getProperty("findByGym"))
                .setParameter("gymId", gymId).addEntity(Coach.class).list();
        System.out.println(coaches.size());
        log.info("findByGym end");
        return coaches;
    }

    @Override
    public List<Coach> findByClient(Integer clientId) {
        log.info("findByClient start with parameters: gymId: " + clientId);

        Session session = this.sessionFactory.getCurrentSession();
        List<Coach> coaches = (List<Coach>) session.createSQLQuery(properties.getProperty("findByClient"))
                .setParameter("clientId", clientId).addEntity(Coach.class).list();
        log.info("findByClient end");
        return coaches;
    }

    @Override
    public void create(Coach coach) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(coach);
    }

    @Override
    public Coach findById(Integer entityId) throws InvalidIdException {
        Session session = this.sessionFactory.getCurrentSession();
        Coach coach = session.get(Coach.class, entityId);
        return coach;
    }

    @Override
    public void update(Coach coach) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(coach);
    }

    @Override
    public void delete(Coach coach) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(coach);
    }

    @Override
    public List<Coach> findAll() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Coach> coaches = (List<Coach>) session.createQuery(properties.getProperty("findAll")).list();
        return coaches;
    }
}
