package com.galaxyxl.exam.dao;

import javax.ejb.Remote;

@Remote
public interface TestDao {

    String sayHello();

}
