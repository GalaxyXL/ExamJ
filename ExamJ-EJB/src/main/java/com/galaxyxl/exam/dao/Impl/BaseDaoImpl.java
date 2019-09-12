package com.galaxyxl.exam.dao.Impl;

import com.galaxyxl.exam.dao.BaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

public class BaseDaoImpl<T extends Serializable, PK extends Serializable> implements BaseDao<T, PK> {
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    @PersistenceContext(unitName = "ExamPersistenceUnit")
    protected EntityManager em;

    private void flush() {
        em.flush();
    }

    public T save(T entity) {
        log.debug("save {}", entity);
        em.persist(entity);
        flush();
        return entity;
    }

    public T delete(T entity) {
        log.debug("delete {}", entity);
        em.remove(entity);
        flush();
        return entity;
    }

    public T delete(Class<T> clazz, PK primaryKey) {
        log.debug("delete: class={}, id={}", clazz, primaryKey);
        T entity = em.find(clazz, primaryKey);
        em.remove(entity);
        flush();
        return entity;
    }

    public T update(T entity) {
        log.debug("update {}", entity);
        em.merge(entity);
        flush();
        return entity;
    }

    public T find(Class<T> clazz, PK primaryKey) {
        log.debug("find: class={}, id={}", clazz, primaryKey);
        return em.find(clazz, primaryKey);
    }

    public List<T> findAll(Class<T> clazz) {
        log.debug("find all");
        String jpql = "select t from " + clazz.getSimpleName() + " as t";
        TypedQuery<T> query = em.createQuery(jpql, clazz);
        return query.getResultList();
    }

    <EntityClass> EntityClass getSingleResult(TypedQuery<EntityClass> query) {
        EntityClass entity = null;
        try {
            entity = query.getSingleResult();
            log.trace("获取单个实体成功:{}", entity);
        } catch (NoResultException e) {
            log.trace("找不到符合条件的实体");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

}
