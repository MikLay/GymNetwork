package com.server.model.dao.impl;

import com.server.model.HibernateUtil;
import com.server.model.dao.UserDao;
import com.server.model.entity.User;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Properties;

@Log4j
public class UserDaoImpl implements UserDao {


    private Properties properties;
    private SessionFactory sessionFactory;

    public UserDaoImpl(Properties properties) {
        this.properties = properties;
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @Override
    public User findById(Integer entityId) {
        log.info("findById start with entityId: " + entityId);
        Session session = this.sessionFactory.getCurrentSession();
        User user = session.get(User.class, entityId);
        return user;
    }

    @Override
    public void create(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(user);
    }


    @Override
    public void update(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    public void delete(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.delete(user);
    }

    @Override
    public List<User> findAll() {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> users = (List<User>) session.createQuery(properties.getProperty("selectAll")).list();
        return users;
    }

    @Override
    public User findByEmail(String email) {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> users = (List<User>) session.createQuery(properties.getProperty("selectByEmail")).setParameter("email", email).list();

        return users.isEmpty() ? null : users.get(0);
    }
}
