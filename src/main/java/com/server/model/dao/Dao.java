package com.server.model.dao;

import com.server.model.exception.InvalidIdException;

import java.util.List;

public interface Dao<Entity, Integer> {
    void create(Entity entity);

    Entity findById(Integer entityId) throws InvalidIdException;

    void update(Entity entity);

    void delete(Entity entity);

    List<Entity> findAll();
}
