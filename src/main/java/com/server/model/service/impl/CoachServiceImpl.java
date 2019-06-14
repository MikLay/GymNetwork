package com.server.model.service.impl;

import com.server.model.dao.CoachDao;
import com.server.model.entity.Coach;
import com.server.model.exception.InvalidIdException;
import com.server.model.service.CoachService;

import java.util.List;

public class CoachServiceImpl implements CoachService {

    CoachDao coachDao;

    public CoachServiceImpl(CoachDao coachDao) {
        this.coachDao = coachDao;
    }

    @Override
    public void createCoach(String lastName, String firstName, String middleName, String email,
                            String photoUrl, String phone, String country, String town, String area,
                            String street, String building, int flat, String sportRang,
                            int payment, boolean sex) {
        //TODO: write create
    }

    @Override
    public List<Coach> getAll() {
        List<Coach> coaches = coachDao.findAll();
        return coaches;
    }

    @Override
    public Coach getById(Integer coachId) {
        Coach coach = null;
        try {
            coach = coachDao.findById(coachId);
        } catch (InvalidIdException e) {
            e.printStackTrace();
        }
        return coach;

    }

    @Override
    public List<Coach> getByGym(Integer gymId) {
        List<Coach> coaches = coachDao.findByGym(gymId);
        return coaches;
    }

    @Override
    public List<Coach> getByClient(Integer clientId) {
        List<Coach> coaches = coachDao.findByClient(clientId);
        return coaches;

    }
}
