package com.server.model.dao;

import com.server.model.entity.Client;

import java.util.List;

public interface ClientDao extends Dao<Client, Integer> {
    List<Client> findByGym(Integer gymId);

    List<Client> findByCoach(Integer coachId);
}
