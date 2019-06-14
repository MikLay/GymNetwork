package com.server.model.service.impl;

import com.server.model.dao.UserDao;
import com.server.model.entity.User;
import com.server.model.exception.InvalidIdException;
import com.server.model.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findUser(Integer id) {
        User user = null;
        try {
            user = userDao.findById(id);
        } catch (InvalidIdException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void createUser(String email, String password, String type, Integer id) {

    }

    @Override
    public void deleteUser(User user) {
        userDao.delete(user);
    }

    @Override
    public User authUser(String email, String password) {
        User user = userDao.findByEmail(email);
        if (user != null && password.equals(user.getUserPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public boolean validateAccess(String email, String password) {
        User user = userDao.findByEmail(email);
        return user != null && password.equals(user.getUserPassword());
    }

    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }
}
