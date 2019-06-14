package com.server.model.service;

import com.server.model.entity.Subscription;
import com.server.model.exception.InvalidIdException;

import java.util.List;

public interface SubscriptionService {
    void createSubscription(Subscription subscription);

    List<Subscription> getSubscriptionsOfClient(Integer clientId);


    Subscription getSubscriptionById(Integer subscriptionId) throws InvalidIdException;

    List<Subscription> getAllSubscriptions();
}
