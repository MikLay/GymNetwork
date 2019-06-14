package com.server.model.service.impl;

import com.server.model.QueriesManager;
import com.server.model.dao.SubscriptionDao;
import com.server.model.dao.impl.SubscriptionDaoImpl;
import com.server.model.entity.Subscription;
import com.server.model.exception.InvalidIdException;
import com.server.model.service.SubscriptionService;

import java.util.List;

public class SubscriptionServiceImpl implements SubscriptionService {

    SubscriptionDao subscriptionDao = new SubscriptionDaoImpl(QueriesManager.getProperties("subscription"));

    public SubscriptionServiceImpl(SubscriptionDao subscriptionDao) {
        this.subscriptionDao = subscriptionDao;
    }

    @Override
    public void createSubscription(Subscription subscription) {
        subscriptionDao.create(subscription);
    }

    @Override
    public List<Subscription> getSubscriptionsOfClient(Integer clientId) {
        return subscriptionDao.findByClient(clientId);
    }


    @Override
    public Subscription getSubscriptionById(Integer subscriptionId) throws InvalidIdException {
        return subscriptionDao.findById(subscriptionId);
    }

    @Override
    public List<Subscription> getAllSubscriptions() {
        return subscriptionDao.findAll();
    }
}
