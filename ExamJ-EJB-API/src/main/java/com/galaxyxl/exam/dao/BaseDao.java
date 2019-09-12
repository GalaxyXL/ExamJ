package com.galaxyxl.exam.dao;

import javax.ejb.Remote;
import java.io.Serializable;
import java.util.List;

/**
 * BaseDao 包含基本的增删改查功能
 * T ：实体
 * PK：主键
 */
public interface BaseDao<T extends Serializable, PK extends Serializable> {
    T save(T entity);

    T delete(T entity);

    T delete(Class<T> clazz, PK primaryKey);

    T update(T entity);

    T find(Class<T> clazz, PK primaryKey);

    List<T> findAll(Class<T> clazz);
}
