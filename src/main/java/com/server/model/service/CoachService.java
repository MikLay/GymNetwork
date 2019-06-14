package com.server.model.service;

import com.server.model.entity.Coach;

import java.util.List;

public interface CoachService {

    void createCoach(String lastName, String firstName, String middleName,
                     String email, String photoUrl, String phone, String country, String town,
                     String area, String street, String building, int flat,
                     String sportRang, int payment, boolean sex);


    List<Coach> getAll();

    Coach getById(Integer coachId);

    List<Coach> getByGym(Integer gymId);

    List<Coach> getByClient(Integer clientId);
}
