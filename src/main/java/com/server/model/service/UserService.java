package com.server.model.service;

import com.server.model.entity.User;

public interface UserService {

    User findUser(Integer id);


    void createUser(String email, String password, String type, Integer id);

    void deleteUser(User user);

    User validateUser(String email, String password);

    void updateUser(User user);

}
