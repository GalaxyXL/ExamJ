package com.galaxyxl.exam.dao.Impl;

import com.galaxyxl.exam.dao.TestDao;

import javax.ejb.Stateless;

@Stateless
public class TestDaoImpl implements TestDao {

    @Override
    public String sayHello() {
        return "Hello Server";
    }
}
