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

    //FindBy methods
    @Override
    public List<Workout> findByCoach(Integer coachId) {
        log.info("findByCoach start with coachId: " + coachId);
        Session session = this.sessionFactory.getCurrentSession();
        List<Workout> workouts = (List<Workout>) session.createSQLQuery(properties.getProperty("selectByCoach"))
                .setParameter("coachId", coachId).addEntity(Workout.class).list();

        log.info("findByCoach end");
        return workouts;
    }

    @Override
    public List<Workout> findByClient(Integer clientId) {
        log.info("findByClient start with coachId: " + clientId);
        Session session = this.sessionFactory.getCurrentSession();
        List<Workout> workouts = (List<Workout>) session.createSQLQuery(properties.getProperty("selectByClient"))
                .setParameter("clientId", clientId)
                .addEntity(Workout.class).list();
        log.info("findByClient end");
        return workouts;
    }

    @Override
    public List<Workout> findByGym(Integer gymId) {
        log.info("findByGym start with coachId: " + gymId);
        Session session = this.sessionFactory.getCurrentSession();
        List<Workout> workouts = (List<Workout>) session.createQuery(properties.getProperty("selectByGym"))
                .setParameter("gym_id", gymId).list();
        log.info("findByGym end");
        return workouts;
    }


    //FindByTwoParameters
    @Override
    public List<Workout> getWorkoutsByCoachInGym(Integer coachId, Integer gymId) {
        log.info("getWorkoutsByCoachInGym start with parameters: coachId: " + gymId + "; gymId: " + gymId);
        Session session = this.sessionFactory.getCurrentSession();
        List<Workout> amountOfWorkouts = (List<Workout>) session
                .createQuery(properties.getProperty("selectByCoachAndGym"))
                .setParameter("coachId", coachId).setParameter("gymId", gymId).list();
        log.info("getWorkoutsByCoachInGym end");
        return amountOfWorkouts;
    }

    @Override
    public List<Workout> getWorkoutsByClientInGym(Integer clientId, Integer gymId) {
        log.info("getWorkoutsByClientInGym start with parameters: clientId: " + gymId + "; gymId: " + gymId);
        Session session = this.sessionFactory.getCurrentSession();
        List<Workout> amountOfWorkouts = (List<Workout>) session
                .createQuery(properties.getProperty("selectByClientAndGym"))
                .setParameter("clientId", clientId).setParameter("gymId", gymId).list();
        log.info("getWorkoutsByClientInGym end");
        return amountOfWorkouts;
    }

    @Override
    public void create(Workout workout) {
        log.info("create start with parameters: workout: " + workout);
        Session session = this.sessionFactory.getCurrentSession();
        session.save(workout);
        log.info("create end");
    }

    @Override
    public Workout findById(Integer entityId) throws InvalidIdException {
        Session session = this.sessionFactory.getCurrentSession();
        Workout workout = session.get(Workout.class, entityId);
        return workout;
    }

    @Override
    public void update(Workout workout) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(workout);
    }

    @Override
    public void delete(Workout workout) {
        Session session = this.sessionFactory.getCurrentSession();
        session.getTransaction().commit();
    }

    @Override
    public List<Workout> findAll() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Workout> workouts = (List<Workout>) session
                .createQuery(properties.getProperty("findAll")).list();
        return workouts;

    }
}
