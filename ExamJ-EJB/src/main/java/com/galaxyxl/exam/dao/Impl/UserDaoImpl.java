package com.galaxyxl.exam.dao.Impl;

import com.galaxyxl.exam.dao.UserDao;
import com.galaxyxl.exam.model.User;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

@Stateless
public class UserDaoImpl extends BaseDaoImpl<User, Long> implements UserDao {

    public User findByUserName(String username) {
        log.debug("根据用户名查找...");
        TypedQuery<User> query = em.createQuery("select u from User as u where u.name = :username", User.class);
        query.setParameter("username", username);
        return getSingleResult(query);
    }

    public User findAdmin() {
        log.debug("找管理员...");
        TypedQuery<User> query = em.createQuery("select u from User as u where u.role = :admin", User.class);
        query.setParameter("admin", 1);
        query.setFirstResult(0);
        query.setMaxResults(1);
        return getSingleResult(query);
    }
}
