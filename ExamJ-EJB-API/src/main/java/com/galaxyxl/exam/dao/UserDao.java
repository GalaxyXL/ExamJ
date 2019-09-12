package com.galaxyxl.exam.dao;

import com.galaxyxl.exam.model.User;

import javax.ejb.Remote;

@Remote
public interface UserDao extends BaseDao<User, Long> {
    User findByUserName(String username);

    User findAdmin();
}
