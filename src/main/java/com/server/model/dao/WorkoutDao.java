package com.server.model.dao;

import com.server.model.entity.Workout;

import java.util.List;

public interface WorkoutDao extends Dao<Workout, Integer> {

    List<Workout> findByCoach(Integer coachId);

    List<Workout> findByClient(Integer coachId);

    List<Workout> findByGym(Integer gymId);

    List<Workout> getWorkoutsByCoachInGym(Integer coachId, Integer gymId);

    List<Workout> getWorkoutsByClientInGym(Integer clientId, Integer gymId);

}
