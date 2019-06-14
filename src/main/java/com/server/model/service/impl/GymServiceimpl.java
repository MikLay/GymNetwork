package com.server.model.service.impl;

import com.server.model.HibernateUtil;
import com.server.model.dao.GymDao;
import com.server.model.entity.Gym;
import com.server.model.exception.InvalidIdException;
import com.server.model.service.GymService;
import org.hibernate.Transaction;

import java.util.List;

public class GymServiceimpl implements GymService {

    GymDao gymDao;

    public GymServiceimpl(GymDao gymDao) {
        this.gymDao = gymDao;
    }

    @Override
    public void createGym(String country, String town, String area, String street,
                          int building, int office, int fine, String post_index, String email) {
        //TODO: create Gym
    }

    @Override
    public List<Gym> getAll() {
        List<Gym> gyms = gymDao.findAll();
        return gyms;

    }

    @Override
    public List<Gym> getByClient(Integer clientId) {
        List<Gym> gyms = gymDao.findByClient(clientId);
        return gyms;
    }

    @Override
    public List<Gym> getByCoach(Integer coachId) {
        List<Gym> gyms = gymDao.findByCoach(coachId);
        return gyms;
    }

    @Override
    public Gym getById(Integer gymId) {
        Transaction transaction = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
        Gym gym = null;
        try {
            gym = gymDao.findById(gymId);
        } catch (InvalidIdException e) {
            e.printStackTrace();
        }
        transaction.commit();
        return gym;
    }
}
