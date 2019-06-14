package com.server.model.dao;

import com.server.model.entity.Gym;

import java.util.List;

public interface GymDao extends Dao<Gym, Integer> {
    List<Gym> findByCoach(Integer coachId);

    List<Gym> findByClient(Integer clientId);
}
