package com.server.model.dao;

import com.server.model.entity.Coach;

import java.util.List;

public interface CoachDao extends Dao<Coach, Integer> {
    List<Coach> findByGym(Integer gymId);

    List<Coach> findByClient(Integer clientId);
}
