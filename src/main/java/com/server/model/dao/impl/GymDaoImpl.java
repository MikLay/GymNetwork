package com.server.model.dao.impl;

import com.server.model.HibernateUtil;
import com.server.model.dao.GymDao;
import com.server.model.entity.Gym;
import com.server.model.exception.InvalidIdException;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Properties;

@Log4j
public class GymDaoImpl implements GymDao {

    private Properties properties;
    private SessionFactory sessionFactory;

    public GymDaoImpl(Properties properties) {
        this.properties = properties;
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public List<Gym> findByCoach(Integer coachId) {
        log.info("findByCoach start with coachId: " + coachId);
        Session session = this.sessionFactory.getCurrentSession();
        List<Gym> gyms = (List<Gym>) session.createSQLQuery(properties.getProperty("findByCoach"))
                .setParameter("coachId", coachId).addEntity(Gym.class).list();
        log.info("findByCoach end");
        return gyms;
    }

    @Override
    public List<Gym> findByClient(Integer clientId) {
        Session session = this.sessionFactory.getCurrentSession();
        List<Gym> gyms = (List<Gym>) session.createSQLQuery(properties.getProperty("findByClient"))
                .setParameter("clientId", clientId).addEntity(Gym.class).list();
        return gyms;
    }

    @Override
    public void create(Gym gym) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(gym);
    }

    @Override
    public Gym findById(Integer entityId) throws InvalidIdException {
        Session session = this.sessionFactory.getCurrentSession();
        Gym gym = session.get(Gym.class, entityId);
        return gym;
    }

    @Override
    public void update(Gym gym) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(gym);
    }

    @Override
    public void delete(Gym gym) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(gym);

    }

    @Override
    public List<Gym> findAll() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Gym> gyms = (List<Gym>) session.createQuery(properties.getProperty("findAll")).list();
        return gyms;
    }
}
