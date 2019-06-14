package com.server.model.service.impl;

import com.server.model.dao.ClientDao;
import com.server.model.entity.Client;
import com.server.model.exception.InvalidIdException;
import com.server.model.service.ClientService;

import java.sql.Date;
import java.util.List;

public class ClientServiceImpl implements ClientService {

    ClientDao clientDao;

    public ClientServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public Client getById(Integer clientId) {
        Client client = null;
        try {
            client = clientDao.findById(clientId);
        } catch (InvalidIdException e) {
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public List<Client> getByCoach(Integer coachId) {
        List<Client> clients = clientDao.findByCoach(coachId);
        return clients;
    }

    @Override
    public List<Client> getByGym(Integer gymId) {
        List<Client> clients = clientDao.findByGym(gymId);
        return clients;
    }

    @Override
    public void createClient(String lastName, String firstName, String middleName, String photoUrl, String email, String phone, Date date) {
        //TODO: write create
    }

}
