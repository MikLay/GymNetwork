package com.server.model.service;

import com.server.model.entity.Gym;

import java.util.List;

public interface GymService {

    void createGym(String country, String town, String area,
                   String street, int building, int office,
                   int fine, String post_index, String email);

    List<Gym> getAll();

    List<Gym> getByClient(Integer clientId);

    List<Gym> getByCoach(Integer coachId);

    Gym getById(Integer gymId);


}
