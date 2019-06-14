package com.server.model.service;

import com.server.model.entity.Client;

import java.sql.Date;
import java.util.List;


public interface ClientService {
    Client getById(Integer clientId);

    List<Client> getByCoach(Integer coachId);

    List<Client> getByGym(Integer gymId);

    void createClient(String lastName, String firstName, String middleName,
                      String photoUrl, String email, String phone, Date date);

}
