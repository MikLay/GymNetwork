package com.server.model.dao;

import com.server.model.entity.User;

public interface UserDao extends Dao<User, Integer> {
    User findByEmail(String email);

}
