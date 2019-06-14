package com.server.model.service.impl;

import com.server.model.HibernateUtil;
import com.server.model.dao.ClientDao;
import com.server.model.entity.Client;
import com.server.model.exception.InvalidIdException;
import com.server.model.service.ClientService;
import org.hibernate.Transaction;

import java.sql.Date;
import java.util.List;

public class ClientServiceImpl implements ClientService {

    ClientDao clientDao;

    public ClientServiceImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public Client getById(Integer clientId) {
        Transaction transaction = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
        Client client = null;
        try {
            client = clientDao.findById(clientId);
        } catch (InvalidIdException e) {
            e.printStackTrace();
        }
        transaction.commit();
        return client;
    }

    @Override
    public List<Client> getByCoach(Integer coachId) {
        Transaction transaction = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
        List<Client> clients = clientDao.findByCoach(coachId);
        transaction.commit();
        return clients;
    }

    @Override
    public List<Client> getByGym(Integer gymId) {
        Transaction transaction = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
        List<Client> clients = clientDao.findByGym(gymId);
        transaction.commit();
        return clients;
    }

    @Override
    public void createClient(String lastName, String firstName, String middleName, String photoUrl, String email, String phone, Date date) {
        //TODO: write create
    }

}
