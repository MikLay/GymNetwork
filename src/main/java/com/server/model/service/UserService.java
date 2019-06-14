package com.server.model.service;

import com.server.model.entity.User;

public interface UserService {

    User findUser(Integer id);


    void createUser(String email, String password, String type, Integer id);

    void deleteUser(User user);

    User authUser(String email, String password);

    boolean validateAccess(String email, String password);

    void updateUser(User user);

}
