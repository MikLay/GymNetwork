package com.server.model.service.impl;

import com.server.model.HibernateUtil;
import com.server.model.QueriesManager;
import com.server.model.dao.UserDao;
import com.server.model.dao.impl.UserDaoImpl;
import com.server.model.entity.User;
import com.server.model.exception.InvalidIdException;
import com.server.model.service.UserService;
import org.hibernate.Transaction;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl(QueriesManager.getProperties("user"));


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
    public User validateUser(String email, String password) {
        Transaction transaction = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
        User user = userDao.findByEmail(email);
        transaction.commit();
        if (user != null && password.equals(user.getUserPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }
}
