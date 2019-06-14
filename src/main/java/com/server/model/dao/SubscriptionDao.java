package com.server.model.dao;

import com.server.model.entity.Subscription;

import java.util.List;

public interface SubscriptionDao extends Dao<Subscription, Integer> {
    List<Subscription> findByClient(Integer clientId);
}
