package com.server.model.service;

import com.server.model.entity.Workout;

import java.util.List;

public interface WorkoutsService {

    Workout findWorkout(Integer workoutId);

    List<Workout> findByCoach(Integer coachId);

    List<Workout> findByClient(Integer clientId);

    List<Workout> findByGym(Integer gymId);

    void saveWorkout(Workout workout);

    void deleteWorkout(Workout workout);

    void updateWorkout(Workout workout);

    List<Workout> findAll();
}
