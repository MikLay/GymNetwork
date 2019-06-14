package com.server.model.dao.impl;

import com.server.model.HibernateUtil;
import com.server.model.dao.WorkoutDao;
import com.server.model.entity.Workout;
import com.server.model.exception.InvalidIdException;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Properties;

@Log4j
public class WorkoutDaoImpl implements WorkoutDao {

    private Properties properties;
    private SessionFactory sessionFactory;


    public WorkoutDaoImpl(Properties properties) {
        this.properties = properties;
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    //FindBy methods
    @Override
    public List<Workout> findByCoach(Integer coachId) {
        log.info("findByCoach start with coachId: " + coachId);
        return getWorkouts(coachId, "selectByCoach", "coachId");
    }

    @Override
    public List<Workout> findByClient(Integer clientId) {
        log.info("findByClient start with coachId: " + clientId);
        return HibernateUtil.getSessionFactory().openSession().createQuery(properties.getProperty("selectByClient")).setParameter(clientId, clientId).list();
    }

    @Override
    public List<Workout> findByGym(Integer gymId) {
        log.info("findByGym start with gymId: " + gymId);
        return getWorkouts(gymId, "selectByGym", "gymId");
    }

    private List<Workout> getWorkouts(Integer clientId, String queryName, String parameterName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Workout> workouts = (List<Workout>) session.createQuery(properties.getProperty(queryName)).setParameter(parameterName, clientId).list();
        session.getTransaction().commit();
        return workouts;
    }


    //FindByTwoParameters
    @Override
    public List<Workout> getWorkoutsByCoachInGym(Integer coachId, Integer gymId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Workout> amountOfWorkouts = (List<Workout>) session
                .createQuery(properties.getProperty("selectByCoachAndGym"))
                .setParameter("coachId", coachId).setParameter("gymId", gymId).list();
        session.getTransaction().commit();

        return amountOfWorkouts;
    }

    @Override
    public List<Workout> getWorkoutsByClientInGym(Integer clientId, Integer gymId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Workout> amountOfWorkouts = (List<Workout>) session
                .createQuery(properties.getProperty("selectByClientAndGym"))
                .setParameter("clientId", clientId).setParameter("gymId", gymId).list();
        session.getTransaction().commit();
        return amountOfWorkouts;
    }

    @Override
    public void create(Workout workout) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(workout);
        session.getTransaction().commit();
    }

    @Override
    public Workout findById(Integer entityId) throws InvalidIdException {
        Session session = this.sessionFactory.getCurrentSession();
        Workout workout = session.get(Workout.class, entityId);
        return workout;
    }

    @Override
    public void update(Workout workout) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.update(workout);
        session.getTransaction().commit();
    }

    @Override
    public void delete(Workout workout) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.delete(workout);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Workout> findAll() {
        log.info("findAll start");
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Workout> workouts = (List<Workout>) session
                .createQuery(properties.getProperty("findAll")).list();
        session.getTransaction().commit();
        return workouts;

    }
}
