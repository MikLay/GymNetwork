package com.server.model.service.impl;

import com.server.model.dao.WorkoutDao;
import com.server.model.entity.Workout;
import com.server.model.exception.InvalidIdException;
import com.server.model.service.WorkoutsService;

import java.util.List;

public class WorkoutServiceImpl implements WorkoutsService {

    private WorkoutDao workoutDao;

    public WorkoutServiceImpl(WorkoutDao workoutDao) {
        this.workoutDao = workoutDao;
    }

    @Override
    public Workout findWorkout(Integer workoutId) {
        Workout workout = null;
        try {
            workout = workoutDao.findById(workoutId);
        } catch (InvalidIdException e) {
            e.printStackTrace();
        }
        return workout;
    }

    @Override
    public List<Workout> findByCoach(Integer coachId) {
        return workoutDao.findByCoach(coachId);
    }

    @Override
    public List<Workout> findByClient(Integer clientId) {
        return workoutDao.findByClient(clientId);
    }

    @Override
    public List<Workout> findByGym(Integer gymId) {
        return workoutDao.findByGym(gymId);
    }

    @Override
    public void saveWorkout(Workout workout) {
        workoutDao.create(workout);
    }

    @Override
    public void deleteWorkout(Workout workout) {
        workoutDao.delete(workout);
    }

    @Override
    public void updateWorkout(Workout workout) {
        workoutDao.update(workout);
    }

    @Override
    public List<Workout> findAll() {
        return workoutDao.findAll();
    }
}
